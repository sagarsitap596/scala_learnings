package actors

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.routing.FromConfig

import scala.collection.mutable.ListBuffer


object DispatcherAndRouterConfigExample {

  def main(args: Array[String]): Unit = {

    val system: ActorSystem = ActorSystem("RouterExample_ActorSystem-")

    val routerPool: ActorRef = system.actorOf(FromConfig.props(Props[Worker].withDispatcher("sag-dispatcher")), "random-router-pool")

    var operations = new ListBuffer[Opr]()
    operations += new Opr(3, 1, "ADD")
    operations += new Opr(3, 1, "SUB")
    operations += new Opr(3, 1, "MUL")
    operations += new Opr(4, 1, "ADD")
    operations += new Opr(4, 1, "SUB")
    operations += new Opr(4, 1, "MUL")

    for (i <- 0 to operations.size - 1) {
      routerPool ! operations(i)
    }
  }

}
