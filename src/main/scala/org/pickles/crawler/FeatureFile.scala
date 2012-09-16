package org.pickles.crawler

import org.pickles.features.model.Feature
import org.apache.commons.vfs.FileObject

class FeatureFile(fileObject: FileObject, parent: Folder, feature: Feature) extends File(fileObject, parent) {
}

object FeatureFile {
  def apply(fileObject: FileObject, parent: Folder, feature: Feature) = {
    if (parent == null) {
      throw new Exception
    }
    new FeatureFile(fileObject, parent, feature)
  }
}