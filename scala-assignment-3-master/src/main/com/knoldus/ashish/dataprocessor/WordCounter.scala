package main.com.knoldus.ashish.dataprocessor

import scala.collection.mutable.HashMap

/**
  * Created by ashish on 1/27/17.
  */

class WordCounter {

}

object WordCounter {

  def wordCount(text : String) : String = {

    if(text.length == 0) return ""

    /* Considering these as word separator . , ( ) ! ; ' " : ? [ ] { } _ - + = */
    val textArray : Array[String] = text.replaceAll("[.,()!;'\":?\\]\\[\\{\\}_\\-\\+=&]+"," ").split("\\s++")

    val wordCountMap = new HashMap[String, Long]()

    textArray.foreach(word => {

      //Checking redundant word saves from
      //hitting the Map, because we don't
      // want to store its counts in Map.
      if(word != "") {

        if (wordCountMap.contains(word)) {
          wordCountMap(word) += 1
        } else {
          wordCountMap(word) = 1
        }

      }

    })

    stringifyMap(wordCountMap)
  }

  def stringifyMap(hashMap: HashMap[String, Long]) : String = {

    val stringStore = new StringBuilder("")

    //Sort by occurrence of a word
    hashMap.toList.sortBy(_._2).foreach( pair => {
      stringStore.append(f"${pair._1}%20s ~> ${pair._2}\n")
    })

    stringStore.toString()
  }

}
