package com.rocketta.importer.core

import com.rocketta.importer.core.model.Message

trait Source[+MESSAGE <: Message] {}

trait Sink[+MESSAGE <: Message] {}
