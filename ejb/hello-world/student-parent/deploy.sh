#!/bin/bash

echo $# arguments 

mvn clean install
rm -rf $JBOSS_HOME/standalone/deployments/student.ear*
cp student-ear/target/student.ear $JBOSS_HOME/standalone/deployments/student.ear

sh $JBOSS_HOME/bin/standalone.sh --debug 8787
