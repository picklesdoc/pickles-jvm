/**
 *
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
  def parse(file: FileObject): Feature = {
    val listener = new PicklesListener
    val lexer = new I18nLexer(listener)

    val contentStream = file.getContent.getInputStream
    val lineSeparator = System.getProperty("line.separator")
    var contentSource: BufferedSource = null
    var content: Option[String] = None
    try {
      contentSource = io.Source.fromInputStream(contentStream)
      content = Some(contentSource.mkString)
    } finally {
      if (contentSource != null) { contentSource.close }
      contentStream.close
    }

    content match {
      case Some(_content) => {
        lexer.scan(_content)
        listener.getFeature
      }
      case None => throw ParseException(file)
    }
  }
}