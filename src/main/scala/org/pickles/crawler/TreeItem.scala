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

package org.pickles.crawler

import java.util.ArrayList
import scala.collection.mutable.ListBuffer
import sun.reflect.generics.reflectiveObjects.NotImplementedException
import scala.util.control.Breaks._

trait TreeItem {
  def getName(): String
  def getParent(): Option[TreeItem]

  def findCommonAncestor(other: TreeItem): Option[TreeItem] = {
    val thisHierarchy = getHierarchy()
    val otherHierarchy = other.getHierarchy()

    val intersection = thisHierarchy intersect otherHierarchy
    if (intersection.isEmpty) None
    else Some(intersection.head)
  }

  def getRelativePathFromHereToThere(other: TreeItem): String = {
    throw new NotImplementedException()
  }

  def getHierarchy(): Seq[TreeItem] = {
    var hierarchy = new ListBuffer[TreeItem]();
    var temp: Option[TreeItem] = Some(this)

    while (temp != None) {
      temp match {
        case Some(item) => {
          hierarchy.append(item)
          temp = item.getParent()
        }
        case None => null // should never hit this because of the break condition
      }
    }

    hierarchy
  }
}