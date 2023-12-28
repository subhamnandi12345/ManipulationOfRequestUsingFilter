package controllers
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import play.api.mvc.Security.AuthenticatedBuilder
import play.api.libs.functional.syntax._
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, Security}
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {


  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
  var employees = scala.collection.mutable.ListBuffer(
    Employee(1, "Subham", "Pune", "12345"),
    Employee(2, "Ankit", "Mumbai", "233453")
  )
 implicit val employeeWrites = Json.writes[Employee]

  def getAllEmployees = Action {
    Ok(Json.toJson(employees))
  }

  def getEmployee(id: Long) = Action {
    employees.find(_.id == id) match {
      case Some(employee) => Ok(Json.toJson(employee))
      case None => NotFound(Json.obj("error" -> "Employee not found"))
    }
  }

  def createEmployee = Action(parse.json) { request =>
    val name = (request.body \ "name").as[String]
    val city = (request.body \ "city").as[String]
    val mobile = (request.body \ "mobile").as[String]
    val newEmployee = Employee(employees.length + 1, name, city, mobile)
    employees += newEmployee
    Created(Json.toJson(newEmployee))
  }

  def updateEmployee(id: Long) = Action(parse.json) { request =>
    employees.find(_.id == id) match {
      case Some(existingEmployee) =>
        val updatedEmployee = existingEmployee.copy(
          name = (request.body \ "name").as[String],
          city = (request.body  \ "city").as[String],
          mobile = (request.body \ "mobile").as[String]
        )
        employees = employees.map(e => if (e.id == id) updatedEmployee else e)
        Ok(Json.toJson(updatedEmployee))
      case None => NotFound
    }
  }

  def deleteEmployee(id: Long) = Action {
    employees.find(_.id == id) match {
      case Some(employee) =>
        employees = employees.filterNot(_.id == id)
        Ok(Json.toJson(employee))
      case None => NotFound
    }
  }
  def authenticatedAction: Action[AnyContent]  = Action { implicit request =>
    val username = request.headers
    Ok(s"Authenticated User header: $username")
  }
}
