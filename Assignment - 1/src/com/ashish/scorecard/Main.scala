package com.ashish.scorecard

/**
  * Created by ashish on 1/23/17.
  */

/*
* Every Student has some marks associated with it. Student details contains its id and name.
* And for Marks, there are subjectId, studentId and number of marks a student scored.
*
* Q1. Now, I require a case class named ScoreCard having fields (studentId: Long, marks: Map[Long, Float], percentage: Float).
*
* Write a method which takes no parameter and generates a Map with key student name and value as ScoreCard. As there can be more than one student with same name, the logic we have to follow is that, if two or more student has same name the key shold be the name of the student and the values (ScoreCard s) should be in a List, otherwise the key should be the student name and value should be the case class ScoreCard. e.g. Map should be Map[String, AnyRef].
* Write a method which takes input as student name and print the score cards. If it finds one or more than one score card  print all of them other wise print "No data found". The print should be in increasing order of the student id.
* */

class Main {

}

object Main {
  def main(args: Array[String]): Unit = {

    val scoreCardCreator = new ScoreCardCreator

    scoreCardCreator.printScoreCard("Akhil")
    scoreCardCreator.printScoreCard("Ashish")
    scoreCardCreator.printScoreCard("Sachin")
  }
}
