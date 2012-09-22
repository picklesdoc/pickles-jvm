/**
 *
 */
package org.pickles.documentation

import org.pickles.crawler.TreeItem

/**
 * @author jeffrey
 *
 */
trait DocumentationBuilder {
  def build(root: TreeItem)
}