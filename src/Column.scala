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
        blocks += new Block(0, Array[Range]())
    }else{  
      if (blocks.last.length <= 0 && !this.isActive)
        blocks = blocks.dropRight(1)
      else if (blocks.last.length > 0 && this.isActive)
        blocks = (blocks.reverse.+:(new Block(0, Array[Range]()))).reverse
    }
  }
  
  def updateRanges() = {
    var indexes = contents.zipWithIndex.filter(!_._1.state.isDefined).map(_._2)
    println(contents.map(_.state).mkString(","))
    println(indexes.mkString(","))
    var temp = Buffer[Int]()
    for(ind <- indexes){
      if(!temp.isEmpty){
    	  if(temp.last == ind - 1)
    		  temp += ind
  		  else{
  			  ranges += temp.head to temp.last
  			  temp.clear()  		    
  		  }
      }else{
        temp += ind
      }
    }
//    var temp = process.zipWithIndex.span(_._1.state.isDefined)
//    ranges += temp._1.map(_._2).head to temp._1.map(_._2).last
////    if(!temp._2.isEmpty) 
//      updateRanges(temp._2.tail.map(_._1))
  }
}