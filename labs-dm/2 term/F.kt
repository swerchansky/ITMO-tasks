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

var terminalFirst = setOf<Int>()
var terminalSecond = setOf<Int>()
var used: Array<Boolean> = arrayOf()
var pathsFirst: Array<MutableMap<Int, Int>> = arrayOf()
var pathsSecond: Array<MutableMap<Int, Int>> = arrayOf()
var same: Array<Int> = arrayOf()

fun check(first: Int, second: Int): Boolean {
    used[first] = true
    if (terminalFirst.contains(first) != terminalSecond.contains(second)) {
        return false
    }
    same[first] = second
    var res = true
    for (c in 0..25) {
        if (pathsFirst[first][c] == null || pathsFirst[first][c] == null) {
            if (pathsFirst[first][c] == null && pathsFirst[first][c] == null) {
                continue
            } else {
                return false
            }
        }
        if (pathsFirst[first][c] == 0 && pathsSecond[second][c] == 0) {
            continue
        }
        val pathF = pathsFirst[first][c]
        val pathS = pathsSecond[second][c]
        if (pathF == null || pathS == null) {
            if (pathF == null && pathS == null) {
                continue
            } else {
                return false
            }
        }
        if (cycle1(pathF) != cycle2(pathS)) {
            return false
        }
        res = if (used[pathF]) {
            res and (pathS == same[pathF])
        } else {
            res and check(pathF, pathS)
        }
    }
    return res
}

fun cycle1(pathF: Int?): Boolean {
    for (c in 0..25) {
        if (pathsFirst[pathF!!][c] == 0) {
            continue
        }
        if (pathsFirst[pathF][c] != pathF) {
            return false
        }
    }
    return true
}

fun cycle2(pathS: Int?): Boolean {
    for (c in 0..25) {
        if (pathsSecond[pathS!!][c] == 0) {
            continue
        }
        if (pathsSecond[pathS][c] != pathS) {
            return false
        }
    }
    return true
}

fun main() {
    val input = File("isomorphism.in")
    val output = File("isomorphism.out")
    lines = input.readLines()
    val (n, m, k) = readInts()
    var end = readIntArray()
    terminalFirst = end.toSet()
    pathsFirst = Array(n + 1) { mutableMapOf() }

    for (i in 0 until m) {
        val (from, toWhere, ch) = readStrings()
        pathsFirst[from.toInt()][ch.toCharArray()[0] - 'a'] = toWhere.toInt()
    }

    val (n2, m2, k2) = readInts()
    end = readIntArray()
    terminalSecond = end.toSet()
    pathsSecond = Array(n2 + 1) { mutableMapOf() }

    for (i in 0 until m2) {
        val (from, toWhere, ch) = readStrings()
        pathsSecond[from.toInt()][ch.toCharArray()[0] - 'a'] = toWhere.toInt()
    }
    lines = listOf()
    same = Array(n+1) { 0 }
    used = Array(n+1) { false }

//    println(check(pathsFirst, pathsSecond))

    if (check(1, 1)) output.writeText("YES") else output.writeText("NO")

}