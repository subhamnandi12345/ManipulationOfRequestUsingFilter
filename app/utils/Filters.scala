package utils

import akka.stream.Materializer
import play.Logger
import play.api.http.DefaultHttpFilters
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

  val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z")

  def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    nextFilter(requestHeader).map { result =>
      println("passing through filters ")
      println("hello")
      result.withHeaders(
        PRAGMA -> "no-cache",
        CACHE_CONTROL -> "no-cache, no-store, must-revalidate, max-age=0",
        EXPIRES -> serverTime
      )
    }
  }

  private def serverTime = {
    dateFormat.format(LocalDateTime.now().atZone(ZoneId.systemDefault()))
  }

}