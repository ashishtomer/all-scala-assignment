package edu.knoldus.filer

import java.io.File

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by ashish on 2/2/17.
  * This class returns list  of files available in a directory
  */

class FileLister {
  /**
    * Returns the list of files (with their directories) in a directory
    * @param directory : directory for which we need the list of files
    * @throws NullPointerException -  In case the correct directory isn't mentioned throw exception
    * @return : List of directories
    */
  @throws[NullPointerException]
  def listFiles(directory: String): Future[List[String]] = {

    val classLoader: ClassLoader = getClass.getClassLoader
    val rootDirectory: File = new File(classLoader.getResource(directory).getFile)

    val listOfFiles: Future[List[File]] = Future {
      filesInDirectory(rootDirectory)
    }

    val finalList = listOfFiles.map(_.map(file => reachFromFileToRoot(List[File](file), rootDirectory)))

    getListInString(finalList)
  }

  /**
    * Returns the list of directories for all files
    * @param listOfFiles : list of -> list of all intemediate folders for the given file
    * @return : returns the list of directories of all files
    */
  private def getListInString(listOfFiles: Future[List[List[File]]]): Future[List[String]] = {
    listOfFiles.map(list => {
      list.map(innerList => {
        stringify(innerList)
      })
    })
  }

  /**
    * This method concats the file names with '/' as separator
    * @param list  - list of files (from root to the leaf node (file))
    * @param pointer  - used as counter to get files from list one by one
    * @param result - used as result holder
    * @return - returns the concated directory (relative to root) for file
    */
  private def stringify(list: List[File], pointer: Int = 0, result: String = ""): String = {
    if (pointer < list.length) {
      result + list(pointer).getName + "/" + stringify(list, pointer + 1)
    }
    else {
      result
    }
  }

  /**
    * All the files (not folders) available in directory and subdirectories
    * @param directory : directory for which we need the files
    * @return : list of files available in the given directory and all its subdirectories
    */
  private def filesInDirectory(directory: File): List[File] = {

    if (directory.isFile) {
      List(directory)
    } else {

      val files: List[File] = directory.listFiles.toList

      files.map(file => {
        filesInDirectory(file)
      }).flatten
    }

  }

  /**
    * this method finds the direct parents of a file upto given root
    * @param files file for which we need to return parent
    * @param root root is the upper bound directory relative to which we need all upper directories
    * @return returns the list of parent files
    */
  private def reachFromFileToRoot(files: List[File], root: File): List[File] = {

    if (files.head.getParentFile != root) {
      reachFromFileToRoot(files.head.getParentFile :: files, root)
    } else {
      files
    }
  }

}
