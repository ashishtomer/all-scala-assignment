package edu.knoldus.mysqlworkder

import edu.knoldus.model.Project
import edu.knoldus.mysqlworker.MysqlWorker
import org.scalatest._

/**
  * Created by ashish on 2/1/17.
  */
class MysqlWorkerTest extends FunSuite{

  test("Insert in department") {
    val query : String = s"insert into department values(67512,'HR');"
    assert(MysqlWorker.pushData(query) == 1)
  }

  test("Update in department") {
    val query : String = s"update department set name = 'Marketing' where id=67512"
    assert(MysqlWorker.updateData(query) == 1)
  }

  test("Delete from department") {
    val query : String = s"delete from department where id = 67512"
    assert(MysqlWorker.deleteData(query) == 1)
  }

  test("Insert in employee") {
    val query : String = s"insert into employee values(67512,'Prabhat', 'Delhi', 934863092, 547, 123);"
    assert(MysqlWorker.pushData(query) == 1)
  }

  test("Update in employee") {
    val query : String = s"update employee set address = 'Ranchi' where id=67512"
    assert(MysqlWorker.updateData(query) == 1)
  }

  test("Delete from employee") {
    val query : String = s"delete from employee where id = 67512"
    assert(MysqlWorker.deleteData(query) == 1)
  }

  test("Insert in client") {
    val query : String = s"insert into client values(612, 123, 'IBM', 'Delhi');"
    assert(MysqlWorker.pushData(query) == 1)
  }

  test("Update in client") {
    val query : String = s"update client set address = 'Ranchi' where id=612"
    assert(MysqlWorker.updateData(query) == 1)
  }

  test("Delete from client") {
    val query : String = s"delete from client where id=612"
    assert(MysqlWorker.deleteData(query) == 1)
  }

  test("Insert in project") {
    //id 67519 already exist in department
    //id 9012 already exist in client
    val query : String = s"insert into project values(3905, 67519, 'Scala Geek', 9012);"
    assert(MysqlWorker.pushData(query) == 1)
  }

  test("Update in project") {
    val query : String = s"update project set name = 'Scala Worms' where id=3905"
    assert(MysqlWorker.updateData(query) == 1)
  }

  test("Delete from project") {
    val query : String = s"delete from project where id = 3905"
    assert(MysqlWorker.deleteData(query) == 1)
  }

}
