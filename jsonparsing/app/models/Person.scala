package models

import play.api.libs.json.{Json, Reads, Writes}

case class Person(name: String, age: Int, city: String, grades: Seq[Int])
object Person {
  implicit val personReads: Reads[Person] = Json.reads[Person]
  implicit val personWrites: Writes[Person] = Json.writes[Person]
}
