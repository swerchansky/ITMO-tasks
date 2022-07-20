import java.io.File

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

fun main() {
    curLine = 0
    lines = listOf<String>()
    val input = File("problem1.in")
    val output = File("problem1.out")
    lines = input.readLines()
    val str = readStr()
    val (n, m, k) = readInts()
    val end = readIntArray()
    val check: Array<Array<Int>> = Array(n+1) {Array(26) {0}}

    for (i in 0 until m) {
        val (from, toWhere, ch) = readStrings()
        check[from.toInt()][ch.toCharArray()[0] - 'a'] = toWhere.toInt()
    }

    var cur: Int = 1
    for (i in str.indices) {
        cur = check[cur][str[i] - 'a']
    }

    if (cur in end) {
        output.writeText("Accepts")
    } else {
        output.writeText("Rejects")
    }

