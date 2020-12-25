import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val size = scanner.nextInt()
    val array = IntArray(size)

    for (i in array.indices) {
        array[i] = scanner.nextInt()
    }
    
    var count = 0
    for (i in 0 until array.size - 1) {
        if (i + 2 < array.size && array[i] == array[i + 1] - 1 && array[i + 1] == array[i + 2] - 1) {
            count += 1
        } else continue
    }
    print(count)
}
