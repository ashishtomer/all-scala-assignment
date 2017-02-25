package com.ashish.scorecard

import scala.collection.mutable.Map

/**
  * Created by ashish on 1/23/17.
  */
case class ScoreCard(studentId : Long, marks : collection.mutable.Map[Long, Float], percentage : Float)
