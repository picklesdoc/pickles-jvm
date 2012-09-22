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

object Keyword extends Enumeration {
  type Keyword = Value
  val Given = Value("Given")
  val When = Value("When")
  val Then = Value("Then")
  val And = Value("And")
  val But = Value("But")

  def parse(text: String) = text.toLowerCase() match {
    case "given" => Keyword.Given
    case "when" => Keyword.When
    case "then" => Keyword.Then
    case "and" => Keyword.And
    case "but" => Keyword.But
    case _ => Keyword.Given // TODO - replace this with an exception
  }
}