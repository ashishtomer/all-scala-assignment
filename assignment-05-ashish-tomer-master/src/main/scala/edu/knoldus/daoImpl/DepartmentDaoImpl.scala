package edu.knoldus.daoImpl

import java.sql.ResultSet

import edu.knoldus.dao.DepartmentDao
import edu.knoldus.model.Department
import edu.knoldus.mysqlworker.MysqlWorker

/**
  * Created by ashish on 1/31/17.
  */
class DepartmentDaoImpl extends DepartmentDao{

  override def createDepartment(department: Department): Boolean = {
    val query : String = s"insert into department values(${department.id},'${department.name}');"

    if(MysqlWorker.pushData(query) ==1) {
      true
    }
    else {
      false
    }
  }

  override def getDepartment(deptId: Long): Department = {

    val query : String = s"select * from department where id = $deptId"
    val department:ResultSet = MysqlWorker.pullData(query)

    department.next //Setting cursor to first row in resultSet

    val id = department.getLong("id")
    val name = department.getString("name")

    Department(id,name)
  }

  override def updateDepartment(department: Department): Boolean = {

    val query : String = s"update department set name = '${department.name}' where id=${department.id};"

    if(MysqlWorker.updateData(query) == 1) {
      true
    }
    else {
      false
    }

  }

  override def removeDepartment(id: Long): Boolean = {

    val query : String = s"delete from department where id = $id"

    if(MysqlWorker.deleteData(query) == 1) {
      true
    }
    else {
      false
    }

  }
}
