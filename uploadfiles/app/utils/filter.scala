import org.apache.pekko.stream.Materializer
import play.api.http.DefaultHttpFilters

import javax.inject.Inject
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class SessionAuthFilter @Inject()(implicit val mat: Materializer, ec: ExecutionContext) extends Filter {

  override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    val maybeSessionKey=requestHeader.headers.get("sessionKey")
    maybeSessionKey match {
      case Some(sessionKey) =>
        // Checking if the session key matches a predefined value
        if (sessionKey == "adminSessionKey123") {
          // If session key is valid for admin, continue processing the request
          nextFilter(requestHeader)
        } else {
          // If session key is not valid for admin, reject the request
          Future.successful(Results.Unauthorized("Invalid admin session key"))
        }
      case None =>
        // If session key is not found, reject the request
        Future.successful(Results.Unauthorized("Session key missing"))
    }
  }

  // Example method to validate session key
  //  private def isValidSessionKey(sessionKey: String): Boolean = {
  //    // Your validation logic goes here
  //    // For example, you can check if the sessionKey exists in the database or matches some criteria
  //    // This is a placeholder method, replace it with your actual implementation
  //    sessionKey.nonEmpty && sessionKey == "validSessionKey"
  //  }
}

//class Filters @Inject()(sessionAuthFilter: SessionAuthFilter) extends
//  DefaultHttpFilters(sessionAuthFilter)
