package fr.takima.cdb.kafka;

import kafka.zk.AdminZkClient;
import kafka.zk.KafkaZkClient;
import org.apache.kafka.common.utils.Time;

import java.io.IOException;
import java.util.Properties;

public enum KafkaSource {
    INSTANCE;
    private String zookeeperHost;
    private String datasource = "kafka.properties";
    private AdminZkClient adminZkClient;
    KafkaSource() {
        Properties properties = new Properties();

        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream(datasource));
            //String zookeeperHost = "localhost:2181"; // Il faut mettre localhost en local à la place de "zookeeper" !!!
            String zookeeperHost = properties.getProperty("kakfa.zookeeper");
            Boolean isSucre = false;
            int sessionTimeoutMs = 200000;
            int connectionTimeoutMs = 15000;
            int maxInFlightRequests = 10;
            Time time = Time.SYSTEM;
            String metricGroup = "myGroup";
            String metricType = "myType";
            KafkaZkClient zkClient = KafkaZkClient.apply(zookeeperHost, isSucre, sessionTimeoutMs,
                    connectionTimeoutMs, maxInFlightRequests, time, metricGroup, metricType);

            adminZkClient = new AdminZkClient(zkClient);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AdminZkClient getKafkaConnection(){
        return this.adminZkClient;
    }
}
