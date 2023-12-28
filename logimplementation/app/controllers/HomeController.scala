package controllers

import javax.inject._
import play.api._
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
  private val logger = Logger(getClass)

  def index() = Action { implicit request: Request[AnyContent] =>
    logger.info("Received request.")
    Ok(views.html.index())

  }


  //def myAction(): Action[AnyContent] = Action { implicit request =>
//    logger.debug("This is a debug message.")
//    logger.info("This is an info message.")
//    logger.warn("This is a warning message.")
//    logger.error("This is an error message.")
//    // Your controller logic here
//    logger.info("Received request.")
//     Ok("Success")

  //}
}
