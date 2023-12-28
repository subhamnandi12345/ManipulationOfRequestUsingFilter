package controllers
import models._;
import javax.inject._
import play.api._
import play.api.libs.json.JsValue
import play.api.mvc._
import play.api.libs.json.{JsError, Json}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  val jsonData: JsValue = Json.parse(
    """
      |{
      |  "person": {
      |    "name": "John",
      |    "age": 30,
      |    "address": "New York, NY"
      |  },
      | "job": {
      |   "title":"software Engineer",
      |   "company": "GS LAB ,PUNE"
      | }
      |}
      |""".stripMargin
  )

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
  // Action to parse JSON with custom path
  def parseJsonWithPath = Action {
    val result = getByPathRecursive(jsonData, "job//company")
    result match {
      case Some(value) => Ok(Json.toJson(value))
      case None => NotFound("Path not found in JSON")
    }
  }
}
