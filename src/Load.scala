
import java.io.BufferedReader
import java.io.IOException
import java.io.Reader
import scala.collection.mutable.Buffer

object Load {
  
  def nonogram(input: Reader, number: Int) = {
     val lineReader = new BufferedReader(input)
     var currentLine = lineReader.readLine()
     var text = Buffer[String]() //file in bufferform
     var line = 0 //starting line
     
     while (currentLine != null) { //adds all the lines to buffer
      text += currentLine
      currentLine = lineReader.readLine()
     }
     
     while (line < text.size) { // while there's still something to read
       if(text(line).toUpperCase.startsWith("#EXAMPLE") && text(line).drop(8).trim == number.toString){
         readExampleInfo
       }
       line += 1
     }
     
     def readExampleInfo = {
        line += 1
        if (line < text.size) {
        	  if(text(line).startsWith("dimensions")){
              Board.width = text(line).drop(11).split("x")(0).toInt
              Board.height = text(line).drop(11).split("x")(1).toInt
            }  
        	  line+=1
            if(text(line).startsWith("rows")){
            	line+=1
              for(i <- 0 until Board.height){
            	  var rowInfo = text(line).split(",")
            	  var row = new Row(Board.width)
            	  for(block <- rowInfo)
                  row.blocks += new Block(block.toInt)
                Board.rows += row 
                line+=1
              }
            }
            if(text(line).startsWith("columns")){
            	line+=1
              for(i <- 0 until Board.width){
            	  var colInfo = text(line).split(",")
            	  var col = new Column(Board.height)
            	  for(block <- colInfo)
                  col.blocks += new Block(block.toInt)
                Board.columns += col 
                line+=1
              }
            }
        }    
     }

  }
  
}