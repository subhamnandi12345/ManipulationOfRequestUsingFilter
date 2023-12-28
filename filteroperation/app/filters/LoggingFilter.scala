package filters

import jdk.internal.net.http.common.Log.headers
import org.apache.pekko.stream.Materializer

import javax.inject.Inject
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class LoggingFilter @Inject()(implicit val mat: Materializer, ec: ExecutionContext) extends Filter {

  override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    val startTime = System.currentTimeMillis

    nextFilter(requestHeader).map { result =>
      val endTime = System.currentTimeMillis
      val requestTime = endTime - startTime
      val dateString = "12/20/2023"
//      requestHeader.headers.add("Date" -> dateString)
      requestHeader.withHeaders(Headers("Date" -> dateString))

      println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+ requestHeader.headers + "############################")
      //println(a)
      // Log the request details
      println(s"${requestHeader.method} ${requestHeader.uri} took ${requestTime}ms and returned ${result.header.status}")
      result
    }
  }
}
