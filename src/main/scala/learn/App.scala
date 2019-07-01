

package learn

class App(private val rollNumber: Int, private val name: String) {

  def this() {
    this(0, "dummy")
  }

  def test(name: String): Unit = {
    println(name)
  }

  def getName: String = {
    name
  }

  def getRollNumber(): Int = {
    rollNumber
  }
  
  

}

object MyClass {

  def main(args: Array[String]): Unit = {

    val data = new {
      val a=12
      val b=20
    }

    println(data.a)
    println(data.b)


    val app1 = new App(1, "sagar")
    val app2 = new App()

    println(app1.getName)
    println(app1.getRollNumber())

    println(app2.getName)
    println(app2.getRollNumber())
    
    print(app1)
    
    

  }

}
