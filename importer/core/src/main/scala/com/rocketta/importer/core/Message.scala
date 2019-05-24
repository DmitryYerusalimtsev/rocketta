package com.rocketta.importer.core

import java.sql.Timestamp

abstract class Message {
  val timestamp: Timestamp
}
