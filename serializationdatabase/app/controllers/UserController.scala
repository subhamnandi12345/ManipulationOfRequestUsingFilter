// app/controllers/UserController.scala

package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{User, UserRepository}

@Singleton
class UserController @Inject()(cc: ControllerComponents, userRepository: UserRepository) extends AbstractController(cc) {

  def getUser(id: Long) = Action { implicit request: Request[AnyContent] =>
    userRepository.findById(id) match {
      case Some(user) => Ok(Json.toJson(user))
      case None => NotFound
    }
  }

  def createUser = Action(parse.json) { implicit request =>
    request.body.validate[User].map { user =>
      userRepository.save(user)
      Created("user created")
    }.getOrElse(BadRequest("Invalid user format"))
  }

  def listUsers = Action { implicit request: Request[AnyContent] =>
    val users = userRepository.getAllUsers
    Ok(Json.toJson(users))
  }
}
