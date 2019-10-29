package slickexample

import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

//testing
class EmployeeDetailsTest extends FunSuite {
  val emp=new EmployeeDetails
  val query=new Queries
  test("insert"){
    val insertInput =Await.result(query.insert(query.EmployeeData(1,"ken","ken@gmail.com",2)),10 seconds)
    assert(insertInput === 1)
  }
  test("get employee"){
    val insertInput =Await.result(query.getAllEmployee(),10 seconds)
    assert(insertInput === 1)
  }
  test("get employee by dep_id"){
    val insertInput =Await.result(query.getEmployeeByDepartmentId(),10 seconds)
    assert(insertInput === 1)
  }
  test("get employee dep_name"){
    val insertInput =Await.result(query.getEmployeeNameWithDepartmentName(),10 seconds)
    assert(insertInput === 1)
  }

//  test("update test") {
//    val employeeInput = Await.result(DatabaseConnection.db.run(employee.update("Johnn","john",34)), Duration.Inf)
////    val employeeOutput = Await.result(DatabaseConnection.db.run(employee.updateSql), Duration.Inf)
//    assert(employeeInput === 1)
//  }
//  test("retrieve test"){
////    val retrieveInput= Await.result(DatabaseConnection.db.run(employee.sql),Duration.Inf)
//    val retrieveOutput= Await.result(DatabaseConnection.db.run(employee.slick("hyderabad")),Duration.Inf)
//    assert(Vector(employee.EmployeeData(5,"kim",50,4), employee.EmployeeData(1,"john",34,3))===retrieveOutput)
//  }
//  test("delete test"){
//    val deleteInput = Await.result(DatabaseConnection.db.run(employee.delete("ken")), Duration.Inf)
////    val deleteOutput = Await.result(DatabaseConnection.db.run(employee.deleteSql), Duration.Inf)
//    assert(deleteInput===1)
//  }
}
