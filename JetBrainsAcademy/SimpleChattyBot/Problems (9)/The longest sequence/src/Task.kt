import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val r = scanner.nextInt()
    var lenght = 0
    var previous = 0
    var maxLenght = 0
    repeat(r) {
        val inp = scanner.nextInt()
        if (inp >= previous) {
            lenght += 1
            previous = inp
        } else if (inp < previous) {
            previous = inp
            if (maxLenght < lenght) maxLenght = lenght
            lenght = 1

        }

    }
    print(if (maxLenght > lenght) maxLenght else lenght)
}
