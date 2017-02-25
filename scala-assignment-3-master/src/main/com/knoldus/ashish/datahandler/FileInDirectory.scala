package main.com.knoldus.ashish.datahandler

import java.io.File

/**
  * Created by ashish on 1/30/17.
  */
object FileInDirectory {

  def getFiles(directoryStr : String) : Array[File] = {

    (new File(directoryStr)).listFiles

  }

}
