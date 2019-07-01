package actors

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

class CountDownActor extends Actor {

  override def receive = {

    case startCountDown: StartCountDown => {

      println(self.path.name + " >> CountDown Started : " + startCountDown.n)
      startCountDown.actor ! CountDown(startCountDown.n - 1)
    }

    case countDown: CountDown => {
      if (countDown.n > 0) {
        println(self.path.name + " >> CountDown : " + countDown.n)
        sender() ! CountDown(countDown.n - 1)
      } else {
        context.system.terminate()
      }
    }
  }
}

case class StartCountDown(n: Int, actor: ActorRef)

case class CountDown(n: Int)

object CountDownActorExample {

  def main(args: Array[String]): Unit = {

    val system: ActorSystem = ActorSystem("CountDownActorActorSystem")
    val actor1: ActorRef = system.actorOf(Props[CountDownActor], "Actor1")
    val actor2: ActorRef = system.actorOf(Props[CountDownActor], "Actor2")

    actor1 ! StartCountDown(10, actor2)
  }
}