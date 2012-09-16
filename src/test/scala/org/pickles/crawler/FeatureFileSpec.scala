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

      folder.getParent() should be (Some(parentFolder))
    }
    
    it("should throw an exception if no parent is specified on creation") {
      val builder = FileSystemBuilder.build()
      val parentObject = builder.addFolder("ram://features/")
      val childObject = builder.addFolder("ram://features/feature.txt")

      val parentFolder = Folder(parentObject)
      evaluating { FeatureFile(childObject, null, null) } should produce [Exception]
    }
    
    it("should be able to find a common ancestor where one exists") {
      val builder = FileSystemBuilder.build()
      val a = builder.addFolder("ram://a/")
      val b = builder.addFolder("ram://a/b/c.txt")
      val c = builder.addFolder("ram://d/e/f.txt")
            
      val folderA = Folder(a)
      val folderB = Folder(b, Some(folderA))
      val folderC = Folder(c, Some(folderA))
      
      folderC.findCommonAncestor(folderB) should be (Some(folderA))
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
      
      folderC.findCommonAncestor(folderB) should be (None)
    }
  }
}