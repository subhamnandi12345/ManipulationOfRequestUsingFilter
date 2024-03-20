package controllers

import play.api.libs.json.{Json, Reads, Writes}

case class Person(name: String, age: Int)
object Person {
  // Writes instance for JSON serialization
  implicit val personWrites: Writes[Person] = Json.writes[Person]

  // Reads instance for JSON deserialization
  implicit val personReads: Reads[Person] = Json.reads[Person]
}