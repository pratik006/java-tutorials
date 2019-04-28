kubectl apply -f pega-namespace.yaml
#kubectl create configmap postgres-db-config --from-file=postgres.properties -n pega
kubectl label namespace pega istio-injection=enabled
#kubectl create -f mysql-deployment.yaml
kubectl create -f postgres.yaml
kubectl create -f student-service.yaml
kubectl create -f db-service.yaml

kubectl create -n istio-system secret tls istio-ingressgateway-certs --key student.example.com/3_application/private/student.example.com.key.pem --cert student.example.com/3_application/certs/student.example.com.cert.pem

# verify that secret is created
# kubectl exec -it -n istio-system $(kubectl -n istio-system get pods -l istio=ingressgateway -o jsonpath='{.items[0].metadata.name}') -- ls -al /etc/istio/ingressgateway-certs


kubectl create -f istios-routing.yaml

export INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')
export SECURE_INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="https")].nodePort}')
export INGRESS_HOST=$(minikube ip)

# curl -v -HHost:student.example.com --resolve student.example.com:$SECURE_INGRESS_PORT:$INGRESS_HOST --cacert student.example.com/2_intermediate/certs/ca-chain.cert.pem https://student.example.com:$SECURE_INGRESS_PORT/search?name=Pratik

# troubleshoot: kubectl delete pod -n istio-system -l istio=ingressgateway
# for mutual tls
kubectl create -n istio-system secret generic istio-ingressgateway-ca-certs --from-file=student.example.com/2_intermediate/certs/ca-chain.cert.pem
kubectl apply -f mutual-tls.yaml
# run app
# curl -HHost:student.example.com --resolve student.example.com:$SECURE_INGRESS_PORT:$INGRESS_HOST --cacert student.example.com/2_intermediate/certs/ca-chain.cert.pem --cert student.example.com/4_client/certs/student.example.com.cert.pem --key student.example.com/4_client/private/student.example.com.key.pem https://student.example.com:$SECURE_INGRESS_PORT/search?name=Pratik


kubectl apply -f external-gatewal.yaml
# Test it by making a call
# kubectl exec -n pega -it $(kubectl -n pega get pods -l app=student-service -o jsonpath='{.items[0].metadata.name}') -c student-service-pod -- curl -sL -D - http://worldtimeapi.org/api/ip




# curl -I -HHost:pegasample.com http://$INGRESS_HOST:$INGRESS_PORT/search?name=Pratik

# to port forward the gateway to localhost
# kubectl get pod -n istio-system -l app=istio-ingressgateway -o jsonpath={.items..metadata.name}
# sudo kubectl -n istio-system port-forward $(kubectl get pod -n istio-system -l app=istio-ingressgateway -o jsonpath={.items..metadata.name}) 80

# Envoy Admin console
# kubectl -n istio-system port-forward istio-ingressgateway-d67598f4-s54lv 15000

# SSH into student-service pod and call db-service
# kubectl exec $(kubectl get pod -l app=student-service -n pega -o jsonpath={.items..metadata.name}) -c student-service-pod -n pega -- curl http://db-service.pega:8080/rest/db/student/Pratik

# port forward Grafana dashboard
# kubectl -n istio-system port-forward $(kubectl -n istio-system get pod -l app=grafana -o jsonpath='{.items[0].metadata.name}') 3000:3000 &
# http://localhost:3000/dashboard/db/istio-mesh-dashboard
# Prometheus port forwarding
# kubectl -n istio-system port-forward $(kubectl -n istio-system get pod -l app=prometheus -o jsonpath='{.items[0].metadata.name}') 9090:9090 &