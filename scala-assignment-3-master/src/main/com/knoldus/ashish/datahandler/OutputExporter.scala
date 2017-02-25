package main.com.knoldus.ashish.datahandler

/**
  * Created by ashish on 1/27/17.
  */
trait OutputExporter {
  def outputLocation : String
  def exportText(text : String) : Boolean
}
