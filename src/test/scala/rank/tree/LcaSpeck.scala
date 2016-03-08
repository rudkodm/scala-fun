package rank.tree

import org.scalatest.{FlatSpec, Matchers}
import rank.tree.model.Node
import rank.tree.util.NodeUtil._

/**
  * Created by rudkodm on 3/6/16.
  */
class LcaSpeck extends FlatSpec with Matchers{


  "Lca" should "should return lowest common accessor" in {
    val actual = Lca.lca(root4, 1, 3)
    actual should equal(root4.left)
  }

  "Lca" should "should return root node if no common node exist" in {
    val actual = Lca.lca(root4, 1, 6)
    actual should equal(root4)
  }

  "Lca" should "of the root is root" in {
    val actual = Lca.lca(root4, 4, 4)
    actual should equal(root4)
  }

}
