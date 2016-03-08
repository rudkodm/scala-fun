package rank.tree

import org.scalatest.{FlatSpec, Matchers}
import rank.tree.util.NodeUtil
import NodeUtil._
import PostorderPrint.Console

/**
  * Created by rudkodm on 3/6/16.
  */
class PostorderPrintSpeck extends FlatSpec with Matchers{

  val result: StringBuilder = new StringBuilder()
  val application = new PostorderPrint()
  val console = new Console {
    override def print(str: String): Unit = result ++= str
  }

  "Application" should "print number sequence in right order" in {
    application.console = console
    application.Postorder(root1)
    result.result() should be ("9 1 4 5 6 8 7 2 3 ")
  }
}
