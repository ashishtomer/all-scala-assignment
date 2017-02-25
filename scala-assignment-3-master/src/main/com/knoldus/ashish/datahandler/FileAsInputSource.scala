package main.com.knoldus.ashish.datahandler

import java.io.{File, FileNotFoundException, IOException}

import scala.io.Source

/**
  * Created by ashish on 1/27/17.
  */

/*
* This class is to get text from a file on directory
* */
final class FileAsInputSource(sourceArg : File) extends InputGrabber{

  val source : File = sourceArg

  @throws[Exception]
  override def getText(): String = {

    /* The previous method of getting text out of the file
     wasn't efficient way. Because mkString reads file byte by byte */

    val text : StringBuilder = new StringBuilder("")
    try {

        for (line <- Source.fromFile(source).getLines())
          text.append(s"$line\n")

        //Remove extra \n from end
        text.deleteCharAt(text.length - 1)

    } catch {
        case ex:FileNotFoundException => {
          println("The given file doesn't exist. Please provide a correct file name.")
          println(ex.getMessage)
        }
        case ex:IOException => {
          println(s"IOException occurred while trying to use (open / read) ${source.getName}")
          println(ex.getMessage)
          text.delete(0, text.length)
        }
        case ex:Exception => {
          println("Some exception occurred ; please handle that exception")
          println(ex.getMessage)
          //If unknown Exception occurred throw it. Analyze it in Unit testing!
          throw ex
        }
    }
    text.toString
  }

}
