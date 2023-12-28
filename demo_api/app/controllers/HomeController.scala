package controllers
import javax.inject._
import play.api._
import play.api.libs.json.{JsArray, Json, Writes}
import play.api.mvc._

import scala.collection.mutable.ListBuffer


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

  val empList: ListBuffer[Employee] = ListBuffer(
    Employee(1, "Subham", "Pune", "555-1234"),
    Employee(2, "Ankit", "Mumbai", "555-5678"),
    Employee(3, "Sameer", "Thane", "555-1234")
  )
  // Implicit Writes for Employee
  implicit val employeeWrites = Json.writes[Employee]

  def getEmployees: Action[AnyContent] = Action {
    val jsonResult = Json.toJson(empList)
    Ok(jsonResult)
  }

  //  def addEmployee(employee: Employee): Unit = {
  //    empList += employee
  //    println(s"Employee ${employee.name} added successfully.")
  //  }
  def filterEmployeeById(id: Int): Action[AnyContent] = Action {
    val filteredEmployees = empList.filter(_.id == id)

    if (filteredEmployees.nonEmpty) {
      val jsonResult = Json.toJson(filteredEmployees)
      Ok(jsonResult)
    } else {
      Ok(s"No employees found with ID $id.")
    }
  }

  def filterEmployeeByName(name: String): Action[AnyContent] = Action {
    val filteredEmployees = empList.filter(_.name == name)
    if (filteredEmployees.nonEmpty) {
      val jsonResult = Json.toJson(filteredEmployees)
      Ok(jsonResult)
    } else {
      Ok(s"No employees found with name $name.")
    }
  }

  def filterEmployeeByMobile(mobile: String): Action[AnyContent] = Action {
    val filteredEmployees = empList.filter(_.mobile == mobile)
    if (filteredEmployees.nonEmpty) {
      val jsonResult = Json.toJson(filteredEmployees)
      Ok(jsonResult)
    } else {
      Ok(s"No employees found with mobile $mobile.")
    }
  }

  def deleteEmployeeById(id: Int): Action[AnyContent] = Action {
    val indexOption = empList.indexWhere(_.id == id)
    indexOption match {
      case -1 =>
        Ok(s"Employee with ID $id not found. No deletion performed.")
      case indexOption =>
        empList.remove(indexOption)
        Ok(s"Employee with ID $id deleted successfully.")
    }
  }
}

