package test.com.knoldus.ashish.datahandler

import java.io.File

import main.com.knoldus.ashish.datahandler.{FileAsInputSource, FileAsOutputSource, OutputExporter}
import org.junit.Test
import org.junit.Assert._

/**
  * Created by ashish on 1/29/17.
  */
class TestFileAsOutputSource {

  val outputDirectory = "/home/ashish/Documents/workspace/scala-assignment-3/src/resources/output/"
  val outputSource = "testFileOutput.txt"
  val outputSourceObj : OutputExporter= new FileAsOutputSource(outputDirectory, outputSource)

  val outputFileRef = new File(outputDirectory+outputSource)

  val source = new File("/home/ashish/Documents/workspace/scala-assignment-3/src/resources/input/capital/datafilecapital")
  val text = new FileAsInputSource(source).getText()

  @Test
  def trueWhenEmptyText() : Unit = {
    assertTrue(outputSourceObj.exportText(""))
  }

  @Test
  def fileSizeZeroWhenEmptyText() : Unit = {
    outputSourceObj.exportText("")
    assertEquals(outputFileRef.length, 0)
  }

  @Test
  def fileCreatedOnExport(): Unit = {
    outputSourceObj.exportText(text)
    assertTrue(outputFileRef.exists && outputFileRef.isFile)
  }

  @Test
  def allTextIsExported(): Unit = {
    outputSourceObj.exportText(text)
    assertEquals(outputFileRef.length, text.length)
  }

  @Test
  def trueWhenCorrectText(): Unit = {
    assertTrue(outputSourceObj.exportText(text))
  }

}