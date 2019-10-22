package slick

import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class EmployeeDetailsTest extends FunSuite{
  val employee= new EmployeeDetails()
  test("update test") {
    val employeeInput = Await.result(DatabaseConnection.db.run(employee.update), Duration.Inf)
    val employeeOutput = Await.result(DatabaseConnection.db.run(employee.updateSql), Duration.Inf)
    assert(employeeInput === employeeOutput)
  }
  test("retrieve test"){
    val retrieveInput= Await.result(DatabaseConnection.db.run(employee.sql),Duration.Inf)
    val retrieveOutput= Await.result(DatabaseConnection.db.run(employee.slick),Duration.Inf)
    assert(retrieveInput===retrieveOutput)
  }
}
