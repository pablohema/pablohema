package processor

import java.util.Scanner

val scanner = Scanner(System.`in`)

fun main() {
    menu()
}

fun printMax (matrix: Array<Array<Double>>, input1: Int, input2: Int){
    for (row in 0 until input1) {
        for (col in 0 until input2) {
            print("${matrix[row][col]} ")
        }
        println()
    }
}

fun createMax (input1: Int, input2: Int): Array<Array<Double>> {
        val matrix: Array<Array<Double>> = Array(input1) { Array(input2) { 0.0 } }
        for (row in 0 until input1) {
            for (col in 0 until input2) {
                matrix[row][col] = scanner.nextDouble()
            }
        }
        return matrix
}

fun addMax (){

    print("Enter size of first matrix: ")
    val ip1 = scanner.nextInt()
    val ip2 = scanner.nextInt()
    println("Enter first matrix:")
    val maxOne = createMax(ip1, ip2)

    print("Enter size of second matrix: ")
    val ip3 = scanner.nextInt()
    val ip4 = scanner.nextInt()
    println("Enter second matrix:")
    val maxTwo= createMax(ip3, ip4)

    val finalMax: Array<Array<Double>> = Array(ip1) { Array(ip2) { 0.0 } }

    if (ip1 == ip3 && ip2 == ip4) {
        for (row in 0 until ip1) {
            for (col in 0 until ip2) {
                finalMax[row][col] = maxOne[row][col] + maxTwo[row][col]
            }
        }

        println("The addition result is:")
        printMax(finalMax, ip1, ip2)
    } else {
        println("ERROR")
    }
}

fun multiplyMax () {
    print("Enter size of first matrix: ")
    val ip1 = scanner.nextInt()
    val ip2 = scanner.nextInt()
    println("Enter first matrix:")
    val maxOne = createMax(ip1, ip2)
    print("Enter your constant: ")
    val MULT = readLine()!!.toInt()

    val finalMax: Array<Array<Double>> = Array(ip1) { Array(ip2) { 0.0 } }
    
    for (row in 0 until ip1) {
        for (col in 0 until ip2) {
                finalMax[row][col] = maxOne[row][col] * MULT
           }
    }
    println("The multiplication result is:")
    printMax(finalMax, ip1, ip2)
    println()
}

fun multiplyMaxMax () {

    print("Enter size of first matrix: ")
    val ip1 = scanner.nextInt()
    val ip2 = scanner.nextInt()
    println("Enter first matrix:")
    val maxOne = createMax(ip1, ip2)

    print("Enter size of second matrix: ")
    val ip3 = scanner.nextInt()
    val ip4 = scanner.nextInt()
    println("Enter second matrix:")
    val maxTwo= createMax(ip3, ip4)

    val finalMax: Array<Array<Double>> = Array(ip1) { Array(ip4) { 0.0 } }

    if (ip2 == ip3) {
        for ( row in 0 until ip1) {
            for (col in 0 until ip4) {
                for (cl in 0 until ip2) {
                    finalMax[row][col] += maxOne[row][cl] * maxTwo[cl][col]
                }
            }
        }
    }
    println("The multiplication result is:")
    printMax(finalMax, ip1, ip4)
    println()
}

fun menu() {

    var exit = true
    println("""1. Add matrices
            |2. Multiply matrix to a constant
            |3. Multiply matrices
            |4. Transpose matrix
            |0. Exit
    """.trimMargin())
    print("Your choice: ")
    while (exit) {
        when (scanner.nextInt()) {
            1 -> addMax()
            2 -> multiplyMax()
            3 -> multiplyMaxMax()
            4 -> menuTranspose()
            0 -> exit = false
        }
    }

}

fun menuTranspose() {
    println()
    println("""1. Main diagonal
              |2. Side diagonal
              |3. Vertical line
              |4. Horizontal line
              |5. Calculate a determinant
              """.trimMargin())
    print("Your choice: ")

    when (scanner.nextInt()) {
        1 -> mainDiagonal()
        2 -> sideDiagonal()
        3 -> verticaLine()
        4 -> horizontalLine()
        5 -> determinant()
        else -> {}
    }
}

fun mainDiagonal() {
    print("Enter size of first matrix: ")
    val ip1 = scanner.nextInt()
    val ip2 = scanner.nextInt()
    println("Enter first matrix:")
    val maxOne = createMax(ip1, ip2)

    val finalMax: Array<Array<Double>> = Array(ip1) { Array(ip2) { 0.0 } }

    for (row in 0 until ip1) {
        for (col in 0 until ip2) {
            finalMax[col][row] = maxOne[row][col]
        }
    }
    println("The result is:")
    printMax(finalMax, ip1, ip2)
    println()
}

fun sideDiagonal() {
    print("Enter size of first matrix: ")
    val ip1 = scanner.nextInt()
    val ip2 = scanner.nextInt()
    println("Enter first matrix:")
    val maxOne = createMax(ip1, ip2)

    val finalMax: Array<Array<Double>> = Array(ip1) { Array(ip2) { 0.0 } }

    for (row in 0 until ip1) {
        for (col in 0 until ip2) {
                finalMax[row][col] = maxOne[ip2 - col - 1][ip1 - row - 1]
            }
        }
    println("The result is:")
    printMax(finalMax, ip1, ip2)
    println()
}

fun verticaLine () {
    print("Enter size of first matrix: ")
    val ip1 = scanner.nextInt()
    val ip2 = scanner.nextInt()
    println("Enter first matrix:")
    val maxOne = createMax(ip1, ip2)

    val finalMax: Array<Array<Double>> = Array(ip1) { Array(ip2) { 0.0 } }

    for (row in 0 until ip1) {
        for (col in 0 until ip2) {
            finalMax[row][col] = maxOne[row][ip2 - col - 1]
        }
    }
    println("The result is:")
    printMax(finalMax, ip1, ip2)
    println()
}

fun horizontalLine () {
    print("Enter size of first matrix: ")
    val ip1 = scanner.nextInt()
    val ip2 = scanner.nextInt()
    println("Enter first matrix:")
    val maxOne = createMax(ip1, ip2)

    val finalMax: Array<Array<Double>> = Array(ip1) { Array(ip2) { 0.0 } }

    for (row in 0 until ip1) {
        for (col in 0 until ip2) {
            finalMax[row][col] = maxOne[ip1 - row - 1][col]
        }
    }
    println("The result is:")
    printMax(finalMax, ip1, ip2)
    println()
}

fun determinant () {
    print("Enter size of first matrix: ")
    val ip1 = scanner.nextInt()
    val ip2 = scanner.nextInt()
    println("Enter first matrix:")
    val maxOne = createMax(ip1, ip2)

    println("The result is:")
    println(calculateDeterminant(maxOne, ip1))
    println()
}

fun calculateDeterminant (matrix: Array<Array<Double>>, ip1: Int): Double {
    val cofactorMax: Array<Array<Double>> = Array(ip1) { Array(ip1) { 0.0 } }
    var cofactor = 1
    var determinant: Double = 0.0

    if (ip1 == 1) {
        return matrix[ip1][ip1]
    } else {
        for (i in 0 until ip1) {
            getCofactor(matrix, cofactorMax, 0, i, ip1)
            determinant += cofactor * matrix[0][i] * calculateDeterminant(cofactorMax, ip1 - 1)

            cofactor = -cofactor
        }
    }
    return determinant
}

fun getCofactor (matrix: Array<Array<Double>>, cofactorMax: Array<Array<Double>>, def: Int, iterator: Int, ip1: Int) {
    var y: Int = 0
    var x: Int = 0

    for (row in 0 until ip1) {
        for (col in 0 until ip1) {
            if ( row != def && col != iterator) {
                cofactorMax[y][x++] = matrix[row][col]
                if (x == ip1 - 1) {
                    x = 0
                    y++
                }
            }
        }
    }
}