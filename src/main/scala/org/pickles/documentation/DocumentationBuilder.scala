/**
 *
 */
package org.pickles.documentation

import org.pickles.crawler.TreeItem
import org.pickles.Configuration
import org.pickles.documentation.html.HtmlDocumentationBuilder

/**
 * @author jeffrey
 *
 */
trait DocumentationBuilder {
  def build(root: TreeItem, configuration: Configuration)
}

object DocumentationBuilder {
  def apply(configuration: Configuration) = {
    new HtmlDocumentationBuilder
  }
}