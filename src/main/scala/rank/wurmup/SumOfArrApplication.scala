package rank.wurmup

/**
  * Created by rudkodm on 3/5/16.
  */
object SumOfArrApplication extends App{
  val arg1 = Console.in.readLine().toInt
  val arg2 = Console.in.readLine().split(" ")
  println(arg2.map(BigInt(_)).sum)
}
