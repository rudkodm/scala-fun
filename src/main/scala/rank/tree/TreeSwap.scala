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

class TreeBuilder(val tree:Node = Node(1)) {

  def process(commands: Iterator[String]) = {

    def applyNext(f: (Iterator[String] => Unit), n: Int): Any = {
      if (n == 0) return
      else {
        f(commands)
        applyNext(f, n - 1)
      };
    }

    val nodesNum = commands.next.toInt
    applyNext({ input =>
      val args = input.next.split(" ").map(_.toInt)
      tree.add(Node(args(0)))
      tree.add(Node(args(1)))
    }, nodesNum)

    val swapNum = commands.next.toInt
    applyNext({ input =>
      val k = input.next.toInt
      tree.swap(k)
    }, swapNum)
  }
}

case class Node(val data: Option[Int] = None, val left: Option[Node] = None, val right: Option[Node] = None) {
  def add(node: Node) = print(node.data.getOrElse("N") + " ")

  def swap(k: Int) = print(s"swap -> $k; ")

  override def toString: String = s"${left.fold("")(_.toString)} ${data.getOrElse("")} ${right.fold("")(_.toString)}".trim
}

object Node {
  def apply(data: Int, left: Node, right: Node) = new Node(Some(data), Some(left), Some(right))

  def apply(data: Int) = {
    if (data == -1) new Node()
    else new Node(Some(data), None, None)
  }
}
