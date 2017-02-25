package main.com.knoldus.ashish.datahandler

/**
  * Created by ashish on 1/27/17.
  */
/*
* This class helps in saving data to DB
* Implementation is yet to be done in future assignments
* */
final class DatabaseAsOutputSource(source : Any) extends OutputExporter{

  //Database name, table name combination
  override val outputLocation = " "

  override def exportText(text: String): Boolean = {

    //    Save the data to database

    false

  }
}