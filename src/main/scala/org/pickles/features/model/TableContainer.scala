/**
 *
 */
package org.pickles.features.model

/**
 * @author jeffrey
 *
 */
trait TableContainer {
  protected val table = new Table()
  def getTable() = table
  def addRow(row: TableRow) = table.addRow(row)
}