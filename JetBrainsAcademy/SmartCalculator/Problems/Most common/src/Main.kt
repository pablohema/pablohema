fun main() {
    var words = mutableMapOf<String ,Int>()

    var line: String = readLine()!!

    do {
        if (line == "stop") {
            println("null")
            break
        }
        else if (words.containsKey(line)) {
            val sum: Int = words[line]!! + 1
            words[line] = sum
        } else {
            words[line] = 1
        }
        line = readLine()!!
    } while (line != "stop")

    var name: String = ""
    var count = 0
    for (entry in words) {
        if (entry.value > count) {
            count = entry.value
            name = entry.key
        }
    }
    println(name)
}