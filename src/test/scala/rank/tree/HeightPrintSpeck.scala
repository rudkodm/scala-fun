package rank.tree

import org.scalatest.{FlatSpec, Matchers}
import HeightPrint.Console
import rank.tree.util.NodeUtil._

/**
  * Created by rudkodm on 3/6/16.
  */
class HeightPrintSpeck extends FlatSpec with Matchers{

  var result: Object = null
  val application = new HeightPrint()
  val console = new Console {
    override def print(o: Object): Unit = result = o
  }

  "Application" should "print max Insert of the tree" in {
    application.console = console
    application.height(root3)
    result should be (Integer.valueOf(4))
  }
}
