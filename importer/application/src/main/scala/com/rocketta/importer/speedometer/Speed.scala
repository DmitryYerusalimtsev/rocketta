package com.rocketta.importer.speedometer

import java.util.Date

import com.rocketta.importer.core.Message

case class Speed(
                  deviceId: String,
                  value: Double,
                  timestamp: Date
                ) extends Message {}
