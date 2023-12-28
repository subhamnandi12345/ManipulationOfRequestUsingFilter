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
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def hello() = Action { implicit request: Request[AnyContent] =>
    Ok("hello world")
  }

  def world(a:Int,b:Int)= Action { implicit request: Request[AnyContent] =>
    val c=a+b
    Ok("sum is"+c)
  }
  def employee()=Action{implicit request :Request[AnyContent]=>
    var empName="Ankit Sharma"
    var companyName="Gs Lab"
    var Salary=20000
    Ok(s"EmployeeName is ${empName} Comapny Name is ${companyName} and the salary is ${Salary}")
  }
  def circle(r:Int)=Action{implicit request :Request[AnyContent]=>
    var area: Float = 0
    area = 3.14F * r * r
    Ok(s"Area of circle: $area")
  }

  def triangle(height: Int,width: Int) = Action { implicit request: Request[AnyContent] =>
    var area: Float = 0
    area = 0.5F * height * width
    Ok(s"Area of circle: $area")
  }
  def cube( height: Int,width: Int,length:Int) = Action { implicit request: Request[AnyContent] =>
    var area: Float = 0
    area=  height * width * length
    Ok(s"Area of circle: $area")
  }
   def  add(p:Int,q:Int)=Action { implicit request: Request[AnyContent] =>
     val sum:Int=p+q
     Ok(s"Addition :$sum")
   }
  def sub(p: Int, q: Int) = Action { implicit request: Request[AnyContent] =>
    val sub: Int = p-q
    Ok(s"Addition :$sub")
  }

}
