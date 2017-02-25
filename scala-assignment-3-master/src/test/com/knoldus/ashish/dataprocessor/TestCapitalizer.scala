package test.com.knoldus.ashish.dataprocessor

import main.com.knoldus.ashish.dataprocessor.Capitalizer
import org.junit.Test
import org.junit.Assert._

/**
  * Created by ashish on 1/29/17.
  */
class TestCapitalizer {

  @Test
  def testForNumbers(): Unit = {
    assertEquals(Capitalizer.capitalizeText("1233"), "1233")
  }

  @Test
  def testForNullString(): Unit = {
    assertEquals(Capitalizer.capitalizeText(""), "")
  }

  @Test
  def testForEmptyString(): Unit = {
    assertEquals(Capitalizer.capitalizeText("   "), "   ")
  }

  @Test
  def testForUpperCaseString(): Unit = {
    assertEquals(Capitalizer.capitalizeText("ABCD"), "ABCD")
  }

  @Test
  def testForLowerCaseString(): Unit = {
    assertEquals(Capitalizer.capitalizeText("abcd efgh"), "ABCD EFGH")
  }

  @Test
  def testForMixedCaseString(): Unit = {
    assertEquals(Capitalizer.capitalizeText("abcd DEFG gh123"), "ABCD DEFG GH123")
  }

}