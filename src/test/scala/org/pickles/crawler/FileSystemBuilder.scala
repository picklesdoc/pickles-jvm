package org.pickles.crawler

import org.apache.commons.vfs.FileObject
import org.apache.commons.vfs.FileSystemManager
import org.apache.commons.vfs.impl.DefaultFileSystemManager
import org.apache.commons.vfs.provider.ram.RamFileProvider

class FileSystemBuilder(manager: DefaultFileSystemManager) {
  def addFile(path: String): FileObject = {
    val file = this.manager.resolveFile(path)
    if (!file.exists()) {
      file.createFile()
    }
    file
  }

  def addFolder(path: String): FileObject = {
    val folder = this.manager.resolveFile(path)
    if (!folder.exists()) {
      folder.createFolder()
    }
    folder
  }
}

object FileSystemBuilder {
  def build() : FileSystemBuilder = {
    val manager = new DefaultFileSystemManager()
    manager.addProvider("ram", new RamFileProvider())
    manager.init()
    new FileSystemBuilder(manager)
  }
}