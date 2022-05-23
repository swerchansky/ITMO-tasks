import java.util.*
import kotlin.system.exitProcess

fun checkBST(node: Int, a: Array<Array<Int>>, min: Int, max: Int): Boolean {
    if (node == -1) {
        return true
    }
    if (a[node][0] < min || a[node][0] > max) {
        println("NO")
        exitProcess(0)
    }
    return checkBST(a[node][1], a, min, a[node][0]-1) && checkBST(a[node][2], a, a[node][0] + 1, max)
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    val a: Array<Array<Int>> = Array(n + 1) { Array(3) { 0 } }
    for (i in 1..n) {
        for (j in 0 until 3) {
            a[i][j] = sc.nextInt()
        }
    }
    val root = sc.nextInt()
    if (checkBST(root, a, Int.MIN_VALUE, Int.MAX_VALUE)) {
        println("YES");
    }
}