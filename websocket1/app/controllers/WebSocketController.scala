package controllers

import akka.actor.ActorSystem
import akka.stream.{Materializer, ThrottleMode}
import akka.actor._
import akka.stream._
import akka.stream.scaladsl._
import com.google.inject.Inject
import play.api.mvc._
import play.api.libs.streams._

import scala.concurrent.duration.DurationInt

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
class WebSocketController @Inject()(val controllerComponents: ControllerComponents)(implicit system: ActorSystem, mat: Materializer) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def socket: WebSocket = WebSocket.accept[String, String] { request =>
    val sink = Sink.foreach[String]{ requestMessage =>
      if (requestMessage == "Hi")
        println("Hello")
      else
        println("I am on sink ")
    }

    val customFlow = Flow[String].map { clientMessage =>
      if (clientMessage == "Hi") "Hello!"
      else "How are you?"
    }
    val source = Source.single("Hello").via(customFlow)

    Flow.fromSinkAndSourceMat(sink, source)(Keep.left)
  }
}
