import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val c1 = scanner.next().single()
    val c2 = scanner.next().single()
    val c3 = scanner.next().single()

    print(c2 == c1 + 1 && c3 == c2 + 1)
}