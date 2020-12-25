import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val c = scanner.next().first()

    print(c.isDigit() && c != '0' || c.isUpperCase())
}
