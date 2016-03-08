package rank.tree

import org.scalatest.{FlatSpec, Matchers}
import rank.tree.util.NodeUtil
import NodeUtil._
import InorderPrint.Console

/**
  * Created by rudkodm on 3/6/16.
  */
class InorderPrintSpeck extends FlatSpec with Matchers{

  val result: StringBuilder = new StringBuilder()
  val application = new InorderPrint()
  val console = new Console {
    override def print(str: String): Unit = result ++= str
  }

  "Application" should "print number sequence in right order" in {
    application.console = console
    application.Inorder(root2)
    result.result() should be ("1 5 4 3 6 2 ")
  }
}
