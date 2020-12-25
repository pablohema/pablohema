import java.util.regex.Pattern

class Fabric(var color: String = "grey") {
    var pattern: String = "none"
    var patternColor: String = "none"
    init {
        println("grey fabric is created")
    }

    constructor(color: String, pattern: String, patternColor: String): this(color) {
        this.pattern = pattern
        this.patternColor = patternColor
        println("the fabric is dyed $color")
        println("$patternColor $pattern pattern is printed on the fabric")
    }
}
