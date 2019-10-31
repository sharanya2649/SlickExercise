package com.techsophy.employee

import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

//testing
class EmployeeDetailsTest extends FunSuite with EmployeeQuery with DepartmentQueries with TestDBConnector {
  test("insert") {
    val insertInput = Await.result(insert(EmployeeData("ken", "ken@gmail.com", 2)), 10 seconds)
    assert(insertInput === 1)
  }

  test("get employees") {
    val res = Await.result(getAllEmployee(), 10 seconds)
    assert(res.length === 3)
  }
  test("insert dep") {
    val insertDep = Await.result(insertDepartment((DepartmentData(2, "IT", "hyderabad"))), 10 seconds)
    assert(insertDep === 1)
  }
  test("get employee by dep_id") {
    val insertInput = Await.result(getEmployeeByDepartmentId(2), 10 seconds)
    assert(insertInput.length === 1)
  }

  test("get employee by dep_id if empty") {
    val insertInput = Await.result(getEmployeeByDepartmentId(4), 10 seconds)
    assert(insertInput.length === 0)
  }
  test("get employee dep_name") {
    val insertInput = Await.result(getEmployeeNameWithDepartmentName(), 10 seconds)
    assert(insertInput === List(("peter","HR")))
  }

}
