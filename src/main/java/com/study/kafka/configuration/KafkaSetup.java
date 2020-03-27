package com.study.kafka.configuration;
import com.study.kafka.controller.model.Student;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;


public class KafkaSetup {

    private final static String TOPIC = "test001";
    private final static String BOOTSTRAP_SERVERS =
            "localhost:9092";

    private static Producer<Long, Student> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
        return new KafkaProducer<>(
                props,
                new StringSerializer(),
                new KafkaJsonSerializer());
    }

    public static void runProducer(Student student) throws Exception {

        final Producer<Long, Student> producer = createProducer();
        try {
                final ProducerRecord<Long, Student> record = new ProducerRecord<>(TOPIC, student);

                RecordMetadata metadata = producer.send(record).get();
        } finally {
            producer.flush();
            producer.close();
        }
    }
}
