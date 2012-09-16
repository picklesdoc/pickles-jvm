package org.pickles.crawler

import org.apache.commons.vfs.FileObject
import sun.reflect.generics.reflectiveObjects.NotImplementedException

class Folder(fileObject : FileObject, parent : Option[Folder]) extends TreeItem {
	def getName() : String =  {
	  this.fileObject.getName().getBaseName()
	}
	
	def getParent() : Option[TreeItem] = {
	  this.parent
	}
}

object Folder {
  def apply(fileObject: FileObject) = {
    new Folder(fileObject, None)
  }
  
  def apply(fileObject: FileObject, parent: Option[Folder]) = {
    new Folder(fileObject, parent) 
  }
}