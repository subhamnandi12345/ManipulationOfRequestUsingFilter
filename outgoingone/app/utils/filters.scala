package utils

import org.apache.pekko.stream.Materializer
import play.api.Logger
import play.api.http.DefaultHttpFilters
import play.api.mvc._
import play.api.mvc.Filter

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
      result.withHeaders(

      )
    }
  }

  private def serverTime = {
    dateFormat.format(LocalDateTime.now().atZone(ZoneId.systemDefault()))
  }

}