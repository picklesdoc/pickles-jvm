package org.pickles.crawler

import org.apache.commons.vfs.FileObject

class MarkdownFile(fileObject : FileObject, parent : Folder, content: String) extends File(fileObject, parent) {

}