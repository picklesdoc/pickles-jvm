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

package org.pickles.features

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.pickles.crawler.FileSystemBuilder
import java.io.PrintWriter
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.pickles.features.model.Keyword

/**
 * @author jeffrey
 *
 */
@RunWith(classOf[JUnitRunner])
class PicklesParserSpec extends FunSpec with ShouldMatchers {
  describe("A PicklesParser") {
    it("should be able to parse a properly formatted feature file") {
      val feature =
        """
        @accepted
        Feature: A test feature
          In order to ensure all features are properly parsed
          As a developer
          I want to  test the PicklesParser class to ensure it can properly read and parse content from a file
        
          @slow
          Background: Some facts about parsing
            Here is some context about the background
            
            Given a precondition
            And another precondition
        
          @ignored
          Scenario: A scenario
            Here is some context about the scenario
            
            Given some other precondition with a table
            | column 1 | column 2 | 
            | value 1  | value 2  |
            When this thing happens
            Then this postcondition is present
        
          @outline
          Scenario Outline: A scenario outline
            Here is some context about the scenario outline
        
            Given yet another precondition
            When this <thing> happens
            Then this postcondition is present
        
            Examples: where thing is good
            | thing  |
            | good   |
            | gooder |
        
            Examples: where thing is bad
            | thing  |
            | bad    |
            | badder |
        """

      val builder = FileSystemBuilder.build()
      val featureFile = builder.addFile("ram://feature.txt")
      val featureFileOutputStream = featureFile.getContent().getOutputStream()
      val writer = new PrintWriter(featureFileOutputStream)
      writer.write(feature)
      writer.flush()
      writer.close()
      featureFileOutputStream.close()

      val parser = new PicklesParser()
      val parsedFeature = parser.parse(featureFile)

      parsedFeature should not be (null)
      parsedFeature.name should equal("A test feature")
      parsedFeature.description should equal(
        """In order to ensure all features are properly parsed
As a developer
I want to  test the PicklesParser class to ensure it can properly read and parse content from a file""")

      parsedFeature.getTags should have size (1)
      parsedFeature.getTags.head should equal("@accepted")

      parsedFeature.background should be('defined)
      val background = parsedFeature.background.get
      background should have(
        'name("Some facts about parsing"),
        'description("Here is some context about the background"))
      background.getTags should have size (1)
      background.getTags.head should be("@slow")
      background.steps should have size (2)

      parsedFeature.scenarios should have size (1)
      val scenario = parsedFeature.scenarios.head
      scenario should have(
        'name("A scenario"),
        'description("Here is some context about the scenario"))
      scenario.getTags should have size (1)
      scenario.getTags.head should be("@ignored")
      scenario.steps should have size (3)
      val table = scenario.steps.find({ _.keyword == Keyword.Given }).head.getTable
      table.getRows should have size (2) // NOTE - this includes the header row

      parsedFeature.scenarioOutlines should have size (1)
      val scenarioOutline = parsedFeature.scenarioOutlines.head
      scenarioOutline should have(
        'name("A scenario outline"),
        'description("Here is some context about the scenario outline"))
      scenarioOutline.getTags should have size (1)
      scenarioOutline.getTags.head should be("@outline")
      scenarioOutline.steps should have size (3)
      scenarioOutline.examples should have size (2)
    }

    it("should be able to parse a feature file with no scenarios") {
      val feature =
        """
        @accepted
        Feature: A test feature
          In order to ensure all features are properly parsed
          As a developer
          I want to  test the PicklesParser class to ensure it can properly read and parse content from a file
        """

      val builder = FileSystemBuilder.build()
      val featureFile = builder.addFile("ram://feature.txt")
      val featureFileOutputStream = featureFile.getContent().getOutputStream()
      val writer = new PrintWriter(featureFileOutputStream)
      writer.write(feature)
      writer.flush()
      writer.close()
      featureFileOutputStream.close()

      val parser = new PicklesParser()
      val parsedFeature = parser.parse(featureFile)

      parsedFeature should not be (null)
      parsedFeature.name should equal("A test feature")
      parsedFeature.description should equal(
        """In order to ensure all features are properly parsed
As a developer
I want to  test the PicklesParser class to ensure it can properly read and parse content from a file""")

      parsedFeature.getTags should have size (1)
      parsedFeature.getTags.head should equal("@accepted")
    }

    it("should throw an exception when given an empty file") {
      val feature = "      "

      val builder = FileSystemBuilder.build()
      val featureFile = builder.addFile("ram://feature.txt")
      val featureFileOutputStream = featureFile.getContent().getOutputStream()
      val writer = new PrintWriter(featureFileOutputStream)
      writer.write(feature)
      writer.flush()
      writer.close()
      featureFileOutputStream.close()

      val parser = new PicklesParser()
      evaluating { parser.parse(featureFile) } should produce[ParseException]

    }
  }
}