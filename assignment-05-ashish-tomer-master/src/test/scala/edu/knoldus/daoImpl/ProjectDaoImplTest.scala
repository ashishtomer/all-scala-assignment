package edu.knoldus.daoImpl

import edu.knoldus.model.{Client, Department, Project}
import org.scalatest.{BeforeAndAfterAll, FunSuite}

/**
  * Created by ashish on 2/1/17.
  */
class ProjectDaoImplTest extends FunSuite with BeforeAndAfterAll{

  override def beforeAll(): Unit = {
    val department = Department(9834, "Pantry")
    val departmentDaoImpl = new DepartmentDaoImpl
    departmentDaoImpl.createDepartment(department)

    //id 123 is already in project
    val client = Client(612, 123, "Dhiman Tech", "Kochi")
    val clientDaoImpl = new ClientDaoImpl
    clientDaoImpl.createClient(client)

  }

  override def afterAll(): Unit = {

    val departmentDaoImpl = new DepartmentDaoImpl
    departmentDaoImpl.removeDepartment(9834)


    val clientDaoImpl = new ClientDaoImpl
    clientDaoImpl.removeClient(612)
  }

  test("test createProject") {
    val project = Project(3498, 9834, "Brahmos", 612)
    val projectDaoImpl = new ProjectDaoImpl

    assert(projectDaoImpl.createProject(project))
  }

  test("test updateProject") {
    val project = Project(3498, 9834, "Navaratan", 612)
    val projectDaoImpl = new ProjectDaoImpl

    assert(projectDaoImpl.updateProject(project))
  }

  test("test getProject()") {
    val projectId = 3498
    val project = Project(3498, 9834, "Navaratan", 612)
    val projectDaoImpl = new ProjectDaoImpl

    assert(projectDaoImpl.getProject(projectId) == project)
  }

  test("test removeProject") {
    val projectId = 3498
    val projectDaoImpl = new ProjectDaoImpl

    assert(projectDaoImpl.removeProject(projectId))
  }
}
