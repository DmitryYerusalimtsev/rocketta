package com.rocketta.importer.speedometer

import java.sql.Timestamp

import com.rocketta.importer.core.Message

case class Speed(
                  deviceId: String,
                  value: Double,
                  timestamp: Timestamp
                ) extends Message {}
