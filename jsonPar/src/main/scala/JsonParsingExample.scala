import play.api.libs.json._

object JsonParsingExample extends App {

  // Example JSON data
  val jsonData = Json.parse(
    """
      |{
      |  "person": {
      |    "name": "John",
      |    "age": 30,
      |    "address": {
      |      "city": "New York",
      |      "zip": "10001"
      |    },
      |    "phones": [
      |      {"type": "home", "number": "123-456-7890"},
      |      {"type": "work", "number": "987-654-3210"}
      |    ]
      |  }
      |}
      |""".stripMargin
  )

  // Function to navigate through JSON using "/"
  def getByPath(json: JsValue, path: String): Option[JsValue] = {
    path.split("/").foldLeft[Option[JsValue]](Some(json)) {
      case (Some(jsObj), key) =>
        (jsObj \ key).toOption
      case _ => None
    }
  }

  // Function to navigate through JSON using "//"
  def getByPathRecursive(json: JsValue, path: String): Option[JsValue] = {
    def traverse(jsObj: JsValue, keys: List[String]): Option[JsValue] = keys match {
      case Nil => Some(jsObj)
      case key :: rest =>
        (jsObj \ key).toOption.flatMap(subObj => traverse(subObj, rest))
    }

    path.split("//").foldLeft[Option[JsValue]](Some(json)) {
      case (Some(jsObj), subPath) =>
        traverse(jsObj, subPath.split("/").toList)
      case _ => None
    }
  }

  // Usage examples
  val nameResult = getByPath(jsonData, "person/name")
  println(s"Name: ${nameResult.getOrElse("Not found")}")

  val zipResult = getByPath(jsonData, "person/address/zip")
  println(s"Zip Code: ${zipResult.getOrElse("Not found")}")

  val workPhoneResult = getByPath(jsonData, "person/phones/work/number")
  println(s"Work Phone: ${workPhoneResult.getOrElse("Not found")}")

  val recursiveResult = getByPathRecursive(jsonData, "person//number")
  println(s"Phone Numbers: ${recursiveResult.getOrElse("Not found")}")
}
