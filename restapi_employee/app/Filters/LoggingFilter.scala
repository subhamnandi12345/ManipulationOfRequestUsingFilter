package Filters

// app/Filters/LoggingFilter.scala
package filters

import org.apache.pekko.stream.Materializer

import javax.inject.Inject
import play.api.Logger
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class LoggingFilter @Inject()(implicit val mat: Materializer, ec: ExecutionContext) extends Filter {

  private val logger = Logger(this.getClass)

  override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    val startTime = System.currentTimeMillis

    nextFilter(requestHeader).map { result =>
      val endTime = System.currentTimeMillis
      val requestTime = endTime - startTime

      logger.info(s"${requestHeader.method} ${requestHeader.uri} took ${requestTime}ms and returned ${result.header.status}")

      result.withHeaders("Request-Time" -> requestTime.toString)
    }
  }
}

