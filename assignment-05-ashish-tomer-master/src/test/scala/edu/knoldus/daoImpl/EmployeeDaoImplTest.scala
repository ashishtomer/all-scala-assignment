package edu.knoldus.daoImpl

import edu.knoldus.model.{Client, Department, Employee, Project}
import org.scalatest.{BeforeAndAfterAll, FunSuite}

/**
  * Created by ashish on 2/1/17.
  */
class EmployeeDaoImplTest extends FunSuite with BeforeAndAfterAll{

  override def beforeAll(): Unit = {
    val department = Department(9834, "Pantry")
    val departmentDaoImpl = new DepartmentDaoImpl
    departmentDaoImpl.createDepartment(department)

    //id 123 is already in project
    val client = Client(434, 123, "Dhiman Tech", "Kochi")
    val clientDaoImpl = new ClientDaoImpl
    clientDaoImpl.createClient(client)

    val project = Project(1023, 9834, "Employee Assessment System", 434)
    val projectDaoImpl = new ProjectDaoImpl
    projectDaoImpl.createProject(project)

  }

  override def afterAll(): Unit = {

    val projectDaoImpl = new ProjectDaoImpl
    projectDaoImpl.removeProject(1023)

    val departmentDaoImpl = new DepartmentDaoImpl
    departmentDaoImpl.removeDepartment(9834)


    val clientDaoImpl = new ClientDaoImpl
    clientDaoImpl.removeClient(434)
  }

  test("test create employee") {
    val employee = Employee(34621L, "Vijay Deenanath", "Mandwa", 7046948290L, 9834L, 1023L)
    val employeeDaoImpl  = new EmployeeDaoImpl

    assert(employeeDaoImpl.createEmployee(employee))
  }

  test("test updateEmployee()") {
    val employee = Employee(34621L, "Vijay Deenanath", "Mumbai", 9866948290L, 9834L, 1023L)
    val employeeDaoImpl  = new EmployeeDaoImpl

    assert(employeeDaoImpl.updateEmployee(employee))
  }

  test("test getEmployee()") {
    val empId = 34621L
    val employeeDaoImpl  = new EmployeeDaoImpl
    val employee = Employee(34621L, "Vijay Deenanath", "Mumbai", 9866948290L, 9834L, 1023L)

    assert(employeeDaoImpl.getEmployee(empId) == employee)
  }

  test("test getEmployeesByDeptId") {
    val deptId = 9834L
    val employeeDaoImpl  = new EmployeeDaoImpl
    val employee = Employee(34621L, "Vijay Deenanath", "Mumbai", 9866948290L, 9834L, 1023L)

    assert(employeeDaoImpl.getEmployeesByDeptId(deptId) == List(employee)) //Only one employee in that department
  }

  test("test getEmployeesByProjectId") {
    val proectId = 1023L
    val employeeDaoImpl  = new EmployeeDaoImpl
    val employee = Employee(34621L, "Vijay Deenanath", "Mumbai", 9866948290L, 9834L, 1023L)

    assert(employeeDaoImpl.getEmployeesByProjectId(proectId) == List(employee)) //Only one employee in that department
  }

  test("test removeEmployee()") {
    val empId = 34621L
    val employeeDaoImpl  = new EmployeeDaoImpl

    assert(employeeDaoImpl.removeEmployee(empId))
  }
}
