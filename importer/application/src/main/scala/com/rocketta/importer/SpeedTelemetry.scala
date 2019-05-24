package com.rocketta.importer

import java.sql.Timestamp

import com.rocketta.importer.core.Message

case class SpeedTelemetry(
                           deviceId: String,
                           value: Double,
                           timestamp: Timestamp
                         ) extends Message {}
