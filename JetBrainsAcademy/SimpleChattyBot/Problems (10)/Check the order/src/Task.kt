import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    var asc = true
    val n = scanner.nextInt()
    var num1 = scanner.nextInt()
    for (x in 1 until n) {
        val num2 = scanner.nextInt()
        if (num2 < num1) asc = false
        num1 = num2
    }
    print(if (asc) "YES" else "NO")
}
