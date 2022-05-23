//import java.util.*
//
//class BTree {
//    var right: BTree? = null
//    var left: BTree? = null
//    var parent: BTree? = null
//    var value: Int = 0
//}
//
//fun insert(node: BTree?, x: Int) {
//    var node = node
//    if (node == null) {
//        node = BTree()
//        node.value = x
//    }
//    if (node!!.value > x) {
//        if (node.left == null) {
//            node.left = BTree()
//            node.left!!.parent = node
//            node.left!!.value = x
//            return
//        } else {
//            insert(node.left!!, x)
//        }
//    }
//    if (node.value < x) {
//        if (node.right == null) {
//            node.right = BTree()
//            node.right!!.parent = node
//            node.right!!.value = x
//            return
//        } else {
//            insert(node.right!!, x)
//        }
//    }
//}
//
//fun subMin(node: BTree): Int {
//    var node = node
//    var min = node.value
//    while (node.left != null) {
//        min = node.left!!.value
//        node = node.left!!
//    }
//    return min
//}
//
//fun subMax(node: BTree): Int {
//    var node = node
//    var max = node.value
//    while (node.right != null) {
//        max = node.right!!.value
//        node = node.right!!
//    }
//    return max
//}
//
//fun delete(node: BTree?, x: Int): BTree? {
//    if (node == null) {
//        return node
//    }
//    if (x < node.value) {
//        node.left = delete(node.left, x)
//    } else if (x > node.value) {
//        node.right = delete(node.right, x)
//    } else {
//        if (node.left == null) {
//            if (node.right != null) {
//                node.right!!.parent = node.parent
//            }
//            return node.right
//        } else if (node.right == null) {
//            if (node.left != null) {
//                node.left!!.parent = node.parent
//            }
//            return node.left
//        } else {
//            node.value = subMin(node.right!!)
//            node.right = delete(node.right, node.value)
//        }
//    }
//    return node
//}
//
//fun exists(node: BTree?, x: Int): BTree? {
//    if (node == null) {
//        return node
//    }
//    if (node.value == x) {
//        return node
//    }
//    if (node.value < x) {
//        return exists(node.right, x)
//    }
//    return exists(node.left, x)
//}
//
//fun next(node: BTree?): Int {
//    if (node == null) {
//        return Int.MIN_VALUE
//    }
//    var node = node
//    if (node!!.right != null) {
//        return subMin(node.right!!)
//    }
//    while (node!!.parent != null) {
//        if (node.parent!!.left == node) {
//            return node.parent!!.value
//        }
//        node = node.parent
//    }
//    return Int.MIN_VALUE
//}
//
//fun prev(node: BTree?): Int {
//    if (node == null) {
//        return Int.MAX_VALUE
//    }
//    var node = node
//    if (node!!.left != null) {
//        return subMax(node.left!!)
//    }
//    while (node!!.parent != null) {
//        if (node.parent!!.right == node) {
//            return node.parent!!.value
//        }
//        node = node.parent
//    }
//    return Int.MAX_VALUE
//}
//
//fun main() {
//    val sc = Scanner(System.`in`)
//    var root: BTree? = null
//    while (sc.hasNext()) {
//        val operation: String = sc.next()
//        val x: Int = sc.nextInt()
//        if (operation == "insert") {
//            if (root == null) {
//                root = BTree()
//                root.value = x
//            } else {
//                insert(root, x)
//            }
//        } else if (operation == "delete") {
//            if (root != null) {
//                root = delete(root, x)
//            }
//        } else if (operation == "exists") {
//            if (exists(root, x) == null) {
//                println(false)
//            } else {
//                println(true)
//            }
//        } else if (operation == "next") {
//            val tmp = exists(root, x)
//            if (tmp == null) {
//                insert(root, x)
//                val ans = next(exists(root, x))
//                if (ans == Int.MIN_VALUE) {
//                    println("none")
//                } else {
//                    println(ans)
//                }
//                delete(root, x)
//            } else {
//                val ans = next(tmp)
//                if (ans == Int.MIN_VALUE) {
//                    println("none")
//                } else {
//                    println(ans)
//                }
//            }
//        } else if (operation == "prev") {
//            val tmp = exists(root, x)
//            if (tmp == null) {
//                insert(root, x)
//                val ans = prev(exists(root, x))
//                if (ans == Int.MAX_VALUE) {
//                    println("none")
//                } else {
//                    println(ans)
//                }
//                delete(root, x)
//            } else {
//                val ans = prev(tmp)
//                if (ans == Int.MAX_VALUE) {
//                    println("none")
//                } else {
//                    println(ans)
//                }
//            }
//        }
//    }
//}