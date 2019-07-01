package actors

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.TypedActor.PreStart

import scala.reflect.macros.blackbox.Context
import akka.actor.Props
import akka.actor.ActorSystem
import akka.routing.FromConfig

import scala.collection.mutable.ListBuffer

object RouterExample {

  def main(args: Array[String]): Unit = {

    val system: ActorSystem = ActorSystem("RouterExample_ActorSystem")
    val routerActor: ActorRef = system.actorOf(Props[Router], "Router")

    var operations = new ListBuffer[Opr]()
    operations += new Opr(3, 1, "ADD")
    operations += new Opr(3, 1, "SUB")
    operations += new Opr(3, 1, "MUL")
    operations += new Opr(4, 1, "ADD")
    operations += new Opr(4, 1, "SUB")
    operations += new Opr(4, 1, "MUL")


    routerActor ! operations
  }

}

case class Opr(n1: Int, n2: Int, operation: String)

class Worker extends Actor {

  def receive = {

    case Opr(n1, n2, operation) => {
      //      println(self)
      operation match {
        case "ADD" => {
          Thread.sleep(3000)
          println(" Add is process by :" + Thread.currentThread().getName()+" - Addition of " + n1 + " and " + n2 + " is " + (n1 + n2))
        }
        case "SUB" => {
          Thread.sleep(3000)
          println(" Sub  is process by :" + Thread.currentThread().getName()+" - Sub of " + n1 + " and " + n2 + " is " + (n1 - n2))
        }
        case "MUL" => {
          Thread.sleep(3000)
          println(" Mul is process by :" + Thread.currentThread().getName()+" - Mul of " + n1 + " and " + n2 + " is " + (n1 * n2))
        }
      }
    }
  }
}

class Router extends Actor {

  var workers: List[ActorRef] = List()

  override def preStart(): Unit = {
    workers = List.fill(3)(context.actorOf(Props[Worker]))
  }

  def receive() = {

    case msg: ListBuffer[Opr] => {
      for (i <- 0 to msg.size - 1) {
        workers(util.Random.nextInt(workers.size)) ! msg(i)
      }
    }
  }
}