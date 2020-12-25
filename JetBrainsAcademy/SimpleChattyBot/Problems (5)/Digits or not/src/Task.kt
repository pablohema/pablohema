import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val letter1 = scanner.next().single()
    val letter2 = scanner.next().single()
    val letter3 = scanner.next().single()
    val letter4 = scanner.next().single()
    val c = '\u005C'

    print(letter1.isDigit())
    print(c)
    print(letter2.isDigit())
    print(c)
    print(letter3.isDigit())
    print(c)
    print(letter4.isDigit())

}