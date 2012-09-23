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

package org.pickles

import org.apache.commons.vfs.FileObject

class Configuration {
  var featureFolder: FileObject = null
  var outputFolder: FileObject = null
  var documentationFormat: String = "HTML"
  var language: String = ""
  var testResultsFormat: String = null
  var hasTestResults: Boolean = testResultsFile.isDefined
  var testResultsFile: Option[FileObject] = None
  var systemUnderTestName: String = ""
  var systemUnderTestVersion: String = ""
}