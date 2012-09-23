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

/**
 * @author jeffrey
 *
 */
class FeatureElementState {
  var isFeatureActive: Boolean = true
  var isBackgroundActive: Boolean = false
  var isScenarioActive: Boolean = false
  var isScenarioOutlineActive: Boolean = false
  var isExampleActive: Boolean = false

  def setExampleActive = isExampleActive = true

  def setFeatureActive() = {
    isExampleActive = false
    isFeatureActive = true
    isBackgroundActive = false
    isScenarioActive = false
    isScenarioOutlineActive = false
  }

  def setBackgroundActive() = {
    isExampleActive = false
    isFeatureActive = false
    isBackgroundActive = true
    isScenarioActive = false
    isScenarioOutlineActive = false
  }

  def setScenarioActive() = {
    isExampleActive = false
    isFeatureActive = false
    isBackgroundActive = false
    isScenarioActive = true
    isScenarioOutlineActive = false
  }

  def setScenarioOutlineActive() = {
    isExampleActive = false
    isFeatureActive = false
    isBackgroundActive = false
    isScenarioActive = false
    isScenarioOutlineActive = true
  }
}