// app/controllers/ImageController.scala
package controllers

import java.nio.file.{Files, Paths}
import javax.inject._
import models.Image
import play.api.mvc._
import repositories.ImageRepository

@Singleton
class ImageController @Inject()(cc: ControllerComponents, imageRepository: ImageRepository) extends AbstractController(cc) {

  def uploadImage = Action(parse.multipartFormData) { request =>
    request.body.file("image").map { image =>
      val fileBytes = Files.readAllBytes(Paths.get(image.ref.path.toFile.getAbsolutePath))
      val imageModel = Image(1, fileBytes)
      imageRepository.save(imageModel)
      Ok("Image uploaded successfully")
    }.getOrElse {
      BadRequest("No image found in request")
    }
  }

  def getImage(id: Long) = Action {
    imageRepository.findById(id) match {
      case Some(image) => Ok(image.data).as("image/jpeg")
      case None => NotFound("Image not found")
    }
  }
}
