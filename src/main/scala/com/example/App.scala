package com.example

import org.rogach.scallop.{ScallopConf, Subcommand}

import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
 * @author ${user.name}
 */
object App {

  def main(args : Array[String]): Unit = {
    val conf = new Conf(args)  // Note: This line also works for "object Main extends App"
    println("conf = " + conf.tpch.inputFormat)
  }

}


class Conf(args: Array[String]) extends ScallopConf(args) {
  val convertTpch = new Subcommand("convert-tpch") {
    val input = opt[String](required = true)
    val inputFormat = opt[String](required = true)
    val output = opt[String](required = true)
    val outputFormat = opt[String](required = true)
    val partitions = opt[Int](required = true)
  }
  val tpch = new Subcommand("tpch") {
    val inputPath = opt[String](required = true)
    val queryPath = opt[String](required = true)
    val inputFormat = opt[String](required = true)
    val query = opt[String](required = true)
    val iterations = opt[Int](required = false, default = Some(1))
  }
  addSubcommand(convertTpch)
  addSubcommand(tpch)
  requireSubcommand()
  verify()
}
