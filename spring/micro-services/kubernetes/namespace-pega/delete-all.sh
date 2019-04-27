kubectl apply -f external-gatewal.yaml
kubectl delete --ignore-not-found=true -n istio-system secret istio-ingressgateway-certs istio-ingressgateway-ca-certs
kubectl delete --ignore-not-found=true -n istio-system generic istio-ingressgateway-certs istio-ingressgateway-ca-certs

kubectl delete --ignore-not-found=true -f mutual-tls.yaml
kubectl delete -f istios-routing.yaml

kubectl delete -f student-service.yaml
kubectl delete -f db-service.yaml
#kubectl delete -f mysql-deployment.yaml
kubectl delete -f postgres.yaml
kubectl delete configmap postgres-db-config -n pega
kubectl delete -f pega-namespace.yaml





