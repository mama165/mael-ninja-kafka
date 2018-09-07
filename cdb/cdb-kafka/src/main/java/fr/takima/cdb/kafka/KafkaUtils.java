package fr.takima.cdb.kafka;


import kafka.admin.RackAwareMode;
import kafka.zk.AdminZkClient;
import kafka.zk.KafkaZkClient;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.common.errors.TopicExistsException;
import org.apache.kafka.common.utils.Time;
import scala.collection.Seq;
import scala.collection.convert.Wrappers;

import java.io.IOException;
import java.util.Properties;


public class KafkaUtils {

    public static void main(String... args) {
        System.out.println("Hello world");

        String myTopic = "HelloNinja566646";

        int partitions = 3;
        int replication = 1; // you should have replication factor less than or equal to number of nodes in Kafka cluster
        short replicationFactor = 1;
        Properties topicConfig = new Properties(); // you can pass topic configurations while creating topic

        AdminZkClient adminZkClient = KafkaSource.INSTANCE.getKafkaConnection();
        try{
            adminZkClient.createTopic(myTopic , partitions, replication, topicConfig, RackAwareMode.Disabled$.MODULE$);
        }
        catch (TopicExistsException e){
            System.out.println("Topic :  " + myTopic + "already exists");
        }
    }
}

