package edu.knoldus.dao

import edu.knoldus.model.Department

/**
  * Created by ashish on 1/31/17.
  */
trait DepartmentDao {
  def getDepartment(id : Long) : Department
  def removeDepartment(id : Long) : Boolean
  def updateDepartment(department : Department) : Boolean
  def createDepartment(department : Department) : Boolean//Using create instead of insert
}
