Eureka Server
http://localhost:8761/

Hystrix Url
http://localhost:8080/hystrix

Create a Keystore file sample:
keytool -genkey -alias db-service -storetype JKS -keystore db-service.jks

#scaling
kubectl scale --replicas=3 deployment/db-service