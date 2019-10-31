package com.techsophy.employee.connection

import com.typesafe.config.ConfigFactory
import slick.jdbc.MySQLProfile

trait MySQLDBConnector extends DatabaseConnection {

  val driver = MySQLProfile

  import driver.api._

  def db: MySQLProfile.backend.DatabaseDef = Database.forConfig("db",ConfigFactory.load().withFallback(ConfigFactory.load("application")))
}
