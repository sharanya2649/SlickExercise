package slick
import slick.dbio.{DBIOAction, Effect}
import slick.jdbc.MySQLProfile
import slick.jdbc.MySQLProfile.api._
import slick.lifted.QueryBase
import slick.sql.FixedSqlAction

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
class EmployeeDetails{

    case class Employee(tag: Tag) extends Table[(Int, String, Int, Int)](tag, "EMPLOYEE") {
      def id = column[Int]("ID")

      def name = column[String]("NAME")

      def age = column[Int]("AGE")

      def depId = column[Int]("DEP_ID")

      def * = (id, name, age, depId)
    }

    val employee = TableQuery(Employee)

    case class Department(tag: Tag) extends Table[(Int, String, String)](tag, "DEPARTMENT") {
      def id = column[Int]("ID")

      def depName = column[String]("DEP_NAME")

      def depLocation = column[String]("DEP_LOCATION")

      def * = (id, depName, depLocation)

    }

    val department = TableQuery(Department)

    //    try {

    //    val employeeSchema: Unit =Await.result(DatabaseConnection.db.run(DBIO.seq(
    //      employee.schema.create
    //    )), Duration.Inf)

    //      val departmentSchema: Unit = Await.result(DatabaseConnection.db.run(DBIO.seq(
    //        department.schema.create
    //      )), Duration.Inf)

    val insertEmployee = {
      employee.map(e => (e.id, e.name, e.age, e.depId)) ++= Seq((1, "john", 26, 3), (2, "sony", 30, 4), (3, "peter", 40, 2), (4, "marvel", 22, 3), (5, "kim", 50, 4))
    }

    val update = {
      employee.filter(_.name === "john")
        .map(p => (p.name, p.age))
        .update(("Johnn", 32))
    }
    val updateSql = {
      sqlu"""
            update EMPLOYEE set NAME='Johnn', AGE=38 where NAME='john'
          """
    }
    val delete = {
      employee.filter(e => e.name === "john")
        .delete
    }

    val insertDepartment = {
      department.map(d => (d.id, d.depName, d.depLocation)) ++= Seq((5, "IT", "hyderabad"), (2, "IT", "bangalore"), (3, "IT", "chennai"), (1, "HR", "hyderabad"), (4, "HR", "chennai"))
    }

  val sql =
    sql"""
            select *
            from EMPLOYEE E
            where E.ID in (select ID
                           from DEPARTMENT
                           where DEP_LOCATION = 'hyderabad')
          """.as[(Int, String, Int, Int)]
  val slick = {
    val address_ids = department.filter(_.depLocation=== "hyderabad").map(_.id)
    employee.filter(_.id in address_ids).result
  }

    //    val insertEmployeeResult = Await.result(DatabaseConnection.db.run(insertEmployee), Duration.Inf)
    //      val insertDepartmentResult = Await.result(DatabaseConnection.db.run(insertDepartment), Duration.Inf)
    //    val updateResult = Await.result(DatabaseConnection.db.run(update), Duration.Inf)
    //    val deleteResult = Await.result(DatabaseConnection.db.run(delete), Duration.Inf)
    //    } finally DatabaseConnection.db.close


    //    try {
    //    val set: DBIOAction[Unit, NoStream, Effect.Schema with Effect.Write] = DBIO.seq(
    //      (employee.schema ++ department.schema).create,
    //      employee += (1, "john", 26, 3),
    //      employee += (2, "sony", 30, 4),
    //      employee += (3, "peter", 40, 2),
    //      employee += (4, "marvel", 22, 3),
    //      employee += (5, "kim", 50, 4),
    //      employee += (6, "bill", 45, 2),
    //      employee += (6, "james", 29, 4),
    //
    //      department ++= Seq(
    //        (5, "IT", "hyderabad"),
    //        (2, "IT", "bangalore"),
    //        (3, "IT", "chennai"),
    //        (1, "HR", "hyderabad"),
    //        (4, "HR", "chennai"),
    //        (6, "HR", "bangalore")
    //      )
    //    )
    //    val setupFuture: Future[Unit] = DatabaseConnection.db.run(set)
    //
    //    println("DepNames:")
    //    val dep= department.map(p => (p.id, p.depName, p.depLocation))
    //    val results: Future[Seq[(Int, String, String)]] =DatabaseConnection.db.run(dep.result)
    //    println(results.map(r=>(r)))
    //
    //
    //    employee.filter(x => x.name === "john")
    //      .map(p => (p.name, p.age))
    //      .update(("john", 23))
    //
    //    employee.filter(x => x.name === "james").delete
    //
    //    println("employee details")
    //    val emp = employee.map(e => (e.id, e.name, e.age, e.depId))
    //    DatabaseConnection.db.run(employee.result)
    //  }finally DatabaseConnection.db.close()


}

