package rank.wurmup

/**
  * Created by rudkodm on 3/5/16.
  */
object FractionInArrayApplication extends App{
  val size = Console.in.readLine().toInt
  val arr = Console.in.readLine().split(" ").map(_.toInt)
  val fractionOf = (f: (Int => Boolean)) => arr.count(f).toDouble / size
  println(fractionOf(_ >  0))
  println(fractionOf(_ <  0))
  println(fractionOf(_ ==  0))
}
