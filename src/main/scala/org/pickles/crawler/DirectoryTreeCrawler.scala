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
import org.apache.commons.vfs.FileType

/**
 * @author jeffrey
 *
 */
class DirectoryTreeCrawler {
  def crawl(rootPath: FileObject): TreeItem = {
    crawl(rootPath, None)
  }

  def crawl(rootPath: FileObject, rootNode: Option[Folder]): TreeItem = {
    // TODO - implement logic to determine if any relevant files were found in this directory tree or not
    val currentNode = Folder(rootPath, rootNode)

    val newRootNode: Option[Folder] = rootNode match {
      case None => Some(currentNode)
      case Some(_) => rootNode
    }

    var isRelevantFileFound = true
    rootPath.getChildren().foreach(child => {
      child.getType() match {
        case FileType.FOLDER => currentNode.addChild(crawl(child, Some(currentNode)))
        case FileType.FILE => currentNode.addChild(File(child, currentNode))
      }
    })

    currentNode
  }
}