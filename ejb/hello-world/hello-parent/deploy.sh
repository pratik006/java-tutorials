#!/bin/sh

echo $# arguments 

mvn clean install
$JBOSS_HOME/bin/jboss-cli.sh --connect command=:shutdown
rm -rf $JBOSS_HOME/standalone/deployments/hello.ear
cp hello-ear/target/hello.ear $JBOSS_HOME/standalone/deployments/hello.ear
xfce4-terminal -e $JBOSS_HOME/bin/standalone.sh

