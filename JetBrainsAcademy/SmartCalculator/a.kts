import java.util.*
/* Do not change code below */

fun main() {
    val scanner = Scanner(System.`in`)
    val a = scanner.nextInt()
    val b = scanner.nextInt()

    divide(a, b)
}

fun divide(a: Int, b: Int): {
    if (b == 0) {
        throw Exception("Division by zero, please fix the second argument!")
    }
    return print(a / b)
}
