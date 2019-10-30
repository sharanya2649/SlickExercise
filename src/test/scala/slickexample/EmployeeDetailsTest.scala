package slickexample

import java.sql.Date

import org.scalatest.FunSuite
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.Await
import scala.concurrent.duration._

//testing
class EmployeeDetailsTest extends FunSuite with DatabaseConnection {
  val emp=new EmployeeDetails
  val query=new Queries
  val dep=new DepartmentDetails
  test("insert"){
    Await.result(db.run(DBIO.seq(
      emp.employee.schema.create
    )), Duration.Inf)
    val insertInput =Await.result(query.insert(query.EmployeeData(1,"ken","ken@gmail.com",2)),10 seconds)
    assert(insertInput === 1)
  }
  test("insert dep"){
    Await.result(db.run(DBIO.seq(
      dep.department.schema.create
    )), Duration.Inf)
    val insertDep= Await.result(query.insertDepartment((query.departmentObj.DepartmentData(2,"IT","hyderabad"))),10 seconds)
    assert(insertDep===1)
  }
  test("get employee"){
    val insertInput =Await.result(query.getAllEmployee(),10 seconds)
    assert(insertInput === List(query.EmployeeData(1,"ken","ken@gmail.com",2)))
  }
  test("get employee by dep_id"){
    val insertInput =Await.result(query.getEmployeeByDepartmentId(2),10 seconds)
    assert(insertInput === List(query.EmployeeData(1,"ken","ken@gmail.com",2)))
  }
  test("get employee dep_name"){
    val insertInput =Await.result(query.getEmployeeNameWithDepartmentName(),10 seconds)
    assert(insertInput === List(("ken","IT")))
  }

}
