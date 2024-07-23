Yrs : 10 +
Domain : Healthcare
Message queue : N/A
Linux : Ubuntu 
Java :10 years
Spring : 6 years
SQL : 10 years
docker : 2 years
kafka : N/A


https://labs.dmrtechcloud.com
user76
Admin@123

docker ps
# both kafka & zookeeper should be lister
docker exec -it singlenodekafka-kafka-1  bash
# create a topic in kafka called demotopic1
kafka-topics.sh --create --bootstrap-server localhost:9092 --partitions 2 --replication-factor 1 --topic demotopic1
# output: Created topic demotopic1.
kafka-topics.sh --list --bootstrap-server localhost:9092 

# Start a producer
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic demotopic1
>message1
>Hello IBM
>New message again

# duplicate the terminal -> Duplicate putty : Username: alchemy, pwd: welcome
docker exec -it  singlenodekafka-kafka-1  bash

# Start a consumer
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic demotopic1 --from-beginning

# Produce more messages on the producer tab, consumer should list the messages recently produced

# duplicate the terminal -> Duplicate putty : Username: alchemy, pwd: welcome
docker exec -it  singlenodekafka-kafka-1  bash
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic demotopic1
>New message1
>New Hello IBM
>New message again again

# duplicate the terminal -> Duplicate putty : Username: alchemy, pwd: welcome
docker exec -it  singlenodekafka-kafka-1  bash

# Start a consumer
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic demotopic1 --from-beginning

# Produce more messages on the producer tab, both consumer should list the messages recently produced
docker-compose stop

----
#Stop all the prodcuer and consumer from previous exercise
# connect to the container from another terminal
# docker exec -it singlenodekafka_kafka_1 bash
# create a topic named sampletopic2
kafka-topics.sh --create --partitions 2 --replication-factor 1 --bootstrap-server localhost:9092 --topic sampletopic2

# start a new terminal in a new window & start a new consumer belonging to consumer group group1
kafka-console-consumer.sh --topic sampletopic2 --from-beginning --bootstrap-server localhost:9092 --property "print.key=true" --property "print.offset=true"  --property "print.partition=true" --group group1 

# start a new terminal in a new window & start a new consumer belonging to same consumer group group1
# connect to the container
# docker exec -it singlenodekafka_kafka_1 bash
kafka-console-consumer.sh --topic sampletopic2 --from-beginning --bootstrap-server localhost:9092 --property "print.key=true" --property "print.offset=true"  --property "print.partition=true" --group group1 

# publish a few messages from producer
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic sampletopic2 --property "parse.key=true" --property "key.separator=;"
>order1;{"prd":"redmi"}
>order2;{"prd":"oppo"}
>order1;{"prd":"iPhone"}
>order3;{"prd":"redmi"}
>order4;{"prd":"oppo"}
>order5;{"prd":"iPhone"}
>order5;{"prd":"oppo"}

# start a new terminal in a new window & start a new consumer belonging to same consumer group group1
# connect to the container
docker exec -it singlenodekafka_kafka_1 bash
kafka-console-consumer.sh --topic sampletopic2 --from-beginning --bootstrap-server localhost:9092 --property "print.key=true" --property "print.offset=true"  --property "print.partition=true" --group group1 
# publish more messages, observe that one of the consumers in the group will be idle & will not receive any messages

# stop one of the consumers in the group by Ctrl+C
# publish new messages
# oberve that existing 2 consumers will continue to consume as group

# start the consumer that is stopped in the previous step
# publish new messages
>order3;{"prd":"redmi"}
>order5;{"prd":"iPhone"}
>order5;{"prd":"oppo"}
# oberve that  one of the consumers in the group will be idle again & will not receive any further messages
# publish new messages
>order3;{"prd":"redmi"}
>order5;{"prd":"iPhone"}




