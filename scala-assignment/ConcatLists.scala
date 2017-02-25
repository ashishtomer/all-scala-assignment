import scala.annotation.tailrec

class ConcatLists {
  
  def concat(list1: List[Int], list2: List[Int]): List[Int] = {
    
    @tailrec
    def innerConcat(list: List[Int], outputList: List[Int] = List[Int]()): List[Int] = {
      
      list match {
        case Nil => outputList
        case head :: tail => innerConcat(tail, head::outputList) 
      }
      
    }
    
    innerConcat(innerConcat(list1), list2)
    
  }
  
}

(new ConcatLists).concat(List(1,2,34), List(45,3,90))

