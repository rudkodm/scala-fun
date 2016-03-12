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
    new RootTreeBuilder(RootNode()).process(
      """
        |0
        |0
      """)
  }

  it should "add 2 Empty nodes when '-1 -1' command applied" in {
    val tree = mock[RootNode]
    val builder = new RootTreeBuilder(tree)
    (tree.add _) expects TreeNode() returning tree twice

    builder.process(
      """
        |1
        |-1 -1
        |0
      """)
  }

  it should "add 1 Empty nodes and Node(2)" in {
    val tree = mock[RootNode]
    val builder = new RootTreeBuilder(tree)
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
    val tree = mock[RootNode]
    val builder = new RootTreeBuilder(tree)
    (tree.swapOnEach _) expects 99 returning tree

    builder.process(
      """
        |0
        |1
        |99
      """)
  }

  it should "call swap method 2 times when 2 swap command in stack" in {
    val tree = mock[RootNode]
    val builder = new RootTreeBuilder(tree)
    inSequence {
      (tree.swapOnEach _) expects 91 returning tree
      (tree.swapOnEach _) expects 92 returning tree
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
    val tree = mock[RootNode]
    val builder = new RootTreeBuilder(tree)
    (tree.add _) expects * returning tree anyNumberOfTimes()
    (tree.swapOnEach _) expects * returning tree anyNumberOfTimes()

    intercept[Exception] {
      builder.process(
        """
          |2
          |-1 -1
          |1
        """)
    }
  }

  it should "print right sequence for predefined commands set # 1" in {
    val builder = new RootTreeBuilder(RootNode())
    builder.process(
      """
        |5
        |2 3
        |-1 4
        |-1 5
        |-1 -1
        |-1 -1
        |1
        |2
      """)
    builder.tree.toString should equal("4 2 1 5 3")
  }


  it should "print right sequence for predefined commands set # 2" in {
    val builder = new RootTreeBuilder(RootNode())
    builder.process(
      """
        |11
        |2 3
        |4 -1
        |5 -1
        |6 -1
        |7 8
        |-1 9
        |-1 -1
        |10 11
        |-1 -1
        |-1 -1
        |-1 -1
        |1
        |2
      """)
    builder.tree.toString should equal("2 9 6 4 1 3 7 5 11 8 10")
    builder.process(
      """
        |0
        |1
        |4
      """
    )
    builder.tree.toString should equal("2 6 9 4 1 3 7 5 10 8 11")
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


  it should "swap child nodes when method called" in {
    val actual: TreeNode = TreeNode(1, TreeNode(2), TreeNode(3)).swap
    val expected: TreeNode = TreeNode(1, TreeNode(3), TreeNode(2))
    actual should equal (expected)
  }

  "RootNode" should "swap nodes on levels K*1..n , i.e when K is 3 then K*1=3, K*2=6, K*3=9 level's nodes should be swapped" in {
    RootNode(
        TreeNode(2),
        TreeNode(3)
    ).swapOnEach(1)
      .toString should equal("3 1 2")

    RootNode(
        TreeNode(2, TreeNode(4), TreeNode(5)),
        TreeNode(3, TreeNode(6), TreeNode(7))
    ).swapOnEach(1)
      .toString should equal("7 3 6 1 5 2 4")
  }

  it should "add nodes to tree in '1st level -> 2nd level -> 3d ...' order." in {
    RootNode()
      .add(TreeNode(2)).add(TreeNode(3))
      .add(TreeNode(4)).add(TreeNode(5)).add(TreeNode(6)).add(TreeNode(7))
      .add(TreeNode(8))
      .toString should equal("8 4 2 5 1 6 3 7")
  }

  it should "return Sets of nodes by level for levelNodesSet(k) method" in {
    val root = RootNode(
      TreeNode(2, TreeNode(3), TreeNode(4)),
      TreeNode(5, TreeNode(6), new TreeNode(Some(7),
        Some(TreeNode(8)),
        None
      ))
    )
    root.nodesOnLevel(1) should contain only TreeNode(1)
    root.nodesOnLevel(2) should contain allOf (TreeNode(2), TreeNode(5))
    root.nodesOnLevel(3) should contain allOf (TreeNode(3), TreeNode(4), TreeNode(6), TreeNode(7))
    root.nodesOnLevel(4) should contain only TreeNode(8)
  }
}


