mvn clean package -DskipTests
rm -rf /home/pratik/Java/wildfly-10.1.0.CR1/standalone/deployments/restful-webservice.war
cp target/restful-webservice.war /home/pratik/Java/wildfly-10.1.0.CR1/standalone/deployments/
sh /home/pratik/Java/wildfly-10.1.0.CR1/bin/standalone.sh --debug 8787
