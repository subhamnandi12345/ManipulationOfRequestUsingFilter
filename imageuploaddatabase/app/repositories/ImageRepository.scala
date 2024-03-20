// app/repositories/ImageRepository.scala
package repositories

import java.sql.{Connection, PreparedStatement, ResultSet}
import javax.inject.Inject
import models.Image
import play.api.db.Database

class ImageRepository @Inject()(db: Database) {

  def save(image: Image)(implicit connection: Connection): Unit = {
    val statement: PreparedStatement = connection.prepareStatement("INSERT INTO images(data) VALUES (?)")
    statement.setBytes(1, image.data)
    statement.executeUpdate()
    statement.close()
  }
  def findById(id: Long)(implicit connection: Connection): Option[Image] = {
    val statement: PreparedStatement = connection.prepareStatement("SELECT data FROM images WHERE id = ?")
    statement.setLong(1, id)
    val resultSet: ResultSet = statement.executeQuery()
    if (resultSet.next()) {
      val data = resultSet.getBytes("data")
      Some(Image(id, data))
    } else {
      None
    }
  }
}
