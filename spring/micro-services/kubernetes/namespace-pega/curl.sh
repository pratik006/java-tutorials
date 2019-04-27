#!/bin/bash
for i in {1..200}
do
   #curl -v --silent http://192.168.99.101:32001/search?name=Pratik 2>&1 | grep hashCode

   curl -s 'http://192.168.99.101:32001/search?name=Pratik' | \
    python3 -c "import sys, json; print(json.load(sys.stdin)['messages'])"
done