package five

import java.io.File

class Five {


    object Main {
        @JvmStatic
        fun main(args: Array<String>) {
            val path = "C:\\Entwicklung\\intellij_projects\\advent-of-code-kotlin\\src\\main\\kotlin\\five\\input.txt"
//            val path = "C:\\Entwicklung\\intellij_projects\\advent-of-code-kotlin\\src\\main\\kotlin\\five\\test.txt"
            val input = File(path).inputStream().readBytes().toString(Charsets.UTF_8)
//            one(parseRules(input))
            two(parseRules(input))
        }

        private fun one(input: CalInput) {
            val correctReports = arrayListOf<IntArray>();
            input.pages.forEach { page ->
                val validRules = calcValidRules(page, input.rules)
                if (validRules.isEmpty()) {
                    correctReports.add(page)
                } else {
                    var valid = true
                    for (validRule in validRules) {
                        if (page.indexOf(validRule[0]) > page.indexOf(validRule[1])) {
                            valid = false
                            break
                        }
                    }
                    if (valid) {
                        correctReports.add(page)
                    }
                }
            }
            correctReports.forEach { report ->
                println(report.joinToString(","))
            }
            var sum = 0
            correctReports.forEach { report ->
                sum += report[report.size / 2]
            }
            println("sum: $sum")
        }

        private fun two(input: CalInput) {
            val incorrectReports = incorrectReports(input)
            val correctedReports = arrayListOf<IntArray>()

            incorrectReports.forEach { report ->
                val validRules = calcValidRules(report, input.rules)
                do {
                    validRules.forEach { validRule ->
                        if (report.indexOf(validRule[0]) > report.indexOf(validRule[1])) {
                            val index1 = report.indexOf(validRule[0])
                            val index2 = report.indexOf(validRule[1])

                            val memory = report[index1]
                            report[index1] = report[index2]
                            report[index2] = memory
                        }
                    }
                } while (!checkValidReport(report, validRules))
                println("correct: ${checkValidReport(report, validRules)} ")
                correctedReports.add(report)
            }

            correctedReports.forEach { report ->
                println(report.joinToString(","))
            }

            var sum = 0
            correctedReports.forEach { report ->
                sum += report[report.size / 2]
            }
            println("sum: $sum")
        }

        fun checkValidReport(report: IntArray, validRules: ArrayList<IntArray>): Boolean {
            for (validRule in validRules) {
                if (report.indexOf(validRule[0]) > report.indexOf(validRule[1])) {
                    return false
                }
            }
            return true
        }

        private fun incorrectReports(input: CalInput): ArrayList<IntArray> {
            val incorrectReports = arrayListOf<IntArray>();
            input.pages.forEach { page ->
                val validRules = calcValidRules(page, input.rules)
                if (validRules.isNotEmpty()) {
                    for (validRule in validRules) {
                        if (page.indexOf(validRule[0]) > page.indexOf(validRule[1])) {
                            incorrectReports.add(page)
                            break
                        }
                    }
                }
            }
            return incorrectReports
        }

        fun calcValidRules(page: IntArray, rules: ArrayList<IntArray>): ArrayList<IntArray> {
            val validRules = arrayListOf<IntArray>()
            rules.forEach { rule ->
                if (page.contains(rule[0]) && page.contains(rule[1])) {
                    validRules.add(rule)
                }
            }
            return validRules
        }


        fun parseRules(input: String): CalInput {
            val split = input.split("\r\n\r\n")
            val calInput = CalInput()
            //Rules
            val splitRules = split[0].split("\r\n")
            val rules = arrayListOf<IntArray>()
            splitRules.forEach { rule ->
                val singleRuleTrue = rule.split("|")
                val singleRule = intArrayOf(singleRuleTrue[0].toInt(), singleRuleTrue[1].toInt())
                rules.add(singleRule)
            }
            //Pages
            val splitPages = split[1].split("\r\n")
            val pages = arrayListOf<IntArray>()
            splitPages.forEach { array ->
                val elementList: ArrayList<Int> = arrayListOf()
                val elements = array.split(",")
                elements.forEach { element ->
                    elementList.add(element.toInt())
                }
                pages.add(elementList.toIntArray())
            }
            calInput.rules = rules
            calInput.pages = pages
            return calInput
        }
    }

    class CalInput {
        var rules: ArrayList<IntArray> = arrayListOf()
        var pages: ArrayList<IntArray> = arrayListOf()
    }

}
