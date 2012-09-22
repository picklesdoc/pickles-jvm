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

import gherkin.lexer.Listener
import org.pickles.features.builder._
import org.pickles.features.model._
import scala.collection.immutable.List

/**
 * @author jeffrey
 *
 */
class PicklesListener extends Listener {

  var keyword: String = _
  var name: String = _
  var description: String = _
  var tags: List[String] = List()
  var background: Option[Scenario] = None
  var scenarios: List[Scenario] = List()
  var scenarioOutlines: List[ScenarioOutline] = List()
  var backgroundBuilder = new ScenarioBuilder
  var scenarioBuilder = new ScenarioBuilder
  var scenarioOutlineBuilder = new ScenarioOutlineBuilder
  var stepBuilder = new StepBuilder
  var exampleBuilder = new ExampleBuilder

  val featureElementState = new FeatureElementState

  def getFeature(): Feature = {
    Feature(name, description, tags, background, scenarios, scenarioOutlines)
  }

  def addTagToElement(tag: String) = {
    if (featureElementState.isBackgroundActive) {
      backgroundBuilder.addTag(tag)
    } else if (featureElementState.isScenarioActive) {
      scenarioBuilder.addTag(tag)
    } else if (featureElementState.isScenarioOutlineActive) {
      scenarioOutlineBuilder.addTag(tag)
    }
  }

  def addStepToElement(step: Step) = {
    if (featureElementState.isBackgroundActive) {
      backgroundBuilder.addStep(step)
    } else if (featureElementState.isScenarioActive) {
      scenarioBuilder.addStep(step)
    } else if (featureElementState.isScenarioOutlineActive) {
      scenarioOutlineBuilder.addStep(step)
    }
  }

  def captureAndStoreRemainingElements() = {
    if (featureElementState.isBackgroundActive) {
      backgroundBuilder.addStep(stepBuilder.getResult())
      background = Some(backgroundBuilder.getResult())
    } else if (featureElementState.isScenarioActive) {
      if (stepBuilder != null) scenarioBuilder.addStep(stepBuilder.getResult());
      scenarios ::= scenarioBuilder.getResult()
    } else if (featureElementState.isScenarioOutlineActive) {
      if (stepBuilder != null) scenarioOutlineBuilder.addStep(stepBuilder.getResult());
      scenarioOutlines ::= scenarioOutlineBuilder.getResult()
    }

    stepBuilder = null;
    scenarioBuilder = null;
    scenarioOutlineBuilder = null;
    backgroundBuilder = null;
  }

  def comment(comment: String, line: Integer) = {
    // TODO - add searching for meta tags here
  }

  def tag(tag: String, line: Integer) = {
    if (featureElementState.isFeatureActive) {
      this.tags ::= tag
    } else {
      addTagToElement(tag)
    }
  }

  def feature(keyword: String, name: String, description: String, line: Integer) = {
    featureElementState.setFeatureActive
    this.name = name
    this.description = description
  }

  def background(keyword: String, name: String, description: String, line: Integer) = {
    captureAndStoreRemainingElements
    featureElementState.setBackgroundActive
    backgroundBuilder = new ScenarioBuilder
    backgroundBuilder.setName(name)
    backgroundBuilder.setDescription(description)
  }

  def scenario(keyword: String, name: String, description: String, line: Integer) = {
    captureAndStoreRemainingElements
    featureElementState.setScenarioActive
    scenarioBuilder = new ScenarioBuilder
    scenarioBuilder.setName(name)
    scenarioBuilder.setDescription(description)
  }

  def scenarioOutline(keyword: String, name: String, description: String, line: Integer) = {
    captureAndStoreRemainingElements
    featureElementState.setScenarioOutlineActive
    scenarioOutlineBuilder = new ScenarioOutlineBuilder
    scenarioOutlineBuilder.setName(name)
    scenarioOutlineBuilder.setDescription(description)
  }

  def examples(keyword: String, name: String, description: String, line: Integer) = {
    featureElementState.setExampleActive
    scenarioOutlineBuilder.addExample(exampleBuilder.getResult())
    exampleBuilder = new ExampleBuilder
    exampleBuilder.setName(name)
    exampleBuilder.setDescription(description)
  }

  def step(keyword: String, name: String, line: Integer) = {
    if (stepBuilder != null) {
      addStepToElement(stepBuilder.getResult())
    }

    stepBuilder = new StepBuilder
    stepBuilder.setName(name)
    stepBuilder.setKeyword(keyword)
  }

  def row(cells: java.util.List[String], line: Integer) = {
    if (featureElementState.isExampleActive) {
      exampleBuilder.addRow(TableRow(cells))
    } else {
      stepBuilder.addRow(TableRow(cells))
    }
  }

  def docString(contentType: String, content: String, line: Integer) = {
    stepBuilder.setDocString(content)

  }

  def eof() = { captureAndStoreRemainingElements }

}