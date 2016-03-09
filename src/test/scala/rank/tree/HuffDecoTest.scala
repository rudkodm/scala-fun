package rank.tree

import org.scalatest.{FlatSpec, Matchers}
import rank.tree.HuffDeco.Console
import rank.tree.model.huff.Node
import rank.tree.util.NodeUtil._

/**
  * Created by rudkodm on 3/7/16.
  */
class HuffDecoTest extends FlatSpec with Matchers {
  /**
    * <pre>
    *         {ϕ,5}
    *      0 /    \ 1
    *    {ϕ,2}   {A,3}
    *   0/   \1
    * {B,1}  {C,1}
    * </pre>
    */
  val root: Node = H(0, 0, H(0, 0, H(1, 'B'), H(1, 'C')), H(3, 'A'));
  val code = "1001011"
  val expected = "ABACA"

  val builder: StringBuilder = new StringBuilder()
  val application = new HuffDeco()
  val console = new Console {
    override def print(str: String): Unit = builder ++= str
  }

  behavior of "HuffDecoTest"

  it should "decode" in {
    application.console = console;
    application.decode(code, root);
    builder.result() should equal(expected)
  }

}
