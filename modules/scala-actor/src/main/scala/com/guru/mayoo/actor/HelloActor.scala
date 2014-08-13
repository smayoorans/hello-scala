package com.guru.mayoo.actor

import akka.actor.{ActorSystem, Props, Actor}

class HelloActor(name: String) extends Actor {

  def receive = {
    case "hello" => println("hello back at you! %s".format(name))
    case _ => println("others back at you! %s".format(name))
  }
}

object Main extends App {

  //Create an ActorSystem to get things started with an arbitrary string
  val system = ActorSystem("HelloSystem")

  // Actor constructor with parameter
  val helloActor = system.actorOf(Props(new HelloActor("Mayooran")), name = "helloActor")

  //send three messages
  helloActor ! "welcome"
  helloActor ! "hello"
  helloActor ! "actor"
}
