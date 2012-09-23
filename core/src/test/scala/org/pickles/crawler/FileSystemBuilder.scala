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
  def build(): FileSystemBuilder = {
    val manager = new DefaultFileSystemManager()
    manager.addProvider("ram", new RamFileProvider())
    manager.init()
    new FileSystemBuilder(manager)
  }
}