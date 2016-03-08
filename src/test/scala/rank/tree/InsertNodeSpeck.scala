package rank.tree

import org.scalatest.{FlatSpec, Matchers}
import rank.tree.model.Node
import rank.tree.util.NodeUtil._

/**
  * Created by rudkodm on 3/6/16.
  */
class InsertNodeSpeck extends FlatSpec with Matchers{

  /**
    * <pre>
    * {@code
    *
    *        4
     *     /   \
     *    2     7
     *   / \
     *  1   3
     * }
    * </pre>
    */
  var inicial: Node = N(4, N(2, N(1), N(3)), N(7))
  /**
    * <pre>
    * {@code
    *
    *        4
     *     /   \
     *    2     7
     *   / \   /
     *  1   3 6
     * }
    * </pre>
    */
  var expected: Node = N(4, N(2, N(1), N(3)), N(7, N(6), null))


  "Insert" should "should insert element in right position" in {
    val actual = InsertNode.Insert(inicial, 6)
    actual should equal(expected)
  }

  "Insert" should "should insert element in left if less" in {
    val actual = InsertNode.Insert(N(5), 4)
    actual should equal(N(5, N(4), null))
  }

  "Insert" should "should insert element in right if more" in {
    val actual = InsertNode.Insert(N(5), 6)
    actual should equal(N(5, null, N(6)))
  }

  "Insert" should "should return same if exist" in {
    val actual = InsertNode.Insert(N(5, N(4), N(6)), 6)
    actual should equal(N(5, N(4), N(6)))
  }


}
