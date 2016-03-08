package rank.tree

import org.scalamock.matchers.Matchers
import org.scalamock.scalatest.MockFactory
import org.scalatest.FlatSpec

/**
  * Created by rudkodm on 3/8/16.
  */
class TreeSwapSpec extends FlatSpec with MockFactory with Matchers {

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
    val builder = new TreeBuilder(mock[Node])
    (builder.tree.add _) expects Node() twice

    builder.process(
      """
        |1
        |-1 -1
        |0
      """.stripMargin)
  }

  it should "add 1 Empty nodes and Node(2)" in {
    val builder = new TreeBuilder(mock[Node])
    inAnyOrder {
      (builder.tree.add _) expects Node()
      (builder.tree.add _) expects Node(2)
    }

    builder.process(
      """
        |1
        |-1 2
        |0
      """.stripMargin)
  }

  it should "call swap method when 1 swap command in stack" in {
    val builder = new TreeBuilder(mock[Node])
    (builder.tree.swap _) expects 99

    builder.process(
      """
        |0
        |1
        |99
      """.stripMargin)
  }

  it should "call swap method 2 times when 2 swap command in stack" in {
    val builder = new TreeBuilder(mock[Node])
    inSequence {
      (builder.tree.swap _) expects 91
      (builder.tree.swap _) expects 92
    }

    builder.process(
      """
        |0
        |2
        |91
        |92
      """.stripMargin)
  }

  it should "throw Exception when Number of commands (Add + Swap) more than commands" in {
    val builder = new TreeBuilder(mock[Node])
    (builder.tree.add _) expects * anyNumberOfTimes()
    (builder.tree.swap _) expects * anyNumberOfTimes()

    intercept[Exception] {
      builder.process(
        """
          |2
          |-1 -1
          |1
        """.stripMargin)
    }
  }

}
