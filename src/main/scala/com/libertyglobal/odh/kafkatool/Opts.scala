/*
 *    Copyright 2018 Ilya Epifanov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and limitations under the License.
 */

package com.libertyglobal.odh.kafkatool

import org.rogach.scallop.{ScallopConf, Subcommand}

//noinspection TypeAnnotation
class Opts(arguments: Seq[String]) extends ScallopConf(arguments) {
  val verbose = opt[Boolean]("verbose", descr = "Make output more verbose")

  val reassign = new Subcommand("reassign") {
    val out = opt[String]("output", descr = "output filename (stdout if not specified)")

    val repair = new Subcommand("repair")
    addSubcommand(repair)

    val cleanup = new Subcommand("cleanup")
    addSubcommand(cleanup)
  }
  addSubcommand(reassign)

  val update = new Subcommand("update") {
    val alterIfNeeded = opt[Boolean]("alter-if-needed", descr = "Alter topics if needed (will only create topics by default)")
    val dryRun = opt[Boolean]("dry-run", 'n', descr = "Don't change anything")
  }
  addSubcommand(update)

  val listSuperfluousTopics = new Subcommand("list-superfluous-topics")
  addSubcommand(listSuperfluousTopics)


  val acl = new Subcommand("acl") {
    val acl_add = new Subcommand("add") {
      val allow_user = opt[String]("allow-users", descr = "List of user")
      val allow_hosts= opt[String]("allow-hosts", descr = "List of host")
      val operations = opt[String]("operations", descr = "Operations to be enabled")
      val topic = opt[String]("topic", descr = "topic")
    }
    addSubcommand(acl_add)

    val acl_remove = new Subcommand("remove") {
      val allow_user = opt[String]("allow-users", descr = "List of user")
      val allow_hosts= opt[String]("allow-hosts", descr = "List of host")
      val operations = opt[String]("operations", descr = "Operations to be enabled")
      val topic = opt[String]("topic", descr = "topic")
    }
    addSubcommand(acl_remove)

    val acl_list = new Subcommand("list")
    addSubcommand(acl_list)

    val acl_remove_all = new Subcommand("remove-all")
    addSubcommand(acl_remove_all)

  }
  addSubcommand(acl)

  verify()
}
