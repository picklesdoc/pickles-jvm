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

class ScenarioOutline(name: String, description: String) extends Taggable with StepContainer {
  var feature: Feature = null
  var examples: List[Example] = List()

  def getFeature() = feature
  def setFeature(feature: Feature) = this.feature = feature
  def getExamples(): Seq[Example] = examples
  def addExample(example: Example) = this.examples ::= example
}