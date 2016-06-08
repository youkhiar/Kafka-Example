Dummy example for testing a simple producer/consumer on Kafka version 0.9.0

Steps to Run:

1) build the jar prompt> mvn clean package once Maven has finished, it generates a kafka-main.jar which should be invoked like like this:

2) launch the consumer Suposing we're on 'Target' directory, run on a console the consumer, then wait prompt> ./kafka-main consumer

3) run the producer In other different console we run the producer prompt> ./kafka-main producer

If everything went right, the consoles of both producer and consumer should be displaying the messages generating by the producer and receiving by the consumer
