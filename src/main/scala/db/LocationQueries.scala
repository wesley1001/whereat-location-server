package db

import model.Location
import slick.lifted.TableQuery
import slick.driver.H2Driver.api._

/**
 * Author: @aguestuser
 * License: GPLv3 (https://www.gnu.org/licenses/gpl-3.0.html)
 */

trait LocationQueries {
  val locations = TableQuery[Locations]
  val createSchema = locations.schema.create

  val insert = { l: Location ⇒ locations += l }
  val insertMany = { ls: Seq[Location] ⇒ locations ++= ls }

  val fetch = { id: String ⇒ locations.filter(_.id === id) }
  val allSince = { t: Long ⇒ locations.filter(_.time > t) }

  val update = { (id: String, l: Location) ⇒ fetch(id).update(l) }

  val delete = { id: String ⇒ fetch(id).delete }
  val clear = locations.delete
}
