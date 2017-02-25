package main.com.knoldus.ashish.datahandler

import java.io.{File, FileNotFoundException, IOException, PrintWriter}

/**
  * Created by ashish on 1/27/17.
  */

/*
* This class is to save text to a file on directory
* */

final class FileAsOutputSource(directoryArg: String,sourceArg : String) extends OutputExporter{

  val source : String = sourceArg

  //The directory is hard coded - with DI it can be mentioned in XML file
  override val outputLocation = directoryArg

  override def exportText(text: String): Boolean = {

    val outputFile = new File(s"$outputLocation$source")
    val writer = new PrintWriter(outputFile)

    if(text.length == 0) {
      return true
    }

    writer.println(text)

    //PrintWriter object doesn't throw Exception so using its checkError() method

    if(writer.checkError()) {
        println("Couldn't write data to file. Error!")
        writer.close()
        return false
    }

    writer.close()
    true

  }

}