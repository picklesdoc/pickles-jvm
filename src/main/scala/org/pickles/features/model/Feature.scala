package org.pickles.features.model

import java.util.ArrayList

class Feature(name: String, description: String) extends Taggable {
  var scenarios: List[Scenario] = List()
  var scenarioOutlines: List[ScenarioOutline] = List()
  var background: Scenario = null

  def setBackground(background: Scenario) = this.background = background
  def addScenario(scenario: Scenario) = scenarios ::= scenario
  def addScenarioOutline(scenarioOutline: ScenarioOutline) = scenarioOutlines ::= scenarioOutline
}

object Feature {
  def apply(
    name: String,
    description: String,
    optionalBackground: Option[Scenario],
    scenarios: Seq[Scenario],
    scenarioOutlines: Seq[ScenarioOutline]) = {
    val feature = new Feature(name, description)
    optionalBackground match {
      case Some(background) => feature.setBackground(background)
      case None => None
    }
    scenarios.foreach(scenario => feature.addScenario(scenario))
    scenarioOutlines.foreach(scenarioOutline => feature.addScenarioOutline(scenarioOutline))
    feature
  }
}