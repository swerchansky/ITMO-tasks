//import java.util.*
//import kotlin.random.Random
//
//private fun readInt() = readln().toInt()
//private fun readStr() = readln()
//private fun readStrings() = readln().split(" ")
//private fun readInts() = readStrings().map { it.toInt() }
//
//data class Treap(
//    var x: Int = 0,
//    var size: Int = 1,
//    val y: Int = Random.nextInt(),
//    var left: Treap? = null,
//    var right: Treap? = null
//)
//
//fun split(root: Treap?, x: Int): Pair<Treap?, Treap?> {
//    root ?: return Pair(null, null)
//    return if ((root.left?.size ?: 0) >= x) {
//        val leftSplit = split(root.left, x)
//        root.left = leftSplit.second
//        update(root)
//        Pair(leftSplit.first, root)
//    } else {
//        val rightSplit = split(root.right, x - (root.left?.size ?: 0) - 1)
//        root.right = rightSplit.first
//        update(root)
//        Pair(root, rightSplit.second)
//    }
//}
//
//fun merge(left: Treap?, right: Treap?): Treap? {
//    left ?: return right
//    right ?: return left
//    return if (left.y > right.y) {
//        left.right = merge(left.right, right)
//        update(left)
//        left
//    } else {
//        right.left = merge(left, right.left)
//        update(right)
//        right
//    }
//}
//
//fun update(root: Treap?) {
//    root ?: return
//    root.size = (root.left?.size ?: 0) + (root.right?.size ?: 0) + 1
//}
//
//fun insert(root: Treap?, i: Int): Treap? {
//    size++
//    val tmp = split(root, i)
//    return merge(merge(tmp.first, Treap(value)), tmp.second)
//}
//
//fun delete(root: Treap?, x: Int): Treap? {
//    size--
//    val tmp = split(root, x)
//    val tmp2 = split(tmp.second, 1)
//    return merge(tmp.first, tmp2.second)
//}
//
//fun build(a: List<Int>, n: Int): Treap? {
//    var root: Treap? = null
//    for (i in 0 until n) {
//        value = a[i]
//        root = merge(root, Treap(value))
//    }
//    return root
//}
//
//var count = 0
//var size = 0
//
//fun toArray(root: Treap?) {
//    root ?: return
//    toArray(root.left)
//    res[count] = root.x
//    count++
//    toArray(root.right)
//}
//
//var value = 0
//var res: Array<Int> = Array(0) {0}
//
//fun main() {
//    val (n, m) = readInts()
//    var root: Treap? = null
//    val a: List<Int> = readStrings().map { it.toInt() }
////    val a: Array<Int> = Array(n) {0}
//    size = n
//    root = build(a, n)
//    for (i in 0 until m) {
//        val type = readStrings()
//        when (type[0]) {
//            "add" -> {
//                val tmp = type[1].toInt()
//                value = type[2].toInt()
//                root = insert(root, tmp)
//            }
//            "del" -> root = delete(root, type[1].toInt() - 1)
//        }
//    }
//    res = Array(size) {0}
//    toArray(root)
//    println(res.size)
//    println(res.joinToString(" "))
//}
//
