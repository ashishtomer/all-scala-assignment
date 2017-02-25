package com.ashish.scorecard

import scala.collection.mutable.Map

/**
  * Created by ashish on 1/23/17.
  */
class ScoreCardCreator {

  /*----- Subjects -----*/

  private val math: Subject = Subject("Mathematics", 9028)
  private val science: Subject = Subject("Science", 2842)
  private val history: Subject = Subject("History", 2730)
  private val hindi: Subject = Subject("Hindi", 1003)
  private val english: Subject = Subject("English", 9110)

  private val subjects:List[Subject] = List(math, science, history, hindi, english)

  /*----- Students -----*/

  private val students:List[Student] = List(
    Student(902873, "Kunal"),
    Student(349394, "Shubhra"),
    Student(936239, "Ashish"),
    Student(293520, "Neha"),
    Student(882930, "Shubham"),
    Student(983402, "Shivangi"),
    Student(394201, "Prashant"),
    Student(384945, "Charmy"),
    Student(539204, "Akhil"),
    Student(904384, "Akhil")
  )

  /*----- Marks of each student -----*/

  private val marks : List[Marks] = List (

    Marks(math.subjectId, 293520, 89),
    Marks(science.subjectId, 293520, 69),
    Marks(hindi.subjectId, 293520, 87),
    Marks(english.subjectId, 293520, 91),

    Marks(math.subjectId, 983402, 92),
    Marks(science.subjectId, 983402, 56),
    Marks(hindi.subjectId, 983402, 78),
    Marks(english.subjectId, 983402, 91),

    Marks(math.subjectId, 384945, 99),
    Marks(science.subjectId, 384945, 97),
    Marks(hindi.subjectId, 384945, 98),
    Marks(english.subjectId, 384945, 91),

    Marks(math.subjectId, 904384, 40),
    Marks(science.subjectId, 904384, 30),
    Marks(hindi.subjectId, 904384, 23),
    Marks(english.subjectId, 904384, 21),

    Marks(math.subjectId, 902873, 90),
    Marks(science.subjectId, 902873, 30),
    Marks(hindi.subjectId, 902873, 43),
    Marks(english.subjectId, 902873, 91),

    Marks(science.subjectId, 349394, 68),
    Marks(math.subjectId, 349394, 62),
    Marks(english.subjectId, 349394, 27),
    Marks(history.subjectId, 349394, 36),

    Marks(science.subjectId, 936239, 72),
    Marks(math.subjectId, 936239, 92),
    Marks(hindi.subjectId, 936239, 89),
    Marks(history.subjectId, 936239, 34),

    Marks(english.subjectId, 882930, 68),
    Marks(science.subjectId, 882930, 45),
    Marks(math.subjectId, 882930, 49),
    Marks(history.subjectId, 882930, 53),

    Marks(hindi.subjectId, 394201, 51),
    Marks(english.subjectId, 394201, 93),
    Marks(history.subjectId, 394201, 56),
    Marks(science.subjectId, 394201, 34),

    Marks(science.subjectId, 539204, 90),
    Marks(math.subjectId, 539204, 49),
    Marks(english.subjectId, 539204, 32),
    Marks(hindi.subjectId, 539204, 72)

  )

  /*----- This variable stores the ScoreCards in a map -----*/
  private val scoreCardMap = collection.mutable.Map[String, AnyRef]()


  /*----- When class loads it'll automatically generate all of the scorecards in map ----*/

  generateScoreCards()


  /*----- This method prints the scorecard(s) for a given student name -----*/

  def printScoreCard(studentName : String): Unit ={

    if(scoreCardMap.contains(studentName)) {

      scoreCardMap(studentName) match {
        case x :: xs => {
          println(s"Two students have same name - $studentName")
          scoreCardMap(studentName).asInstanceOf[List[ScoreCard]].map( scoreCard => println(scoreCard))
        }
        case ScoreCard(studentId : Long, marks : collection.mutable.Map[Long, Float], percentage : Float) => {
          println(s"Only one student found - $studentName")
          println(scoreCardMap(studentName).asInstanceOf[ScoreCard])
        }
        case _ => println("Something wrong happened")
      }
    } else {
      println("No data found!")
    }

  }

  /*----- This method will populate @scoreCardMap with ScoreCard's of all students -----*/

  def generateScoreCards(): Unit = {

    students.foreach(student => {
      addScoreCardToMap(createScoreCard(student.id))
    })

  }

  def addScoreCardToMap(scoreCard: ScoreCard): collection.mutable.Map[String, AnyRef] = {
    val studentName: String = students.filter(_.id ==scoreCard.studentId)(0).name

    if(scoreCardMap.contains(studentName)) {
      var scoreCardList:List[ScoreCard] = List(scoreCard)
      scoreCardMap(studentName) match {
        case x :: xs => {
          scoreCardList = scoreCardMap(studentName).asInstanceOf[List[ScoreCard]] ::: scoreCardList
        }
        case ScoreCard(studentId : Long, marks : collection.mutable.Map[Long, Float], percentage : Float) => {
          println(s"Score card when already has a ScoreCard")
          scoreCardList = scoreCardList :+ scoreCardMap(studentName).asInstanceOf[ScoreCard]
        }
        case _ => println("Something wrong happened while adding the scorecard in Map")
      }
      scoreCardMap.remove(studentName)
      scoreCardMap(studentName) = scoreCardList

    } else {
      scoreCardMap(studentName) = scoreCard
    }

    scoreCardMap
  }

  def createScoreCard(studentId : Long): ScoreCard = {
    val marksMap = collection.mutable.Map[Long, Float]()

    var totalMarks : Float = 0

    marks.filter(_.studentId == studentId).foreach(marksInstance => {
            marksMap(marksInstance.subjectId) = marksInstance.marksObtained
            totalMarks += marksInstance.marksObtained
    })

    ScoreCard(studentId, marksMap, totalMarks / 4)
  } /* Function ends here*/

}