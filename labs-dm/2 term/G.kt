import java.io.File
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

fun check(pathsFirst: Array<Array<Array<Int>>>, pathsSecond: Array<Array<Array<Int>>>): Boolean {
    val q: Queue<Pair<Int, Int>> = LinkedList()
    q.add(Pair(1, 1))
    while (q.isNotEmpty()) {
        val tmp = q.poll()
        val first = tmp.first
        val second = tmp.second
        val boolF = terminalFirst.contains(first)
        val boolS = terminalSecond.contains(second)
        if (boolF != boolS) {
            return false
        }
        used[first][second] = true
        for (c in 0..25) {
            if (!used[pathsFirst[first][c][0]][pathsSecond[second][c][0]])
                q.add(Pair(pathsFirst[first][c][0], pathsSecond[second][c][0]))
        }
    }
    return true
}

var end: IntArray = intArrayOf()
var terminalFirst = end.toSet()
var terminalSecond = end.toSet()
var used: Array<Array<Boolean>> = arrayOf()

fun main() {
    val input = File("equivalence.in")
    val output = File("equivalence.out")
    lines = input.readLines()
    val (n, m, k) = readInts()
    var end = readIntArray()
    terminalFirst = end.toSet()
    val pathsFirst: Array<Array<Array<Int>>> = Array(n + 1) { Array(26) { Array(1) { 0 } } }

    for (i in 0 until m) {
        val (from, toWhere, ch) = readStrings()
        val tmp = pathsFirst[from.toInt()][ch.toCharArray()[0] - 'a']
        tmp[0] = toWhere.toInt()
        pathsFirst[from.toInt()][ch.toCharArray()[0] - 'a'] = tmp
    }

    val (n2, m2, k2) = readInts()
    end = readIntArray()
    terminalSecond = end.toSet()
    val pathsSecond: Array<Array<Array<Int>>> = Array(n2 + 1) { Array(26) { Array(1) { 0 } } }

    for (i in 0 until m2) {
        val (from, toWhere, ch) = readStrings()
        val tmp = pathsSecond[from.toInt()][ch.toCharArray()[0] - 'a']
        tmp[0] = toWhere.toInt()
        pathsSecond[from.toInt()][ch.toCharArray()[0] - 'a'] = tmp
    }

    used = Array(n + 1) { Array(n2 + 1) { false } }

//    println(check(pathsFirst, pathsSecond))

    if (check(pathsFirst, pathsSecond)) output.writeText("YES") else output.writeText("NO")

}