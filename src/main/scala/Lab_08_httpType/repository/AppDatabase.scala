package Lab_08_httpType.repository

import com.outworkers.phantom.connectors.{CassandraConnection, Connector}
import com.outworkers.phantom.database.{Database, DatabaseProvider}

class AppDatabase(override val connector: CassandraConnection) extends Database[AppDatabase](connector) {
  object jobsTable extends JobsTable with Connector
}
