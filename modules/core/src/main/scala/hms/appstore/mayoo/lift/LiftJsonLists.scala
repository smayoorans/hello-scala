package hms.appstore.mayoo.lift

import net.liftweb.json._
import net.liftweb.json.JsonDSL._

case class Person1(name: String, address: Address1) {
  var friends = List[Person1]()
}
case class Address1(city: String, state: String)

object LiftJsonLists extends App {

  implicit val formats = DefaultFormats

  val p = Person1("Mayooran", Address1("Jaffna", "Northern"))

  val x = Person1("PersonX", Address1("CityX", "StateX"))
  val y = Person1("PersonY", Address1("CityY", "StateY"))

  val friends = List(x, y)
  p.friends = friends

  /*Creating a JSON String from Classes That Have Collections*/

  val json =
    "person" ->
      ("name" -> p.name)~
      ("address" ->
        ("city" -> p.address.city) ~
        ("state" -> p.address.state)) ~
      ("friends" ->
        friends.map {
          f =>
            ("name" -> f.name) ~
            ("address" ->
                ("city" -> f.address.city) ~
                ("state" -> f.address.state))
        })


  println(pretty(render(json)))

}
