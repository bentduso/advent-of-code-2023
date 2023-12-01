fun main() {
    with(Day01) {
        val firstTestInput: List<String> = readInput("Day01_test1")
        check(solvePart1(firstTestInput) == 142)

        val secondTestInput: List<String> = readInput("Day01_test2")
        check(solvePart2(secondTestInput) == 281)

        solvePart1(this.input).println()
        solvePart2(this.input).println()
    }
}

object Day01 {
    val input: List<String> = readInput("Day01")

    fun solvePart1(input: List<String>): Int = input.sumOf { line ->
        line.replace(Regex("[a-z]"), "").run { "${first()}${last()}".toInt() }
    }

    fun solvePart2(input: List<String>): Int {
        with(
            mapOf(
                "one" to "o1e",
                "two" to "t2o",
                "three" to "t3e",
                "four" to "f4r",
                "five" to "f5e",
                "six" to "s6x",
                "seven" to "s7n",
                "eight" to "e8t",
                "nine" to "n9e"
            )
        ) {
            val wordDigitPattern = Regex(this.keys.joinToString("|"))

            return input.sumOf { line ->
                generateSequence(line) { currentString ->
                    wordDigitPattern.find(currentString)?.let { numberWordMatch ->
                        currentString.replaceFirst(numberWordMatch.value, this[numberWordMatch.value].toString())
                    }
                }.last().filter { it.isDigit() }.run { "${first()}${last()}".toInt() }
            }
        }
    }
}
