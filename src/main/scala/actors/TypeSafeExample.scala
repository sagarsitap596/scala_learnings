package actors

import com.typesafe.config.{Config, ConfigFactory}

object TypeSafeExample {

  def main(args: Array[String]): Unit = {
    val config :Config = ConfigFactory.load()
    println(config.getString("producer-uri"))

  }
}
