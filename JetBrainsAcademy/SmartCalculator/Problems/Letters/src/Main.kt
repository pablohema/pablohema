fun main() {
    val letters = mutableMapOf<String, String>()


    var i: Int = 1
    var ans = "null"
    do {
        var line: String = readLine()!!

        if (i == 4) {
            ans = line
        }
        i++

    } while (line != "z")


    print(ans)
}