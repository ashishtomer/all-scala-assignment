package com.ashish.scorecard_with_gender

/**
  * Created by ashish on 1/23/17.
  */
case class ScoreCard(studentId : Long, marks : collection.mutable.Map[Long, Float], percentage : Float, gender : Gender.Value)