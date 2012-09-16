/**
 *
 */
package org.pickles.features.builder

import org.pickles.features.model.Step
import org.pickles.features.model.Scenario

/**
 * @author jeffrey
 *
 */
class ScenarioBuilder {
  var steps: List[Step] = List()
  var tags: List[String] = List()
  var description: String = _
  var name: String = _

  def setName(name: String) = this.name = name
  def setDescription(description: String) = this.description = description
  def addStep(step: Step) = this.steps ::= step
  def addTag(tag: String) = this.tags ::= tag
  def getResult(): Scenario = Scenario(name, description, steps, tags)
}