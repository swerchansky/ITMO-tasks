import java.util.*
import kotlin.random.Random

private fun readInt() = readln().toInt()
private fun readStr() = readln()
private fun readStrings() = readln().split(" ")
private fun readInts() = readStrings().map { it.toInt() }

data class Treap(
   var x: Int = 0,
   var size: Int = 1,
   val y: Int = Random.nextInt(),
   var left: Treap? = null,
   var right: Treap? = null,
   var fl: Boolean = false
)

fun push(root: Treap?) {
   root ?: return
   if (!root.fl) return

   val tmp = root.left
   root.left = root.right
   root.right = tmp

   root.fl = false
   root.left?.fl = root.left?.fl?.xor(true) == true
   root.right?.fl = root.right?.fl?.xor(true) == true
}

fun split(root: Treap?, x: Int): Pair<Treap?, Treap?> {
   root ?: return Pair(null, null)
   push(root)
   return if ((root.left?.size ?: 0) >= x) {
       val leftSplit = split(root.left, x)
       root.left = leftSplit.second
       push(root)
       update(root)
       Pair(leftSplit.first, root)
   } else {
       val rightSplit = split(root.right, x - (root.left?.size ?: 0) - 1)
       root.right = rightSplit.first
       push(root)
       update(root)
       Pair(root, rightSplit.second)
   }
}

fun merge(left: Treap?, right: Treap?): Treap? {
   left ?: return right
   right ?: return left
   push(left)
   push(right)
   return if (left.y > right.y) {
       left.right = merge(left.right, right)
       push(left)
       update(left)
       left
   } else {
       right.left = merge(left, right.left)
       push(right)
       update(right)
       right
   }
}

fun update(root: Treap?) {
   root ?: return
   root.size = (root.left?.size ?: 0) + (root.right?.size ?: 0) + 1
}

fun insert(root: Treap?, i: Int): Treap? {
   size++
   val tmp = split(root, i)
   return merge(merge(tmp.first, Treap(value)), tmp.second)
}

fun delete(root: Treap?, x: Int): Treap? {
   size--
   val tmp = split(root, x)
   val tmp2 = split(tmp.second, 1)
   return merge(tmp.first, tmp2.second)
}

fun build(n: Int): Treap? {
   var root: Treap? = null
   for (i in 0 until n) {
       root = merge(root, Treap(++value))
   }
   return root
}

var count = 0
var size = 0

fun toArray(root: Treap?) {
   root ?: return
   push(root)
   toArray(root.left)
   res[count] = root.x
   count++
   toArray(root.right)
}

fun reverse(root: Treap?, left: Int, right: Int): Treap? {
   val l = split(root, left)
   val r = split(l.second, right - left)
   r.first?.fl = r.first?.fl?.xor(true) == true

//    println("ok")
   return merge(merge(l.first, r.first), r.second)
}

var value = 0
var res: Array<Int> = Array(0) { 0 }

fun main() {
   val (n, m) = readInts()
   var root: Treap? = null
   size = n
   root = build(n)
   for (i in 0 until m) {
       val (l, r) = readInts()
       root = reverse(root, l - 1, r)
   }
   res = Array(size) { 0 }
   toArray(root)
//    println(res.size)
   println(res.joinToString(" "))
}

