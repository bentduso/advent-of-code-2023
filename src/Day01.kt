fun main() {
    fun part1(input: List<String>): Int = input.sumOf { line ->
        line.replace(Regex("[a-z]"), "").run { "${first()}${last()}".toInt() }
    }

    fun part2(input: List<String>): Int {
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

    val firstTestInput: List<String> = readInput("Day01_test1")
    check(part1(firstTestInput) == 142)

    val secondTestInput: List<String> = readInput("Day01_test2")
    check(part2(secondTestInput) == 281)

    val input: List<String> = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
