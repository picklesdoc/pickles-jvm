/**
 *
 */
package org.pickles.features.model

/**
 * @author jeffrey
 *
 */
class Scenario(name: String, description: String) extends Taggable with StepContainer {
  var feature: Feature = null

  def setFeature(feature: Feature) {
    this.feature = feature
  }

  def getFeature() {
    this.feature
  }
}

object Scenario {
  def apply(name: String, description: String, steps: Seq[Step], tags: Seq[String]) = {
    val scenario = new Scenario(name, description)
    steps.foreach(step => scenario.addStep(step))
    tags.foreach(tag => scenario.addTag(tag))
    scenario
  }
}