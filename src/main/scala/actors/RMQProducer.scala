package actors

import akka.actor.{ActorLogging, Props}
import akka.camel.{Oneway, Producer}

object RMQProducer {
  def props(endPoint: String): Props = Props(new RMQProducer(endPoint))
}

class RMQProducer(endPoint: String) extends Producer with ActorLogging with Oneway {

  var lastMessageTime: Long = System.currentTimeMillis()

  def endpointUri: String = endPoint

  override def routeResponse(msg: Any): Unit = {
    lastMessageTime = System.currentTimeMillis
  }
}