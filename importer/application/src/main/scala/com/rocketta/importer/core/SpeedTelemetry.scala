package com.rocketta.importer.core

import java.sql.Timestamp

import com.rocketta.importer.core.model.Message

case class SpeedTelemetry(
                           deviceId: String,
                           value: Double,
                           timestamp: Timestamp
                         ) extends Message {}
