package com.techsophy.employee

import slick.jdbc.MySQLProfile.api._

class DepartmentDetails extends DatabaseConnection {

  val department = TableQuery[Department]

  class Department(tag: Tag) extends Table[DepartmentData](tag, "DEPARTMENT") {
    def * = (id, depName, depLocation) <> (DepartmentData.tupled, DepartmentData.unapply)

    def id = column[Int]("id")

    def depName = column[String]("dep_name")

    def depLocation = column[String]("dep_location")

  }

  case class DepartmentData(id: Int, depName: String, depLocation: String)

}
