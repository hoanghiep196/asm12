package fa.training
class Task1 {
    private fun task1UsingFor() {
        val list = mutableListOf<String>()
        for (i in 1..200) {
            when {
                i % 3 == 0 && i % 4 != 0 -> list.add(i.toString())
            }
        }
        println(list.joinToString(", "))
    }
    private fun task1UsingWhile() {
        val list = mutableListOf<String>()
        var i = 1
        while (i <= 200) {
            when {
                i % 3 == 0 && i % 4 != 0 -> list.add(i.toString())
            }
            i++
        }
        println(list.joinToString(", "))
    }
    fun task1() {
        task1UsingFor()
        task1UsingWhile()
    }
}
class Task2 {
    private fun task2UsingFor(nums: IntArray): Boolean {
        val numMap = mutableMapOf<Int, Int>()
        for (num in nums) {
            numMap[num] = numMap.getOrDefault(num, 0) + 1
            if (numMap[num] == 3) {
                return true
            }
        }
        return false
    }
    private fun task2UsingWhile(nums: IntArray): Boolean {
        val numMap = mutableMapOf<Int, Int>()
        var i = 0
        while (i < nums.size) {
            val num = nums[i]
            numMap[num] = numMap.getOrDefault(num, 0) + 1
            if (numMap[num] == 3) {
                return true
            }
            i++
        }
        return false
    }
    fun task2() {
        val nums = intArrayOf(4, 2, 3, 4, 2, 4, 4, 1)
        println(task2UsingFor(nums))
        println(task2UsingWhile(nums))
    }
}
class Task3 {
    private fun task3UsingFor(nums: IntArray): IntArray {
        var minSum = Int.MAX_VALUE
        var minStart = 0
        var minEnd = 0

        for (start in nums.indices) {
            var sum = 0
            for (end in start until nums.size) {
                sum += nums[end]
                if (sum < minSum) {
                    minSum = sum
                    minStart = start
                    minEnd = end
                }
            }
        }
        return nums.sliceArray(minStart..minEnd)
    }
    private fun task3UsingWhile(nums: IntArray): IntArray {
        var minSum = Int.MAX_VALUE
        var minStart = 0
        var minEnd = 0

        var start = 0
        while (start < nums.size) {
            var sum = 0
            var end = start
            while (end < nums.size) {
                sum += nums[end]
                if (sum < minSum) {
                    minSum = sum
                    minStart = start
                    minEnd = end
                }
                end++
            }
            start++
        }
        return nums.sliceArray(minStart..minEnd)
    }
    fun task3() {
        val nums = intArrayOf(-1, -4, 1, 2, 0, 4, 6, -2)
        println(task3UsingFor(nums).joinToString(", "))
        println(task3UsingWhile(nums).joinToString(", "))
    }
}
class Task4 {
    private fun task4UsingFor(nums: IntArray): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()
        nums.sort()

        for (i in nums.indices) {
            var left = i + 1
            var right = nums.size - 1

            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]
                when {
                    sum == 2 -> {
                        result.add(listOf(nums[i], nums[left], nums[right]))
                        left++
                        right--
                    }
                    sum < 2 -> left++
                    else -> right--
                }
            }
        }
        return result.toList()
    }
    private fun task4UsingWhile(nums: IntArray): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()
        nums.sort()

        var i = 0
        while (i < nums.size) {
            var left = i + 1
            var right = nums.size - 1

            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]
                when {
                    sum == 2 -> {
                        result.add(listOf(nums[i], nums[left], nums[right]))
                        left++
                        right--
                    }
                    sum < 2 -> left++
                    else -> right--
                }
            }
            i++
        }
        return result.toList()
    }
    fun task4() {
        val nums = intArrayOf(0, 1, 4, 1, -2, 1, 9, -1, 0)
        println(task4UsingFor(nums))
        println(task4UsingWhile(nums))
    }
}
@Suppress("SameParameterValue")
class Task5 {
    private fun task5UsingFor(nums: IntArray, target: Int): Int {
        var maxLength = 0
        var currentSum = 0
        var start = 0

        for (end in nums.indices) {
            currentSum += nums[end]
            while (currentSum > target) {
                currentSum -= nums[start]
                start++
            }
            maxLength = maxOf(maxLength, end - start + 1)
        }
        return maxLength
    }
    private fun task5UsingWhile(nums: IntArray, target: Int): Int {
        var maxLength = 0
        var currentSum = 0
        var start = 0
        var end = 0

        while (end < nums.size) {
            currentSum += nums[end]
            while (currentSum > target) {
                currentSum -= nums[start]
                start++
            }
            maxLength = maxOf(maxLength, end - start + 1)
            end++
        }
        return maxLength
    }
    fun task5() {
        val nums = intArrayOf(1, 2, 3, 4, 5, -1, 4, 9, 1)
        val target = 10
        println(task5UsingFor(nums, target))
        println(task5UsingWhile(nums, target))
    }
}
class Task6 {
    private fun task6UsingFor(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        nums.sort()

        for (i in 0 until nums.size - 2) {
            for (j in i + 1 until nums.size - 1) {
                for (k in j + 1 until nums.size) {
                    if (nums[i] + nums[j] > nums[k] && nums[i] + nums[k] > nums[j] && nums[j] + nums[k] > nums[i]) {
                        result.add(listOf(nums[i], nums[j], nums[k]))
                        break
                    }
                }
            }
        }
        return result
    }
    private fun task6UsingWhile(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        nums.sort()

        var i = 0
        while (i < nums.size - 2) {
            var j = i + 1
            while (j < nums.size - 1) {
                var k = j + 1
                while (k < nums.size) {
                    if (nums[i] + nums[j] > nums[k] && nums[i] + nums[k] > nums[j] && nums[j] + nums[k] > nums[i]) {
                        result.add(listOf(nums[i], nums[j], nums[k]))
                        break
                    }
                    k++
                }
                j++
            }
            i++
        }
        return result
    }
    fun task6() {
        val nums = intArrayOf(2, 2, 3, -3, 4, 4, 9, 1)
        println(task6UsingFor(nums))
        println(task6UsingWhile(nums))
    }
}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    var choice : String?
    val task1Execute = Task1()
    val task2Execute = Task2()
    val task3Execute = Task3()
    val task4Execute = Task4()
    val task5Execute = Task5()
    val task6Execute = Task6()
    while(true) {
        println("-------Asm1-------")
        println("1. Task 1")
        println("2. Task 2")
        println("3. Task 3")
        println("4. Task 4")
        println("5. Task 5")
        println("6. Task 6")
        println("7. Exit")
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
            "7" -> {
                println("Exit")
                break
            }
        }
    }
}