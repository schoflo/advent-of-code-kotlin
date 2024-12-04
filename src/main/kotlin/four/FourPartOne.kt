package four

import java.io.File

class FourPartOne {
    object Main {
        @JvmStatic
        fun main(args: Array<String>) {
            val path = "C:\\Entwicklung\\intellij_projects\\advent-of-code-kotlin\\src\\main\\kotlin\\four\\input.txt"
//            val path = "C:\\Entwicklung\\intellij_projects\\advent-of-code-kotlin\\src\\main\\kotlin\\four\\test.txt"
            val input = File(path).inputStream().readBytes().toString(Charsets.UTF_8)
//            printMatrix(convertInput(input))
            println(one(input))
//            val x = "MMMSXXMASM".toCharArray()
//            println(x.slice(3 downTo 3 - 3).toCharArray())

        }

        private fun one(input: String): Int {
            var sum = 0
            val matrix = convertInput(input)
            matrix.forEachIndexed { indexRow, row ->
//                println("indexRow: $indexRow")
                row.forEachIndexed { indexCol, col ->
                    println("indexRow: $indexRow, indexCol: $indexCol")
                    sum += checkLeft(matrix, indexRow, indexCol)
                    sum += checkLeftUp(matrix, indexRow, indexCol)
                    sum += checkUp(matrix, indexRow, indexCol)
                    sum += checkRightUp(matrix, indexRow, indexCol)
                    sum += checkRight(matrix, indexRow, indexCol)
                    sum += checkRightDown(matrix, indexRow, indexCol)
                    sum += checkDown(matrix, indexRow, indexCol)
                    sum += checkLeftDown(matrix, indexRow, indexCol)
                }
            }
            return sum
        }

        private fun checkLeft(matrix: Array<CharArray>, indexRow: Int, indexCol: Int): Int {
            if (indexCol >= 3) {
                val subArray = matrix[indexRow].slice(indexCol downTo indexCol - 3).toCharArray()
//                println("subArray: ${String(subArray)}")
                return if (String(subArray).contains("XMAS")) 1 else 0
            }
            return 0
        }

        private fun checkLeftUp(matrix: Array<CharArray>, indexRow: Int, indexCol: Int): Int {
            if (indexCol >= 3 && indexRow >= 3) {
                var toCheck = ""
                for (i in 0..3) {
                    toCheck += matrix[indexRow - i][indexCol - i]
                }
                println("subArray: $toCheck")
                return if (toCheck.contains("XMAS")) 1 else 0
            }
            return 0
        }

        private fun checkUp(matrix: Array<CharArray>, indexRow: Int, indexCol: Int): Int {
            if (indexRow >= 3) {
                var toCheck = ""
                for (i in indexRow downTo indexRow - 3) {
                    toCheck += matrix[i][indexCol]
                }
                println("subArray: $toCheck")
                return if (toCheck.contains("XMAS")) 1 else 0
            }
            return 0
        }

        private fun checkRightUp(matrix: Array<CharArray>, indexRow: Int, indexCol: Int): Int {
            if (indexCol < matrix[indexRow].size - 3 && indexRow >= 3) {
                var toCheck = ""
                for (i in 0..3) {
                    toCheck += matrix[indexRow - i][indexCol + i]
                }
                println("subArray: $toCheck")
                return if (toCheck.contains("XMAS")) 1 else 0
            }
            return 0
        }

        private fun checkRight(matrix: Array<CharArray>, indexRow: Int, indexCol: Int): Int {
            if (indexCol < matrix[indexRow].size - 3) {
                val subArray = matrix[indexRow].slice(indexCol..indexCol + 3).toCharArray()
                println("subArray: ${String(subArray)}")
                return if (String(subArray).contains("XMAS")) 1 else 0
            }
            return 0
        }

        private fun checkRightDown(matrix: Array<CharArray>, indexRow: Int, indexCol: Int): Int {
            if (indexCol < matrix[indexRow].size - 3 && indexRow < matrix.size - 3) {
                var toCheck = ""
                for (i in 0..3) {
                    toCheck += matrix[indexRow + i][indexCol + i]
                }
                println("subArray: $toCheck")
                return if (toCheck.contains("XMAS")) 1 else 0
            }
            return 0
        }

        private fun checkDown(matrix: Array<CharArray>, indexRow: Int, indexCol: Int): Int {
            if (indexRow < matrix.size - 3) {
                var toCheck = ""
                for (i in indexRow..indexRow + 3) {
                    toCheck += matrix[i][indexCol]
                }
                println("subArray: $toCheck")
                return if (toCheck.contains("XMAS")) 1 else 0
            }
            return 0
        }

        private fun checkLeftDown(matrix: Array<CharArray>, indexRow: Int, indexCol: Int): Int {
            if (indexCol >= 3 && indexRow < matrix.size - 3) {
                var toCheck = ""
                for (i in 0..3) {
                    toCheck += matrix[indexRow + i][indexCol - i]
                }
                println("subArray: $toCheck")
                return if (toCheck.contains("XMAS")) 1 else 0
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

        private fun printMatrix(matrix: Array<CharArray>) {
            matrix.forEach { row ->
                row.forEach { char ->
                    print(char)
                }
                println()
            }
        }
    }
}