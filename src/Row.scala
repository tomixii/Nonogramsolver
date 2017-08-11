import scala.collection.mutable.Buffer

class Row(size: Int) {
  val contents = Buffer[Cell]()
  var blocks = Buffer[Block]()
  var isActive = Board.activerow == Board.rows.indexOf(this)
  var ranges = Buffer[Range]()
  
  def checkActive = {
    isActive = Board.activerow == Board.rows.indexOf(this)
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
//    var temp = process.zipWithIndex.span(!_._1.state.isDefined)
//    
//    println(temp._1.size)
//    ranges += temp._1.map(_._2).head to temp._1.map(_._2).last
////    if(!temp._2.isEmpty) 
//      updateRanges(temp._2.tail.map(_._1))
  }
}