package test.com.knoldus.ashish.dataprocessor

import main.com.knoldus.ashish.dataprocessor.WordCounter
import org.junit.Test
import org.junit.Assert._

/**
  * Created by ashish on 1/29/17.
  */
class TestWordCounter {

  @Test
  def countWordsInOnlyWhiteSpace(): Unit = {
    assertEquals(WordCounter.wordCount("    \t        \n  "),"")
  }

  @Test
  def countWordsInOnlyCommas(): Unit = {
    assertEquals(WordCounter.wordCount(",,,,,"),"")
  }

  @Test
  def countWordsInOnlyDots(): Unit = {
    assertEquals(WordCounter.wordCount("......."),"")
  }

  @Test
  def testForWordsWithSpaces(): Unit = {
    val word1 : String = "abc"
    val word2 : String = "efg"
    val word3 : String = "xyz"
    assertEquals(WordCounter.wordCount(s"  $word1 $word2 $word3"),f"${word1}%20s ~> 1\n"+
                                                        f"${word3}%20s ~> 1\n"+
                                                        f"${word2}%20s ~> 1\n")
  }

  @Test
  def countWordsWithSpacesCommasAndDots(): Unit = {
    val word1 : String = "abc"
    val word2 : String = "efg"
    val word3 : String = "wxy"
    assertEquals(WordCounter.wordCount(s"   ,$word1,. $word2.$word3., ,     ."),f"${word3}%20s ~> 1\n"+
                                                                          f"${word1}%20s ~> 1\n"+
                                                                          f"${word2}%20s ~> 1\n")
  }




}
