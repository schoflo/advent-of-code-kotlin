package four

import java.io.File

class FourPartTwo {
    object Main {
        @JvmStatic
        fun main(args: Array<String>) {
            val path = "C:\\Entwicklung\\intellij_projects\\advent-of-code-kotlin\\src\\main\\kotlin\\four\\input.txt"
//            val path = "C:\\Entwicklung\\intellij_projects\\advent-of-code-kotlin\\src\\main\\kotlin\\four\\test.txt"
            val input = File(path).inputStream().readBytes().toString(Charsets.UTF_8)
            println(one(input))

        }

        private fun one(input: String): Int {
            var sum = 0
            val matrix = convertInput(input)
            matrix.forEachIndexed { indexRow, row ->
                row.forEachIndexed { indexCol, col ->
                    println("indexRow: $indexRow, indexCol: $indexCol")
                    sum += checkRightDown(matrix, indexRow, indexCol)
                }
            }
            return sum
        }

        private fun checkRightDown(matrix: Array<CharArray>, indexRow: Int, indexCol: Int): Int {
            if (indexCol < matrix[indexRow].size - 2 && indexRow < matrix.size - 2) {
                var toCheck = ""
                for (i in 0..2) {
                    toCheck += matrix[indexRow + i][indexCol + i]
                }
                println("subArray: $toCheck")
                if (toCheck.contains("SAM") || toCheck.contains("MAS")) {
                    return checkLeftDown(matrix, indexRow, indexCol + 2)
                }
            }
            return 0
        }

        private fun checkLeftDown(matrix: Array<CharArray>, indexRow: Int, indexCol: Int): Int {
            if (indexCol >= 2 && indexRow < matrix.size - 2) {
                var toCheck = ""
                for (i in 0..2) {
                    toCheck += matrix[indexRow + i][indexCol - i]
                }
                println("subArray: $toCheck")
                return if (toCheck.contains("SAM") || toCheck.contains("MAS")) 1 else 0
            }
            return 0
        }

        private fun convertInput(input: String): Array<CharArray> {
            val list: ArrayList<CharArray> = ArrayList()
            val matches = input.split(Regex("""\r\n"""))
            matches.forEach { match ->
                list.add(match.toCharArray())
            }
            return list.toTypedArray()
        }
    }
}