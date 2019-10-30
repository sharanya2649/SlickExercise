package com.techsophy.employee

import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

class Queries extends EmployeeDetails {
  val departmentObj = new DepartmentDetails
  val dep = new DepartmentDetails

  def insert(emp: EmployeeData) = {
    db.run(employee += emp)
  }

  def insertDepartment(dep: departmentObj.DepartmentData) = {
    db.run(departmentObj.department += dep)
  }

  def getAllEmployee(): Future[List[EmployeeData]] = {
    db.run(employee.to[List].result)
  }

  def getEmployeeByDepartmentId(id: Int): Future[List[EmployeeData]] = {
    db.run((employee.filter(f => f.depId === id)).to[List].result)
  }

  def getEmployeeNameWithDepartmentName(): Future[List[(String, String)]] = {
    db.run(((employee join dep.department on (_.depId === _.id))
      .map { case (a, b) => (a.name, b.depName) }).to[List].result)
  }
}
