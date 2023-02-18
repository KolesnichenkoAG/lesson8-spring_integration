package ru.geekbrains.integration.subscriber;

import com.rabbitmq.client.*;

import java.util.Scanner;

public class SubscriberReceiverApp {
    private static final String EXCHANGE_NAME = "it-blog";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        String queueName = channel.queueDeclare().getQueue();
        System.out.println("My queue name: " + queueName);

        channel.queueBind(queueName, EXCHANGE_NAME, "php");

        Subscriber subscriber1 = new Subscriber(1L, "Bob", "php");

        System.out.println("Введите 1, если Вас интересует тема php");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        System.out.println(" [*] Waiting for messages");
        if (a == 1) {
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                System.out.println(Thread.currentThread().getName());
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        } else System.out.println("Вы не будите получать темы php");

    }
}
