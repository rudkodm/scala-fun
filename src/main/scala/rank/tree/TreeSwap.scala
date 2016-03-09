package rank.tree

/**
  * Created by rudkodm on 3/5/16.
  */
object TreeSwap extends App{
  val iterator = new Iterator[String](){
    override def hasNext: Boolean = ???
    override def next(): String = Console.in.readLine()
  }
  new TreeBuilder().process(iterator)
}

class TreeBuilder(val tree:TreeNode = TreeNode(1)) {

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
      tree.swap(k)
    }, swapNum)
  }
}

case class TreeNode(val data: Option[Int] = None, val left: Option[TreeNode] = None, val right: Option[TreeNode] = None) {
  def add(node: TreeNode):TreeNode = {
    print(node.data.getOrElse("N") + " ")
    this
  }

  def swap(k: Int):TreeNode = {
    print(s"swap -> $k; ")
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
