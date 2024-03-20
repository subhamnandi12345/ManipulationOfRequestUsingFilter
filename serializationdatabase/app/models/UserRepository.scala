package models

import java.sql.{Connection, DriverManager, ResultSet}
import javax.inject.{Inject, Singleton}
import scala.collection.mutable.ListBuffer


@Singleton
class UserRepository @Inject()() {
  val url = "jdbc:mysql://localhost:3306/serializedb"
  val user = "root"
  val password = "root@123"

  def getConnection: Connection = DriverManager.getConnection(url, user, password)

  def findById(id: Long): Option[User] = {
    val connection = getConnection
    try {
      val statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")
      statement.setLong(1, id)
      val resultSet = statement.executeQuery()
      if (resultSet.next()) {
        Some(User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email")))
      } else {
        None
      }
    } finally {
      connection.close()
    }
  }

  def save(user: User): Int = {
    val connection = getConnection
    try {
      val statement = connection.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")
      statement.setString(1, user.name)
      statement.setString(2, user.email)
      statement.executeUpdate()
    } finally {
      connection.close()
    }
  }

  def getAllUsers: Seq[User] = {
    val connection = getConnection
    try {
      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("SELECT * FROM users")
      val users = ListBuffer[User]()
      while (resultSet.next()) {
        users += User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email"))
      }
      users.toList
    } finally {
      connection.close()
    }
  }
}
