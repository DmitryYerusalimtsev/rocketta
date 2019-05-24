package com.rocketta.importer.kafkastreams

import java.util.Properties

import com.rocketta.importer.config.EnvironmentVariables
import com.rocketta.importer.core.{Message, Source}
import com.rocketta.importer.kafkastreams.serde.{JsonDeserializer, JsonSerializer}
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.kstream.{Consumed, KStream}
import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder, StreamsConfig}

trait StreamProcessor[M <: Message] extends Source[M] with EnvironmentVariables {

  protected val appId: String
  protected val topic: String

  private[this] val props: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, appId)
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaStreams.bootstrapServers)
    p
  }

  implicit val messageSerde = {
    val serializer = new JsonSerializer[M]
    val deserializer = new JsonDeserializer[M]
    val messageSerde = Serdes.serdeFrom(serializer, deserializer)
    Consumed.`with`(Serdes.String(), messageSerde)
  }

  def start(): Unit = streams.start()

  private[this] def streams(implicit consumed: Consumed[String, M]) = {

    val builder: StreamsBuilder = new StreamsBuilder
    process(builder)

    val streams: KafkaStreams = new KafkaStreams(builder.build(), props)

    streams
  }

  protected def process(builder: StreamsBuilder): KStream[String, M]
}
