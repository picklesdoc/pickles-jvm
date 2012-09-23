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
import org.pickles.features.model.Feature

abstract class File(fileObject: FileObject, parent: Folder) extends TreeItem {
  def getName(): String = {
    fileObject.getName().getBaseName()
  }

  def getParent(): Option[TreeItem] = {
    Some(parent)
  }
}

object File {
  def apply(file: FileObject, parent: Folder) = {
    file.getName().getExtension() match {
      case "feature" => FeatureFile(file, parent, null)
      case "markdown" => MarkdownFile(file, parent, null)
      case "mdown" => MarkdownFile(file, parent, null)
      case "mkdn" => MarkdownFile(file, parent, null)
      case "md" => MarkdownFile(file, parent, null)
      case "mdwn" => MarkdownFile(file, parent, null)
      case "mdtxt" => MarkdownFile(file, parent, null)
      case "mdtext" => MarkdownFile(file, parent, null)
      case "text" => MarkdownFile(file, parent, null)
      case "txt" => MarkdownFile(file, parent, null)
      case _ => MarkdownFile(file, parent, null)
    }
  }
}