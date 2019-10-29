package slickexample

import slick.jdbc.MySQLProfile.api._

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
class Queries extends EmployeeDetails with DepartmentDetails {
    def insert(emp:EmployeeData)={
      db.run(employee+=emp)
    }

  def getAllEmployee():List[EmployeeData] ={
    employee.result
  }


  def getEmployeeByDepartmentId(id:Int):List[EmployeeData] ={
    employee.filter(f=>f.depId===id).result
  }

  def getEmployeeNameWithDepartmentName():List[(String,String)] = {
    db.run((employee join department on (_.depId===_.id))
      .map{case(a,b)=>(a.name,b.depName)}.result)
  }

}
