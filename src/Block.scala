import scala.collection.mutable.Buffer

class Block(val length: Int) {
    var placeLeft: Option[Range] = None
    var placeRight: Option[Range] = None
    
    def intersect: Option[Array[Int]] = {
      if(placeLeft.isDefined && placeRight.isDefined)
        Some(placeLeft.get.intersect(placeRight.get).toArray)
      else
        None
    }
}