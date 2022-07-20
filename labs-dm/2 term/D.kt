import java.io.File
import java.math.BigInteger

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
var E = Array (0) { Array(0) {0L} }

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

fun main() {
    val input = File("problem4.in")
    val output = File("problem4.out")
    lines = input.readLines()
    val (n, m, k, l) = readInts()
    val end = readIntArray()
    var check: Array<Array<Long>> = Array(n) { Array(n) { 0 } }

    for (i in 0 until m) {
        val (from, toWhere, ch) = readStrings()
        check[from.toInt() - 1][toWhere.toInt() - 1] += 1L
    }

    E = Array (n) {Array(n) {0} }
    for (i in 0 until n) {
        E[i][i] = 1
    }

    check = fastPow(check, n, l)
    for (i in 0 until n) {
        println(check[i].contentToString())
    }

    var ans: Long = 0
    for (el in end) {
        ans += check[0][el-1]
        ans %= 1000000007L
    }

    output.writeText(ans.toString())
}