package edu.knoldus.daoImpl

import edu.knoldus.model.{Client, Department, Project}
import org.scalatest.{BeforeAndAfterAll, FunSuite}
import org.apache.logging.log4j.core

/**
  * Created by ashish on 2/1/17.
  */
class ClientDaoImplTest extends FunSuite with BeforeAndAfterAll {

  override def beforeAll(): Unit = {

    val department = Department(1269, "Pantry")
    val departmentDaoImpl = new DepartmentDaoImpl
    departmentDaoImpl.createDepartment(department)

    //id 9012 is already in client
    val project = Project(1023, 1269, "Employee Assessment System", 9012)
    val projectDaoImpl = new ProjectDaoImpl
    projectDaoImpl.createProject(project)

  }

  override def afterAll(): Unit = {

    val projectDaoImpl = new ProjectDaoImpl
    projectDaoImpl.removeProject(1023)

    val departmentDaoImpl = new DepartmentDaoImpl
    departmentDaoImpl.removeDepartment(1269)

  }

  test("test createClient() ") {
    val client = Client(543903L,1023, "Bombay Rockers", "Mumbai")
    val clientDaoImpl = new ClientDaoImpl
    assert(clientDaoImpl.createClient(client))
  }

  test("test updateClient()") {
    val client = Client(543903L,1023, "Kolkata Bloomers", "Kolkata")
    val clientDaoImpl = new ClientDaoImpl
    assert(clientDaoImpl.updateClient(client))
  }

  test("test getClient() ") {
    val clientId = 543903L
    val client = Client(543903L,1023, "Kolkata Bloomers", "Kolkata")
    val clientDaoImpl = new ClientDaoImpl
    assert(clientDaoImpl.getClient(clientId) == client)
  }

  test("test getClientByProjectId") {
    val projectId = 1023
    val client = Client(543903L,1023, "Kolkata Bloomers", "Kolkata")
    val clientDaoImpl = new ClientDaoImpl
    assert(clientDaoImpl.getClientByProjectId(projectId) == List(client))
  }

  test("test removeClient() ") {
    val clientId = 543903L
    val clientDaoImpl = new ClientDaoImpl
    assert(clientDaoImpl.removeClient(clientId))
  }
}
