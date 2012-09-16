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
class RelevantFileDeterminer {
  def isRelevant(file: FileObject) = {
    file.getType() match {
      case FileType.FOLDER => false
      case FileType.FILE => file.getName().getExtension() match {
        case ".feature" => true
        case ".markdown" => true
        case ".mdown" => true
        case ".mkdn" => true
        case ".md" => true
        case ".mdwn" => true
        case ".mdtxt" => true
        case ".mdtext" => true
        case ".text" => true
        case ".txt" => true
        case _ => false
      }
      case _ => false
    }
  }
}