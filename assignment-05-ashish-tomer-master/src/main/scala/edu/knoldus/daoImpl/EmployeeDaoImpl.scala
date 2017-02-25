package edu.knoldus.daoImpl

import java.sql.ResultSet

import edu.knoldus.dao.EmployeeDao
import edu.knoldus.model.Employee
import edu.knoldus.mysqlworker.MysqlWorker

/**
  * Created by ashish on 1/31/17.
  */
class EmployeeDaoImpl extends EmployeeDao{

  override def createEmployee(employee: Employee): Boolean = {

    val query : String = s"insert into employee values(${employee.id},'${employee.name}', '${employee.address}'," +
      s"${employee.phone}, ${employee.deptId}, ${employee.projectId});"

    if(MysqlWorker.pushData(query) ==1) {
      true
    }
    else {
      false
    }

  }

  override def getEmployee(idArg: Long): Employee = {

    val query : String = s"select * from employee where id = $idArg"
    val employee:ResultSet = MysqlWorker.pullData(query)

    employee.next //To set the cursor to first row in resultSet

    val id = employee.getLong("id")
    val name = employee.getString("name")
    val address = employee.getString("address")
    val phone = employee.getLong("phone")
    val deptId = employee.getLong("dept_id")
    val projectId = employee.getLong("project_id")

    Employee(id,name, address, phone, deptId, projectId)
  }

  override def getEmployeesByDeptId(idArg: Long): List[Employee] = {
    val query : String = s"select * from employee where dept_id = $idArg"
    val employees : ResultSet = MysqlWorker.pullData(query)

    getEmployeeList(employees)

  }

  override def getEmployeesByProjectId(idArg: Long): List[Employee] = {

    val query : String = s"select * from employee where project_id = $idArg"
    val employees : ResultSet = MysqlWorker.pullData(query)

    getEmployeeList(employees)

  }

  override def updateEmployee(employee: Employee): Boolean = {

    val query : String = s"update employee set name = '${employee.name}', address = '${employee.address}', " +
      s"phone = ${employee.phone}, dept_id = ${employee.deptId}, project_id = ${employee.projectId} where id = ${employee.id}";
    if(MysqlWorker.updateData(query) == 1) {
      true
    }
    else {
      false
    }

  }

  override def removeEmployee(idArg: Long): Boolean = {

    val query: String = s"delete from employee where id = $idArg"
    if(MysqlWorker.deleteData(query) == 1) {
      true
    }
    else {
      false
    }

  }


  /*--- Use absolute on resultset in case this method doesn't work---*/
  def getEmployeeList(employees: ResultSet, employeeList: List[Employee] = List[Employee]()) : List[Employee] = {
    if(employees.next) {
      getEmployeeList(employees, Employee(employees.getLong("id"),
        employees.getString("name"),
        employees.getString("address"),
        employees.getLong("phone"),
        employees.getLong("dept_id"),
        employees.getLong("project_id")
      ) :: employeeList)
    } else {
      employeeList
    }
  }

}
