package com.guru.mayoo

import akka.actor.{ActorSystem, Props, Actor}

class HelloActor extends Actor {

  def receive = {
    case "hello" => println("hello back at you!")
    case _ => println("others back at you!")
  }
}

object Main extends App {

  //Create an ActorSystem to get things started with an arbitrary string
  val system = ActorSystem("HelloSystem")

  // default Actor constructor
  val helloActor = system.actorOf(Props[HelloActor], name = "helloActor")

  //send three messages
  helloActor ! "welcome"
  helloActor ! "hello"
  helloActor ! "actor"
}
