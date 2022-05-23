import java.util.*

fun main() {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    var a: Array<Int> = Array(n) {0}
    for (i in 0 until n) {
        a[i] = sc.nextInt()
    }
    a.sort()
    println(n)
    for (i in 0 until n) {
        if (i == n-1) {
            println("${a[i]} -1 -1")
        } else {
            println("${a[i]} -1 ${i + 2}")
        }
    }
    println(1)
}