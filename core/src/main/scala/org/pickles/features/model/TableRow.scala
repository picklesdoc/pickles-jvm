/**
 * Copyright 2012 Jeffrey Cameron
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.pickles.features.model

import scala.collection.immutable.List
import scala.collection.Seq

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
  def apply(cells: java.util.List[String]): TableRow = apply(scala.collection.JavaConversions.asScalaBuffer(cells))

  def apply(cells: Seq[String]) = {
    var row = new TableRow()
    cells.foreach(cell => row.addCell(cell))
    row
  }
}