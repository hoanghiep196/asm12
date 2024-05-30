package fa.training
import java.util.Scanner
import java.time.YearMonth
import kotlin.math.sqrt

class Task1 {
    fun task1() {
        val fibonacciNumbers = mutableListOf<Long>(0, 1)
        while (fibonacciNumbers.size < 20) {
            val nextFib = fibonacciNumbers.takeLast(2).sum()
            fibonacciNumbers.add(nextFib)
        }
        fibonacciNumbers.reverse()
        println(fibonacciNumbers.joinToString(", "))
    }
}
class Task2 {
    private fun String.turnOffB2b3(): String {
        require(this.length == 8) { "Input string (8 hexadecimal characters long) : " }
        // Convert hex string to binary string
        val binaryString = this.chunked(2).joinToString("") { it.toInt(16).toString(2).padStart(8, '0') }
        val modifiedBinaryString = binaryString.chunked(8).toMutableList()

        // Turn off the 3rd bit of the 2nd byte
        val byte2 = modifiedBinaryString[1].toCharArray()
        byte2[4] = '0'
        modifiedBinaryString[1] = String(byte2)

        // Convert the modified binary string back to hexadecimal
        val modifiedHexString = modifiedBinaryString.joinToString("") {
            it.toInt(2).toString(16).padStart(2, '0')
        }

        return modifiedHexString.uppercase()
    }

    fun task2() {
        val result = "67889999".turnOffB2b3()
        println(result)
    }
}
class Task3 {
    fun task3() {
        val result = (10..200).filter { it % 7 == 0 && it % 5 != 0 }
        println(result.joinToString(", "))
    }

}
class Task4 {
    private val scanner : Scanner = Scanner(System.`in`)
    fun task4() {
        do {
            print("Enter a two-digit integer: (ex : 99)")
            val number = scanner.nextLine()?.toIntOrNull()
            if (number != null && number in 10..99) {
                println("Binary: ${number.toString(2)}")
                println("Hexadecimal: ${number.toString(16).uppercase()}")
            } else {
                println("Invalid input. Please enter a two-digit integer. (ex: 99)")
            }
            println("If you continue, press y or n : ")
            val input = readlnOrNull() ?: ""
        } while (!input.equals("n", ignoreCase = true))
    }
}
class Task5 {
    private val scanner : Scanner = Scanner(System.`in`)
    fun task5() {
        print("Enter integers separated by spaces: ")
        val input = scanner.nextLine() ?: ""
        val numbers = input.split(" ").mapNotNull { it.toIntOrNull() }.toMutableList()
        numbers.sort()
        println(numbers.joinToString(", "))
    }
}
class Task6 {
    private val scanner : Scanner = Scanner(System.`in`)
    fun task6() {
        print("Enter a string: ")
        val input = scanner.nextLine() ?: ""
        val words = input.split("\\s+".toRegex()).filter { it.isNotEmpty() }
        val result = words.joinToString(" ") { word -> word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } }
        println("Number of words: ${words.size}")
        println("Capitalized sentence: $result")
    }

}
class Task7 {
    private val scanner : Scanner = Scanner(System.`in`)
    fun task7() {
        print("Enter month (1-12): ")
        val month = scanner.nextLine().toInt()
        print("Enter year: ")
        val year = scanner.nextLine().toInt()
        if (month in 1..12) {
            val daysInMonth = YearMonth.of(year, month).lengthOfMonth()
            println("Number of days: $daysInMonth")
        } else {
            println("Invalid input.")
        }
    }

}
class Task8 {
    private fun isValidPAN(pan: String): Boolean {
        if (!pan.all { it.isDigit() } || pan.length !in 12..19) return false

        val digits = pan.map { it.toString().toInt() }.toMutableList()
        for (i in digits.size - 2 downTo 0 step 2) {
            val doubled = digits[i] * 2
            digits[i] = if (doubled > 9) doubled - 9 else doubled
        }

        return digits.sum() % 10 == 0
    }

    private fun getCardType(pan: String): String {
        return when {
            pan.startsWith("4") -> "VISA Card"
            pan.startsWith("50") || pan.startsWith("51") || pan.startsWith("52") || pan.startsWith("53") ||
                    pan.startsWith("54") || pan.startsWith("55") || pan.startsWith("56") || pan.startsWith("57") ||
                    pan.startsWith("58") || pan.startsWith("59") || pan.startsWith("60") || pan.startsWith("61") ||
                    pan.startsWith("62") || pan.startsWith("63") || pan.startsWith("64") || pan.startsWith("65") ||
                    pan.startsWith("66") || pan.startsWith("67") || pan.startsWith("68") || pan.startsWith("69") ||
                    pan.startsWith("2221") || pan.startsWith("2222") || pan.startsWith("2223") || pan.startsWith("2224") ||
                    pan.startsWith("2225") || pan.startsWith("2226") || pan.startsWith("2227") || pan.startsWith("2228") ||
                    pan.startsWith("2229") || pan.startsWith("223") || pan.startsWith("224") || pan.startsWith("225") ||
                    pan.startsWith("226") || pan.startsWith("227") || pan.startsWith("228") || pan.startsWith("229") ||
                    pan.startsWith("23") || pan.startsWith("24") || pan.startsWith("25") || pan.startsWith("26") ||
                    pan.startsWith("270") || pan.startsWith("271") || pan.startsWith("2720") -> "Master Card"
            pan.startsWith("3528") || pan.startsWith("3529") || pan.startsWith("353") || pan.startsWith("354") ||
                    pan.startsWith("355") || pan.startsWith("356") || pan.startsWith("357") || pan.startsWith("358") ||
                    pan.startsWith("3589") -> "JCB Card"
            else -> "Unknown Card"
        }
    }

    fun task8() {
        do {
            print("Enter PAN: (12-19 digits and all numbers) : ")
            val pan = readlnOrNull() ?: ""
            if (isValidPAN(pan)) {
                println("PAN is valid.")
                println("Card Type: ${getCardType(pan)}")
            } else
                println("Invalid PAN.")
            println("If you continue. y or n : ")
            val input = readlnOrNull() ?: ""
            } while (!input.equals("n", ignoreCase = true))
    }

}
class Task9 {
    private fun resolveQuadraticEquation(a: Double, b: Double, c: Double) {
        val deltaValue = b * b - 4 * a * c
        if (deltaValue > 0) {
            val value1 = (-b + sqrt(deltaValue)) / (2 * a)
            val value2 = (-b - sqrt(deltaValue)) / (2 * a)
            val stringValue1 = String.format("%.3f", value1)
            val stringValue2 = String.format("%.3f", value2)
            println("Values of roots are: $stringValue1 and $stringValue2")
        } else if (deltaValue == 0.0) {
            val value = -b / (2 * a)
            println("Only value of root is: $value")
        } else {
            println("The equation has no any roots.")
    }}

    fun task9() {
        println("Enter quadratic equation a, b, and c: (ax2 + bx + c = 0)")
        println("Value a: (format 0.0) : ")
        val a = readlnOrNull()?.toDoubleOrNull()
        println("Value b: (format 0.0) : ")
        val b = readlnOrNull()?.toDoubleOrNull()
        println("Value c: (format 0.0) : ")
        val c = readlnOrNull()?.toDoubleOrNull()
        if (a != null && b != null && c != null) {
            resolveQuadraticEquation(a, b, c)
        } else {
            println("Invalid input.")
            }
        }
    }

class  Task10 {
    fun task10() {
        var attempts = 0
        val maxAttempts = 3
        val initialBalance = 4000000
        var balance = initialBalance

        while (attempts < maxAttempts) {
            print("Insert your ATM card and press Enter to continue: ")
            readlnOrNull()
            print("Enter password: ")
            val password = readlnOrNull() ?: ""

            if (password.length < 6) {
                println("Invalid password.")
                attempts++
                if (attempts == maxAttempts) {
                    println("Account blocked.")
                    return
                }
            } else {
                println("Your balance is: $balance VND")
                break
            }
        }

        while (true) {
            println("1. Withdraw money")
            println("2. Check remaining amount of money")
            println("3. Exit")
            print("Choose an option: ")
            val option = readlnOrNull() ?: ""

            when (option) {
                "1" -> {
                    print("Enter the amount to withdraw: ")
                    val amount = readlnOrNull()?.toIntOrNull()
                    if (amount == null || amount <= 0) {
                        println("Invalid amount.")
                    } else if (amount > balance) {
                        println("Insufficient funds.")
                    } else {
                        balance -= amount
                        println("Transaction successful. Please take your card and money.")
                        print("Would you like to do another transaction? (yes/no): ")
                        val anotherTransaction = readlnOrNull() ?: ""
                        if (anotherTransaction.lowercase() != "yes") {
                            println("Thank you for using our ATM. Goodbye!")
                            break
                        }
                    }
                }
                "2" -> {
                    println("Your balance is: $balance VND")
                }
                "3" -> {
                    println("Thank you for using our ATM. Goodbye!")
                    break
                }
                else -> {
                    println("Invalid option. Please try again.")
                }
            }
        }
    }

}
fun main() {
    var choice : String?
    val task1Execute = Task1()
    val task2Execute = Task2()
    val task3Execute = Task3()
    val task4Execute = Task4()
    val task5Execute = Task5()
    val task6Execute = Task6()
    val task7Execute = Task7()
    val task8Execute = Task8()
    val task9Execute = Task9()
    val task10Execute = Task10()
    while(true) {
        println("-------Asm1-------")
        println("1. Task 1")
        println("2. Task 2")
        println("3. Task 3")
        println("4. Task 4")
        println("5. Task 5")
        println("6. Task 6")
        println("7. Task 7")
        println("8. Task 8")
        println("9. Task 9")
        println("10. Task 10")
        println("11. Exit")
        println("------------------")
        println("Chooice number : ")

        choice = readlnOrNull() ?: ""
        when(choice) {
            "1" -> task1Execute.task1()
            "2" -> task2Execute.task2()
            "3" -> task3Execute.task3()
            "4" -> task4Execute.task4()
            "5" -> task5Execute.task5()
            "6" -> task6Execute.task6()
            "7" -> task7Execute.task7()
            "8" -> task8Execute.task8()
            "9" -> task9Execute.task9()
            "10" -> task10Execute.task10()
            "11" -> {
                println("Exit")
                break
            }
        }
    }
}


