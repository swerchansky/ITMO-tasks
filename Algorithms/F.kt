//import java.util.*
//import kotlin.math.max
//import kotlin.math.min
//
//class BTree {
//    var right: BTree? = null
//    var left: BTree? = null
//    var value: Int = 0
//    var height: Int = 1
//    var min: Int = Int.MIN_VALUE
//    var max: Int = Int.MAX_VALUE
//    var sum: Long = 0
//}
//
//fun getsum(root: BTree?): Long {
//    if (root == null) {
//        return 0
//    } else {
//        return root.sum
//    }
//}
//
//fun getmax(root: BTree?): Int {
//    if (root == null) {
//        return Int.MIN_VALUE
//    } else {
//        return root.max
//    }
//}
//
//fun getmin(root: BTree?): Int {
//    if (root == null) {
//        return Int.MAX_VALUE
//    } else {
//        return root.min
//    }
//}
//
//fun recalc(root: BTree?) {
//    if (root == null) {
//        return
//    }
//    root.min = min(min(getmin(root.left), getmin(root.right)), root.value)
//    root.max = max(max(getmax(root.left), getmax(root.right)), root.value)
//    root.sum = getsum(root.left) + getsum(root.right)
//    if (root.left != null) root.sum += root.left!!.value
//    if (root.right != null) root.sum += root.right!!.value
//}
//
//fun height(node: BTree?): Int {
//    if (node == null) {
//        return 0
//    }
//    return node.height
//}
//
//fun getBalance(node: BTree?): Int {
//    if (node == null) {
//        return 0
//    }
//    return height(node.left) - height(node.right)
//}
//
//fun rightRotate(y: BTree?): BTree? {
////    val x = y?.left
////    val T2 = x?.right
////
////    x?.right = y
////    y?.left = T2
////
////    y?.height = max(height(y?.left), height(y?.right)) + 1
////    x?.height = max(height(x?.left), height(x?.right)) + 1
////
////    recalc(x?.left?.right)
////    recalc(x?.left?.left)
////    recalc(x?.right?.left)
////    recalc(x?.right?.right)
////    recalc(x?.right)
////    recalc(x?.left)
////    recalc(x)
////    return x
//
//    val left = y?.right
//    y?.left = left?.right
//
//    y?.height = max(height(y?.left), height(y?.right)) + 1;
//    left?.height = max(height(left?.left), height(left?.right)) + 1;
//
//    recalc(y)
//    recalc(left)
//
//    return left;
//}
//
//fun leftRotate(x: BTree?): BTree? {
////    val y = x?.right;
////    val T2 = y?.left;
////
////    y?.left = x;
////    x?.right = T2;
////
////    x?.height = max(height(x?.left), height(x?.right)) + 1;
////    y?.height = max(height(y?.left), height(y?.right)) + 1;
//
////    recalc(y?.right?.right)
////    recalc(y?.right?.left)
////    recalc(y?.left?.right)
////    recalc(y?.left?.left)
////    recalc(y?.left)
////    recalc(y?.right)
////    recalc(y)
//
//    val right = x?.right
//    x?.right = right?.left
//
//    x?.height = max(height(x?.left), height(x?.right)) + 1;
//    right?.height = max(height(right?.left), height(right?.right)) + 1;
//
//    recalc(x)
//    recalc(right)
//
//    return right;
//}
//
//fun insert(node: BTree?, x: Int): BTree? {
//    var node = node
//    if (node == null) {
//        node = BTree()
//        node.value = x
//        node.min = x
//        node.max = x
//        node.sum = 0
//        return node
//    }
//    if (node.value > x) {
//        node.left = insert(node.left, x)
//    }
//    if (node.value < x) {
//        node.right = insert(node.right, x)
//    }
//
//
//    node.height = 1 + max(height(node.left), height(node.right))
//
//    val balance = getBalance(node)
//
//    recalc(node)
//
//    if (balance > 1 && x < node.left!!.value) {
//        return rightRotate(node)
//    }
//
//    if (balance < -1 && x > node.right!!.value) {
//        return leftRotate(node)
//    }
//
//    if (balance > 1 && x > node.left!!.value) {
//        node.left = leftRotate(node.left)
//        return rightRotate(node)
//    }
//
//    if (balance < -1 && x < node.right!!.value) {
//        node.right = rightRotate(node.right)
//        return leftRotate(node)
//    }
//
//    return node
//}
//
//
//fun sum(root: BTree?, l: Int, r: Int): Long {
//    if (root == null) {
//        return 0
//    }
//
//    if (root.value > r) {
//        return sum(root.left, l, r)
//    }
//
//    if (root.value < l) {
//        return sum(root.right, l, r)
//    }
//
//    if (root.left == null && root.right == null) {
//        return root.value.toLong()
//    }
//
//    if (root.min >= l && root.max <= r) {
//        return root.sum + root.value
//    }
//
//    return sum(root.left, l, r) + sum(root.right, l, r) + root.value
//}
//
//fun main() {
//    val sc = Scanner(System.`in`)
//    val n = sc.nextInt()
//    var treap: BTree? = null
//    var last = ""
//    var num: Long = 0
//    val set = TreeSet<Int>()
//    for (i in 0 until n) {
//        val type = sc.next()
//        when (type) {
//            "+" -> {
//                var tmp = sc.nextInt()
//                treap = if (last == "?") {
//                    tmp = ((tmp + num) % 1000000000).toInt()
//                    if (set.contains(tmp)) continue
//                    set.add(tmp)
//                    insert(treap, tmp)
//                } else {
//                    if (set.contains(tmp)) continue
//                    set.add(tmp)
//                    insert(treap, tmp)
//                }
//                last = type
//            }
//            "?" -> {
//                val tmp = sum(treap, sc.nextInt(), sc.nextInt())
//                num = tmp
//                last = type
//                println(num)
//            }
//        }
//    }
//}