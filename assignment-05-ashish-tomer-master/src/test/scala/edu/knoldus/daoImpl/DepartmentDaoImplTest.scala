package edu.knoldus.daoImpl

import edu.knoldus.model.Department
import org.scalatest.FunSuite
/**
  * Created by ashish on 2/1/17.
  */
class DepartmentDaoImplTest extends FunSuite {

  test("test createDepartment()") {
    val department = Department(4394, "Supply")
    val departmentDaoImpl = new DepartmentDaoImpl
    assert(departmentDaoImpl.createDepartment(department))
  }

  test("test getDepartment()") {
    val departmentId = 4394
    val departmentDaoImpl = new DepartmentDaoImpl
    val department = Department(4394, "Supply")

    assert(departmentDaoImpl.getDepartment(departmentId) == department)
  }

  test("test updateDepartment()") {
    val department = Department(4394, "Inventory")
    val departmentDaoImpl = new DepartmentDaoImpl

    assert(departmentDaoImpl.updateDepartment(department))
  }

  test("test removeDepartment()") {
    val departmentId = 4394L
    val departmentDaoImpl = new DepartmentDaoImpl

    assert(departmentDaoImpl.removeDepartment(departmentId))
  }

}
