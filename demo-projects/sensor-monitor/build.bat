set JBOSS_HOME=C:\Users\prasengupta\Softwares\wildfly-10.1.0.CR1
del C:\Users\prasengupta\Softwares\wildfly-10.1.0.CR1\standalone\deployments\sense-monitor-0.0.1-SNAPSHOT.jar
copy target\sense-monitor-0.0.1-SNAPSHOT.jar C:\Users\prasengupta\Softwares\wildfly-10.1.0.CR1\standalone\deployments

%JBOSS_HOME%/bin/jboss-cli.sh --connect --command=:reload
