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
import org.apache.commons.vfs.FileObject
import org.apache.commons.vfs.FileType

trait TreeItem {
  var children: List[TreeItem] = List()
  def getName(): String
  def getParent(): Option[TreeItem]
  def getChildren(): Seq[TreeItem] = children

  def addChild(child: TreeItem) = children ::= child

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

  def walk(action: (TreeItem) => Unit): Unit = {
    action(this)
    children.foreach({ _.walk(action) })
  }
}

object TreeItem {
  val relevantFileDeterminer = new RelevantFileDeterminer

  def apply(file: FileObject): TreeItem = {
    apply(file, None)
  }

  def apply(file: FileObject, parent: Option[Folder]): TreeItem = {
    file.getType() match {
      case FileType.FOLDER => Folder(file, parent)
      case FileType.FILE => relevantFileDeterminer.isRelevant(file) match {
        case false => throw IrrelevantFileException(file)
        case true => File(file, parent.getOrElse(null))
      }
    }
  }
}