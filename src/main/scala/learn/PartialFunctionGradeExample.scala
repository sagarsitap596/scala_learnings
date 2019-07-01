package learn

object PartialFunctionGradeExample {

  val gradeD: PartialFunction[Int, String] = {
    case marks: Int if marks >= 0 && marks < 30 => "D"
  }

  val gradeC: PartialFunction[Int, String] = {

    case marks: Int if marks >= 30 && marks < 50 => "C"
  }

  val gradeB: PartialFunction[Int, String] = {
    case marks: Int if marks >= 50 && marks < 70 => "B"
  }

  val gradeA: PartialFunction[Int, String] = {
    case marks: Int if marks >= 70 && marks < 100 => "A"
  }

}

object PartialFunctionTaxOperation {


  val serviceTax: PartialFunction[Double, Double] = {
    case amount: Double if amount > 0 => amount * 2.5
  }

  val gst: PartialFunction[Double, Double] = {
    case amount: Double if amount > 0 => amount * 5
  }

  val additionalTaxes: PartialFunction[Double, Double] = {
    case amount: Double if amount > 0 => amount * 1.5
  }

}

object PFGE {

  import PartialFunctionGradeExample._
  import PartialFunctionTaxOperation._

  def main(args: Array[String]): Unit = {

    val studentMarks = List(25, 55, 95)

    val studentGrade = studentMarks.map(gradeA orElse gradeB orElse gradeC orElse gradeD)

    println("Students grades :" + studentGrade)

    val bills = List(6546, 4564, 244.43, 24525.64)

    val billsAfterTax = bills.map(serviceTax andThen gst andThen additionalTaxes)

    println("Bills after taxes : " + billsAfterTax)

  }
}


