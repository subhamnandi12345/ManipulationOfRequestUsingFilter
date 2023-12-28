package controllers



import javax.inject._
import play.api.mvc._
import play.api.libs.json.Json

import scala.concurrent.{Await, ExecutionContext, Future}
import dao.EmployeeDAO
import models.Employee
import play.api.db

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps
import scala.reflect.internal.NoPhase.id

@Singleton
class EmployeeController @Inject()(cc: ControllerComponents, employeeDAO: EmployeeDAO)(implicit ec: ExecutionContext)
  extends AbstractController(cc) {
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  implicit val employeeWrites = Json.writes[Employee]

  def listEmployees = Action.async {
    employeeDAO.list().map { employees =>
      Ok(Json.toJson(employees))
    }
  }

  def createEmployee = Action(parse.json) { request =>

    val name = (request.body \ "name").as[String]
    val city = (request.body \ "city").as[String]
    val mobile = (request.body \ "mobile").as[String]
    val count = Await.result(employeeDAO.list().map { employees => employees.size }, 2 seconds)
    val newEmployee = Employee(count + 1, name, city, mobile)
    employeeDAO.add(newEmployee)
    Ok("Employee Record Created Successfully")
  }

  def deleteEmployeebyId(id: Int) = Action { request =>
    val indexOption = employeeDAO.sub(id)
    Ok(indexOption + "Deleted")
  }
  def updateEmployeebyId = Action(parse.json) { request =>

    val name = (request.body \ "name").as[String]
    val city = (request.body \ "city").as[String]
    val mobile = (request.body \ "mobile").as[String]
    //val update= Await.result((EmployeeDAO.table.filter(_.id === id).update(updatedEmployee)), 2 seconds)
    //val newEmployee = Employee(id, name, city, mobile)
  }

}
