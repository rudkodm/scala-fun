package rank.tree

import scala.collection.mutable

/**
  * Created by rudkodm on 3/5/16.
  */
object TreeSwap extends App{
  val iterator = new Iterator[String](){
    override def hasNext: Boolean = ???
    override def next(): String = Console.in.readLine()
  }
  new RootTreeBuilder(RootNode()).process(iterator)
}

// ========================================

class RootTreeBuilder(root: RootNode) {
  val tree = root

  def process(commands: Iterator[String]) = {

    def applyNext(f: (Iterator[String] => Unit), n: Int): Any = {
      if (n != 0) {
        f(commands)
        applyNext(f, n - 1)
      }
    }

    val nodesNum = commands.next.toInt
    applyNext({ input =>
      val args = input.next.split(" ").map(_.toInt)
      tree
        .add(TreeNode(args(0)))
        .add(TreeNode(args(1)))
    }, nodesNum)

    val swapNum = commands.next.toInt
    applyNext({ input =>
      val k = input.next.toInt
      tree.swapOnEach(k)
    }, swapNum)
  }
}

// =============== MODEL ===================

class RootNode() extends TreeNode(Some(1), None, None) {
  val levelSetCache = mutable.Seq[Set[TreeNode]](Set(this))

  def levelNodesSet(k:Int):Set[TreeNode] = {
    if(k == 1) levelSetCache.head
    if(levelSetCache.size < k) {
      levelSetCache(k) = levelNodesSet(k-1).flatMap(node => node.child)
      levelSetCache(k)
    } else levelSetCache(k-1)
  }

  def add(node: TreeNode):RootNode = {
    // TODO
    Stream.from(1).takeWhile { i =>
      val freeNodes = levelNodesSet(i).collect {
        case tree @ TreeNode(_, _, _) if tree.child.size < 2 => true
      }
      true
    }
    this
  }

  def swapOnEach(k: Int):TreeNode = {
    this
  }
}
object RootNode {
  def apply() = new RootNode()
  def apply(left: TreeNode, right: TreeNode) = {
    val root = new RootNode()
    root.left = Some(left)
    root.right = Some(right)
    root
  }
}

// ===========================================

case class TreeNode(val data: Option[Int] = None, var left: Option[TreeNode] = None, var right: Option[TreeNode] = None) {

  def child:Seq[TreeNode] = left :: right :: Nil collect {case Some(x) => x}

  def swap(): TreeNode = {
    val tmp = left
    left = right
    right = tmp
    this
  }

  override def toString: String = s"${left.fold("")(_.toString)} ${data.getOrElse("")} ${right.fold("")(_.toString)}".trim
}

object TreeNode {
  def apply(data: Int, left: TreeNode, right: TreeNode) = new TreeNode(Some(data), Some(left), Some(right))

  def apply(data: Int) = {
    if (data == -1) new TreeNode()
    else new TreeNode(Some(data), None, None)
  }
}




