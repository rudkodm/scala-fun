package rank.tree.model

import org.scalatest.{FlatSpec, Matchers}
import rank.tree.HeightPrint
import HeightPrint.Console
import rank.tree.util.NodeUtil
import rank.tree.util.NodeUtil._

/**
  * Created by rudkodm on 3/6/16.
  */
class NodeSpeck extends FlatSpec with Matchers{

  "Node with 1 element" should "should be equal to node with same data" in {
    N(1) should equal(N(1))
    N(1).hashCode() should equal(N(1).hashCode())
  }

  "Node with 1 element" should "should not be equal to node with another data" in {
    N(1) should not equal(N(2))
  }

  "Node with nested elements" should "should be equal to same node" in {
    N(1, N(2), N(3)) should equal(N(1, N(2), N(3)))
    N(1, N(2), N(3)).hashCode() should equal(N(1, N(2), N(3)).hashCode())
  }

  "Node with nested elements" should "should not be equal to another complex node" in {
    N(1, N(2), N(3)) should not equal(N(2, N(3), N(4)))
  }

  "Node with nested elements" should "should not be equal to node with reordered nested nodes" in {
    N(1, N(2), N(3)) should not equal(N(2, N(3), N(2)))
  }
}
