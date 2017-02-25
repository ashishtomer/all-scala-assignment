package edu.knoldus.daoImpl

import java.sql.ResultSet

import edu.knoldus.dao.ProjectDao
import edu.knoldus.model.Project
import edu.knoldus.mysqlworker.MysqlWorker

/**
  * Created by ashish on 1/31/17.
  */
class ProjectDaoImpl extends ProjectDao{

  override def getProject(idArg: Long): Project = {

    val query : String = s"select * from project where id = $idArg"
    val projectRes:ResultSet = MysqlWorker.pullData(query)

    projectRes.next // To move the cursor to first row in resultSet

    val id = projectRes.getLong("id")
    val deptId = projectRes.getLong("dept_id")
    val name = projectRes.getString("name")
    val clientId = projectRes.getLong("client_id")

    Project(id,deptId,name, clientId)
  }

  override def removeProject(idArg: Long): Boolean = {

    val query : String = s"delete from project where id = $idArg"

    if(MysqlWorker.deleteData(query) == 1) {
      true
    }
    else {
      false
    }
  }

  override def updateProject(project: Project): Boolean = {

    val query : String = s"update project set dept_id = '${project.deptId}', name = '${project.name}'," +
      s"client_id = '${project.clientId}' where id = ${project.id}"
    if(MysqlWorker.updateData(query) == 1) {
      true
    }
    else {
      false
    }

  }

  override def createProject(project: Project): Boolean = {

    val query : String = s"insert into project values(${project.id}, ${project.deptId}, '${project.name}', ${project.clientId})"

    if(MysqlWorker.pushData(query) ==1) {
      true
    }
    else {
      false
    }

  }
}
