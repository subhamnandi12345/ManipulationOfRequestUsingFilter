package config

import java.sql.{Connection, DriverManager}

object DatabaseConfig {
  private val url="jdbc:mysql://localhost:3306:imagedatabase"
  private val user="root"
  private val password="root@123"
  def getConnection: Connection={
     Class.forName("com.mysql.cj.jdbc.driver")
     DriverManager.getConnection(url,user,password)
  }
}

