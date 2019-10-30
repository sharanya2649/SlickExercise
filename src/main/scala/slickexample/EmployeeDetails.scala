package slickexample

import slick.jdbc.MySQLProfile.api._

class EmployeeDetails extends DatabaseConnection {

  val employee = TableQuery[Employee]

  class Employee(tag: Tag) extends Table[EmployeeData](tag, "DEPARTMENT") {
    def * = (id, name, email, depId) <> (EmployeeData.tupled, EmployeeData.unapply)

    def id = column[Int]("id")

    def name = column[String]("name")

    def email = column[String]("email")
    def depId = column[Int]("dep_id")

  }

  case class EmployeeData(id: Int, name: String, email: String, depId: Int)
}
