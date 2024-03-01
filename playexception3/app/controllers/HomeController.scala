package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ErrorDemoController @Inject()(
                                     val controllerComponents: ControllerComponents
                                   ) extends BaseController {
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
//  def notFound(): Action[AnyContent] = Action {
//    NotFound
//  }
  def exception(): Action[AnyContent] = Action {
    throw new RuntimeException("Pretend that we have an application error.")
    Ok // We add this line just to make the returned type match the expected type
  }

  def internalError(): Action[AnyContent] = Action {
    InternalServerError
  }

  def badRequest(): Action[AnyContent] = Action {
    BadRequest
  }
}
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

