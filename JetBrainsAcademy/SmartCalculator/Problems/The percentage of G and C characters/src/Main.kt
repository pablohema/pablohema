fun main() {
    val input = readLine()!!.toLowerCase()
    var count = 0
    for (c in input) {
        if (c == 'g' || c == 'c') count++
    }
    
    if (count == 0) print(0.0) else print((count.toDouble() / input.length) * 100)
}
