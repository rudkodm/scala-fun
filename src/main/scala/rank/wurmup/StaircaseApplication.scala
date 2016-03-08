package rank.wurmup

/**
  * Created by rudkodm on 3/5/16.
  */
object StaircaseApplication extends App{
  val n = Console.in.readLine().toInt
  (1 to n).foreach(i => println(" " * (n-i) + "#" * i))
}
