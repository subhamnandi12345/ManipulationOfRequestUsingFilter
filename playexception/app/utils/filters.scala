package utils

import akka.stream.Materializer
import javax.inject.Inject
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class OutgoingRequestFilter @Inject()(implicit val mat: Materializer, ec: ExecutionContext) extends EssentialFilter {
  override def apply(next: EssentialAction): EssentialAction = new EssentialAction {
    override def apply(request: RequestHeader): Future[Result] = {
      // Manipulate the outgoing request here
      val modifiedRequest = request.addHttpHeaders("Custom-Header" -> "Value")

      // Call the next action in the chain
      val resultFuture: Future[Result] = next(modifiedRequest)

      // You can also manipulate the response here if needed
      resultFuture.map { result =>
        result.withHeaders("Another-Custom-Header" -> "Another-Value")
      }
    }
  }
}
