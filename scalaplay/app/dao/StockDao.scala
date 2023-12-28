package dao
//TODO: clean up these imports
import scala.concurrent.{ ExecutionContext, Future }
import javax.inject.Inject

import models.Stock
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

class StockDAO @Inject()(
                          protected val dbConfigProvider: DatabaseConfigProvider
                        )(
                          implicit executionContext: ExecutionContext
                        ) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  private val Stocks = TableQuery[StocksTable]

  def all(): Future[Seq[Stock]] = db.run(Stocks.result)

  // SLICK database stuff
  // this first "stocks" string refers to my database table name
  private class StocksTable(tag: Tag) extends Table[Stock](tag, "stocks") {
    def id      = column[Long](  "ID", O.PrimaryKey)
    def symbol  = column[String]("SYMBOL")
    def company = column[String]("COMPANY")
    def *       = (id, symbol, company) <> (Stock.tupled, Stock.unapply)
  }
}