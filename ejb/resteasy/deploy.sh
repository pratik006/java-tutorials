mvn clean war:war
rm -rf /home/pratik/Java/wildfly-10.1.0.CR1/standalone/deployments/RestfulWebservice*
cp target/*.war /home/pratik/Java/wildfly-10.1.0.CR1/standalone/deployments/
$JBOSS_HOME/bin/standalone.sh -Djboss.home.dir=$JBOSS_HOME \
