package com.rocketta.importer.core.model

import java.sql.Timestamp

abstract class Message {
  val timestamp: Timestamp
}
