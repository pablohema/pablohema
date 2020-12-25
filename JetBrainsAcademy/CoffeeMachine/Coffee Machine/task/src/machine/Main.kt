package machine
import java.util.Scanner


var water = 400
var milk = 540
var coffee = 120
var cups = 9
var money = 550

fun main() {

    do {
        print("Write action (buy, fill, take, remaining, exit): ")
        val selection = readLine()
        println("")

        when (selection) {
            "buy" -> buy()
            "fill" -> fill()
            "take" -> take()
            "remaining" -> results()
            "exit" -> null
        }
    } while (selection != "exit")
}

fun results () {
    print("The coffee machine has:\n" +
            "$water of water\n" +
            "$milk of milk\n" +
            "$coffee of coffee beans\n" +
            "$cups of disposable cups\n" +
            "${'$'}$money of money\n" +
            "\n")
}

fun buy() {
    print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
    val type = readLine()

    when (type) {
        "1" -> {
            if (water >= 250 && coffee >= 16 && cups >= 1){
            water -= 250
            coffee -= 16
            money += 4
            cups -= 1
            println("I have enough resources, making you a coffee!")
            } else if (water < 250){
                println("Sorry, not enough water!")
            } else if (coffee < 16){
                println("Sorry, not enough coffee beans!")
            } else {
                println("Sorry, not enough disposable cups!")
            }
        }
        "2" -> {
            if (water >= 350 && milk >= 75 && coffee >= 20 && cups >= 1){
                water -= 350
                milk -= 75
                coffee -= 20
                money += 7
                cups -= 1
                println("I have enough resources, making you a coffee!")
            } else if (water < 350){
                println("Sorry, not enough water!")
            } else if (milk < 75){
                println("Sorry, not enough milk!")
            }else if (coffee < 16){
                println("Sorry, not enough coffee beans!")
            } else {
                println("Sorry, not enough disposable cups!")
            }
        }
        "3" -> {
            if (water >= 200 && milk >= 100 && coffee >= 12 && cups >= 1){
                water -= 200
                milk -= 100
                coffee -= 12
                money += 6
                cups -= 1
                println("I have enough resources, making you a coffee!")
            } else if (water < 200){
                println("Sorry, not enough water!")
            } else if (milk < 100){
                println("Sorry, not enough milk!")
            }else if (coffee < 12){
                println("Sorry, not enough coffee beans!")
            } else {
                println("Sorry, not enough disposable cups!")
            }
        }
        "back" -> {
            return
        }

    }
    println()
    return
}

fun fill() {
    val scanner = Scanner(System.`in`)

    print("Write how many ml of water do you want to add: ")
    val waterFill = scanner.nextInt()
    print("Write how many ml of milk do you want to add: ")
    val milkFill = scanner.nextInt()
    print("Write how many grams of coffee beans do you want to add: ")
    val coffeeFill = scanner.nextInt()
    print("Write how many disposable cups of coffee do you want to add: ")
    val cupFill = scanner.nextInt()
    println("")

    water += waterFill
    milk += milkFill
    coffee += coffeeFill
    cups += cupFill

}

fun take() {
    print("I gave you ${'$'}$money")
    money = 0
    println("")

}

