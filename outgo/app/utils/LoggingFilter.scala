package utils

import akka.stream.Materializer

import javax.inject.Inject
import play.api.Logger
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

class LoggingFilter @Inject()(val mat: Materializer,ec: ExecutionContext) extends Filter {
  override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    val startTime = System.currentTimeMillis
    nextFilter(requestHeader).map { result =>
      val endTime = System.currentTimeMillis
      val requestTime = endTime - startTime
      println("subham")
      println(s"${requestHeader.method} ${requestHeader.uri} took ${requestTime}ms and returned ${result.header.status}")
      result
    }
  }
}
