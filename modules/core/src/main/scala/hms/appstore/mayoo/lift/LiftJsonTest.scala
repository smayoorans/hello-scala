package hms.appstore.mayoo.lift

import net.liftweb.json.Serialization.write
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Printer.{compact,pretty}
import net.liftweb.json.JsonAST
import net.liftweb.json.JsonDSL._


case class Person(name: String, address: Address)
case class Address(city: String, state: String)

object LiftJsonTest extends App{
  val p = Person("Alvin Alexander", Address("Talkeetna", "AK"))

  // create a JSON string for the Person, then print it
  implicit val formats = DefaultFormats
  val jsonString = write(p)
  println(jsonString)
}


object LiftJsonWithCollections extends App {
  val json = List(1, 2, 3)
  println(compact(JsonAST.render(json)))

  val map = Map("fname" -> "Alvin", "lname" -> "Alexander")
  println(pretty(JsonAST.render(map)))
}

