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
class FeatureFileSpec extends FunSpec with ShouldMatchers {
  describe("A feature file") {
    it("should have a name") {
      val builder = FileSystemBuilder.build()
      val folderObject = builder.addFolder("ram://features/")
      val featureObject = builder.addFile("ram://features/feature.txt")

      val folder = Folder(folderObject)
      val featureFile = FeatureFile(featureObject, folder, null)

      featureFile.getName() should equal("feature.txt")
    }

    it("must have a parent") {
      val builder = FileSystemBuilder.build()
      val parentObject = builder.addFolder("ram://features/")
      val childObject = builder.addFolder("ram://features/feature.txt")

      val parentFolder = Folder(parentObject)
      val folder = FeatureFile(childObject, parentFolder, null)

      folder.getParent() should be(Some(parentFolder))
    }

    it("should throw an exception if no parent is specified on creation") {
      val builder = FileSystemBuilder.build()
      val parentObject = builder.addFolder("ram://features/")
      val childObject = builder.addFolder("ram://features/feature.txt")

      val parentFolder = Folder(parentObject)
      evaluating { FeatureFile(childObject, null, null) } should produce[Exception]
    }

    it("should be able to find a common ancestor where one exists") {
      val builder = FileSystemBuilder.build()
      val a = builder.addFolder("ram://a/")
      val b = builder.addFolder("ram://a/b/c.txt")
      val c = builder.addFolder("ram://d/e/f.txt")

      val folderA = Folder(a)
      val folderB = Folder(b, Some(folderA))
      val folderC = Folder(c, Some(folderA))

      folderC.findCommonAncestor(folderB) should be(Some(folderA))
    }

    it("should return nothing if no common ancestor can be found") {
      val builder = FileSystemBuilder.build()
      val a = builder.addFolder("ram://a/")
      val b = builder.addFolder("ram://a/b/")
      val d = builder.addFolder("ram://d/")
      val c = builder.addFolder("ram://d/c/")

      val folderA = Folder(a)
      val folderB = Folder(b, Some(folderA))
      val folderD = Folder(d)
      val folderC = Folder(c, Some(folderD))

      folderC.findCommonAncestor(folderB) should be(None)
    }
  }
}