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

import org.junit.runner.RunWith
import org.scalatest.FunSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class DirectoryCrawlerSpec extends FunSpec with ShouldMatchers {
  describe("A Directory Crawler") {
    it("should be able to scan a directory hierarchy, picking out relevant files") {
      val builder = FileSystemBuilder.build()
      val featuresObject = builder.addFolder("ram://features/")
      builder.addFile("ram://features/index.markdown")
      builder.addFile("ram://features/a.feature")
      builder.addFolder("ram://features/other/")
      builder.addFile("ram://features/other/context.markdown")
      builder.addFile("ram://features/other/b.feature")

      val directoryTreeCrawler = new DirectoryTreeCrawler
      val rootItem = directoryTreeCrawler.crawl(featuresObject)

      rootItem.isInstanceOf[Folder] should be(true)
      rootItem.children should have size (3)

      val indexMarkdown = rootItem.children.find(_.getName() == "index.markdown").head
      indexMarkdown.isInstanceOf[MarkdownFile] should be(true)

      val aFeature = rootItem.children.find(_.getName() == "a.feature").head
      aFeature.isInstanceOf[FeatureFile] should be(true)

      val otherFolder = rootItem.children.find(_.getName() == "other").head
      otherFolder.isInstanceOf[Folder] should be(true)
      otherFolder.children should have size (2)

      val contextMarkdown = otherFolder.children.find(_.getName() == "context.markdown").head
      contextMarkdown.isInstanceOf[MarkdownFile] should be(true)

      val bFeature = otherFolder.children.find(_.getName() == "b.feature").head
      contextMarkdown.isInstanceOf[FeatureFile] should be(true)
    }
  }
}