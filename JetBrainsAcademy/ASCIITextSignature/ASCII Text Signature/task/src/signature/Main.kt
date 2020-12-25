package signature

import org.w3c.dom.ranges.Range
import java.awt.List
import java.util.*
import java.io.*

fun main() {
    val scanner = Scanner(System.`in`)

    val fileName = "C:\\Users\\pablo\\Downloads\\roman.txt" // roma шрифт
    val lines = File(fileName).readLines() // roman шрифт
    val letterPosition = mutableMapOf<Char, Int>() // roman шрифт
    val letterlength = mutableMapOf<Char, Int>() // roman шрифт

    val fileName2 = "C:\\Users\\pablo\\Downloads\\medium.txt" // medium шрифт
    val linesMedium = File(fileName2).readLines() // medium шрифт
    val letterPositionMedium = mutableMapOf<Char, Int>() // medium шрифт
    val letterlengthMedium = mutableMapOf<Char, Int>() // medium шрифт

    print("Enter name and surname: ")
    val name = scanner.nextLine()
    print("Enter person's status: ")
    val status = scanner.nextLine()


    //длина символов в шрифте Роман
    letterLengthCalc(letterlength, lines)
    //длина символов в шрифте Медиум
    letterLengthCalc(letterlengthMedium, linesMedium)
    // список номеров букв в файле Роман
    letterPlace(letterPosition, lines)
    //список номеров букв в файел Медиум
    letterPlace(letterPositionMedium, linesMedium)
    //считаем, какой длины ввели слова
    val romanCountLetters = letterInWordRoman(name, letterlength)
    val mediumCountLetters =letterInWordMedium(status, letterlengthMedium)
    //считаем, какое слово длиннее
    val totalLength = compareLength(romanCountLetters, mediumCountLetters)
    val FreeSpaceFrstRoman = totalLength["romanF"]
    val FreeSpaceScndRoman = totalLength["romanS"]
    val FreeSpaceFrstMedium = totalLength["mediumF"]
    val FreeSpaceScndMedium = totalLength["mediumS"]

    //выводим верхнюю границу
    border(totalLength.values.max()!!)
    // печатаем Roman
    GetWordRoman(name, letterPosition, lines, FreeSpaceFrstRoman!!, FreeSpaceScndRoman!!)
    //печатаем Медиум
    GetWordMedium(status, letterPositionMedium, linesMedium, FreeSpaceFrstMedium!!, FreeSpaceScndMedium!!)
    //выводим нижнюю границу
    border(totalLength.values.max()!!)

}

// список номеров букв в файле
fun letterPlace (letterPosition: MutableMap<Char, Int>, lines: kotlin.collections.List<String>): MutableMap<Char, Int> {
    for (letter in 'A'..'z') {
        for (i in lines.indices) {
            if (lines[i].first() == letter && lines[i][1] == ' ') {
                letterPosition[letter] = i
            }
        }
    }
    return letterPosition

}
//выводим нужные слова в шрифте роман
fun GetWordRoman (word:String, letterPosition: MutableMap<Char, Int>, lines: kotlin.collections.List<String>, spaceF: Int, spaceS: Int) {
    var linenumber = 0
    for (i in 1..10) {
        print("88")
        print(" ".repeat(spaceF))
        for (letter in word) {
            if (letter == ' ') {
                print("          ")
            } else {
                linenumber=letterPosition[letter]!!
                print(lines[linenumber + i])
            }
        }
        print(" ".repeat(spaceS))
        print("88")
        println()
    }
}
//выводим нужные слова в шрифте медиум
fun GetWordMedium (word:String, letterPosition: MutableMap<Char, Int>, lines: kotlin.collections.List<String>, spaceF: Int, spaceS: Int) {
    var linenumber = 0
    for (i in 1..3) {
        print("88")
        print(" ".repeat(spaceF))
        for (letter in word) {
            if (letter == ' ') {
                print("     ")
            } else {
                linenumber=letterPosition[letter]!!
                print(lines[linenumber + i])
            }
        }
        print(" ".repeat(spaceS))
        print("88")
        println()
    }
}
//считаем ширину букв в обоих шрифтах
fun letterLengthCalc (letterlength: MutableMap<Char, Int>, lines: kotlin.collections.List<String>): MutableMap<Char, Int> {
    for (letter in 'A'..'z') {
        for (i in lines.indices) {
            if (lines[i].first() == letter && lines[i][1] == ' ' && letter.isLetter()) {
                letterlength[letter] = lines[i].split(" ").last().toInt()
            }
        }
    }
    return letterlength

}
//считаем длину символов шрифт роман
fun letterInWordRoman (word: String, letterlength: MutableMap<Char, Int>): Int {
    var total = 0
    for (letter in word) {
        if (letter == ' ') {
            total += 10
        } else {
            total +=letterlength[letter]!!
        }
    }
    return  total
}
//считаем длину символов шрифт медиум
fun letterInWordMedium (word: String, letterlength: MutableMap<Char, Int>): Int {
    var total = 0
    for (letter in word) {
        if (letter == ' ') {
            total += 5
        } else {
            total +=letterlength[letter]!!
        }
    }
    return  total
}
//определяем кто больше роман или медиум и какие будут отступы в этом случае
fun compareLength(totalRoman: Int, totalMedium: Int): MutableMap<String, Int> {
    val comaparison = mutableMapOf<String, Int>()

    if (totalRoman > totalMedium) {
        comaparison["romanF"] = 2
        comaparison["romanS"] = 2
        comaparison["mediumF"] = (totalRoman + 4 - totalMedium)/2
        comaparison["mediumS"] = totalRoman + 4 - comaparison["mediumF"]!! - totalMedium
        comaparison["AbsTotal"] = totalRoman

    } else {
        comaparison["romanF"] = (totalMedium + 4 - totalRoman)/2
        comaparison["romanS"] = totalMedium + 4 - comaparison["romanF"]!! - totalRoman
        comaparison["mediumF"] = 2
        comaparison["mediumS"] = 2
        comaparison["AbsTotal"] = totalMedium
    }

    return comaparison
}
//рисуем верхнюю и нижнюю границы
fun border (total: Int) {
    print("8".repeat(total + 8))
    println()
}
