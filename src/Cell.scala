import scala.util.Random

class Cell(val xCoord: Int,val yCoord: Int) {
  var r = new Random()
  var juu = if(r.nextBoolean) Option(false) else None
  
  var state: Option[Boolean] = juu
}