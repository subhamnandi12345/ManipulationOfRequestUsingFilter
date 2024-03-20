package models

import play.api.libs.json.{Format, JsResult, JsValue, Json}

case class Image(id :Long,data: Array[Byte])
object Image{
  implicit val ImageFormat: Format[image] = new Format[image] {
    override def writes(employee: Employee): JsValue = Json.obj(
      "id" -> employee.id,
      "name" -> employee.name,
      "designation" -> employee.designation
    )
    override def reads(json: JsValue): JsResult[Employee] = {
      for {
        id <- (json \ "id").validate[Long]
        name <- (json \ "name").validate[String]
        designation <- (json \ "designation").validate[String]
      } yield Employee(id, name, designation)
    }
  }