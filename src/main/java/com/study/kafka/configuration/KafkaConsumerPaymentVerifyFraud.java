package com.study.kafka.configuration;

import com.study.kafka.controller.model.Order;
import com.study.kafka.controller.model.Payment;
import com.study.kafka.dataprovider.OrderDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerPaymentVerifyFraud {

    private static final Logger LOG = LoggerFactory.getLogger(Order.class);

    @Autowired
    private OrderDataProvider orderData;

    @KafkaListener(topics = "${payment-topic}", groupId = "verificaFraude")
    public void receive(Payment payment) throws InterruptedException {
        LOG.info("Recebido pagamento da compra {} no topico {}, na classe {}", orderData.getOrderById(payment.getOrderId()).getDescription() , "PAYMENT_TOPIC", KafkaConsumerPaymentVerifyFraud.class.getSimpleName());
        verifyFraud(payment);
    }

    private void verifyFraud(Payment payment) throws InterruptedException {
        LOG.info("Verificando fraude no pagamento da compra {}, do cartão de crédito {}", orderData.getOrderById(payment.getOrderId()), payment.getCreditCard());
        Thread.sleep(25000);
        if (payment.getCreditCard() % 2 == 0) {
            orderData.getOrderById(payment.getOrderId()).setStatus("recusado por fraude");
            payment.setFraud(true);
            orderData.getOrderById(payment.getOrderId()).setPayment(payment);
            LOG.info("Fraude detectada no pagamento da compra {}, do cartão de crédito {}", orderData.getOrderById(payment.getOrderId()), payment.getCreditCard());
        } else {
            orderData.getOrderById(payment.getOrderId()).setStatus("pagamento aprovado.");
            payment.setFraud(false);
            orderData.getOrderById(payment.getOrderId()).setPayment(payment);
            LOG.info("Pagamento com sucesso compra {}, do cartão de crédito {}", orderData.getOrderById(payment.getOrderId()), payment.getCreditCard());
        }
    }
}
