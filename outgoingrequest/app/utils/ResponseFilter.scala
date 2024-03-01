package utils

import org.apache.pekko.stream.Materializer
import play.api.http.HeaderNames.{CACHE_CONTROL, EXPIRES, PRAGMA, wait}

import javax.inject.Inject
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class ResponseFilter @Inject() (implicit val mat: Materializer, ec: ExecutionContext) extends Filter {
    override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
      nextFilter(requestHeader).map { result =>
        result.body match {
          case body: play.api.mvc.AnyContent =>
            val modifiedBody = body.asText.map(_.toUpperCase)
            result.copy(body = modifiedBody)
          case _ => result
        }
      }
    }
}

