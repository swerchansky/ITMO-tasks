//import java.util.*
//import kotlin.math.max
//
//var size = 0
//var out = 0
//
//class BTree {
//    var right: BTree? = null
//    var left: BTree? = null
//    var parent: BTree? = null
//    var value: Int = 0
//    var key: Int? = null
//    var height: Int = 1
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
//fun add(node: BTree?, l: Int, x: Int, root: BTree?): BTree? {
//    var root = root
//    if (node == null) {
//        return null
//    }
//    if (node.value > l) {
//        root = add(node.left, l, x, root)
//    }
//    if (node.value < l) {
//        root = add(node.right, l, x, root)
//    }
//    if (node.value == l) {
//        if (node.key == null) {
//            node.key = x
//        } else {
//            if (size < l + 1) {
//                size++
//                root = insert(root, l + 1)
//            }
//            root = add(root, l + 1, node.key!!, root)
//            node.key = x
//        }
//    }
//    return root
//}
//
//fun toArray(node: BTree?) {
//    if (node == null) {
//        return
//    }
//    toArray(node.left)
//    if (out == 0) {
//        return
//    }
//    print("${if (node.key == null) (0) else (node.key)} ")
//    out--
//    toArray(node.right)
//}
//
//fun main() {
//    val time = System.currentTimeMillis()
//    val sc = Scanner(System.`in`)
//    var root: BTree? = null
//    val n = sc.nextInt()
//    val m = sc.nextInt()
//    size = m
//    var count = 0
//    for (i in 1..m) {
//        root = insert(root, i)
//    }
//    for (i in 1..n) {
//        val tmp = sc.nextInt()
//        count = max(tmp, count)
//        root = add(root, tmp, i, root)
//    }
//    var node = root
//    while (node!!.right != null && node.right!!.key != null) {
//        node = node.right
//        count = node!!.value
//    }
//    out = if (size == m) {
//        count
//    } else {
//        size
//    }
//    if (size == m) {
//        println(count)
//    } else {
//        println(size)
//    }
//    toArray(root)
//}