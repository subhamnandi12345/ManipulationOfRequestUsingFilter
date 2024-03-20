import mill._
import $ivy.`com.lihaoyi::mill-contrib-playlib:`,  mill.playlib._

object demo1 extends PlayModule with SingleModule {

  def scalaVersion = "2.13.13"
  def playVersion = "2.7.1"
  def twirlVersion = "2.0.1"

  object test extends PlayTests
}
