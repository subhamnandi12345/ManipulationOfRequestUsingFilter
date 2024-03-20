package controllers

import akka.{Done, NotUsed}
import akka.stream.scaladsl.{Flow, Sink, Source}
import com.google.inject.Inject
import play.api.mvc._

import scala.concurrent.Future

class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  // Define the WebSocket action
  def socket: WebSocket = WebSocket.accept[String, String] { request =>
    // Log the connection establishment
    println("WebSocket connection established.")

    // Create a Flow to handle WebSocket messages
    val incomingMessages: Sink[Any, Future[Done]] = Sink.ignore
    val outgoingMessages: Source[String, NotUsed] = Source.repeat("Server received your message.")

    // Connect the incoming and outgoing streams
    Flow.fromSinkAndSource(incomingMessages, outgoingMessages)
  }
}
