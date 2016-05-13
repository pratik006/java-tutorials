#!/bin/bash

echo $# arguments 

mvn clean install
rm -rf $JBOSS_HOME/standalone/deployments/hello.ear*
cp hello-ear/target/hello.ear $JBOSS_HOME/standalone/deployments/hello.ear

sh $JBOSS_HOME/bin/standalone.sh --debug 8787
