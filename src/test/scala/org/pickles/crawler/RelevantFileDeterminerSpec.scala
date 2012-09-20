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

/**
 * @author jeffrey
 *
 */
@RunWith(classOf[JUnitRunner])
class RelevantFileDeterminerSpec extends FunSpec with ShouldMatchers {
  describe("A RelevantFileDeterminer") {
    it("should determine file with an extension of .feature are relevant") {
      val builder = FileSystemBuilder.build()
      builder.addFolder("ram://features/")
      val file = builder.addFile("ram://features/my.feature")

      val relevantFileDeterminer = new RelevantFileDeterminer()
      relevantFileDeterminer.isRelevant(file) should be(true)
    }

    it("should determine file with an extension of .markdown are relevant") {
      val builder = FileSystemBuilder.build()
      val file = builder.addFile("ram://features/my.markdown")

      val relevantFileDeterminer = new RelevantFileDeterminer()
      relevantFileDeterminer.isRelevant(file) should be(true)
    }

    it("should determine file with an extension of .mdown are relevant") {
      val builder = FileSystemBuilder.build()
      val file = builder.addFile("ram://features/my.mdown")

      val relevantFileDeterminer = new RelevantFileDeterminer()
      relevantFileDeterminer.isRelevant(file) should be(true)
    }

    it("should determine file with an extension of .mkdn are relevant") {
      val builder = FileSystemBuilder.build()
      val file = builder.addFile("ram://features/my.mkdn")

      val relevantFileDeterminer = new RelevantFileDeterminer()
      relevantFileDeterminer.isRelevant(file) should be(true)
    }

    it("should determine file with an extension of .md are relevant") {
      val builder = FileSystemBuilder.build()
      val file = builder.addFile("ram://features/my.md")

      val relevantFileDeterminer = new RelevantFileDeterminer()
      relevantFileDeterminer.isRelevant(file) should be(true)
    }

    it("should determine file with an extension of .mdwn are relevant") {
      val builder = FileSystemBuilder.build()
      val file = builder.addFile("ram://features/my.mdwn")

      val relevantFileDeterminer = new RelevantFileDeterminer()
      relevantFileDeterminer.isRelevant(file) should be(true)
    }

    it("should determine file with an extension of .mdtxt are relevant") {
      val builder = FileSystemBuilder.build()
      val file = builder.addFile("ram://features/my.mdtxt")

      val relevantFileDeterminer = new RelevantFileDeterminer()
      relevantFileDeterminer.isRelevant(file) should be(true)
    }

    it("should determine file with an extension of .mdtext are relevant") {
      val builder = FileSystemBuilder.build()
      val file = builder.addFile("ram://features/my.mdtext")

      val relevantFileDeterminer = new RelevantFileDeterminer()
      relevantFileDeterminer.isRelevant(file) should be(true)
    }

    it("should determine file with an extension of .text are relevant") {
      val builder = FileSystemBuilder.build()
      val file = builder.addFile("ram://features/my.text")

      val relevantFileDeterminer = new RelevantFileDeterminer()
      relevantFileDeterminer.isRelevant(file) should be(true)
    }

    it("should determine file with an extension of .txt are relevant") {
      val builder = FileSystemBuilder.build()
      val file = builder.addFile("ram://features/my.txt")

      val relevantFileDeterminer = new RelevantFileDeterminer()
      relevantFileDeterminer.isRelevant(file) should be(true)
    }

    it("should determine folders are not relevant") {
      val builder = FileSystemBuilder.build()
      val folder = builder.addFolder("ram://features/")

      val relevantFileDeterminer = new RelevantFileDeterminer()
      relevantFileDeterminer.isRelevant(folder) should be(false)
    }

  }
}