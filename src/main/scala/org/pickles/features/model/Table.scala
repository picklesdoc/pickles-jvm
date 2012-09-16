package org.pickles.features.model

class Table {
  protected var rows: List[TableRow] = List()
  def addRow(row: TableRow) = rows ::= row
}

object Table {
  def apply(rows: TableRow*) = {
    var table = new Table()
    rows.foreach(row => table.addRow(row))
    table
  }
}
