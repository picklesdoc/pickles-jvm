package org.pickles.features

import org.apache.commons.vfs.FileObject

case class ParseException(file: FileObject) extends Exception