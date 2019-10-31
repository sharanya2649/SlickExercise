package com.techsophy.employee.table

import com.techsophy.employee.connection.{DatabaseConnection, MySQLDBConnector}

trait DepartmentQueries extends DepartmentDetails {

  import driver.api._

  def insertDepartment(dep: DepartmentData) = {
    db.run(department += dep)
  }

}

trait DepartmentDetails extends DatabaseConnection {

  import driver.api._

  val department = TableQuery[Department]

  class Department(tag: Tag) extends Table[DepartmentData](tag, "department") {
    def * = (id, depName, depLocation) <> (DepartmentData.tupled, DepartmentData.unapply)

    def id = column[Int]("id")

    def depName = column[String]("dep_name")

    def depLocation = column[String]("dep_location")

  }

}

case class DepartmentData(id: Int, depName: String, depLocation: String)

object DepartmentQueriesObj extends DepartmentQueries with MySQLDBConnector
