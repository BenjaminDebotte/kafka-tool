/*
 *    Copyright 2017 Ilya Epifanov
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

package com.libertyglobal.odh.kafkatool.partitionreassignment

import com.libertyglobal.odh.kafkatool.{BrokerId, TargetPartitionReplicationInfo}

class CleanupOp extends PartitionReassignmentOp {
  override def addBroker(currentReplication: TargetPartitionReplicationInfo,
                         partitionsPerBroker: PartitionsPerBroker,
                         allPartitionsPerBroker: PartitionsPerBroker): Option[BrokerId] = {
    None
  }

  override def removeBroker(currentReplication: TargetPartitionReplicationInfo,
                            partitionsPerBroker: PartitionsPerBroker,
                            allPartitionsPerBroker: PartitionsPerBroker): Option[BrokerId] = {
    partitionsPerBroker.maxReplicasBrokersFromWhitelist(currentReplication.targetReplicas)
      .toSeq
      .map(id => id -> allPartitionsPerBroker(id))
      .map(_.swap)
      .sorted
      .reverse
      .headOption
      .map(_._2)
  }
}
