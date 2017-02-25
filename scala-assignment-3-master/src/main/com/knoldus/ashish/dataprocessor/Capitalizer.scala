package main.com.knoldus.ashish.dataprocessor

/**
  * Created by ashish on 1/27/17.
  */
class Capitalizer {


}

object Capitalizer {

  def capitalizeText(text : String) : String = {
    if(text.length == 0) {
      ""
    }
    else {
      text.toUpperCase()
    }
  }

}