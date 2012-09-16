/**
 *
 */
package org.pickles.features.model

/**
 * @author jeffrey
 *
 */
class TableRow {
  var cells: List[String] = List()
  def addCell(cell: String) = this.cells ::= cell
  def getCell(index: Int) = this.cells(index)
  def getCells(): Seq[String] = this.cells
}

object TableRow {
  def apply(cells: String*) = {
    var row = new TableRow()
    cells.foreach(cell => row.addCell(cell))
    row
  }
}