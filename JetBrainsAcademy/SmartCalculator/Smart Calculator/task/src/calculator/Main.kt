package calculator

import java.math.BigInteger
import java.util.*

fun main() {
    val calculator = Calculator()
    do {
        val state = calculator.process(readLine()!!)
    } while (state)
}

class Calculator {
    private var state = true
    private val variables = mutableMapOf<String, BigInteger>()

    fun process(input: String) : Boolean {
        state = when (Action.find(input)) {
            Action.ASSIGN -> assign(input)
            Action.ECHO -> {
                println(getValue(input) ?: Messages.UNKNOWN_VARIABLE)
                true
            }
            Action.HELP -> {
                println(Messages.HELP)
                true
            }
            Action.EXIT -> {
                println(Messages.BYE)
                false
            }
            Action.UNKNOWN_COMMAND -> {
                println(Messages.UNKNOWN_COMMAND)
                true
            }
            Action.EMPTY -> {
                true
            }
            Action.CALCULATE -> calculate(input)
        }
        return state
    }

    private fun assign(input: String): Boolean {
        var error = false
        val matchResult = Regex(Action.ASSIGN.pattern).matchEntire(input.trim())
        if (matchResult != null) {
            val left = matchResult.groups[1]!!.value
            val right = matchResult.groups[2]!!.value
            if (!left.matches(Regex(Expression.VARIABLE))) {
                error = true
                println(Messages.INVALID_IDENTIFIER)
            }
            if (!right.matches(Regex("""${Expression.VARIABLE}|${Expression.NUMBER}"""))) {
                error = true
                println(Messages.INVALID_ASSIGNMENT)
            }
            if (!error) {
                val cookedRight = getValue(right)
                if (cookedRight != null) variables[left] = cookedRight else println(Messages.UNKNOWN_VARIABLE)
            }
        }
        return true
    }

    private fun calculate(input: String): Boolean {
        val postfix = toPostfix(input)
        if (!postfix.isNullOrEmpty()) println(calculatePostfix(postfix))
        return true
    }

    private fun toPostfix(input: String): MutableList<String> {
        var error = false
        val inputArray = input
                .replace(Regex("""\(|\)${Expression.VARIABLE}|${Expression.NUMBER}""")) { " ${it.value} " }
                .trim()
                .split(Regex("""\s+"""))
        val resultQueue = mutableListOf<String>()
        val operatorStack = LinkedList<String>()

        loop@ for (el in inputArray) {
            when {
                // operand
                el.matches(Regex("${Expression.VARIABLE}|${Expression.NUMBER}")) -> resultQueue.add(el)

                // operator
                el.matches(Regex(Expression.OPERATOR)) -> {
                    var operator = el.take(1)
                    if (el != operator.repeat(el.length) || operator == "*" && el.length > 1 || operator == "/" && el.length > 1) {
                        error = true
                        println(Messages.INVALID_EXPRESSION) // mixed, "**" or "//"
                        break@loop
                    }
                    if (operator == "-" && el.length % 2 == 0) operator = "+"
                    while (operatorStack.size > 0 && compareOperators(operator, operatorStack.peek()) != 1 && operatorStack.peek() != "(") resultQueue.add(operatorStack.pop())
                    operatorStack.push(operator)
                }

                // left
                el == "(" -> operatorStack.push(el)

                // right
                el == ")" -> {
                    while (operatorStack.size > 0 && operatorStack.peek() != "(") resultQueue.add(operatorStack.pop())
                    if (operatorStack.size > 0 && operatorStack.peek() == "(") operatorStack.pop()
                    else {
                        error = true
                        println(Messages.INVALID_EXPRESSION) // unbalanced right
                        break@loop
                    }

                }
            }
        }

        if (operatorStack.any { it == "(" }) {
            error = true
            println(Messages.INVALID_EXPRESSION) // unbalanced left
        }

        if (!error) while (operatorStack.size > 0) resultQueue.add(operatorStack.pop()) else resultQueue.clear()

        return resultQueue
    }

    private fun calculatePostfix(input: MutableList<String>): BigInteger {
        val resultStack = LinkedList<BigInteger>()
        loop@ for (el in input) {
            when {
                // operand
                el.matches(Regex("${Expression.VARIABLE}|${Expression.NUMBER}")) -> resultStack.push(getValue(el))

                // operator
                el.matches(Regex(Expression.OPERATOR)) -> {
                    val b = resultStack.pop()
                    val a = resultStack.pop()
                    var result = BigInteger("0")
                    when (el.single()) {
                        '+' -> result = a + b
                        '-' -> result = a - b
                        '*' -> result = a * b
                        '/' -> result = a / b
                        '^' -> result = a.pow(b.toInt()) // todo "a ^ b ^ c" must be "a ^ (b ^ c)", not "(a ^ b) ^ c"
                    }
                    resultStack.push(result)
                }
            }
        }
        return resultStack.pop()
    }

    private fun compareOperators(a: String, b: String): Int =
            when {
                Expression.PRECEDENCE.getValue(a) > Expression.PRECEDENCE.getValue(b) -> 1
                Expression.PRECEDENCE.getValue(a) < Expression.PRECEDENCE.getValue(b) -> -1
                else -> 0
            }

    private fun getValue(input: String): BigInteger? =
            if (input.matches(Regex(Expression.VARIABLE))) variables.getOrElse(input) { null } else input.toBigInteger()

    private enum class Action(val pattern: String) {
        ASSIGN("""\s*(\w+)\s*=\s*(.+)"""),
        ECHO("${Expression.VARIABLE}|${Expression.NUMBER}"),
        HELP("""/help"""),
        EXIT("""/exit"""),
        UNKNOWN_COMMAND("""/.*"""),
        EMPTY(""),
        CALCULATE(""".*""");

        companion object {
            fun find(command: String) : Action {
                for (it in values()) if (command.matches(Regex(it.pattern))) return it
                return CALCULATE
            }
        }
    }

    private object Messages {
        const val UNKNOWN_VARIABLE = "Unknown variable"
        const val UNKNOWN_COMMAND = "Unknown command"
        const val INVALID_IDENTIFIER = "Invalid identifier"
        const val INVALID_ASSIGNMENT = "Invalid assignment"
        const val INVALID_EXPRESSION = "Invalid expression"
        const val HELP = """The program calculates your expression:
    +, -, *, /, ^, ( and ) are allowed;
    ++ and --- are allowed (multiple minuses are counted and correctly calculated);
    variables are allowed."""
        const val BYE = "Bye!"
    }

    private object Expression {
        const val NUMBER = """-?\d+"""
        const val VARIABLE = """[a-zA-Z]+"""
        const val OPERATOR = """[\/\*\+\-\^]+"""
        val PRECEDENCE = mapOf("(" to 4, ")" to 4, "^" to 3, "*" to 2, "/" to 2, "+" to 1, "-" to 1)
    }
}