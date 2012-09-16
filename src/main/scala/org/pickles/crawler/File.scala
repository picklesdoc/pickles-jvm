package org.pickles.crawler

import org.apache.commons.vfs.FileObject

abstract class File(fileObject : FileObject, parent : Folder) extends TreeItem {
  def getName(): String = {
    fileObject.getName().getBaseName()
  }
  
  def getParent(): Option[TreeItem] = {
    Some(parent)
  }
}