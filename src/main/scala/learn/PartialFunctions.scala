package learn

object PartialFunctions {


  /**
    * This is full function. That means we can give any two values to add fucntion and we get output.
    * There is no exception
    *
    */
  def add(n1: Int, n2: Int): Int = {
    n1 * n2
  }


  /**
    * This is partial function. Since we are handling divide by zero error. So technically we can now pass any input to this method
    * and we get some output.
    * Additionally we get a method isDefinedAt() to check if methid can be called on given input successfully.
    */
  def divide: PartialFunction[(Int, Int), Int] = {

    case (n1, n2) if n2 != 0 => n1 / n2

  }


  val divide3 = new PartialFunction[Int, Int] {

    def apply(n2: Int) = 23 / n2

    def isDefinedAt(n2: Int) = n2 != 0
  }


  /**
    * SquareRoot partialFunction
    */
  val squareRoot: PartialFunction[Double, Double] = {
    case n: Double if n > 0 => Math.sqrt(n)
  }


  def main(args: Array[String]): Unit = {

    println("Add 8,2 :" + add(1, 2))
    println("Divide 8,2 : " + divide(1, 2))

    println("divide.isDefinedAt(8,0) : " + divide.isDefinedAt(8, 0))

    if (divide.isDefinedAt(8, 0)) {
      println("divide(8,0) can be done")
    }
    else {
      println("divide(8,0) cannot be done")
    }

    // below will throw exception
    // println("Divide 8,0 : " + divide(1, 0))

    /**
      * -----------------------------
      */

    val numbers: List[Double] = List(1, 4, 9, 15, -13)

    var sqrtList = numbers.map(Math.sqrt)

    println(sqrtList) //  op : List(1.0, 2.0, 3.0, 3.872983346207417, NaN)

    /**
      * Partial funtions are used with collect
      */
    sqrtList = numbers.collect(squareRoot) // op : List(1.0, 2.0, 3.0, 3.872983346207417)

    println(sqrtList)

  }

}
