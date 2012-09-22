/**
 *
 */
package org.pickles.features.builder

import org.pickles.features.model._

/**
 * @author jeffrey
 *
 */
class ExampleBuilder {
  var description: String = _
  var name: String = _
  var rows: List[TableRow] = List()
  def setName(name: String) = this.name = name
  def setDescription(description: String) = this.description = description
  def addRow(row: TableRow) = rows ::= row
  def getResult(): Example = null
}