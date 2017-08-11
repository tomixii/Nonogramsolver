import scala.collection.mutable.Buffer
import scala.math._

object Board {
  val width = 5
  val height = 10
  var rows = Buffer[Row]()
  var columns = Buffer[Column]()
  var activerow = 0
  var activecolumn = -1
  var activeindex = 0 //rows(activerow).values.size - 1 
  var newValue = ""
  
  def solve() = {
    rows.foreach(_.updateRanges)
    columns.foreach(_.updateRanges)
    println("solve")
    rows.foreach(x => println(x.ranges))
    columns.foreach(x => println(x.ranges))
  }

  def write(char: Char) = if (newValue.length == 1 || (char != '0' && newValue.length == 0)) newValue += char

  def del() = if (newValue.length > 0) newValue = newValue.substring(0, newValue.length - 1)

  def add() = {
    if (newValue.length > 0) {
      if (activerow >= 0)
        rows(activerow).blocks(activeindex) = new Block(newValue.toInt)
      else
        columns(activecolumn).blocks(activeindex) = new Block(newValue.toInt)
      newValue = ""
      activeindex += 1
    }
  }

  def up() = {
    newValue = ""
    if (activerow >= 0) {
    	activerow -= 1
      if (activerow == -1) {
        activecolumn = 0
        activeindex = columns(activecolumn).blocks.size
      } else {
        activeindex = rows(activerow).blocks.size
      }

    } else {
      activeindex = max(0, activeindex - 1)
    }
  }

  def down() = {
    newValue = ""
    if (activerow >= 0) {
      if (activerow < rows.size - 1) {
        activerow = min(rows.size - 1, activerow + 1)
        activeindex = rows(activerow).blocks.size
      }
    } else {
      activeindex = min(columns(activecolumn).blocks.size - 1, activeindex + 1)
    }
  }

  def left() = {
    newValue = ""
    if (activecolumn >= 0) {
    	activecolumn -= 1
      if (activecolumn == -1) {
        activerow = 0
        activeindex = rows(activerow).blocks.size
      } else {
        activeindex = columns(activecolumn).blocks.size
      }
    } else {

      activeindex = max(0, activeindex - 1)
    }
  }

  def right() = {
    newValue = ""
    if (activecolumn >= 0) {
      if (activecolumn < columns.size - 1) {
        activecolumn = min(columns.size - 1, activecolumn + 1)
        activeindex = columns(activecolumn).blocks.size
      }
    } else {
      activeindex = min(rows(activerow).blocks.size - 1, activeindex + 1)
    }
  }
  
  
}