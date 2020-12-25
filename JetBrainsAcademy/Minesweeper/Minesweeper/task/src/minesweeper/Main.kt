package minesweeper

import kotlin.random.Random

const val pWidth = 9
const val pHeight = 9

fun main() {
    print("How many mines do you want on the field? ")
    val bombs = readLine()!!.toInt()
    val game = Mines(pWidth, pHeight, bombs)
    println()
    game.playOne()

}

class Mines(width: Int, height: Int, mines: Int) {

    private val bombs = mines
    private val mines = randomiseMines(width, height, mines)
    private val showMinesMap =  showMines()
    private val finalMap: Array<Array<Char>> = Array(pHeight) { Array(pWidth){ '.' } }
    private var foundBombs = 0
    private var cells = 0
    private val tCells: Int = pWidth * pHeight
    private var discovered = false

    fun print () {
        for (row in showMinesMap) {
            for (col in row) {
                print(col)
            }
            println()
        }
    }

    private fun printGameOne() {
        println(" |123456789| ")
        println("-|---------| ")
        var it = 1
        for (row in finalMap) {
            print("$it|")
            for (field in row) {
                print(field)
            }
            println("| ")
            it += 1
        }
        println("-|---------| ")
    }
    private fun minedMap() {
        for (y in 0 until 9){
            for (x in 0 until 9) {
                if (showMinesMap[x][y] == 'X') {
                    finalMap[x][y] = 'X'
                }
            }
        }

        println(" |123456789| ")
        println("-|---------| ")
        var it = 1
        for (row in finalMap) {
            print("$it|")
            for (field in row) {
                print(field)
            }
            println("| ")
            it += 1
        }
        println("-|---------| ")
    }

    private fun floodFillUtil(x: Int, y: Int) {
        // Base cases
        if (x in 0 until pHeight && y in 0 until pWidth) {
            if (finalMap[x][y] == '.') {
                when {
                    showMinesMap[x][y] == '.' -> {
                        cells += 1
                        finalMap[x][y] = '/'
                        // Recur for north, east, south and west
                        floodFillUtil(x - 1, y)           //L
                        floodFillUtil(x, y + 1)           //U
                        floodFillUtil(x + 1, y)           //R
                        floodFillUtil( x, y - 1)          //D

                        floodFillUtil(x - 1, y - 1)           //L
                        floodFillUtil(x + 1, y - 1)           //U
                        floodFillUtil(x + 1, y + 1)           //R
                        floodFillUtil(x - 1, y + 1)          //D
                    }
                    showMinesMap[x][y] in '1'..'8' -> {
                        cells += 1
                        finalMap[x][y] = showMinesMap[x][y]
                        return
                    }
                }
            } else if (finalMap[x][y] == '*') {
                when {
                    showMinesMap[x][y] == '.' -> {
                        cells += 1
                        finalMap[x][y] = '/'
                        // Recur for north, east, south and west
                        floodFillUtil(x - 1, y)           //L
                        floodFillUtil(x, y + 1)           //U
                        floodFillUtil(x + 1, y)           //R
                        floodFillUtil( x, y - 1)          //D

                        floodFillUtil(x - 1, y - 1)           //L
                        floodFillUtil(x + 1, y - 1)           //U
                        floodFillUtil(x + 1, y + 1)           //R
                        floodFillUtil(x - 1, y + 1)          //D
                    }
                    showMinesMap[x][y] in '1'..'8' -> {
                        cells += 1
                        finalMap[x][y] = showMinesMap[x][y]
                        return
                    }
                }
            }else return
        }

    }

    private fun floodFill(x: Int, y: Int) {
        floodFillUtil(x, y)
    }

    private fun verify(nCell: Int) {
        if (nCell == 81 - bombs) discovered = true
    }

    fun playOne() {

        var exploted = false
        printGameOne()

        while (!discovered && !exploted && foundBombs != bombs){

            print("Set/unset mines marks or claim a cell as free: ")
            val input = readLine()!!.split(" ")
            println()
            val y = input[0].toInt() - 1
            val x = input[1].toInt() - 1
            val mineOrFree = input[2].toLowerCase()

            when (mineOrFree) {
                "free" -> {
                    if (showMinesMap[x][y] == 'X') {
                        minedMap()
                        exploted = true
                    } else if (showMinesMap[x][y] in '1'..'8') {
                        cells += 1
                        finalMap[x][y] = showMinesMap[x][y]
                        printGameOne()
                        verify(cells)
                    } else if (showMinesMap[x][y] == '.'){
                        floodFill(x, y)
                        printGameOne()
                        verify(cells)
                    }
                }
                "mine" -> {
                    if (finalMap[x][y] == '*' && showMinesMap[x][y] == 'X') {
                        foundBombs -= 1
                        finalMap[x][y] = '.'
                        printGameOne()
                    } else if (finalMap[x][y] == '.' && showMinesMap[x][y] == 'X') {
                        foundBombs += 1
                        finalMap[x][y] = '*'
                        printGameOne()
                    } else if (finalMap[x][y] == '*' && showMinesMap[x][y] != 'X') {
                        finalMap[x][y] = '.'
                        printGameOne()
                    } else if (finalMap[x][y] == '.' && showMinesMap[x][y] != 'X') {
                        finalMap[x][y] = '*'
                        printGameOne()
                    }
                }
            }
        }
        if (exploted) {
            print("You stepped on a mine and failed!")
        } else print("Congratulations! You found all the mines!")
    }

    private fun showMines(): Array<Array<Char>> {

        if (pWidth * pHeight <= bombs) return Array(pHeight) { Array(pWidth){ 'X' } }
        val camp: Array<Array<Char>> = Array(pHeight) { Array(pWidth){ '.' } }

        for (i in 0 until pHeight) {
            loop@for (j in 0 until pWidth){
                if (mines[i][j] == 'X') {
                    camp[i][j] = 'X'
                    continue@loop
                } else {
                    val infoBombs = calMines(i, j)
                    if (infoBombs > 0) {
                        camp[i][j] = infoBombs.toString()[0]
                    }
                }
            }
        }
        return camp
    }

    private fun calMines(x: Int, y: Int): Int {
        var surMines = 0

        if (x - 1 in 0 until pWidth && mines[x - 1][y] == 'X') surMines++
        if (x - 1 in 0 until pWidth && y - 1 in 0 until pHeight && mines[x - 1][y - 1] == 'X') surMines++
        if (y - 1 in 0 until pHeight && mines[x][y - 1] == 'X') surMines++
        if (x + 1 in 0 until pWidth && y - 1 in 0 until pHeight && mines[x + 1][y - 1] == 'X') surMines++
        if (x + 1 in 0 until pWidth && mines[x + 1][y] == 'X') surMines++
        if (x + 1 in 0 until pWidth && y + 1 in 0 until pHeight && mines[x + 1][y + 1] == 'X') surMines++
        if (y + 1 in 0 until pHeight && mines[x][y + 1] == 'X') surMines++
        if (x - 1 in 0 until pWidth && y + 1 in 0 until pHeight && mines[x - 1][y + 1] == 'X') surMines++
        return surMines
    }

    private fun randomiseMines(width: Int, height: Int, mines: Int): Array<Array<Char>> {
        if (width * height <= mines) return Array(height) { Array(width){ 'X' } }

        val result = Array(height) { Array(width){ '.' } }

        for (i in 0 until mines) {
            var valCoord = false

            while (!valCoord) {
                val coords = randPoints(width, height)
                if (result[coords.x][coords.y] == '.') {
                    result[coords.x][coords.y] = 'X'
                    valCoord = true
                }
            }
        }
        return result

    }

    private fun randPoints(width: Int, height: Int): Coordinates {
        return Coordinates(Random.nextInt(width), Random.nextInt(height))
    }
}
class Coordinates(val x: Int, val y: Int)
