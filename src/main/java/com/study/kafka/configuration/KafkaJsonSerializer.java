package com.study.kafka.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
import java.util.logging.Logger;

public class KafkaJsonSerializer implements Serializer {


   @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Object o) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsBytes(o);
        } catch (Exception e) {
            System.out.println("erro ao serializar");
        }
        return retVal;
    }

    @Override
    public void close() {

    }
}
