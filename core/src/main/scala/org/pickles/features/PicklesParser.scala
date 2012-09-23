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

import org.apache.commons.vfs.FileObject
import gherkin.lexer.I18nLexer
import java.io.InputStreamReader
import java.util.Scanner
import org.pickles.features.model.Feature
import scala.io.BufferedSource

/**
 * @author jeffrey
 *
 */
class PicklesParser {
  def isAllWhitespace(text: String) = text == "" || text.forall { _.isWhitespace }

  def parse(file: FileObject): Feature = {
    val listener = new PicklesListener
    val lexer = new I18nLexer(listener)

    val contentStream = file.getContent.getInputStream
    val lineSeparator = System.getProperty("line.separator")
    var contentSource: BufferedSource = null
    var content: Option[String] = None
    try {
      contentSource = io.Source.fromInputStream(contentStream)
      val contentText = contentSource.mkString
      if (isAllWhitespace(contentText)) {
        content = None
      } else {
        content = Some(contentText)
      }
    } finally {
      if (contentSource != null) { contentSource.close }
      contentStream.close
    }

    content match {
      case Some(_content) => {
        lexer.scan(_content)
        listener.getFeature()
      }
      case None => throw ParseException(file)
    }
  }
}