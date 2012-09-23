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

import scala.collection.Seq
import scala.collection.immutable.List

class Table {
  protected var rows: List[TableRow] = List()
  def addRow(row: TableRow) = rows ::= row
  def getRows: Seq[TableRow] = rows
}

object Table {
  def apply(rows: Seq[TableRow]) = {
    var table = new Table()
    rows.foreach(row => table.addRow(row))
    table
  }
}
