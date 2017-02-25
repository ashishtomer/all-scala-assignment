package edu.knoldus.dao

import edu.knoldus.model.Project

/**
  * Created by ashish on 1/31/17.
  */
trait ProjectDao {
  def getProject(id : Long) : Project
  def removeProject(id : Long) : Boolean
  def updateProject(project : Project) : Boolean
  def createProject(project : Project) : Boolean //Using create instead of insert
}
