package controllers

import javax.inject.{Inject, Provider}
import play.api._
import play.api.http.DefaultHttpErrorHandler
import play.api.mvc._
import play.api.mvc.Results._
import play.api.routing.Router

import scala.concurrent._

class ErrorHandler @Inject()(
                              env: Environment,
                              config: Configuration,
                              sourceMapper: OptionalSourceMapper,
                              router: Provider[Router]
                            ) extends DefaultHttpErrorHandler(env, config, sourceMapper, router) {

  override def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    Future.successful {
      InternalServerError("Oops! Something went wrong.")
    }
  }
}
