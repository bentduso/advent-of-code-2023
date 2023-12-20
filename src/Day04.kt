import kotlin.math.pow

fun main() {
    with(Day04) {
        val testInput: List<String> = readInput("Day04_test")
        check(solvePart1(testInput) == 13)

        solvePart1(this.input).println()
    }
}

object Day04 {
    val input: List<String> = readInput("Day04")

    fun solvePart1(input: List<String>): Int = input.map { parseLineToScratchCard(it) }.sumOf { it.points }

    private fun parseLineToScratchCard(line: String): ScratchCard {
        val scratchCardNumbers: String = line.split(": ")[1]
        val (winningNumbers: Set<Int>, scratchedNumbers: Set<Int>) = scratchCardNumbers.split("|")
            .map { parseScratchCardNumbersToSet(it) }

        return ScratchCard(winningNumbers, scratchedNumbers)

    }

    private fun parseScratchCardNumbersToSet(numbers: String): Set<Int> =
        numbers.trim().split(Regex("\\s+")).map(String::toInt).toSet()

    data class ScratchCard(val winningNumbers: Set<Int>, val scratchedNumbers: Set<Int>) {
        val points: Int
            get() = winningNumbers.intersect(scratchedNumbers).size
                .takeIf { it > 0 }?.let { 2.0.pow(it - 1).toInt() } ?: 0
    }
}
