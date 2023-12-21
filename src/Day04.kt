import kotlin.math.pow

fun main() {
    with(Day04) {
        val testInput: List<String> = readInput("Day04_test")
        check(solvePart1(testInput) == 13)
        check(solvePart2(testInput) == 30)

        solvePart1(this.input).println()
        solvePart2(this.input).println()
    }
}

object Day04 {
    val input: List<String> = readInput("Day04")

    fun solvePart1(input: List<String>): Int = input.map { it.parseToScratchCard() }.sumOf { it.points }

    fun solvePart2(input: List<String>): Int {
        val cardMatches: List<Int> = input.map { it.parseToScratchCard().matchingNumbers }
        val scratchCards = IntArray(cardMatches.size) { 1 }

        cardMatches.forEachIndexed { index, matchCount ->
            repeat(matchCount) { futureCardOffset ->
                scratchCards.getOrNull(index + futureCardOffset + 1)
                    ?.let { scratchCards[index + futureCardOffset + 1] += scratchCards[index] }
            }
        }

        return scratchCards.sum()
    }

    private fun String.parseToScratchCard(): ScratchCard {
        val scratchCardNumbers: String = this.split(": ")[1]
        val (winningNumbers: Set<Int>, scratchedNumbers: Set<Int>) = scratchCardNumbers.split("|")
            .map {
                it.trim()
                    .split(Regex("\\s+"))
                    .map(String::toInt)
                    .toSet()
            }

        return ScratchCard(winningNumbers, scratchedNumbers)
    }

    data class ScratchCard(val winningNumbers: Set<Int>, val scratchedNumbers: Set<Int>) {
        val matchingNumbers: Int = winningNumbers.intersect(scratchedNumbers).size
        val points: Int = matchingNumbers.takeIf { it > 0 }?.let { 2.0.pow(it - 1).toInt() } ?: 0
    }
}
