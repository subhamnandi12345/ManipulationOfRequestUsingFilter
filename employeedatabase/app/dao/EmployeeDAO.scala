package dao

//import controllers.Employee

import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}
import models.Employee
import play.api.http.MediaRange.parse
import play.api.libs.json.Json
import play.api.mvc.AnyContent
import play.api.mvc.Results.Created

import java.awt.Desktop.Action

class EmployeeDAO @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class EmployeeTable(tag: Tag) extends Table[Employee](tag, "datatable") {
    def id = column[Long]("id", O.PrimaryKey)

    def name = column[String]("name")

    def city = column[String]("city")

    def mobile = column[String]("mobile")

    def * = (id, name, city, mobile) <> (Employee.tupled, Employee.unapply)
  }

  private val employees = TableQuery[EmployeeTable]

  def list(): Future[Seq[Employee]] = db.run {
    employees.result
  }

  def add(employee: Employee) = {
    val insertPlayerQuery = employees += employee
    val insertResult: Future[Int] = db.run(insertPlayerQuery)
    println("insertResult "+ insertResult)
  }

  def sub(id:Int) = {
    val deleteAction = employees.filter(_.id == id).delete
    val deleteResult:Future[Int]=db.run(deleteAction)
    print("deleted successfully"+deleteResult)
  }
  def update(id :Int)={
    val updateCountryAction = employees.filter(_.id == id).map(_.name).update("Vikram")
    val updateResult:Future[Int]=db.run(updateCountryAction)
    print("updated successfully"+updateResult)
  }

}

