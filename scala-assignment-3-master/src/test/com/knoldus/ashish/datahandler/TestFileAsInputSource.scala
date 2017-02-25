package test.com.knoldus.ashish.datahandler

import java.io.File

import main.com.knoldus.ashish.datahandler.FileAsInputSource
import org.junit.{BeforeClass, Rule, Test}
import org.junit.Assert._
import org.junit.rules.ExpectedException

/**
  * Created by ashish on 1/28/17.
  */

class TestFileAsInputSource {

  @BeforeClass
  val source = new File("/home/ashish/Documents/workspace/scala-assignment-3/src/resources/input/capital/datafilecapital")
  val rightInputSource = new FileAsInputSource(source)
  val wrongInputSource = new FileAsInputSource(source)

  //If the name of the resource is right,
  //getText() will NOT return null
  @Test
  def notReturningNull(): Unit = {
    assertNotEquals(rightInputSource.getText(), null)
  }

  //If the name of the resource is wrong,
  //getText() will return empty String
  //the exception is handled already
  @Test
  def fileNotFoundExceptionHandled(): Unit = {
    assertEquals(wrongInputSource.getText(), "")
  }

/* ----Not working -:- DOUBT! ---

  @Test{val expected = classOf[Exception]}
  def handleException() : Unit = {
      wrongInputSource.getText()
  }

*/

/* ----Not working -:- DOUBT! ---

  @Rule
  val thrown : ExpectedException = ExpectedException.none

  @Test
  def handleException(): Unit = {

    thrown.expect(classOf[Exception])
    wrongInputSource.getText()

  }

*/


}
