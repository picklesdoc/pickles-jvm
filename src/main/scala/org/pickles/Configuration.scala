package org.pickles

import org.apache.commons.vfs.FileObject

class Configuration {
  var featureFolder: FileObject = null
  var outputFolder: FileObject = null
  //def documentationFormat: DocumentationFormat = null
  var language: String = ""
  //def testResultsFormat: TestResultsFormat = null
  var hasTestResults: Boolean = testResultsFile.isDefined
  var testResultsFile: Option[FileObject] = None
  var systemUnderTestName: String = ""
  var systemUnderTestVersion: String = ""
}