package models

import play.api.libs.json.{Format, Json}

case class Employee(id:Long,name:String,city:String,mobile:String)
