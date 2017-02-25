package edu.knoldus.daoImpl

import java.sql.ResultSet

import edu.knoldus.dao.ClientDao
import edu.knoldus.model.Client
import edu.knoldus.mysqlworker.MysqlWorker

/**
  * Created by ashish on 1/31/17.
  */
class ClientDaoImpl extends ClientDao {

  override def createClient(client: Client): Boolean = {

    val query : String = s"insert into client values(${client.id}, ${client.projectId},'${client.name}', '${client.address}');"

    if(MysqlWorker.pushData(query) ==1) {
      true
    }
    else {
      false
    }

  }

  override def getClient(idArg: Long): Client = {

    val query : String = s"select * from client where id = $idArg"
    val client:ResultSet = MysqlWorker.pullData(query)


    client.next //Set the cursor to the first row in resultSet

    val id = client.getLong("id")
    val projectId = client.getLong("project_id")
    val name = client.getString("name")
    val address = client.getString("address")

    Client(id,projectId,name, address)
  }

  override def getClientByProjectId(idArg: Long): List[Client] = {

    val query : String = s"select * from client where project_id = ${idArg.toString}"

    val client:ResultSet = MysqlWorker.pullData(query)

    getClientList(client)

  }

  override def updateClient(client: Client): Boolean = {

    val query : String = s"update client set project_id = '${client.projectId}', name = '${client.name}', address = '${client.address}' where id=${client.id};"

    if(MysqlWorker.updateData(query) == 1) {
      true
    }
    else {
      false
    }

  }

  override def removeClient(id: Long): Boolean = {
    val query : String = s"delete from client where id = $id"

    if(MysqlWorker.deleteData(query) == 1) {
      true
    }
    else {
      false
    }

  }

  /*--- Use absolute on resultset in case this method doesn't work---*/
  def getClientList(clients: ResultSet, clientList: List[Client] = List[Client]()) : List[Client] = {
    if(clients.next) {
      getClientList(clients, Client(clients.getLong("id"),
        clients.getLong("project_id"),
        clients.getString("name"),
        clients.getString("address")
      ) :: clientList)
    } else {
      clientList
    }
  }

}
