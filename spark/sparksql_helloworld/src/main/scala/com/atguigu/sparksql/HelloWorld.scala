package com.atguigu.sparksql

import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory

import scala.util.parsing.json.JSONObject


/**
  * Created by wuyufei on 31/07/2017.
  */
object HelloWorld {

  //相当于JavaBean DataSet方式 强类型 编译期进行检查 必须指定类型
  case class Person(name: String, age: Long)

  val logger = LoggerFactory.getLogger(HelloWorld.getClass)

  def main(args: Array[String]) {
    // 创建SparkConf()并设置App名称
       val spark = SparkSession
      .builder().master("local")
      .appName("Spark SQL basic example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()
    val df = spark.read.format("json").load("sparksql_helloworld/src/main/resources/people.json")
    // df.show()
    // import spark.implicits._
    // DSL 风格的使用方式中 属性的获取方法
    // df.filter($"age" > 21).show()
    // df.select("name").show()
    // 将DataFrame注册为表
    df.createOrReplaceTempView("persons")
    // df.createOrReplaceTempView("persons")
    spark.sql("SELECT * FROM persons").show()
    // spark.sql("select * from perosns where age > 20").show()
    spark.stop()
  }
}


