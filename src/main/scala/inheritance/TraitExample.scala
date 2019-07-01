package inheritance

trait Shape {

  val x: Int = 10

  def area: Unit = {
    println("Shape area")
  }
}

class Polygon {

  val x: Int = 11

  def area: Unit = {
    println("Polygon area")
  }
}

class Rectangle extends Polygon with Shape {

  override val x: Int = 12

  override def area: Unit = {
    println("Rectangle area")
    super.area
  }

}
object TraitExample {

  def main(args: Array[String]): Unit = {

    val rec: Rectangle = new Rectangle()
    rec.area
  }
}