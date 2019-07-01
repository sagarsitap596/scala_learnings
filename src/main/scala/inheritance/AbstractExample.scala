

package inheritance

abstract class AbstractFile {

  def open(): Unit
  def save(): Unit
  def logMe(): Unit = {
    println("Logging things --> " + this.getClass().getCanonicalName)
  }

}

class MyFile extends AbstractFile {

  override def open() {
    logMe()
    println("MyFile >> open")
  }

  override def save() {
    logMe()
    println("MyFile >> save")
  }
}

class MyCompressedFile extends MyFile {

  override def open() {
    logMe()
    println("MyCompressedFile >> open")
  }

  override def save() {
    logMe()
    println("MyCompressedFile >> save")
    println("compressing file")
    println("saving compressed file")
    super.save()
  }
}

object AbstractExample {
  def main(args: Array[String]): Unit = {
    var f: AbstractFile = new MyCompressedFile()
    f.open()
    f.save()
  }
}