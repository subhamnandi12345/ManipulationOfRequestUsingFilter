package controllers

import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class SerializationController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
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

  def serializePerson() = Action {
    val person = Person("Alice", 30)
    val json = Json.toJson(person)
    Ok(json)
  }

  def deserializePerson() = Action { request =>
    request.body.asJson.flatMap(_.asOpt[Person]) match {
      case Some(person) => Ok(s"Name: ${person.name}, Age: ${person.age}")
      case None => BadRequest("Invalid JSON format")
    }
  }
}
