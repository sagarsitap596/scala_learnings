package learn

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Set

/**
 * by default members of case class are final/val
 * But can be declared as var
 */
case class Student(name: String, rollNumber: Int, gender: Char)

class Course(courseName: String, students: ArrayBuffer[Student], teachers: Set[String]) {

  def getStudent(): ArrayBuffer[Student] = {
    students
  }

//    def setStudent(students: ArrayBuffer[Student]): Unit = {
//      this.students = students;
//    }

  def getFirstStudent(): Student = {
    if (students != null && !students.isEmpty) {
      students(students.size - 1)
    } else {
      null
    }
  }

  def getLastStudent(): Student = {
    if (students != null && !students.isEmpty) {
      students(0)
    } else {
      null
    }
  }

  def removeStudent(student: Student): Boolean = {
    students.remove(students.indexOf(student)) != null
  }

  def addStudent(student: Student): Unit = {
    students.+=(student)
  }

  def removAllStudents(): Unit = {
    students.clear()
  }

  override def toString(): String = {
    "[CourseName : " + courseName + " student : " + students + "]"
  }

}

object CaseExample {

  def main(args: Array[String]): Unit = {

    var teachers: Set[String] = Set()
    teachers.add("teacher1")
    teachers.add("teacher1")
    teachers.add("teacher2")
    println(teachers)

    var students = new ArrayBuffer[Student]()

    val student1 = new Student("sagar1", 11, 'M')
    val student2 = new Student("sagar2", 12, 'M')
    val student3 = new Student("sagar3", 13, 'M')
    val student4 = new Student("sagar4", 14, 'M')

    val student5 = new Student("sagar1", 11, 'M')

    students.+=(student1)
    students.+=(student2)
    students.+=(student3)
    students.+=(student4)

    /**
     * Case provides toString , hashCode , equals and copy methods
     */
    println(student1)

    println(student1 == student2)

    println(student1 == student5)

    var course = new Course("Scala Training", students, teachers)
    course.addStudent(new Student("sagar5", 15, 'F'))
    println(course)

  }

}