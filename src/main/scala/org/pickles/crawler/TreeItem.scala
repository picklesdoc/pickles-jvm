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