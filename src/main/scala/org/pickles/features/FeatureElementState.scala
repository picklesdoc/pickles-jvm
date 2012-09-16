/**
 *
 */
package org.pickles.features

/**
 * @author jeffrey
 *
 */
class FeatureElementState {
  var IsBackgroundActive: Boolean = _
  var IsScenarioActive: Boolean = _
  var IsScenarioOutlineActive: Boolean = _

  def SetBackgroundActive() {
    IsBackgroundActive = true
    IsScenarioActive = false
    IsScenarioOutlineActive = false
  }

  def SetScenarioActive() {
    IsBackgroundActive = false
    IsScenarioActive = true
    IsScenarioOutlineActive = false
  }

  def SetScenarioOutlineActive() {
    IsBackgroundActive = false
    IsScenarioActive = false
    IsScenarioOutlineActive = true
  }
}