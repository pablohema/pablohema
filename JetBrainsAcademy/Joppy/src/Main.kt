
fun main() {
    optimalChangeDeliver(20)
}

fun optimalChangeDeliver(change: Int): Int {
    val coinsArray = listOf(25, 10, 5, 1)
    var res: Int = 0
    var value = change

    for (i in coinsArray.indices) {
        while( value >= coinsArray[i]) {
            value -= coinsArray[i]
            res++
        }
        if(value == 0) break
    }
    return res
}