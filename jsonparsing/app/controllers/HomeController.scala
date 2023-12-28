package controllers

import models.Person

import javax.inject._
import play.api._
import play.api.libs.json.{JsError, Json}
import play.api.mvc._

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

  def parseJson = Action(parse.json) { request =>
    // Assuming the request body contains a JSON object
    val jsonResult = request.body.validate[Person]

    jsonResult.fold(
      errors => {
        BadRequest(Json.obj("status" -> "error", "message" -> JsError.toJson(errors)))
      },
      person => {
        // Successfully parsed JSON, you can now use the 'person' object
        Ok(Json.obj("status" -> "success", "data" -> Json.toJson(person)))
      }
    )
  }
}
