package rank.tree

import org.scalatest.{FlatSpec, Matchers}
import LevelOrderPrint.Console
import rank.tree.util.NodeUtil
import NodeUtil._

/**
  * Created by rudkodm on 3/6/16.
  */
class LevelOrderPrintSpeck extends FlatSpec with Matchers{

  val result: StringBuilder = new StringBuilder()
  val application = new LevelOrderPrint()
  val console = new Console {
    override def print(str: String): Unit = result ++= str
  }

  "Application" should "print number sequence in right order" in {
    application.console = console
    application.LevelOrder(root2)
    result.result() should be ("3 5 2 1 4 6 ")
  }
}
