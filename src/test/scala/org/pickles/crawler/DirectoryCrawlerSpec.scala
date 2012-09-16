package org.pickles.crawler

import org.scalatest.FunSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.apache.commons.vfs.VFS

@RunWith(classOf[JUnitRunner])
class DirectoryCrawlerSpec extends FunSpec {
  describe("A Directory Crawler") {
    it("should find determine feature files as relevant content") {
      pending
    }
    
    it("should determine markdown files as relevant content") (pending)
    it("should find feature files deeper in the tree hierarchy") (pending)
    it("should find ignore folders that have no relevant content") (pending)
  }
}