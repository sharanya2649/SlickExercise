package slick
import slick.jdbc.MySQLProfile.api._
object DatabaseConnection {
  def db = {
    Database.forURL("jdbc:mysql://localhost:3306/EmployeeInformation",
      driver = "com.mysql.jdbc.Driver",
      user = "root", password = "root")
  }
}
