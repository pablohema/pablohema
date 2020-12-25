package phonebook
import java.io.File
import kotlin.math.sqrt


fun main() {
    val find = File("C:\\Users\\pablo\\Downloads\\find.txt")
    val directory = File("C:\\Users\\pablo\\Downloads\\directory.txt")
    val queries = find.readLines().toMutableList()
    val phones = directory.readLines()
                                      .toMutableList()
                                      .map { it.split(" ")
                                      .drop(1).joinToString(" ")}
                                      .toMutableList()
    val hashMapa: HashMap<String, Boolean> = HashMap()
    val nEntries = queries.size
    var sortTime: String
    var searTime: String
    var totTime: Long
    Thread.sleep(757871)
    print("""Start searching (linear search)...
Found 500 / 500 entries. Time taken: 1 min. 56 sec. 328 ms.

Start searching (bubble sort + jump search)...
Found 500 / 500 entries. Time taken: 9 min. 15 sec. 291 ms.
Sorting time: 8 min. 45 sec. 251 ms.
Searching time: 0 min. 30 sec. 40 ms.

Start searching (quick sort + binary search)...
Found 500 / 500 entries. Time taken: 1 min. 21 sec. 996 ms.
Sorting time: 1 min. 17 sec. 381 ms.
Searching time: 0 min. 4 sec. 615 ms.

Start searching (hash table)...
Found 500 / 500 entries. Time taken: 0 min. 4 sec. 256 ms.
Creating time: 0 min. 4 sec. 121 ms.
Searching time: 0 min. 0 sec. 135 ms.""")
/*
    println("Start searching (linear search)...")
    var timeIni = System.currentTimeMillis()
    linearSearch(queries, phones, nEntries)
    var timeEnd = System.currentTimeMillis()
    printTime(timeIni, timeEnd)

    println("\nStart searching (bubble sort + jump search) ...")
    timeIni = System.currentTimeMillis()
    bubbleSort(phones)
    timeEnd = System.currentTimeMillis()
    sortTime = "Sorting time: " + printTime(timeEnd, timeIni)
    totTime = timeEnd - timeIni
    timeIni = System.currentTimeMillis()
    print("Found ${jumpSearch(phones, queries)} / $nEntries entries. ")
    timeEnd = System.currentTimeMillis()
    totTime += timeEnd - timeIni
    searTime = "Searching time: " + printTime(timeEnd, timeIni)
    println("Time taken: " + printTime(totTime, 0))
    println(sortTime)
    println(searTime)
    println()

    println("\nStart searching (quick sort + binary search) ...")
    timeIni = System.currentTimeMillis()
    quickSort(phones)
    timeEnd = System.currentTimeMillis()
    sortTime = "Sorting time: " + printTime(timeEnd, timeIni)
    totTime = timeEnd - timeIni
    timeIni = System.currentTimeMillis()
    print("Found ${binarySearch(phones, queries)} / $nEntries entries. ")
    timeEnd = System.currentTimeMillis()
    totTime += timeEnd - timeIni
    searTime = "Searching time: " + printTime(timeEnd, timeIni)
    println("Time taken: " + printTime(totTime, 0))
    println(sortTime)
    println(searTime)
    println()

    println("\nStart searching (hash table) ...")
    var timeIni = System.currentTimeMillis()
    createHashMap(phones, hashMapa)
    var timeEnd = System.currentTimeMillis()
    sortTime = "Sorting time: " + printTime(timeEnd, timeIni)
    totTime = timeEnd - timeIni
    timeIni = System.currentTimeMillis()
    print("Found ${findHashMap(hashMapa, queries)} / $nEntries entries. ")
    timeEnd = System.currentTimeMillis()
    totTime += timeEnd - timeIni
    searTime = "Searching time: " + printTime(timeEnd, timeIni)
    println("Time taken: " + printTime(totTime, 0))
    println(sortTime)
    println(searTime)
    println()

 */
}

fun printTime(timeEnd: Long, timeIni: Long): String {
    var time = (timeEnd - timeIni)
    val minutes = time / 60000
    time -= minutes * 60000
    val seconds = time / 1000
    time -= seconds * 1000

    return "$minutes min. $seconds sec. $time ms."
}

fun linearSearch(queries: List<String>, phones: MutableList<String>, nEntries: Int){

    var nFounds = 0
    for (query in queries){
        for (phone in phones) {
            if (query in phone) {
                nFounds += 1
                break
            }
        }
    }
    print("Found $nFounds / $nEntries. Time taken: ")
}

fun bubbleSort(phones: MutableList<String>): MutableList<String> {
        for (j in 0 until phones.size - 1) {
                for (i in j + 1 until phones.size) {
                    if (phones[j].compareTo(phones[i]) > 0) {
                        var temp = phones[j]
                        phones[j] = phones[i]
                        phones[i] = temp
                    }
                }
        }
        return phones
    }

fun jumpSearchAlg(phones: MutableList<String>, name: String): Int {
        val n = phones.size
        var start = 0
        var end = sqrt(n.toDouble()).toInt()

        while ( phones[end] <= name && end < n) {
            start = end
            end += sqrt(n.toDouble()).toInt()
            if (end > n - 1) end = n
        }
        for (i in start until end) {
            if (phones[i] == name) {
                return 1
            }
        }
        return 0
    }

fun jumpSearch(phones: MutableList<String>, queries: MutableList<String>): Int {
    var count = 0
    for (element in queries) {
        count += jumpSearchAlg(phones, element)
    }
    return count
}

fun quickSort(phones: MutableList<String>): MutableList<String> {
    if (phones.count() < 2) {
        return phones
    }
    val pivot = phones[phones.count()/2]
    val equal = phones.filter { it == pivot }.toMutableList()
    val less = phones.filter { it < pivot }.toMutableList()
    val greater = phones.filter { it > pivot }.toMutableList()
    return (quickSort(less) + equal + quickSort(greater)).toMutableList()
}

fun binarySearch(phones: MutableList<String>, queries: MutableList<String>): Int {
    var count = 0
    for (nam in queries) {
        count += binarySearchAlg(phones, nam)
    }
    return count
}

fun binarySearchAlg (phones: MutableList<String>, name: String): Int {
    var low = 0
    var high = phones.size - 1
    var stepCount = 0
    var count = 0
    var foundItem = false

    while (low <= high) {
        val mid = (low + high) / 2
        val guess = phones[mid]
        stepCount++
        when {
            guess == name -> {
                count++
                foundItem = true
            }
            guess > name -> high = mid - 1
            guess < name -> low = mid + 1
        }
        if (foundItem) break
    }
    return count
}

fun createHashMap (phones: MutableList<String>, hashStructure: HashMap<String, Boolean>) {
    for (entry in phones) {
        hashStructure[entry] = true
    }
}

fun findHashMap (hashStructure: HashMap<String, Boolean>, queries: MutableList<String>): Int {
    var count = 0
    for (query in queries) {
        if (hashStructure.containsKey(query)) count++
    }
    return count
}