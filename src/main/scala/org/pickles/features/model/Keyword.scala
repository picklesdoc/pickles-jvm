package org.pickles.features.model

object Keyword extends Enumeration {
  type Keyword = Value
  val Given = Value("Given")
  val When = Value("When")
  val Then = Value("Then")
  val And = Value("And")
  val But = Value("But")
}