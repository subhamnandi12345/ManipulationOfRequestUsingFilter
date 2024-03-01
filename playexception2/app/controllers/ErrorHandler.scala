package controllers

import javax.inject.Singleton
import play.api.mvc.RequestHeader

import scala.concurrent._

import play.api.http.HttpErrorHandler
import play.api.mvc._
import play.api.mvc.Results._

@Singleton
class ErrorHandler extends HttpErrorHandler {
  def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    Future.successful(
      Status(statusCode)("A client error occurred: " + message)
    )
  }

  def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    Future.successful(
      InternalServerError("A server error occurred: " + exception.getMessage)
    )
  }
}
