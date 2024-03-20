package controllers

import javax.inject._
import play.api.mvc._
import models.Image

import java.io.{ByteArrayInputStream, InputStream}
import java.nio.file.{Files, Paths}
import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet, SQLException}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

@Singleton
class ImageController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  private val url = "jdbc:mysql://localhost:3306/imagedatabase"
  private val username = "root"
  private val password = "root@123"
  val driver = "com.mysql.jdbc.Driver"


  def upload = Action(parse.multipartFormData) { request =>
    request.body.file("image").map { image =>
      val fileName = image.filename
      val fileContent = image.ref.file

      val inputStream: InputStream = new ByteArrayInputStream(java.nio.file.Files.readAllBytes(fileContent.toPath))
      val data: Array[Byte] = Stream.continually(inputStream.read).takeWhile(_ != -1).map(_.toByte).toArray

      val connection = DriverManager.getConnection(url, username, password)
      val preparedStatement: PreparedStatement = connection.prepareStatement("INSERT INTO images (name, data) VALUES (?, ?)")
      preparedStatement.setString(1, fileName)
      preparedStatement.setBytes(2, data)

      val result: Try[Int] = Try(preparedStatement.executeUpdate())

      result match {
        case Success(_) =>
          connection.close()
          Ok("Image uploaded successfully")
        case Failure(exception) =>
          connection.close()
          BadRequest("Failed to upload image")
      }
    }.getOrElse {
      BadRequest("Missing file")
    }
  }

  def getImageById(id: Long) = Action {
    val connection: Connection = DriverManager.getConnection(url, username, password)
    val statement = connection.prepareStatement("SELECT data FROM images WHERE id = ?")
    statement.setLong(1, id)
    val resultSet = statement.executeQuery()
    if (resultSet.next()) {
      val imageData: Array[Byte] = resultSet.getBytes("data")
      connection.close()
      Ok(imageData).as("image/png")
    } else {
      connection.close()
      NotFound("Image not found")
    }
  }
}
