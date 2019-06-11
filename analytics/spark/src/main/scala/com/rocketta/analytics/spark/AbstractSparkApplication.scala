package com.rocketta.analytics.spark

import org.apache.spark._

abstract class AbstractSparkApplication {

  protected def getAppName: String

  protected val master: String = "local[*]"

  protected def initSparkConf(conf: SparkConf) {
    conf.setMaster(master)
    conf.setAppName(getAppName)
  }

  protected val sc: SparkContext = {
    val sparkConf = new SparkConf()
    initSparkConf(sparkConf)

    val context = new SparkContext(sparkConf)
    context.setLogLevel("ERROR")

    context
  }

  def main(args: Array[String]): Unit = run(args)

  def run(args: Array[String]): Unit = {
    try {
      init(args)
      execute()
    } catch {
      case e: Exception =>
        throw new RuntimeException("An error occurred while running the application", e)
    }
  }

  protected def init(args: Array[String]): Unit = {}

  protected def execute(): Unit
}
