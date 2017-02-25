package edu.knoldus.dao

import edu.knoldus.model.Client

/**
  * Created by ashish on 1/31/17.
  */
trait ClientDao {
  def getClient(id : Long) : Client
  def getClientByProjectId(id : Long) : List[Client]
  def removeClient(id : Long) : Boolean
  def updateClient(client : Client) : Boolean
  def createClient(client : Client) : Boolean//Using create instead of insert
}
