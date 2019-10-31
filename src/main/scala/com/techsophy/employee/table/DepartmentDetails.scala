package com.techsophy.employee.table

import com.techsophy.employee.connection.{DatabaseConnection, MySQLDBConnector}

import scala.concurrent.Future

trait DepartmentQueries extends DepartmentDetails {

  import driver.api._

  def insertDepartment(dep: DepartmentData) = {
    db.run(department += dep)
  }

  def updateDepartment(id: Int, modifyDepName: String) = {
    db.run(department.filter(_.id === id)
      .map(p => (p.depName))
      .update((modifyDepName)))
  }
  def deleteDepartment(id: Int) = {
    db.run(department.filter(e => e.id === id)
      .delete)
  }
  def getAllDepartment(): Future[List[DepartmentData]] = {
    db.run(department.to[List].result)
  }
}

trait DepartmentDetails extends DatabaseConnection with EmployeeDetails {

  import driver.api._

  val department = TableQuery[Department]

  class Department(tag: Tag) extends Table[DepartmentData](tag, "department") {
    def * = (id, depName, depLocation) <> (DepartmentData.tupled, DepartmentData.unapply)

    def id = column[Int]("id")

    def depName = column[String]("dep_name")

    def depLocation = column[String]("dep_location")

    def emp = foreignKey("emp",id,employee)(_.id)

  }

}

case class DepartmentData(id: Int, depName: String, depLocation: String)

object DepartmentQueriesObj extends DepartmentQueries with MySQLDBConnector
