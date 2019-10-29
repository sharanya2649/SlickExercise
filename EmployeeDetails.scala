package slick
import slick.dbio.{DBIOAction, Effect}
import slick.jdbc.MySQLProfile
import slick.jdbc.MySQLProfile.api._
import slick.lifted.QueryBase
import slick.sql.{FixedSqlAction, FixedSqlStreamingAction}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import slick.jdbc.MySQLProfile.api._

class EmployeeDetails {

    case class Employee(tag: Tag) extends Table[EmployeeData](tag, "EMPLOYEE") {
      def id = column[Int]("id")

      def name = column[String]("name")

      def age = column[Int]("age")

      def depId = column[Int]("dep_id")

      def * = (id, name, age, depId) <> (EmployeeData.tupled, EmployeeData.unapply)
    }
  case class EmployeeData(id: Int, name: String,age: Int, depId: Int)

    val employee = TableQuery(Employee)

    case class Department(tag: Tag) extends Table[DepartmentData](tag, "DEPARTMENT") {
      def id = column[Int]("id")

      def depName = column[String]("dep_name")

      def depLocation = column[String]("dep_location")

      def * = (id, depName, depLocation) <> (DepartmentData.tupled,DepartmentData.unapply)

    }
    case class DepartmentData(id:Int,depName:String,depLocation:String)
    val department = TableQuery(Department)


//        val employeeSchema: Unit =Await.result(DatabaseConnection.db.run(DBIO.seq(
//          employee.schema.create
//        )), Duration.Inf)
//
//          val departmentSchema: Unit = Await.result(DatabaseConnection.db.run(DBIO.seq(
//            department.schema.create
//          )), Duration.Inf)

    def insertEmployee(emp:EmployeeData) = {
      employee.map(e => (e.id, e.name, e.age, e.depId)) += (emp.id,emp.name,emp.age,emp.depId)
    }

    def update(name:String,modifyName:String,age:Int) = {
      employee.filter(_.name === name)
        .map(p => (p.name, p.age))
        .update((modifyName, age))
    }
//    def updateSql = {
//      sqlu"""
//            update EMPLOYEE set NAME='johnn', AGE=38 where NAME='Johnn'
//          """
//    }
    def delete(deleteName:String) = {
      employee.filter(e => e.name === deleteName)
        .delete
    }
//    def deleteSql= {
//      sqlu"""
//             delete EMPLOYEE where NAME='sony'
//             """
//    }

//    def insertDepartment() = {
//      department.map(d => (d.id, d.depName, d.depLocation)) ++= Seq((5, "IT", "hyderabad"), (2, "IT", "bangalore"), (3, "IT", "chennai"), (1, "HR", "hyderabad"), (4, "HR", "chennai"))
//    }

//  def sql =
//    sql"""
//            select *
//            from EMPLOYEE E
//            where E.ID in (select ID
//                           from DEPARTMENT
//                           where DEP_LOCATION = 'hyderabad')
//          """.as[(Int, String, Int, Int)]

  def slick(city:String) = {
    val address_ids = department.filter(_.depLocation=== city).map(_.id)
    employee.filter(_.id in address_ids).result
  }
}

