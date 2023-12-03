fun main() {
    with(Day02) {
        val testInput: List<String> = readInput("Day02_test")
        check(solvePart1(testInput) == 8)
        check(solvePart2(testInput) == 2286)

        solvePart1(this.input).println()
        solvePart2(this.input).println()
    }
}

object Day02 {
    const val MAX_RED_CUBES: Int = 12
    const val MAX_GREEN_CUBES: Int = 13
    const val MAX_BLUE_CUBES: Int = 14

    val input: List<String> = readInput("Day02")

    fun solvePart1(input: List<String>): Int =
        input.map { convertLineToGame(it) }.filter { it.isPossible }.sumOf { it.id }

    fun solvePart2(input: List<String>): Int =
        input.map { convertLineToGame(it) }.sumOf { it.powerOfMinimumCubes }

    private fun convertLineToGame(line: String): Game {
        val (gameId: String, gameInformation: String) = line.split(": ")
        val cubeReveals: Set<CubeReveal> = gameInformation.split(";").map { it.trim().createCubeReveal() }.toSet()

        return Game(gameId.filter { it.isDigit() }.toInt(), cubeReveals)
    }

    private fun String.createCubeReveal(): CubeReveal =
        this.split(", ").associate { it.split(" ")[1] to it.split(" ")[0].toInt() }
            .run {
                CubeReveal(
                    getOrDefault("red", 0),
                    getOrDefault("green", 0),
                    getOrDefault("blue", 0)
                )
            }

    data class CubeReveal(val redCubes: Int, val greenCubes: Int, val blueCubes: Int)

    data class Game(val id: Int, val cubeReveals: Set<CubeReveal>) {
        var isPossible: Boolean = cubeReveals.all { isValidReveal(it) }
            private set

        val powerOfMinimumCubes: Int
            get() = with(cubeReveals) {
                this.maxOf { it.redCubes } * this.maxOf { it.greenCubes } * this.maxOf { it.blueCubes }
            }

        private fun isValidReveal(reveal: CubeReveal): Boolean =
            with(reveal) {
                this.redCubes <= MAX_RED_CUBES && this.greenCubes <= MAX_GREEN_CUBES && this.blueCubes <= MAX_BLUE_CUBES
            }
    }
}