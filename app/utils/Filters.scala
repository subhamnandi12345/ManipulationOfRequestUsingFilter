package utils

import akka.stream.Materializer
import akka.util.ByteString
import play.Logger
import play.api.http.{DefaultHttpFilters, HttpEntity}
import play.api.http.HeaderNames._
import play.api.mvc._

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.{LocalDateTime, ZoneId}
import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class Filters @Inject()(headersFilter: HeadersFilter) extends DefaultHttpFilters( headersFilter)

@Singleton
class HeadersFilter @Inject()(implicit val mat: Materializer, executionContextUtils: ExecutionContext) extends Filter {

  def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    nextFilter(requestHeader).map { result =>
      result.body match {
        case HttpEntity.Strict(data, contentType) =>
          val modifiedData = data.utf8String.replace("hello world", "HELLO WORLD")
          val modifiedByteString = ByteString(modifiedData, "UTF-8")
          result.copy(body = HttpEntity.Strict(modifiedByteString, contentType))
        case other =>
          result
      }
    }
  }

}