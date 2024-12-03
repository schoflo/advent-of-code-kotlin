package three

import java.io.File

class Three {
    object Main {
        @JvmStatic
        fun main(args: Array<String>) {
            println("----")
            val input: String =
                File("C:\\Entwicklung\\intellij_projects\\advent-of-code-kotlin\\src\\main\\kotlin\\three\\input.txt").inputStream()
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
                    print("alternate: ")
                    val splitArray = newInput.split("""don't\(\)""".toRegex()).toTypedArray()
                    println(splitArray[0])
                    sum += mulSumOfString(splitArray[0])
                    println("----")
                    newInput = splitArray.sliceArray(1 until splitArray.size).joinToString("")
                } else {
                    print("!alternate: ")
                    val splitArray = newInput.split("""do\(\)""".toRegex()).toTypedArray()
                    println(splitArray[0])
                    println("----")
                    newInput = splitArray.sliceArray(1 until splitArray.size).joinToString("")
//                    println("newinput")
                    println(newInput)
                }
                alternate = !alternate
            } while (newInput.isNotEmpty())

            println(sum)

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