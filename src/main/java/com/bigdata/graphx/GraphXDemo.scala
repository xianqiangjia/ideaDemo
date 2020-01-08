package com.bigdata.graphx

import org.apache.spark.graphx.{Edge, Graph, VertexId}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.slf4j.LoggerFactory

/**
  * 社交网络，顶点是每个人的信息，边是两人关系的描述
  */

object GraphXDemo {
  val logger = LoggerFactory.getLogger(GraphXDemo.getClass)

  def main(args: Array[String]) {
    //创建SparkConf()来包含整个Spark配置信息
    val conf = new SparkConf().setMaster("local[*]").setAppName("GraphX")
    //创建SparkContext，该对象是提交spark App的入口
    val sc = new SparkContext(conf)
    // 创建一个顶点的集合，VertexID是一个long类型的，顶点的属性是一个二元组
    val users: RDD[(VertexId, (String, String))] = sc.parallelize(Array((3L, ("rxin", "student")), (7L, ("jgonzal", "postdoc")), (5L, ("franklin", "prof")), (2L, ("istoica", "prof"))))
    // 创建一个边的集合，边的类型是string类型
    val relationships: RDD[Edge[String]] = sc.parallelize(Array(Edge(3L, 7L, "collab"), Edge(5L, 3L, "advisor"),
      Edge(2L, 5L, "colleague"), Edge(5L, 7L, "pi")))
    // Define a default user in case there are relationship with missing user---默认（缺失）用户
    val defaultUser = ("DT Liu", "Missing")

    // 创建图，传入顶点和边
    val graph = Graph(users, relationships, defaultUser)
    // 过滤图上所有的顶点和边。如果顶点属性第二个至是posdoc博士后那就过滤出来，计算满足条件的顶点个数
//    val verticesCount = graph.vertices.filter { case (id, (name, pos)) => pos == "postdoc" }.count
//    println(verticesCount)
//
//    //GraphX也包含了一个三元组视图,用一个三元组视图渲染字符串集合用来描述用户之间的关系
//    val graph: Graph[(String, String), String]
//    // Use the triplets view to create an RDD of facts.
//    val facts: RDD[String] = graph.triplets.map(triplet => triplet.srcAttr._1 + " is the " + triplet.attr + " of " + triplet.dstAttr._1)
//    facts.collect.foreach(println(_))
//
//    // 计算满足条件的边的个数，条件是边的sourceid>目的id
//    val edgeCount = graph.edges.filter(e => e.srcId > e.dstId).count

//    println(edgeCount)
    //关闭sparkContext
    sc.stop()
  }
}

