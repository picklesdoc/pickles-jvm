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

package org.pickles.crawler

import org.apache.commons.vfs.FileObject

/**
 * @author jeffrey
 *
 */
class DirectoryTreeCrawler {
  def crawl(rootPath: FileObject): TreeItem = {
    crawl(rootPath, None)
  }

  def crawl(rootPath: FileObject, rootNode: Option[TreeItem]): TreeItem = {
    null
    //    val currentNode = featureNodeFactory.Create(rootNode != null ? rootNode.OriginalLocation : null, directory);
    //    var newRootNode = rootNode.map {Some(_)}

    //    var tree = new GeneralTree<IDirectoryTreeNode>(currentNode);
    //
    //    bool isRelevantFileFound = false;
    //    foreach (FileInfo file in directory.GetFiles().Where(file => relevantFileDetector.IsRelevant(file)))
    //    {
    //        isRelevantFileFound = true;
    //
    //        IDirectoryTreeNode node = null;
    //        try
    //        {
    //            node = featureNodeFactory.Create(rootNode.OriginalLocation, file);
    //        }
    //        catch (Exception)
    //        {     
    //            if (log.IsWarnEnabled) log.WarnFormat("The file, {0}, will be ignored because it could not be read in properly", file.FullName);
    //        }
    //
    //        if (node != null) tree.Add(node);
    //    }
    //
    //    bool isRelevantDirectoryFound = false;
    //    foreach (DirectoryInfo subDirectory in directory.GetDirectories())
    //    {
    //        GeneralTree<IDirectoryTreeNode> subTree = Crawl(subDirectory, rootNode);
    //        if (subTree != null)
    //        {
    //            isRelevantDirectoryFound = true;
    //            tree.Add(subTree);
    //        }
    //    }
    //
    //    if (!isRelevantFileFound && !isRelevantDirectoryFound) return null;
    //
    //    return tree;
  }
}