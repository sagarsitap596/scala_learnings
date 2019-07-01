package actors

import akka.actor.Actor
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._
import akka.actor.Props
import akka.actor.ActorSystem

class ActorChaining {

}

case object CreateChild
case class Add(n1: Int, n2: Int)
case class Sub(n1: Int, n2: Int)
case class Divide(n1: Int, n2: Int)

class ChildActor extends Actor {

  println("Child actor created")
  override def receive = {
    case Add(n1, n2) => println(n1 + n2)
    case sub: Sub    => println(sub.n1 + sub.n2)
    case Divide(n1, n2) =>
      println(n1 / n2)

  }
  val superviseStrategy = OneForOneStrategy(loggingEnabled = false) {
    case ae: ArithmeticException => Resume
    case _: Exception            => Restart
  }

  override def preStart() = {
    println("preStart >> " + self.path.name)
  }

  override def postStop() = {
    println("postStop")
  }
}

class ParentActor extends Actor {

  var count: Int = 0

  override def receive = {
    case CreateChild => {
      println("creatong child Actor")
      context.actorOf(Props[ChildActor], "ChildActor" + count)
      count += 1
    }

  }
}

object ActorChaining {

  def main(args: Array[String]): Unit = {

    val system = ActorSystem("ActorSystem")
    val actor1 = system.actorOf(Props[ParentActor])

    actor1 ! CreateChild
    actor1 ! CreateChild
    actor1 ! CreateChild

  }
}


