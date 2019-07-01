package actors

import akka.actor.Actor

class CommandLineExample extends Actor {

  override def preStart() = {
    println("MyActor has prestart")
  }
    override def receive(): Receive = {
      case data:String => println("MyActor has started")
  }
}
