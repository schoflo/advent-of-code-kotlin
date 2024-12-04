package three

import java.io.File

class Three {
    object Main {
        @JvmStatic
        fun main(args: Array<String>) {
            println("----")
            val path = "C:\\Entwicklung\\intellij_projects\\advent-of-code-kotlin\\src\\three\\input.txt"
//            val path = "C:\\Entwicklung\\intellij_projects\\advent-of-code-kotlin\\src\\three\\test.txt"
            val input: String =
                File(path).inputStream()
                    .readBytes().toString(Charsets.UTF_8)
//            one(input)
            two(input)
//            println(matches.map {match -> match.value}.joinToString(","))
        }

        private fun one(input: String) {
            val regex = Regex("""mul\(\d+,\d+\)""")
            val matches = regex.findAll(input)
            var sum = 0
            matches.map { match -> match.value }
                .forEach { match ->
                    val mulsNumbers = match.substring(4, match.length - 1)
                    val arr = mulsNumbers.split(",")
                    sum += arr[0].toInt() * arr[1].toInt()
                }
            println(sum)
        }

        private fun two(input: String) {
            var alternate = true
            var newInput = input
            var sum = 0

            do {
                if (alternate) {
                    val splitArray = newInput.split("""(?=don't\(\))""".toRegex()).toTypedArray()
                    println(splitArray[0])
                    sum += mulSumOfString(splitArray[0])
                    newInput = splitArray.sliceArray(1 until splitArray.size).joinToString("")
                } else {
                    val splitArray = newInput.split("""(?=do\(\))""".toRegex()).toTypedArray()
                    newInput = splitArray.sliceArray(1 until splitArray.size).joinToString("")
                }
                alternate = !alternate
            } while (newInput.isNotEmpty())

            println("Ergebnis:$sum")

        }

        private fun mulSumOfString(input: String): Int {
            val regex = Regex("""mul\(\d+,\d+\)""")
            val matches = regex.findAll(input)
            var sum = 0
            matches.map { match -> match.value }
                .forEach { match ->
                    val mulsNumbers = match.substring(4, match.length - 1)
                    val arr = mulsNumbers.split(",")
                    sum += arr[0].toInt() * arr[1].toInt()
                }
            return sum
        }

    }

}