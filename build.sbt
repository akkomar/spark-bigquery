/*
 * Copyright 2016 Spotify AB.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

name := "spark-bigquery"
organization := "com.spotify"
scalaVersion := "2.11.12"

val sparkVersion = "2.4.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % Provided,
  "org.apache.spark" %% "spark-sql" % sparkVersion % Provided,
  "com.databricks" %% "spark-avro" % "4.0.0" % Provided,
  "com.google.cloud.bigdataoss" % "bigquery-connector" % "0.10.2-hadoop2"
    exclude ("com.google.guava", "guava-jdk5"),
  "joda-time" % "joda-time" % "2.9.3",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test"
)

assemblyMergeStrategy in assembly := {
  case PathList("com", "databricks", "spark", "avro", xs @ _*) => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
