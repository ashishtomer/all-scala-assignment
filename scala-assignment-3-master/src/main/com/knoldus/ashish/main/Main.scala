package main.com.knoldus.ashish.main

import java.io.File

import main.com.knoldus.ashish.datahandler._
import main.com.knoldus.ashish.dataprocessor.Capitalizer
import main.com.knoldus.ashish.dataprocessor.WordCounter

/**
  * Created by ashish on 1/27/17.
  */
class Main {

}

object Main {

  def main(args: Array[String]): Unit = {

    val capitalInputDirectory = "/home/ashish/Documents/workspace/scala-assignment-3/src/resources/input/capital"
    val countInputDirectory = "/home/ashish/Documents/workspace/scala-assignment-3/src/resources/input/count/"
    val outputDirectory = "/home/ashish/Documents/workspace/scala-assignment-3/src/resources/output/"

    val listCapitalFiles : Array[File] = FileInDirectory.getFiles(capitalInputDirectory)

    for(file <- listCapitalFiles) {

      new FileAsOutputSource(outputDirectory, file.getName).exportText(Capitalizer.capitalizeText(new FileAsInputSource(file).getText()))

    }

    val listCountFiles : Array[File] = FileInDirectory.getFiles(countInputDirectory)

    for(file <- listCountFiles) {

      new FileAsOutputSource(outputDirectory, file.getName).exportText(WordCounter.wordCount(new FileAsInputSource(file).getText()))

    }

  }

}