package slick

import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration._

class DepartmentDetails {

  val department = TableQuery(Department)
  val departmentSchema: Unit = Await.result(DatabaseConnection.db.run(DBIO.seq(
    department.schema.create
  )), 10 seconds)

  case class Department(tag: Tag) extends Table[DepartmentData](tag, "DEPARTMENT") {
    def * = (id, depName, depLocation) <> (DepartmentData.tupled, DepartmentData.unapply)

    def id = column[Int]("id")

    def depName = column[String]("dep_name")

    def depLocation = column[String]("dep_location")

  }

  case class DepartmentData(id: Int, depName: String, depLocation: String)
}
