import scala.collection.mutable.Buffer

class Block(val length: Int) {
    var placeLeft: Option[Range] = None
    var placeRight: Option[Range] = None
    
    def isActive: Boolean = {
      if(Board.activecolumn >= 0)
        return Board.columns(Board.activecolumn).blocks(Board.activeindex) == this
      else
        return Board.rows(Board.activerow).blocks(Board.activeindex) == this
    }
    
    def intersect: Option[Array[Int]] = {
      if(placeLeft.isDefined && placeRight.isDefined)
        Some(placeLeft.get.intersect(placeRight.get).toArray)
      else
        None
    }
}