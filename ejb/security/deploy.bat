call mvn clean install -DskipTests

del C:\Users\prasengupta\Softwares\wildfly-10.1.0.CR1\standalone\deployments\restful-*
copy .\target\restful-webservice.war "C:\Users\prasengupta\Softwares\wildfly-10.1.0.CR1\standalone\deployments\"

call C:\Users\prasengupta\Softwares\wildfly-10.1.0.CR1\bin\standalone.bat --server-config=standalone.xml -Djboss.server.base.dir=C:\Users\prasengupta\Softwares\wildfly-10.1.0.CR1\standalone -b localhost --debug 8787