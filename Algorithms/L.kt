//import java.io.BufferedReader
//import java.io.IOException
//import java.io.InputStreamReader
//import java.util.*
//
//
//class Treap(
//    var x: Int,
//    var y: Int,
//    var number: Int,
//    var left: Treap? = null,
//    var right: Treap? = null,
//    var parent: Treap? = null
//)
//
//var n: Int = 0
//var x: Array<Int> = Array(n) { 0 }
//var y: Array<Int> = Array(n) { 0 }
//var numbers: Array<Treap?> = Array(n) { null }
//var origin: Array<Treap?> = Array(n) { null }
//
//fun build(): Treap {
//    var root = numbers[0]
//    var last = root
//
//    for (i in 1 until n) {
//        if (last != null) {
//            if (last.y < y[i]) {
//                last.right = numbers[i]
//                last.right!!.parent = last
//                last = last.right!!
//            } else {
//                var cur = last
//                while (cur!!.parent != null && cur.y > y[i]) {
//                    cur = cur.parent!!
//                }
//                if (cur.y > numbers[i]!!.y) {
//                    last = numbers[i]
//                    last!!.left = cur
//                    cur.parent = last
//                } else {
//                    last = numbers[i]
//                    last!!.left = cur.right
//                    cur.right!!.parent = last
//                    last.parent = cur
//                    cur.right = last
//                }
//            }
//        }
//    }
//
//    while (last!!.parent != null) {
//        last = last.parent!!
//    }
//
//    root = last
//    return root
//}
//
//object MyScanner {
//    var br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))
//    var st: StringTokenizer? = null
//    operator fun next(): String {
//        while (st == null || !st!!.hasMoreElements()) {
//            try {
//                st = StringTokenizer(br.readLine())
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//        return st!!.nextToken()
//    }
//
//    fun nextInt(): Int {
//        return next().toInt()
//    }
//
//    fun nextLong(): Long {
//        return next().toLong()
//    }
//
//    fun nextDouble(): Double {
//        return next().toDouble()
//    }
//
//    fun nextLine(): String {
//        var str = ""
//        try {
//            str = br.readLine()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//        return str
//    }
//
//}
//
//fun main() {
//    val sc = MyScanner
//    n = sc.nextInt()
//    x = Array(n) { 0 }
//    y = Array(n) { 0 }
//    numbers = Array(n) { Treap(0, 0, 0) }
//    origin = Array(n) { Treap(0, 0, 0) }
//    for (i in 1..n) {
//        val a = sc.nextInt()
//        val b = sc.nextInt()
//        val node = Treap(a, b, i)
//        numbers[i - 1] = node
//        origin[i - 1] = node
//    }
//
//    numbers.sortWith(compareBy { it!!.x })
//
//    for (i in 0 until n) {
//        x[i] = numbers[i]!!.x
//        y[i] = numbers[i]!!.y
//    }
//
//    var res: Array<String?> = Array(n) { null }
//    var root = build()
//    println("YES")
//    for (i in 0 until n) {
//        val node = origin[i]
//        res[i] = "${if (node!!.parent == null) 0 else node.parent!!.number} " +
//                "${if (node.left == null) 0 else node.left!!.number} " +
//                "${if (node.right == null) 0 else node.right!!.number}"
//    }
//    println(res.joinToString("\n"))
//}
//
//
