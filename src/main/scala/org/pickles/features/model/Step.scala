package org.pickles.features.model

import org.pickles.features.model.Keyword._

class Step(keyword: Keyword, nativeKeyword: String, name: String) extends TableContainer {
  var docString: String = _
  def setDocString(docString: String) = this.docString = docString
}