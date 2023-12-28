package controllers
import javax.inject.Inject

// TODO: clean these up.
// SEE:  https://github.com/playframework/play-slick/blob/master/samples/basic/app/controllers/Application.scala
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms.text
import play.api.mvc.{ AbstractController, ControllerComponents }

import scala.concurrent.ExecutionContext

import models.Stock
import dao.StockDAO
import views._

class StocksController @Inject() (
                                   stockDao: StockDAO,
                                   controllerComponents: ControllerComponents
                                 )(
                                   implicit executionContext: ExecutionContext
                                 ) extends AbstractController(controllerComponents) {

  // def list = Action {
  //     Ok(html.stock.list(StockDao.selectAll()))
  // }

  // TODO find a better way to do this
  def list = Action.async {
    stockDao.all().map { case (stocks) => Ok(views.html.list(stocks)) }
  }

}
