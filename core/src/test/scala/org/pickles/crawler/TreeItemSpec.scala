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

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TreeItemSpec extends FunSpec with ShouldMatchers {
  describe("A TreeItem") {
    it("should be able to properly create a Folder with no parent") {
      val builder = FileSystemBuilder.build()
      val folderObject = builder.addFolder("ram://features/")

      val folder = TreeItem(folderObject)

      folder.isInstanceOf[Folder] should be(true)

      folder should have(
        'name("features"),
        'parent(None))
    }

    it("should be able to properly create a Folder with parent") {
      val builder = FileSystemBuilder.build()
      val parentObject = builder.addFolder("ram://my/")
      val folderObject = builder.addFolder("ram://my/features/")

      val parentFolder = Folder(parentObject)
      val folder = TreeItem(folderObject, Some(parentFolder))

      folder.isInstanceOf[Folder] should be(true)

      folder should have(
        'name("features"),
        'parent(Some(parentFolder)))
    }

    it("should be able to properly create a FeatureFile") {
      val builder = FileSystemBuilder.build()
      val parentObject = builder.addFolder("ram://features/")
      val fileObject = builder.addFile("ram://features/my.feature")

      val parentFolder = Folder(parentObject)
      val file = TreeItem(fileObject, Some(parentFolder))

      file.isInstanceOf[FeatureFile] should be(true)

      file should have(
        'name("my.feature"),
        'parent(Some(parentFolder)))
    }

    it("should be able to properly create a MarkdownFile") {
      val builder = FileSystemBuilder.build()
      val parentObject = builder.addFolder("ram://features/")
      val fileObject = builder.addFile("ram://features/my.markdown")

      val parentFolder = Folder(parentObject)
      val file = TreeItem(fileObject, Some(parentFolder))

      file.isInstanceOf[MarkdownFile] should be(true)

      file should have(
        'name("my.markdown"),
        'parent(Some(parentFolder)))
    }

    it("should throw an exception if the file is not relevant") {
      val builder = FileSystemBuilder.build()
      val parentObject = builder.addFolder("ram://features/")
      val fileObject = builder.addFile("ram://features/my.vsd")

      val parentFolder = Folder(parentObject)
      evaluating { TreeItem(fileObject, Some(parentFolder)) } should produce[IrrelevantFileException]
    }
  }
}