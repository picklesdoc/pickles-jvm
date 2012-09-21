/**
 *
 */
package org.pickles.crawler

import scala.util.control.Exception
import org.apache.commons.vfs.FileObject

/**
 * @author jeffrey
 *
 */
case class IrrelevantFileException(file: FileObject) extends Exception