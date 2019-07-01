package learn

class HOFExample {

}

object HOFExample {

  def main(args: Array[String]): Unit = {

    println(operation(add, 1, 2))
    println(operation(addF, 1, 2))
    println(addF(1, 2))

    println(operation(sub, 3, 1))
    println(operation(mul, 4, 3))

    /**
     * Anonymous methods as parameter
     */

    println("Anonymous : " + operation((n1, n2) => n1 + n2, 1, 2))
    println("Anonymous : " + operation((n1, n2) => n1 - n2, 3, 1))
    println("Anonymous : " + operation((n1, n2) => n1 * n2, 4, 3))
  }

  var addF = add _

  def add(n1: Int, n2: Int): Int = n1 + n2

  def sub(n1: Int, n2: Int): Int = n1 - n2

  def mul(n1: Int, n2: Int): Int = n1 * n2

  def operation(f: (Int, Int) => Int, n1: Int, n2: Int): Int = f(n1, n2)

}