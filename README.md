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
Multi node kafka
---------
# Stop all running producers & consumers
# multinode kafka deployment using docker-compose, exit to host machine if you are inside the container
exit
cd Documents\singlenodekafka
docker-compose stop
cd ..
cd multinodekafka
dir
docker-compose up -d
docker-compose ps
# one zookeeper & 3 kafka containers should be listed
# wait for a minute
docker-compose up -d
# if the kafka brokers have failed to start, this above command will start them again
docker-compose ps
# all the 3 brokers should be in up state, not is exited state


------------
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
------------------
docker exec -it multinodekafka-kafka1-1 bash

kafka-topics.sh --create --topic test1 --bootstrap-server kafka1:9092 --replication-factor 2 --partitions 6

# List only single topic named "test1" (prints only topic name)
kafka-topics.sh --list --bootstrap-server kafka1:9092 --topic test1

# List all topics (prints only topic names)
kafka-topics.sh --list --bootstrap-server kafka1:9092
 
# Describe only single topic named "test1" (prints details about the topic)
kafka-topics.sh --describe --bootstrap-server kafka1:9092 --topic test1
 
# Describe all topics (prints details about the topics)
kafka-topics.sh --describe --bootstrap-server kafka1:9092

# List info for topics which have under replicated count
kafka-topics.sh --describe --bootstrap-server kafka1:9092 --under-replicated-partitions

# on kafka broker 3 -> kafka3
# bring down one broker
exit
docker stop multinodekafka-kafka3-1

docker exec -it multinodekafka-kafka1-1 bash
# on the kafka1 terminal
kafka-topics.sh --describe --bootstrap-server kafka1:9092 --under-replicated-partitions

kafka-topics.sh --describe --bootstrap-server kafka1:9092 --topic testtopic1   
kafka-topics.sh --describe --bootstrap-server kafka1:9092 --topic testtopic1 --under-replicated-partitions
# list only topics where some partitions are not available

# List info for topics whose leader for a partition is not available
kafka-topics.sh --describe --bootstrap-server kafka1:9092 --unavailable-partitions
# leader=none

# Create topic with specific number of partitions and/or replicas
# If number of replication factor higher than the brokers, you will get error. " Replication factor: 3 larger than available brokers: 2."
kafka-topics.sh --create --bootstrap-server kafka1:9092 --topic test3 --replication-factor 3 --partitions 3

exit
# start the broker 3 -> kafka3
docker start multinodekafka-kafka3-1
docker exec -it multinodekafka-kafka1-1 bash
kafka-topics.sh --describe --bootstrap-server kafka1:9092 --unavailable-partitions
kafka-topics.sh --describe --bootstrap-server kafka1:9092 --under-replicated-partitions
# both above commands should produce blank output as all partitions in the cluster should be normal and healthy now





