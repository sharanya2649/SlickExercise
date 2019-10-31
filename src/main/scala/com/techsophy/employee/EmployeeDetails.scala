package com.techsophy.employee

import java.sql.Timestamp
import java.util.Date

import com.techsophy.employee.connection.{DatabaseConnection, MySQLDBConnector}

import scala.concurrent.Future

trait EmployeeQuery extends EmployeeDetails with DepartmentDetails {

  import driver.api._

  def insert(emp: EmployeeData) = {
    db.run(employee += emp)
  }

  def update(name: String, modifyEmail: String) = {
    db.run(employee.filter(_.name === name)
      .map(p => (p.email))
      .update((modifyEmail)))
  }

  def delete(deleteName: String) = {
    db.run(employee.filter(e => e.name === deleteName)
      .delete)
  }

  def getAllEmployee(): Future[List[EmployeeData]] = {
    db.run(employee.to[List].result)
  }

  def getEmployeeByDepartmentId(id: Int): Future[List[EmployeeData]] = {
    db.run((employee.filter(f => f.depId === id)).to[List].result)
  }

  def getEmployeeNameWithDepartmentName(): Future[List[(String, String)]] = {
    db.run(((employee join department on (_.depId === _.id))
      .map { case (a, b) => (a.name, b.depName) }).to[List].result)
  }
}

trait EmployeeDetails extends DatabaseConnection {

  import driver.api._

  implicit def dateImplicit = MappedColumnType.base[java.util.Date, java.sql.Timestamp](
    { utilDate => new Timestamp(utilDate.getTime) },
    { timestamp => new Date(timestamp.getTime) }
  )

  val employee = TableQuery[Employee]

  class Employee(tag: Tag) extends Table[EmployeeData](tag, "employee_table") {
    def * = (name, email, depId, joiningDate, id) <> (EmployeeData.tupled, EmployeeData.unapply)

    def id = column[Int]("id", O.AutoInc, O.PrimaryKey)

    def name = column[String]("name")

    def email = column[String]("email")

    def depId = column[Int]("dep_id")

    def joiningDate = column[Date]("joining_date")

  }

}

case class EmployeeData(name: String, email: String, depId: Int, joiningDate: Date = new Date(), id: Int = 0)

object EmployeeQueryObj extends EmployeeQuery with MySQLDBConnector
