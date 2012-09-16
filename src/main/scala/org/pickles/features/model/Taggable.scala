package org.pickles.features.model

import java.util.ArrayList

trait Taggable {
  protected var tags : List[String] = List()
  def getTags : Seq[String] = tags
  def addTag(tag: String) = tags ::= tag
}