package actors

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef
import akka.actor.actorRef2Scala

class SimpleActor extends Actor {

  override def receive = {
    case s: String        => println("String : " + s)
    case n: Int           => println("Int : " + n)
    case student: Student => println(student)
  }

  def getaActorName(): String = {
    "SimpleActor1"
  }

  def getaActorName2() = {
    "SimpleActor1"
  }
}

object SimpleActorExample {

  def main(args: Array[String]): Unit = {
    val actorSystem: ActorSystem = ActorSystem("SimpleActorSystem")
    val actor: ActorRef = actorSystem.actorOf(Props[SimpleActor], "SimpleActor11")

    actor ! "bingo"
    actor ! 12
    actor ! Student("Sagar", 596)
    actorSystem.terminate()

  }
}

case class Student(val name: String, val rollNumber: Int)