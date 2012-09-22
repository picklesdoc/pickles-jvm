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

package org.pickles.features.model

import java.util.ArrayList

class Feature(val name: String, val description: String) extends Taggable {
  var scenarios: List[Scenario] = List()
  var scenarioOutlines: List[ScenarioOutline] = List()
  var background: Option[Scenario] = None

  def setBackground(background: Scenario) = this.background = Some(background)
  def addScenario(scenario: Scenario) = scenarios ::= scenario
  def addScenarioOutline(scenarioOutline: ScenarioOutline) = scenarioOutlines ::= scenarioOutline
}

object Feature {
  def apply(
    name: String,
    description: String,
    tags: Seq[String],
    optionalBackground: Option[Scenario],
    scenarios: Seq[Scenario],
    scenarioOutlines: Seq[ScenarioOutline]) = {
    val feature = new Feature(name, description)
    optionalBackground match {
      case Some(background) => feature.setBackground(background)
      case None => None
    }
    tags.foreach { feature.addTag(_) }
    scenarios.foreach { feature.addScenario(_) }
    scenarioOutlines.foreach { feature.addScenarioOutline(_) }
    feature
  }
}