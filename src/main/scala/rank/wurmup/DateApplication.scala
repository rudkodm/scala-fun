package rank.wurmup

/**
  * Created by rudkodm on 3/5/16.
  */
object DateApplication extends App{
  val in = Console.in.readLine()
  val time = in.dropRight(2).split(":").map(_.toInt)
  val pm = in.contains("PM")
  val am = !pm
  var hh = time(0)
  val mm = time(1)
  val ss = time(2)
  hh =  if(pm && hh < 12 ) hh + 12
        else if(am && hh == 12) hh - 12
        else hh
  printf("%02d:%02d:%02d%n", hh, mm, ss)
}
