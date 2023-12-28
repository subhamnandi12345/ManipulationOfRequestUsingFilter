package controllers
import scala.collection.mutable.ListBuffer
import javax.inject._
import play.api._
import play.api.mvc._
import model._
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  val empList: ListBuffer[Employee] = ListBuffer.empty[Employee]
  val employee1 = Employee(1, "Subham", "Pune", 811624)
  empList += employee1

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

  def displayEmployee() = Action { implicit request: Request[AnyContent] =>
    empList.foreach(println)
    Ok("empList")
  }

}
