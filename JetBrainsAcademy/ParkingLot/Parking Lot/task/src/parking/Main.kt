package parking

import java.util.Scanner

/**
 * Decodes input and calls appropriate function of ParkingLot object.
 */
fun main() {
    var finished = false
    do {
        val command = readLine()!!
        val scanner = Scanner(command)
        when (scanner.next()) {
            "create" -> {
                val spots = scanner.nextInt()
                ParkingLot.create(spots)
            }
            "status" -> ParkingLot.status()
            "park" -> {
                val registration = scanner.next()
                val color = scanner.next()
                ParkingLot.park(registration, color)
            }
            "leave" -> {
                val spotNumber = scanner.nextInt() - 1
                ParkingLot.leave(spotNumber)
            }
            "reg_by_color" -> {
                val color = scanner.next()
                ParkingLot.regByColor(color)
            }
            "spot_by_color" -> {
                val color = scanner.next()
                ParkingLot.spotByColor(color)
            }
            "spot_by_reg" -> {
                val registration = scanner.next()
                ParkingLot.spotByReg(registration)
            }
            "exit" -> finished = true
        }
    } while (!finished)
}

/**
 * Represents the parking lot and provides functions to execute all possible commands.
 */
object ParkingLot {
    private var spots = arrayOf<Spot>()

    fun create(numberOfSpots: Int) {
        spots = Array(numberOfSpots) { Spot() }
        println("Created a parking lot with $numberOfSpots spots.")
    }

    fun status() {
        if (!checkCreated()) return
        if (isEmpty()) {
            println("Parking lot is empty.")
            return
        }
        for (i in spots.indices) {
            if (spots[i].registration != "") {
                println("${i + 1} ${spots[i].registration} ${spots[i].color}")
            }
        }
    }

    fun park(registration: String, color: String) {
        if (!checkCreated()) return
        val spotNumber = nextEmptySpotNumber()
        if (spotNumber == -1) {
            println("Sorry, the parking lot is full.")
            return
        }
        spots[spotNumber].park(registration, color)
        println("$color car parked in spot ${spotNumber + 1}.")
    }

    fun leave(spotNumber: Int) {
        if (!checkCreated()) return
        spots[spotNumber].leave()
        println("Spot ${spotNumber + 1} is free.")
    }

    fun regByColor(color: String) {
        if (!checkCreated()) return
        var found = false
        for (spotNumber in spots.indices) {
            if (spots[spotNumber].color.equals(color, true)) {
                if (found) print(", ")
                print(spots[spotNumber].registration)
                found = true
            }
        }
        println(if (found) "" else "No cars with color $color were found.")
    }

    fun spotByColor(color: String) {
        if (!checkCreated()) return
        var found = false
        for (spotNumber in spots.indices) {
            if (spots[spotNumber].color.equals(color, true)) {
                if (found) print(", ")
                print(spotNumber + 1)
                found = true
            }
        }
        println(if (found) "" else "No cars with color $color were found.")
    }

    fun spotByReg(registration: String) {
        if (!checkCreated()) return
        var found = false
        for (spotNumber in spots.indices) {
            if (spots[spotNumber].registration == registration) {
                println(spotNumber + 1)
                found = true
                break
            }
        }
        if (!found) println("No cars with registration number $registration were found.")
    }

    private fun checkCreated(): Boolean {
        if (spots.isEmpty()) {
            println("Sorry, a parking lot has not been created.")
            return false
        }
        return true
    }

    private fun isEmpty(): Boolean {
        for (spot in spots) {
            if (!spot.isEmpty()) return false
        }
        return true
    }

    private fun nextEmptySpotNumber(): Int {
        for (spotNumber in spots.indices) {
            if (spots[spotNumber].isEmpty()) return spotNumber
        }
        return -1
    }
}

/**
 * Represents one parking spot. Its public members are the [registration] and [color] of any car occupying the spot,
 * or empty strings if unoccupied. The [park] and [leave] functions set and reset the members, and [isEmpty] checks
 * whether the spot is empty.
 */
class Spot {
    var registration = ""
    var color = ""

    fun isEmpty() = registration == ""

    fun park(registration: String, color: String) {
        this.registration = registration
        this.color = color
    }

    fun leave() {
        registration = ""
        color = ""
    }
}