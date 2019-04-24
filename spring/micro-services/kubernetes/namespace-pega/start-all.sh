#kubectl create -f pega-namespace.yaml
#kubectl create configmap postgres-db-config --from-file=postgres.properties -n pega
#kubectl label namespace pega istio-injection=enabled
#kubectl create -f mysql-deployment.yaml
#kubectl create -f postgres.yaml
#kubectl create -f deployments.yaml
kubectl create -f student-service.yaml
kubectl create -f db-service.yaml


#kubectl create -f istios-routing.yaml