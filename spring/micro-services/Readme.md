Eureka Server
http://localhost:8761/

Hystrix Url
http://localhost:8080/hystrix

Create a Keystore file sample:
keytool -genkey -alias db-service -storetype JKS -keystore db-service.jks

# Generate a self signed certificate
keytool -genkeypair -keystore self_signed_ssl.p12 -storetype PKCS12 -storepass changeit -alias db-service -keyalg RSA -keysize 2048 -validity 99999 -dname "CN=db-service, OU=My Team, O=My Company, L=My City, ST=Telengana, C=IN" -ext san=dns:mydomain.com,dns:localhost,ip:127.0.0.1


# Export the public certificate
keytool -exportcert -keystore self_signed_ssl.p12 -storepass changeit -alias b-service -rfc -file public-certificate.pem


# Generate a self signed certificate for eureka-server PKCS12 type
keytool -genkeypair -keystore eureka-server.p12 -storetype PKCS12 -storepass changeit -alias eureka-server -keyalg RSA -keysize 2048 -validity 99999 -dname "CN=eureka-server, OU=My Team, O=My Company, L=My City, ST=Telengana, C=IN" -ext san=dns:mydomain.com,dns:localhost,ip:127.0.0.1
keytool -exportcert -keystore eureka-server.p12 -storepass changeit -alias eureka-server -rfc -file eureka-server.pem


keytool -genkeypair -keystore zuul-server.p12 -storetype PKCS12 -storepass changeit -alias zuul-server -keyalg RSA -keysize 2048 -validity 99999 -dname "CN=zuul-server, OU=My Team, O=My Company, L=My City, ST=Telengana, C=IN" -ext san=dns:mydomain.com,dns:localhost,ip:127.0.0.1
keytool -exportcert -keystore zuul-server.p12 -storepass changeit -alias zuul-server -rfc -file zuul-server.pem


keytool -genkeypair -keystore student-service.p12 -storetype PKCS12 -storepass changeit -alias student-service -keyalg RSA -keysize 2048 -validity 99999 -dname "CN=student-service, OU=My Team, O=My Company, L=My City, ST=Telengana, C=IN" -ext san=dns:mydomain.com,dns:localhost,ip:127.0.0.1
keytool -exportcert -keystore student-service.p12 -storepass changeit -alias student-service -rfc -file student-service.pem

keytool -genkeypair -keystore db-service.p12 -storetype PKCS12 -storepass changeit -alias db-service -keyalg RSA -keysize 2048 -validity 99999 -dname "CN=db-service, OU=My Team, O=My Company, L=My City, ST=Telengana, C=IN" -ext san=dns:mydomain.com,dns:localhost,ip:127.0.0.1
keytool -exportcert -keystore db-service.p12 -storepass changeit -alias db-service -rfc -file db-service.pem


# Add to truststore
keytool -import -alias eureka-server -file eureka-server.pem -keystore mycerts -storepass changeit
keytool -import -alias zuul-server -file zuul-server.pem -keystore mycerts -storepass changeit
keytool -import -alias db-service -file db-service.pem -keystore mycerts -storepass changeit
keytool -import -alias student-service -file student-service.pem -keystore mycerts -storepass changeit


# Delete from truststore
keytool -delete -alias eureka-server -keystore mycerts --storepass changeit
keytool -delete -alias zuul-server -keystore mycerts --storepass changeit
keytool -delete -alias db-service -keystore mycerts --storepass changeit
keytool -delete -alias student-service -keystore mycerts --storepass changeit

# Create a new mysql standalone docker instance
docker run -p 3306:3306 -e MYSQL_DATABASE=test -e MYSQL_USER=root -e MYSQL_PASSWORD=root -e MYSQL_ROOT_PASSWORD=root --name mysql-standalone -d mysql:latest

docker run -p 3306:3306 -e MYSQL_DATABASE=test \
    -e MYSQL_ROOT_PASSWORD=root \
    --name mysql-standalone -d mysql:latest