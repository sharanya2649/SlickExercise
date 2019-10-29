package slickexample

import slick.jdbc.MySQLProfile

trait DatabaseConnection {

  import slick.jdbc.MySQLProfile.api._

  def db: MySQLProfile.backend.DatabaseDef = {
    Database.forURL("jdbc:mysql://localhost:3306/EmployeeInfor",
      driver = "com.mysql.jdbc.Driver",
      user = "root", password = "root")
  }
}
