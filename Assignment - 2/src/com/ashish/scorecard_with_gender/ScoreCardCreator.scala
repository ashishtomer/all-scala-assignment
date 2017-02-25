package com.ashish.scorecard_with_gender

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
    Student(902873, "Kunal", Gender.male),
    Student(349394, "Shubhra", Gender.female),
    Student(936239, "Ashish", Gender.male),
    Student(293520, "Neha", Gender.female),
    Student(882930, "Shubham", Gender.male),
    Student(983402, "Shivangi", Gender.female),
    Student(394201, "Prashant", Gender.male),
    Student(384945, "Charmy", Gender.female),
    Student(539204, "Akhil", Gender.male),
    Student(904384, "Akhil", Gender.male)
  )

  /*----- Marks of each student -----*/

  private val marks : List[Marks] = List (

    Marks(math.subjectId, 293520, 89),
    Marks(science.subjectId, 293520, 69),
    Marks(hindi.subjectId, 293520, 87),
    Marks(english.subjectId, 293520, 91),

    Marks(math.subjectId, 983402, 42),
    Marks(science.subjectId, 983402, 56),
    Marks(hindi.subjectId, 983402, 78),
    Marks(english.subjectId, 983402, 41),

    Marks(math.subjectId, 384945, 99),
    Marks(science.subjectId, 384945, 97),
    Marks(hindi.subjectId, 384945, 98),
    Marks(english.subjectId, 384945, 91),

    Marks(math.subjectId, 904384, 99),
    Marks(science.subjectId, 904384, 97),
    Marks(hindi.subjectId, 904384, 91),
    Marks(english.subjectId, 904384, 98),

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

  /*----- This method prints scoreCards with percentage more than given percentage ----*/

  def printScoreCardWithPercentage(percentage : Float): Unit = {

    println(s"---- Scorecards with percentage > $percentage ----")

    printScoreCardsFromList(getScoreCardsByGender()._1)
    printScoreCardsFromList(getScoreCardsByGender()._2)

    def printScoreCardsFromList(scoreCardList:List[ScoreCard]): Unit = {
      scoreCardList.foreach(scoreCard =>{
        if(scoreCard.percentage > percentage)
          println(scoreCard)
      } )
    }
  }

  /*---- method fo find out the percentage that girls group
  * has scored but no same percentage has got in the boys group ---- */

  def printDifferentGirls(): Unit ={

    println("---- Girls who have scored marks different from boys ----")

    val(maleStudents, femaleStudents) = getScoreCardsByGender

    femaleStudents.foreach( femaleStudent =>{
      if(!maleStudents.exists(_.percentage == femaleStudent.percentage)) {
        println(s"${students.find(_.id == femaleStudent.studentId).get.name} - ${femaleStudent.percentage} ")
      }
    })

  }

  /*---- This method prints those male and female students that have same percentage ----*/

  def printMaleFemaleWithSameMarks(): Unit = {

    println("---- Girl(s) who scored same as a boy ----")

    val(maleStudents, femaleStudents) = getScoreCardsByGender

    maleStudents.foreach(maleStudent => {

      if(femaleStudents.exists( femaleStudent => femaleStudent.percentage == maleStudent.percentage)) {
        print(s"${students.find(_.id == maleStudent.studentId).get.name} - ${maleStudent.percentage} ")

        femaleStudents.filter(_.percentage == maleStudent.percentage).foreach(femaleScoreCard => {

          println(s"${students.find(_.id == femaleScoreCard.studentId).get.name} - ${femaleScoreCard.percentage} ")

        })
      }
    })

  }

  /*----- This method will populate @scoreCardMap with ScoreCard's of all students -----*/

  def getScoreCardsByGender(): (List[ScoreCard], List[ScoreCard]) ={

    val scoreCardMap = collection.mutable.Map[String, AnyRef]()
    val femaleStudents = scala.collection.mutable.ListBuffer.empty[ScoreCard]
    val maleStudents = scala.collection.mutable.ListBuffer.empty[ScoreCard]

    students.foreach(student => {
      addScoreCardToMap(createScoreCard(student.id), scoreCardMap)
    })

    scoreCardMap.foreach( x => x._2 match {
      case ScoreCard(studentId : Long, marks : collection.mutable.Map[Long, Float], percentage : Float, gender : Gender.Value) => {
        if(x._2.asInstanceOf[ScoreCard].gender == Gender.male)
          maleStudents += x._2.asInstanceOf[ScoreCard]
        else
          femaleStudents += x._2.asInstanceOf[ScoreCard]
      }
      case y :: yz => {
        x._2.asInstanceOf[List[ScoreCard]].foreach( scoreCard =>{
          if(scoreCard.asInstanceOf[ScoreCard].gender == Gender.male) {
            maleStudents += scoreCard.asInstanceOf[ScoreCard]
          }
          else {
            femaleStudents += scoreCard.asInstanceOf[ScoreCard]
          }
        })
      }
      case _ => println("Error while adding score card from scorecard map")
    })

    (maleStudents.toList, femaleStudents.toList)
  }

  /*---- This method adds the scoreCard to the Map ----*/

  def addScoreCardToMap(scoreCard: ScoreCard, scoreCardMap : collection.mutable.Map[String, AnyRef]): Unit = {
    val studentName: String = students.filter(_.id ==scoreCard.studentId)(0).name

    if(scoreCardMap.contains(studentName)) {
      var scoreCardList:List[ScoreCard] = List(scoreCard)
      scoreCardMap(studentName) match {
        case x :: xs => {
          scoreCardList = scoreCardMap(studentName).asInstanceOf[List[ScoreCard]] ::: scoreCardList
        }
        case ScoreCard(studentId : Long, marks : collection.mutable.Map[Long, Float], percentage : Float, gender : Gender.Value) => {
          scoreCardList = scoreCardList :+ scoreCardMap(studentName).asInstanceOf[ScoreCard]
        }
        case _ => println("Something wrong happened while adding the scorecard in Map")
      }
      scoreCardMap.remove(studentName)
      scoreCardMap(studentName) = scoreCardList

    } else {
      scoreCardMap(studentName) = scoreCard
    }

  }



  /*---- This method creates the score card for a student ----*/
  def createScoreCard(studentId : Long): ScoreCard = {

    val marksMap = collection.mutable.Map[Long, Float]()
    val gender:Gender.Value = students.filter(_.id == studentId)(0).gender

    var totalMarks : Float = 0

    marks.filter(_.studentId == studentId).foreach(marksInstance => {
      marksMap(marksInstance.subjectId) = marksInstance.marksObtained
      totalMarks += marksInstance.marksObtained
    })

    ScoreCard(studentId, marksMap, totalMarks / 4, gender)
  } /* Function ends here*/

}
