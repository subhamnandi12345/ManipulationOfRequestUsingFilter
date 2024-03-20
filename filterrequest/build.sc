import mill._
import $ivy.`com.lihaoyi::mill-contrib-playlib:`,  mill.playlib._

object filterrequest extends PlayModule with SingleModule {

  def scalaVersion = "2.11.12"
  def playVersion = "2.6.25"
  def twirlVersion = "2.0.1"

  object test extends PlayTests
}
