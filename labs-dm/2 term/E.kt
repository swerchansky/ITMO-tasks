import java.io.File
import java.math.BigInteger
import java.util.*

private fun readln() = lines[curLine++]
private fun readInt() = readln().toInt()
private fun readLong() = readln().toLong()
private fun readFloat() = readln().toFloat()
private fun readDouble() = readln().toDouble()
private fun readBigInt(radix: Int = 10) = readln().toBigInteger(radix)
private fun readBigDecimal() = readln().toBigDecimal()
private fun readStr() = readln()
private fun readStrings() = readln().split(" ").filter { it.isNotEmpty() }
private fun readInts() = readStrings().map { it.toInt() }
private fun readLongs() = readStrings().map { it.toLong() }
private fun readFloats() = readStrings().map { it.toFloat() }
private fun readDoubles() = readStrings().map { it.toDouble() }
private fun readIntArray() = readStrings().run { IntArray(size) { get(it).toInt() } }
private fun readLongArray() = readStrings().run { LongArray(size) { get(it).toLong() } }
private fun readFloatArray() = readStrings().run { FloatArray(size) { get(it).toFloat() } }
private fun readDoubleArray() = readStrings().run { DoubleArray(size) { get(it).toDouble() } }
private fun readLongArray2d(rows: Int, cols: Int) = Array(rows) { readLongArray().also { require(it.size == cols) } }
private fun readIntArray2d(rows: Int, cols: Int) = Array(rows) { readIntArray().also { require(it.size == cols) } }
private fun readFloatArray2d(rows: Int, cols: Int) = Array(rows) { readFloatArray().also { require(it.size == cols) } }
private fun readDoubleArray2d(rows: Int, cols: Int) = Array(rows) { readDoubleArray().also { require(it.size == cols) } }

var lines = listOf<String>()
var curLine = 0
var E = Array(0) { Array(0) { 0L } }
var check: Array<Array<Int>> = Array(0) { Array(0) { 0 } }
var end_NKA: Set<Int> = TreeSet()
var end = TreeSet<Int>()
var nums: MutableMap<TreeSet<Int>, Int> = mutableMapOf()

fun mult(first: Array<Array<Long>>, second: Array<Array<Long>>, n: Int): Array<Array<Long>> {
    val a: Array<Array<Long>> = Array(n) { Array(n) { 0 } }
    for (i in 0 until n) {
        for (j in 0 until n) {
            for (k in 0 until n) {
                a[i][j] += first[i][k] * second[k][j]
                a[i][j] %= 1000000007L
            }
        }
    }
    return a
}

fun fastPow(a: Array<Array<Long>>, n: Int, y: Int): Array<Array<Long>> {
    if (y == 0) {
        return E
    }
    if (y % 2 == 1) {
        return mult(fastPow(a, n, y - 1), a, n)
    } else {
        val tmp = fastPow(a, n, y / 2)
        return mult(tmp, tmp, n)
    }
}

fun tompson(path: Array<Array<TreeSet<Int>>>) {
    val p: Queue<TreeSet<Int>> = LinkedList()
    val tmp: TreeSet<Int> = TreeSet()
    tmp.add(1)
    nums[tmp] = 1
    check += Array(26) { 0 }
    p.add(tmp)
    var res: Array<Array<Long>> = Array(0) { Array(0) { 0 } }
    val was: MutableMap<TreeSet<Int>, Boolean> = mutableMapOf()
    was[tmp] = false
    if (end_NKA.contains(1)) {
        end.add(nums[tmp]!!)
    }

    while (p.isNotEmpty()) {
        val pd = p.poll()
        for (c in 0..25) {
            val new: TreeSet<Int> = TreeSet()
            for (values in pd) {
                if (path[values][c].isEmpty()) {
                    continue
                }
                for (eat in path[values][c]) {
                    new.add(eat)
                }
            }
            if (!was.containsKey(new) && new.isNotEmpty()) {
                was[new] = true
                p.add(new)
                check += Array(26) { 0 }
                nums[new] = check.size - 1
                for (values in new) {
                    if (end_NKA.contains(values)) {
                        end.add(nums[new]!!)
                    }
                }
            }
            if (new.isNotEmpty()) {
                check[nums[pd]!!][c] = nums[new]!!
            }
        }
    }
    println("ok")
}

fun main() {
    val input = File("problem5.in")
    val output = File("problem5.out")
    lines = input.readLines()
    val (n, m, k, l) = readInts()
    val tmp = readIntArray()
    end_NKA = tmp.toSet()
    val path: Array<Array<TreeSet<Int>>> = Array(n + 1) { Array(26) { TreeSet() } }
    check = Array(1) { Array(26) { 0 } }

    for (i in 0 until m) {
        val (from, toWhere, ch) = readStrings()
        path[from.toInt()][ch.toCharArray()[0] - 'a'].add(toWhere.toInt())
    }

    tompson(path)

    var res: Array<Array<Long>> = Array(check.size - 1) { Array(check.size - 1) { 0 } }
    for (i in check.indices) {
        for (c in 0..25) {
            if (check[i][c] == 0) {
                continue
            }
            res[i-1][check[i][c] - 1] = res[i-1][check[i][c] - 1] + 1
        }
    }

    E = Array(check.size - 1) { Array(check.size - 1) { 0 } }
    for (i in 0 until check.size - 1) {
        E[i][i] = 1
    }


    res = fastPow(res, check.size - 1, l)
    for (i in 0 until check.size - 1) {
        println(res[i].contentToString())
    }

    var ans: Long = 0
    for (el in end) {
        ans += res[0][el - 1]
        ans %= 1000000007L
    }

    println(ans)

    output.writeText(ans.toString())
}