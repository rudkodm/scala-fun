package rank.tree



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
  import scala.collection.mutable

  private val cache = mutable.ArrayBuffer[Set[TreeNode]](Set(), Set(this))
  lazy val nodes = Stream.from(1).flatMap(nodesOnLevel)

  def height: Int = {
    def loop(t: TreeNode): Int = t match {
      case TreeNode(None, _, _) => 1
      case n@TreeNode(Some(_), _, _) => Seq(loop(n.left.getOrElse(TreeNode())), loop(n.right.getOrElse(TreeNode()))).max + 1
      case _ => 0
    }
    loop(this) - 1
  }

  def nodesOnLevel(k:Int):Set[TreeNode] = {
    if(k >= cache.size) cache += nodesOnLevel(k - 1).flatMap(node => node.children)
    cache(k)
  }

  def add(node: TreeNode):RootNode = {
    val addTo = (to:TreeNode) => if(to.left.isEmpty) to.left = Some(node) else to.right = Some(node)
    addTo(nodes.filter(_.hasEmptyChild).head)
    this
  }

  def swapOnEach(k: Int):TreeNode = {
    val levels = (1 to height).map(_ * k)
    levels.foreach {i => nodesOnLevel(i).foreach(_.swap)}
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

  def children:Seq[TreeNode] = left :: right :: Nil collect {case Some(x) => x}

  def hasEmptyChild = left.isEmpty || right.isEmpty

  def swap = {
    val tmp = left
    left = right
    right = tmp
    this
  }

  override def hashCode(): Int = this.data.hashCode()
  override def equals(that: scala.Any): Boolean = that.isInstanceOf[TreeNode] && that.asInstanceOf[TreeNode].data == this.data
  override def toString: String = s"${left.fold("")(_.toString)} ${data.getOrElse("")} ${right.fold("")(_.toString)}".trim
}

object TreeNode {
  def apply(data: Int, left: TreeNode, right: TreeNode) = new TreeNode(Some(data), Some(left), Some(right))

  def apply(data: Int) = {
    if (data == -1) new TreeNode()
    else new TreeNode(Some(data), None, None)
  }
}




