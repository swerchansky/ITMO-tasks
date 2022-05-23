//import java.util.*
//import kotlin.math.max
//
//class BTree {
//    var right: BTree? = null
//    var left: BTree? = null
//    var parent: BTree? = null
//    var value: Int = 0
//    var height: Int = 1
//    var rank: Int = 0
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
//fun recalc(node: BTree?): Int {
//    if (node == null) {
//        return 0
//    }
//    return recalc(node.left) + recalc(node.right) + 1
//}
//
//fun rightRotate(y: BTree?): BTree? {
//    val x = y?.left
//    val T2 = x?.right
//
//    x?.right = y
//    y?.left = T2
//
//    y?.height = max(height(y?.left), height(y?.right)) + 1
//    x?.height = max(height(x?.left), height(x?.right)) + 1
//
//    val tmp = y?.parent
//    y?.parent = x
//    x?.parent = tmp
//    T2?.parent = y
//
//    y?.rank = recalc(T2)
//
//    return x
//}
//
//fun leftRotate(x: BTree?): BTree? {
//    val y = x?.right;
//    val T2 = y?.left;
//
//    y?.left = x;
//    x?.right = T2;
//
//    x?.height = max(height(x?.left), height(x?.right)) + 1;
//    y?.height = max(height(y?.left), height(y?.right)) + 1;
//
//    val tmp = x?.parent
//    x?.parent = y
//    y?.parent = tmp
//    T2?.parent = x
//
//    y?.rank = recalc(x)
//
//    return y;
//}
//
//fun insert(node: BTree?, x: Int): BTree? {
//    var node = node
//    if (node == null) {
//        node = BTree()
//        node.value = x
//        return node
//    }
//    if (node.value > x) {
//        node.left = insert(node.left, x)
//        node.left!!.parent = node
//        node.rank++
//    }
//    if (node.value < x) {
//        node.right = insert(node.right, x)
//        node.right!!.parent = node
//    }
//
//    node.height = 1 + max(height(node.left), height(node.right))
//
//    val balance = getBalance(node)
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
//fun subMin(node: BTree): BTree {
//    var node = node
//    while (node.left != null) {
//        node = node.left!!
//    }
//    return node
//}
//
//fun delete(node: BTree?, x: Int): BTree? {
//    var node = node
//    if (node == null) {
//        return node
//    }
//    if (x < node.value) {
//        node.left = delete(node.left, x)
//        node.rank--
//    } else if (x > node.value) {
//        node.right = delete(node.right, x)
//    } else {
//        if (node.left == null || node.right == null) {
//            var tmp: BTree?
//            tmp = if (node.left == null) {
//                node.right
//            } else {
//                node.left
//            }
//            if (tmp == null) {
//                tmp = node
//                node = null
//            } else {
//                tmp.parent = node.parent
//                node = tmp
//            }
//        } else {
//            val tmp = subMin(node.right!!)
//            node.value = tmp.value
//            node.right = delete(node.right, node.value)
//        }
//    }
//
//    if (node == null) {
//        return node
//    }
//
//    node.height = max(height(node.left), height(node.right)) + 1
//
//    val balance = getBalance(node)
//
//    if (balance > 1 && getBalance(node.left) >= 0) {
//        return rightRotate(node)
//    }
//
//    if (balance > 1 && getBalance(node.left) < 0) {
//        node.left = leftRotate(node.left)
//        return rightRotate(node)
//    }
//
//    if (balance < -1 && getBalance(node.right) <= 0) {
//        return leftRotate(node)
//    }
//
//    if (balance < -1 && getBalance(node.right) > 0) {
//        node.right = rightRotate(node.right)
//        return leftRotate(node)
//    }
//
//    return node
//}
//
//fun kth(node: BTree?, x: Int): Int? {
//    var node = node
//    if (node == null) {
//        return null
//    }
//    val count = node.rank + 1
//    if (count == x) {
//        return node.value
//    }
//    if (count > x) {
//        return kth(node.left, x)
//    }
//    return kth(node.right, x - count)
//}
//
//fun main() {
//    val sc = Scanner(System.`in`)
//    var root: BTree? = null
//    val n = sc.nextInt()
//    var count = 0
//    while (sc.hasNext()) {
//        val operation: Int = sc.nextInt()
//        val x: Int = sc.nextInt()
//        when (operation) {
//            1 -> {
//                count++
//                if (root == null) {
//                    root = BTree()
//                    root.value = x
//                } else {
//                    root = insert(root, x)
//                }
//            }
//            -1 -> {
//                count--
//                if (root != null) {
//                    root = delete(root, x)
//                }
//            }
//            0 -> {
//                println(kth(root, count-x+1))
//            }
//        }
//    }
//}