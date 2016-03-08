package rank.wurmup

/**
  * Created by rudkodm on 3/5/16.
  */
object MatrixDiffApplication extends App{
  val size = Console.in.readLine().toInt
  val n = if(size > 0) size - 1 else size
  val arr = Array.ofDim[Int](size, size)
  for (i <- 0 to n) {
    val row = Console.in.readLine().split(" ").map(_.toInt)
    for(j <- 0 to n) arr(i)(j) = row(j)
  }
  val mainD = (0 to n).map(i => arr(i)(i)).reduce(_+_)
  val secD = (0 to n).map(i => arr(i)(n-i)).reduce(_+_)
  println(Math.abs(mainD-secD))
}
