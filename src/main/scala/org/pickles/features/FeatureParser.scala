/**
 *
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