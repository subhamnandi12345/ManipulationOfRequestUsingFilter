package utils
import org.apache.pekko.stream.Materializer

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import play.api.mvc._

class filters @Inject()(implicit val mat: Materializer, ec: ExecutionContext) extends Filter {
  override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    val startTime = System.currentTimeMillis
   println("hello")
    nextFilter(requestHeader).map { result =>
      val endTime = System.currentTimeMillis
      val requestTime = endTime - startTime
      val message = s"${requestHeader.method} ${requestHeader.uri} took ${requestTime}ms and returned ${result.header.status}"
      println(message)
      result
    }
  }
}


