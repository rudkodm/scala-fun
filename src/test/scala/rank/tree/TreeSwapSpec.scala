package rank.tree
import org.scalamock.matchers.{Matchers => MockMatchers}
import org.scalatest.{Matchers => TestMatchers}
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec

import scala.language.{postfixOps, implicitConversions}

/**
  * Created by rudkodm on 3/8/16.
  */
class TreeSwapSpec extends FlatSpec with MockFactory with MockMatchers with TestMatchers {

  implicit def string2Itererator(str:String):Iterator[String] = {
    str.trim.stripMargin.split(System.getProperty("line.separator")).iterator
  }

  "TreeBuilder" should "do nothing when '0' commands applied" in {
    new TreeBuilder().process(
      """
        |0
        |0
      """)
  }

  it should "add 2 Empty nodes when '-1 -1' command applied" in {
    val tree: TreeNode = mock[TreeNode]
    val builder = new TreeBuilder(tree)
    (tree.add _) expects TreeNode() returning tree twice

    builder.process(
      """
        |1
        |-1 -1
        |0
      """)
  }

  it should "add 1 Empty nodes and Node(2)" in {
    val tree: TreeNode = mock[TreeNode]
    val builder = new TreeBuilder(tree)
    inAnyOrder {
      (tree.add _) expects TreeNode() returning tree
      (tree.add _) expects TreeNode(2) returning tree
    }

    builder.process(
      """
        |1
        |-1 2
        |0
      """)
  }

  it should "call swap method when 1 swap command in stack" in {
    val tree: TreeNode = mock[TreeNode]
    val builder = new TreeBuilder(tree)
    (tree.swap _) expects 99 returning tree

    builder.process(
      """
        |0
        |1
        |99
      """)
  }

  it should "call swap method 2 times when 2 swap command in stack" in {
    val tree: TreeNode = mock[TreeNode]
    val builder = new TreeBuilder(tree)
    inSequence {
      (tree.swap _) expects 91 returning tree
      (tree.swap _) expects 92 returning tree
    }

    builder.process(
      """
        |0
        |2
        |91
        |92
      """)
  }

  it should "throw Exception when Number of commands (Add + Swap) more than commands" in {
    val tree: TreeNode = mock[TreeNode]
    val builder = new TreeBuilder(tree)
    (tree.add _) expects * returning tree anyNumberOfTimes()
    (tree.swap _) expects * returning tree anyNumberOfTimes()

    intercept[Exception] {
      builder.process(
        """
          |2
          |-1 -1
          |1
        """)
    }
  }

  "Node" should "return string of data in nodes in internal order (left -> self -> right) when toString is called" in {
    TreeNode(1,
      TreeNode(2, TreeNode(3), TreeNode(4)),
      TreeNode(5, TreeNode(), TreeNode(6,
        TreeNode(7),
        TreeNode(8)
      )))
      .toString should equal("3 2 4 1 5 7 6 8")
  }

  it should "add nodes to tree in '1st level -> 2nd level -> 3d ...' order." in {
    TreeNode(1)
      .add(TreeNode(2)).add(TreeNode(3))
      .add(TreeNode(4)).add(TreeNode(5)).add(TreeNode(6)).add(TreeNode(7))
      .add(TreeNode(8))
      .toString should equal("8 4 2 5 1 3 7")
  }

  it should "swap K*1..n nodes, i.e when K is 3 then K*1=3, K*2=6, K*3=9 levels nodes should be swapped" in {
    TreeNode(1)
      .add(TreeNode(2)).add(TreeNode(3))
      .swap(1)
      .toString should equal("3 1 2")

    TreeNode(1)
      .add(TreeNode(2)).add(TreeNode(3))
      .add(TreeNode(4)).add(TreeNode(5)).add(TreeNode(6)).add(TreeNode(7))
      .swap(1)
      .toString should equal("7 3 6 1 5 2 4")
  }
}


