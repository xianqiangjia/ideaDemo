package com.bigdata.excutorReturnValue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext, TaskContext}

/**
  * 我想要在driver端获取executor执行task返回的结果，
  * 比如task是个规则引擎，我想知道每条规则命中了几条数据，请问这个怎么做呢？
  */
object ExcutorReturnValue {

  def main(args: Array[String]): Unit = {

    ///tianjia xinxixixixix

    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("count")
    val sc = new SparkContext(conf)
    val rdd: RDD[String] = sc.parallelize(Array("1", "2", "3", "4", "5", "6")).repartition(4)
    //
    println(rdd.count())

    val func = (itr: Iterator[String]) => {//Iterator是一个partition的数据集和
      var count = 0
      itr.foreach(each => {
        count += 1
      })
      (TaskContext.getPartitionId(), count)
    }

    // Run a job on all partitions in an RDD and return the results in an array. 到driver端
    val res: Array[(Int, Int)] = sc.runJob(rdd, func)

    res.foreach(println)

    sc.stop()

  }

}
