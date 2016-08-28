#add the following section in wildfly's standalone.xml under urn:jboss:domain:security:1.2 -> <security-domains>

<security-domain name="prapps" cache-type="default">
    <authentication>
        <login-module code="org.jboss.security.auth.spi.DatabaseServerLoginModule" flag="required">
            <module-option name="dsJndiName" value="java:jboss/datasources/ExampleDS"/>
            <module-option name="principalsQuery" value="select passwd from Users username where username=?"/>
            <module-option name="rolesQuery" value="select userRoles, RoleGroup from UserRoles where username=?"/>
        </login-module>
    </authentication>
</security-domain>



#update the default H2 connection details which ships with wildfly to make it like the following
#have added an init script file which will create the users and roles tables at server startup

<datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true">
    <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;INIT=RUNSCRIPT FROM '${jboss.server.config.dir}/user-init.sql'</connection-url>
    <driver>h2</driver>
    <security>
        <user-name>sa</user-name>
        <password>sa</password>
    </security>
</datasource>



#create a file user-init.sql within the $JBOSS/standalone/configuration directory and paste the following sql script

CREATE TABLE Users(username VARCHAR(64) PRIMARY KEY, passwd VARCHAR(64));
CREATE TABLE UserRoles(username VARCHAR(64), userRoles VARCHAR(32), RoleGroup VARCHAR(32));

insert into Users values('testuser1', 'password1');
insert into Users values('testuser2', 'password2');
insert into UserRoles values('testuser1', 'admin', 'Roles');
insert into UserRoles values('testuser1', 'admin', 'Roles');
insert into UserRoles values('testuser2', 'user', 'Roles');