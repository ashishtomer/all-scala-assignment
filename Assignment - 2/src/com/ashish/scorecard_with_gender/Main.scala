package com.ashish.scorecard_with_gender

/**
  * Created by akhil and ashish on 1/24/17.
  */
/*
Q2. The Student class should contain one more field this time, gender. The values of gender must be set in a Enumeration. 

Write a method getScoreCardByGender to return a tuple of ScoreCards (e.g. (List[ScoreCard], List[ScoreCard])), where first field in the tuple has male student's score card and the second field has female student's score cards.

Write a method which calls the getScoreCardByGender method and gives the result which has more than 50%.

Write a method to find out similar percentage between the two groups (male, female). for example Geetika -75, Kunal -75

Write a method fo find out the percentage that girls group has scored but no same percentage has got in the boys group. e.g. ( Geetika -75, Neha - 73, charmy - 72) - (Kunal -75, Anmol - 73, Nitin - 71) = Charmy-72

*/
class Main {

}

object Main {
  def main(args: Array[String]): Unit = {

    val scoreCardCreator = new ScoreCardCreator

    /*--- Printing the male & female score card tuples ---*/
    println(scoreCardCreator.getScoreCardsByGender)
    scoreCardCreator.printScoreCardWithPercentage(50)
    scoreCardCreator.printMaleFemaleWithSameMarks
    scoreCardCreator.printDifferentGirls
  }
}
