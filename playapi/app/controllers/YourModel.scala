package controllers

import slick.jdbc.MySQLProfile.api._

case class YourModel(id: Long, name: String, description: String)

class YourTable(tag: Tag) extends Table[YourModel](tag, "your_table_name") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def description = column[String]("description")

  def * = (id, name, description) <> (YourModel.tupled, YourModel.unapply)
}

val yourTableQuery: TableQuery[YourTable] = TableQuery[YourTable]
