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
import org.pickles.features.builder.ScenarioBuilder
import org.pickles.features.builder.ScenarioOutlineBuilder
import org.pickles.features.builder.StepBuilder
import org.pickles.features.model.Feature
import org.pickles.features.model.Scenario
import org.pickles.features.model.ScenarioOutline

/**
 * @author jeffrey
 *
 */
class FeatureParser extends Listener {

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

  val featureElementState = new FeatureElementState

  def getFeature(): Feature = {
    Feature(name, description, background, scenarios, scenarioOutlines)
  }

  def comment(comment: String, line: Integer) = {
    // TODO - add searching for meta tags here
  }

  def tag(tag: String, line: Integer) = {
    this.tags ::= tag
  }

  def feature(keyword: String, name: String, description: String, line: Integer) = {
  }

  def background(keyword: String, name: String, description: String, line: Integer) = {
    featureElementState.SetBackgroundActive
    backgroundBuilder = new ScenarioBuilder
  }

  def scenario(keyword: String, name: String, description: String, line: Integer) = {
    featureElementState.SetScenarioActive
    scenarioBuilder = new ScenarioBuilder
  }

  def scenarioOutline(keyword: String, name: String, description: String, line: Integer) = {
    featureElementState.SetScenarioOutlineActive
    scenarioOutlineBuilder = new ScenarioOutlineBuilder
  }

  def examples(keyword: String, name: String, description: String, line: Integer) = {}

  def step(keyword: String, name: String, line: Integer) = {}

  def row(cells: java.util.List[String], line: Integer) = {}

  def docString(contentType: String, content: String, line: Integer) = {}

  def eof() = {}

}