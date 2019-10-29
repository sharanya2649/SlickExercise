package slick
import slick.jdbc.MySQLProfile.api._
trait DatabaseConnection {
  def db = {
    Database.forURL("jdbc:mysql://localhost:3306/EmployeeInfor",
      driver = "com.mysql.jdbc.Driver",
      user = "root", password = "root")
  }
}
