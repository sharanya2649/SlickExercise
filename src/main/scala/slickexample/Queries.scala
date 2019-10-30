package slickexample

import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

class Queries extends EmployeeDetails {
  val dep=new DepartmentDetails
    def insert(emp:EmployeeData)={
      db.run(employee+=emp)
    }

  def getAllEmployee(): Future[List[EmployeeData]] ={
    db.run(employee.to[List].result)
  }
  def getEmployeeByDepartmentId(id:Int):Future[List[EmployeeData]] ={
    db.run((employee.filter(f=>f.depId===id)).to[List].result)
  }

  def getEmployeeNameWithDepartmentName():Future[List[(String,String)]] = {
    db.run(((employee join dep.department on (_.depId===_.id))
      .map{case(a,b)=>(a.name,b.depName)}).to[List].result)
  }
}
