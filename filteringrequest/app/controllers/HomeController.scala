import jdk.internal.net.http.common.Log.headers
import org.apache.pekko.stream.Materializer
import play.api.mvc.Headers

import javax.inject.Inject
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class HeaderManipulationFilter @Inject() (
                                           implicit
                                           val mat: Materializer,
                                           ec: ExecutionContext
                                         ) extends Filter {

  override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    // Manipulate headers here
    val modifiedHeaders = requestHeader.headers.add("Custom-Header" -> "Custom-Value")

    // Create a new Headers instance with the modified headers
    val modifiedRequestHeaders = new Headers(modifiedHeaders.toSimpleMap.toSeq: _*)

    // Pass the modified request to the next filter or action
    val modifiedRequest = requestHeader.copy(headers = modifiedRequestHeaders)

    // Call the next filter or action with the modified request
    nextFilter(modifiedRequest)
  }
}
