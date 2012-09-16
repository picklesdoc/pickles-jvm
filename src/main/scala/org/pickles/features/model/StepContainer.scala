package org.pickles.features.model

import java.util.ArrayList

trait StepContainer {
  var steps : List[Step] = List()
  def getSteps() : Seq[Step] = this.steps
  def addStep(step : Step) = this.steps ::= step
}