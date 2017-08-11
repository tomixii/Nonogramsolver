import scala.collection.mutable.Buffer

class Column(size: Int) {
  val contents = Buffer[Cell]()
  var blocks = Buffer[Block]()
  var isActive = Board.activecolumn == Board.columns.indexOf(this)
  var ranges = Buffer[Range]()
  
  def checkActive = {
    isActive = Board.activecolumn == Board.columns.indexOf(this)
    if(blocks.isEmpty){
      if(this.isActive)
        blocks += new Block(0)
    }else{  
      if (blocks.last.length <= 0 && !this.isActive)
        blocks = blocks.dropRight(1)
      else if (blocks.last.length > 0 && this.isActive)
        blocks = (blocks.reverse.+:(new Block(0))).reverse
    }
  }
  
  def updateRanges() = {
    var indexes = contents.zipWithIndex.filter(!_._1.state.isDefined).map(_._2)
//    println(contents.map(_.state).mkString(","))
//    println(indexes.mkString(","))
    var temp = Buffer[Int]()
    for(ind <- indexes){
      if(!temp.isEmpty){
    	  if(temp.last != ind - 1){
    		  ranges += temp.head to temp.last
    		  temp.clear()  			  
  		  }
      }
      temp += ind
    }
    ranges += temp.head to temp.last
  }
  
  def blockPlaces() = {
    for(blockIndex <- 0 until blocks.size){
      blocks(blockIndex).placeLeft = ranges.find(_.size >= blocks(blockIndex).length)
      
    }
    
    for(blockIndex <- blocks.size until 0 by -1){
      blocks(blockIndex).placeRight = ranges.reverse.find(_.size >= blocks(blockIndex).length)
    }
  }
  
//  def updateCellstates() = {
//    for(cell <- contents)
//      blocks.scan(_.intersect.contains(contents.indexOf(cell)) || _.intersect.contains(contents.indexOf(cell)))
//      if()
//  }
}