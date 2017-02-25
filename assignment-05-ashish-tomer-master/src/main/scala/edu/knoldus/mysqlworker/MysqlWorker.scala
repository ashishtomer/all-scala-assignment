package edu.knoldus.mysqlworker

import java.io.{IOException, InputStream}
import java.sql._
import java.util.Properties

/**
  * Created by ashish on 2/1/17.
  */

class MysqlWorker private() {

}

@throws[IOException]
@throws[ClassNotFoundException]
@throws[SQLException]
object MysqlWorker {

  /*--- Make database connection when class loads ---*/

  val dbProperty: Properties = new Properties()
  val inputStream: InputStream = getClass.getClassLoader.getResourceAsStream("database.properties")

  dbProperty.load(inputStream)

  val dbDriver: String = dbProperty.getProperty("dbDriver")
  val dbUrl: String = dbProperty.getProperty("connectionUrl")
  val dbUser: String = dbProperty.getProperty("username")
  val dbPass: String = dbProperty.getProperty("password")

  Class.forName(dbDriver)

  val connection : Connection = DriverManager.getConnection(dbUrl, dbUser, dbPass)
  val statement : Statement = connection.createStatement

/*--- Static methods used to perform the queries on database ---*/

  def pullData(query : String): ResultSet = {
    statement.executeQuery(query)
  }

  /*
  * pushData, deleteData and updateData all
  * three call executeUpdate on query but they are distinguished by
  * different names to make the MysqlWorker better class
  */

  def pushData(query : String): Int = {
    statement.executeUpdate(query)
  }

  def deleteData(query : String): Int = {
    statement.executeUpdate(query)
  }

  def updateData(query : String): Int = {
    statement.executeUpdate(query)
  }

}
