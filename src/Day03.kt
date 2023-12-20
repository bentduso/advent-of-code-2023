fun main() {
    with(Day03) {
        val testInput: List<String> = readInput("Day03_test")
        check(solvePart1(testInput) == 4361)

        solvePart1(this.input).println()
    }
}

object Day03 {
    val input: List<String> = readInput("Day03")

    fun solvePart1(input: List<String>): Int = sumPartNumbers(input)

    fun solvePart2(input: List<String>): Int = input.size

    private fun sumPartNumbers(schematic: List<String>): Int {
        return Regex("\\d+").findAll(schematic.joinToString("\n")).map { matchResult ->
            val position: Pair<Int, Int> =
                matchResult.range.first / (schematic[0].length + 1) to matchResult.range.first % (schematic[0].length + 1)
            SchematicNumber(matchResult.value.toInt(), position.first, position.second)
        }.filter { it.isAdjacentToSymbol(schematic) }.sumOf { it.value }
    }

    data class SchematicNumber(val value: Int, val row: Int, val col: Int) {
        fun isAdjacentToSymbol(schematic: List<String>): Boolean {
            return this.value.toString().indices.any { i ->
                val currentCol: Int = col + i
                with(
                    listOf(
                        -1 to 0, // up
                        -1 to 1, // upper right
                        0 to 1, // right
                        1 to 1, // lower right
                        1 to 0, // down
                        1 to -1, // lower left
                        0 to -1, // left
                        -1 to -1 // upper left
                    )
                ) {
                    any { (dx, dy) ->
                        val (nx: Int, ny: Int) = row + dx to currentCol + dy
                        nx in schematic.indices && ny in schematic[nx].indices && !schematic[nx][ny].isDigit() && schematic[nx][ny] != '.'
                    }
                }
            }
        }
    }

}