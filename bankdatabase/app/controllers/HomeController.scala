package controllers

import javax.inject._
import play.api._
import play.api.libs.json.{Format, JsObject, JsValue, Json}
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

  implicit val customerFormat: Format[Customer] = Json.format[Customer]

  def addCustomer(): Action[JsValue] = Action(parse.json) { request =>
    val customer = request.body.as[Customer]
    bankService.addCustomer(customer.name, customer.id, customer.email, customer.balance)
    Ok(Json.toJson(customer))
  }

  def deleteCustomer(id: String): Action[AnyContent] = Action {
    bankService.deleteCustomer(id)
    Ok(s"Customer with ID $id deleted.")
  }

  def showCustomers(): Action[AnyContent] = Action {
    val customers = bankService.showCustomers()
    Ok(Json.toJson(customers))
  }

  def transferMoney(): Action[JsValue] = Action(parse.json) { request =>
    val transferRequest = request.body.as[JsObject]
    val senderId = (transferRequest \ "senderId").as[String]
    val receiverId = (transferRequest \ "receiverId").as[String]
    val amount = (transferRequest \ "amount").as[Double]

    bankService.transferMoney(senderId, receiverId, amount)
    Ok(s"$amount transferred from $senderId to $receiverId.")
  }
}
