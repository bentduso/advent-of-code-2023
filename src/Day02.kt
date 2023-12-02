fun main() {
    with(Day02) {
        val firstTestInput: List<String> = readInput("Day02_test1")
        check(solvePart1(firstTestInput) == 8)

        /*
        val secondTestInput: List<String> = readInput("Day02_test2")
        check(solvePart2(secondTestInput) == 281)
        */

        solvePart1(this.input).println()
//      solvePart2(this.input).println()
    }
}

object Day02 {
    const val MAX_RED_CUBES: Int = 12
    const val MAX_GREEN_CUBES: Int = 13
    const val MAX_BLUE_CUBES: Int = 14

    val input: List<String> = readInput("Day02")

    fun solvePart1(input: List<String>): Int =
        input.map { convertLineToGame(it) }.filter { it.isPossible }.sumOf { it.id }

    fun solvePart2(input: List<String>): Int = input.size

    private fun convertLineToGame(line: String): Game {
        val (gameId: String, reveals: String) = line.split(": ")
        val cubeReveals: Set<List<String>> = reveals.split(";")
            .map { it.trim().split(", ").toList() }.toSet()

        return Game(gameId.filter { it.isDigit() }.toInt(), cubeReveals)
    }

    data class Game(val id: Int, val cubeReveals: Set<List<String>>) {
        var isPossible: Boolean = cubeReveals.all { isValidReveal(it) }
            private set

        private fun isValidReveal(reveal: List<String>): Boolean {
            val cubeAmounts: Map<String, Int> = tallyCubeCounts(reveal)

            return (cubeAmounts.getOrDefault("red", 0) <= MAX_RED_CUBES &&
                    cubeAmounts.getOrDefault("green", 0) <= MAX_GREEN_CUBES &&
                    cubeAmounts.getOrDefault("blue", 0) <= MAX_BLUE_CUBES)
        }

        private fun tallyCubeCounts(reveal: List<String>): Map<String, Int> = reveal.associate {
            val (count: String, color: String) = it.split(" ")
            color to count.toInt()
        }
    }
}

