package actors


import akka.actor._
import com.typesafe.config.ConfigFactory

class ActorA extends Actor {

  def receive: Receive = {

    case n: String => {
//      Thread.currentThread().setName(n + "-Thread")
//      println(n + " Parent actor processing")
      Thread.sleep(5000)
      println(n + " is process by :" + Thread.currentThread().getName())
    }
  }
}

object DispatcherExample {

  def main(args: Array[String]): Unit = {


    val system: ActorSystem = ActorSystem.create("hello-system")

    val actor: ActorRef = system.actorOf(Props[ActorA].withDispatcher("sag-dispatcher"))
    val actor2: ActorRef = system.actorOf(Props[ActorA].withDispatcher("sag-dispatcher"))
    val actor3: ActorRef = system.actorOf(Props[ActorA].withDispatcher("sag-dispatcher"))


    actor ! "A"
    actor2 ! "B"
    actor3 ! "C"

    actor ! "AA"
    actor2 ! "BB"
    actor3 ! "CC"

    actor ! "AAA"
    actor2 ! "BBB"
    actor3 ! "CCC"
  }
}
