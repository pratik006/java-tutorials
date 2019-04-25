#kubectl create -f pega-namespace.yaml
#kubectl create configmap postgres-db-config --from-file=postgres.properties -n pega
kubectl label namespace pega istio-injection=enabled
#kubectl create -f mysql-deployment.yaml
#kubectl create -f postgres.yaml
#kubectl create -f deployments.yaml
kubectl create -f student-service.yaml
kubectl create -f db-service.yaml


kubectl create -f istios-routing.yaml

export INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')
export SECURE_INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="https")].nodePort}')
export INGRESS_HOST=$(minikube ip)

# curl -I -HHost:pegasample.com http://$INGRESS_HOST:$INGRESS_PORT/search/