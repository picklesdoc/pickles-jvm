package org.pickles.features.model

class ScenarioOutline(name: String, description: String) extends Taggable with StepContainer {
  var feature: Feature = null
  var examples: List[Example] = List()

  def getFeature() = feature
  def setFeature(feature: Feature) = this.feature = feature
  def getExamples(): Seq[Example] = examples
  def addExample(example: Example) = this.examples ::= example
}