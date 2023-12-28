package filters

// app/filters/MyFilter.scala

import org.apache.pekko.stream.Materializer
import org.apache.pekko.util.ByteString
import play.api.libs.streams.Accumulator

import javax.inject.Inject
import play.api.mvc._

// app/filters/MyFilter.scala

import javax.inject.Inject
//import akka.stream.Materializer
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}

// app/filters/MyFilter.scala

import javax.inject.Inject
//import akka.stream.Materializer
import play.api.mvc._
//import akka.util.ByteString

import scala.concurrent.{ExecutionContext, Future}

// MyFilters.scala
import javax.inject.Inject
import play.api.http.HttpFilters
import play.filters.gzip.GzipFilter
import play.filters.cors.CORSFilter

class MyFilters @Inject() (gzipFilter: GzipFilter, corsFilter: CORSFilter) extends HttpFilters {
  def filters = Seq(gzipFilter, corsFilter)
}

