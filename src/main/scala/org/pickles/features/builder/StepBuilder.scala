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

package org.pickles.features.builder

import org.pickles.features.model._
import scala.collection.immutable.List

/**
 * @author jeffrey
 *
 */
class StepBuilder {
  var keyword: String = _
  var name: String = _
  var rows: List[TableRow] = List()
  var docString: Option[String] = None

  def setName(name: String) = this.name = name
  def setKeyword(keyword: String) = this.keyword = keyword
  def addRow(row: TableRow) = rows ::= row
  def setDocString(text: String) = this.docString = Some(text)

  def getResult(): Step = {
    val keywordEnum = Keyword.parse(keyword)
    val step = new Step(keywordEnum, keyword, name)
    rows.foreach { step.addRow(_) }
    docString match {
      case Some(content) => step.setDocString(content)
      case None => Unit
    }
    step
  }
}