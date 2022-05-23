import kotlin.random.Random

private fun readInt() = readln().toInt()
private fun readStr() = readln().toString()
private fun readStrings() = readln().split(" ")
private fun readInts() = readStrings().map { it.toInt() }

data class Treap(
   var x: Int = 0,
   var empty: Boolean = true,
   var size: Int = 1,
   val y: Int = Random.nextInt(),
   var left: Treap? = null,
   var right: Treap? = null
)

fun split(root: Treap?, x: Int): Pair<Treap?, Treap?> {
   root ?: return Pair(null, null)
   return if ((root.left?.size ?: 0) >= x) {
       val leftSplit = split(root.left, x)
       root.left = leftSplit.second
       update(root)
       Pair(leftSplit.first, root)
   } else {
       val rightSplit = split(root.right, x - (root.left?.size ?: 0) - 1)
       root.right = rightSplit.first
       update(root)
       Pair(root, rightSplit.second)
   }
}

fun merge(left: Treap?, right: Treap?): Treap? {
   left ?: return right
   right ?: return left
   return if (left.y > right.y) {
       left.right = merge(left.right, right)
       update(left)
       left
   } else {
       right.left = merge(left, right.left)
       update(right)
       right
   }
}

fun update(root: Treap?) {
   root ?: return
   root.size = (root.left?.size ?: 0) + (root.right?.size ?: 0) + 1
   root.empty = (root.left?.empty ?: false) || (root.right?.empty ?: false) || root.x == 0

}

fun insert(root: Treap?, i: Int): Treap? {
   size++
   val tmp = split(root, i)
   val tmp2 = isEmpty(tmp.second)
   var r: Treap? = tmp.second
   if (tmp2.first?.x == 0) {
       r = delete(tmp.second, tmp2.second)
   }
   return merge(merge(tmp.first, Treap(++value, false)), r)
}

fun delete(root: Treap?, x: Int): Treap? {
   size--
   val tmp = split(root, x)
   val tmp2 = split(tmp.second, 1)
   return merge(tmp.first, tmp2.second)
}

fun isEmpty(root: Treap?): Pair<Treap?, Int> {
   var cur = root
   var i = cur?.left?.size ?: 0

   while (cur?.empty == true) {
       if (cur.left != null && cur.left?.empty == true) {
           cur = cur.left
           i = i - (cur?.right?.size ?: 0) - 1
       } else if (cur.x == 0) {
           break;
       } else {
           cur = cur.right
           i += (cur?.left?.size ?: 0) + 1
       }
   }

   return Pair(cur, i)
}

fun build(m: Int): Treap? {
   var root: Treap? = null
   for (i in 0 until m) {
       root = merge(Treap(), root)
   }
   return root
}

var value = 0
var count = 0
var size = 0
var res: Array<Int> = Array(0) {0}

fun toArray(root: Treap?) {
   root ?: return
   toArray(root.left)
   res[count] = root.x
   count++
   toArray(root.right)
}

fun main() {
   val (n, m) = readInts()
   val a: List<Int> = readStrings().map { it.toInt() }
   var root: Treap? = null
   size = m
   root = build(m)
   for (i in 0 until n) {
       root = insert(root, a[i] - 1)
   }
   res = Array(size) {0}
   toArray(root)
   res = res.dropLastWhile { it == 0 }.toTypedArray()
   println(res.size)
   println(res.joinToString(" "))
}

