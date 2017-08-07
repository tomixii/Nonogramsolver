import processing.core._
import java.awt.event.KeyEvent._

class Nonogramsolver extends PApplet {
  var valueoffset = 0
//testikommenttiiiii
  override def settings = {
    this.size(Board.width * 20 + 120, Board.height * 20 + 120)
  }

  override def setup = {

    for (i <- 0 until Board.width) {
      Board.columns += new Column(Board.height)

    }

    for (i <- 0 until Board.height) {
      Board.rows += new Row(Board.width)

    }

    for (row <- Board.rows)
      for (i <- 0 until Board.width)
        row.contents += new Cell(104 + row.contents.size * 20, 104 + 20 * Board.rows.indexOf(row))

    for (col <- Board.columns)
      for(row <- Board.rows)
        col.contents += row.contents(Board.columns.indexOf(col)) 
  }

  override def draw = {
    Board.rows.foreach(_.checkActive)
    Board.columns.foreach(_.checkActive)
    background(255, 255, 255)
    fill(0, 0, 0)

    for (row <- Board.rows) {
      for (cell <- row.contents) {
        if (cell.state.isDefined) {
          if (cell.state.get)
            rect(cell.xCoord, cell.yCoord, 12, 12)
          else
            rect(5 + cell.xCoord, 5 + cell.yCoord, 2, 2)
        }
      }
    }

    noFill()
    rect(10, 30, 80, 40)
    textSize(24)
    text("SOLVE", 15, 60)
    textSize(14)
    for (row <- Board.rows) {
      for (block_i <- 0 until row.blocks.size) {
        var block = row.blocks(block_i)
        if (block.length > 0) {
          valueoffset = if (block.length.toString.length() == 2) 2 else 6
          text(block.length, 100 - row.blocks.size * 20 + block_i * 20 + valueoffset, 115 + 20 * Board.rows.indexOf(row))
        }
        if (row.isActive) {
          if (block_i == Board.activeindex) {
            rect(100 - row.blocks.size * 20 + block_i * 20, 100 + 20 * Board.rows.indexOf(row), 20, 20)
          }
        }
      }
    }

    for (col <- Board.columns) {
      for (block_i <- 0 until col.blocks.size) {
        var block = col.blocks(block_i)
        if (block.length > 0) {
          valueoffset = if (block.length.toString.length() == 2) 2 else 6
          text(block.length, 100 + 20 * Board.columns.indexOf(col) + valueoffset, 115 - col.blocks.size * 20 + block_i * 20)
        }
        if (col.isActive) {
          if (block_i == Board.activeindex) {
            rect(100 + 20 * Board.columns.indexOf(col), 100 - col.blocks.size * 20 + block_i * 20, 20, 20)
          }
        }
      }
    }

    for (i <- 0 to Board.width) {
      if (i % 5 == 0) this.strokeWeight(2) else this.strokeWeight(1)
      line(100 + 20 * i, 0, 100 + 20 * i, Board.height * 20 + 100)
    }

    for (i <- 0 to Board.height) {
      if (i % 5 == 0) this.strokeWeight(2) else this.strokeWeight(1)
      line(0, 100 + 20 * i, Board.width * 20 + 100, 100 + 20 * i)
    }

  }

  override def keyPressed = {
    if (keyCode == VK_UP)
      Board.up
    else if (keyCode == VK_DOWN)
      Board.down
    else if (keyCode == VK_LEFT)
      Board.left
    else if (keyCode == VK_RIGHT)
      Board.right
    else if (key >= '0' && key <= '9')
      Board.write(key)
    //    else if(keyCode == BACKSPACE)
    //      Board.del
    else if (key == '\n')
      Board.add
  }

  override def mouseClicked = {
    if (mouseX > 10 && mouseX < 90 && mouseY > 30 && mouseY < 70)
      Board.solve
  }
}

object Nonogramsolver {
  def main(args: Array[String]) {
    PApplet.main(Array[String]("Nonogramsolver"))
  }
}	