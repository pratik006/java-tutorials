./gradlew clean build -x test



nohup java -Dlogging.baseloc=/tmp/microservices-log -jar eureka-server/build/libs/eureka-server-0.0.1-SNAPSHOT.jar &
sleep 10
nohup java -Dlogging.baseloc=/tmp/microservices-log -jar zuul-server/build/libs/zuul-server-0.0.1-SNAPSHOT.jar &

nohup java -Dlogging.baseloc=/tmp/microservices-log -jar services/db-service/build/libs/db-service-0.0.1-SNAPSHOT.jar &
nohup java -Dlogging.baseloc=/tmp/microservices-log -jar services/db-service/build/libs/db-service-0.0.1-SNAPSHOT.jar &
nohup java -Dlogging.baseloc=/tmp/microservices-log -jar services/db-service/build/libs/db-service-0.0.1-SNAPSHOT.jar &

nohup java -Dlogging.baseloc=/tmp/microservices-log -jar services/student-service/build/libs/student-service-0.0.1-SNAPSHOT.jar &
nohup java -Dlogging.baseloc=/tmp/microservices-log -jar services/student-service/build/libs/student-service-0.0.1-SNAPSHOT.jar &
nohup java -Dlogging.baseloc=/tmp/microservices-log -jar services/student-service/build/libs/student-service-0.0.1-SNAPSHOT.jar &

