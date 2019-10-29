package slick

import slick.jdbc.MySQLProfile.api._


class Queries extends EmployeeDetails {

  def insertEmployee(emp: EmployeeData) = {
    db.run(employeeTable += emp)
  }


  def getAllEmployee(): List[EmployeeData] = {
    db.run(employeeTable.result)
  }

  def getEmployeeByDepartmentId(id: Int): List[EmployeeData] = {

  }

  //  def update(name: String, modifyName: String, age: Int) = {
  //    employee.filter(_.name === name)
  //      .map(p => (p.name, p.age))
  //      .update((modifyName, age))
  //  }

  //    def updateSql = {
  //      sqlu"""
  //            update EMPLOYEE set NAME='johnn', AGE=38 where NAME='Johnn'
  //          """
  //    }
  //  def delete(deleteName: String) = {
  ////    employee.join(department).filter { case (e, d) => e.depId === d.id}
  //    employee.filter(e => e.name === deleteName)
  //      .delete
  //  }

  //  def slick(city: String) = {
  //    val address_ids = department.filter(_.depLocation === city).map(_.id)
  //    employee.filter(_.id in address_ids).result
  //  }


}
