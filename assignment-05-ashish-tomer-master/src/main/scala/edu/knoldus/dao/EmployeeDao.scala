package edu.knoldus.dao

import edu.knoldus.model.Employee
import scala.collection.immutable.List

/**
  * Created by ashish on 1/31/17.
  */

trait EmployeeDao {
  def getEmployee(id : Long) : Employee
  def getEmployeesByDeptId(id : Long) : List[Employee]
  def getEmployeesByProjectId(id : Long) : List[Employee]
  def removeEmployee(id : Long) : Boolean
  def updateEmployee(employee : Employee) : Boolean
  def createEmployee(employee : Employee) : Boolean//Using create instead of insert
}
