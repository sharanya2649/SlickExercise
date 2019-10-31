package com.techsophy.employee.table

import com.techsophy.employee.connection.TestDBConnector
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

//testing
class DepartmentDetailsTest extends FunSuite with EmployeeQuery with DepartmentQueries with TestDBConnector {
  test("insert department") {
    val insertInput = Await.result(insertDepartment(DepartmentData(2,"HR","hyderabad")), 10 seconds)
    assert(insertInput === 1)
  }
  test("update department") {
    val insertInput = Await.result(updateDepartment(8,"HR"), 10 seconds)
    assert(insertInput === 1)
  }
  test("delete department") {
    val insertInput = Await.result(deleteDepartment(4), 10 seconds)
    assert(insertInput === 1)
  }
  test("get employees") {
    val res = Await.result(getAllDepartment(), 10 seconds)
    assert(res.length === 3)
  }
}

