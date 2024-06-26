### DevOps com Java/Spring/Postgres/Docker/Kubernetes/Prometheus/Grafana (Vscode - PostgresSQL - Springboot - Java - Kubernetes - Github - Dockerhub - Dockerdesktop - Jmeter - Jenkins)
### Integração Contínua e DevOps<br>

### 1. Criação e publicação da imagem Docker
Utilize o Docker para criar uma imagem personalizada de alguma aplicação previamente construída e publique a imagem no Dockerhub.

em: /contador-vendas<br>
docker build -t alfecjo/bd-postgres:1.0 -f Dockerfile.postgres .<br>
docker push alfecjo/bd-postgres:1.0<br>

docker build -t alfecjo/ic-devops:1.0 .<br>
docker push alfecjo/ic-devops:1.0<br>
![DockerHub](imgdockerhub.jpg)

### 2. Subindo a imagem em um cluster Kubernetes
## a. Deployment
Suba a aplicação com 4 réplicas utilizando um Deployment.

em: /contador-vendas/manifests<br>
kubectl apply -f postgres-statefulset.yaml<br>
kubectl apply -f postgres-service.yaml<br>
kubectl apply -f postgres-pod.yaml<br>
kubectl apply -f contador-vendas-deployment.yaml<br>
kubectl apply -f contador-vendas-service.yaml<br>
kubectl apply -f prometheus-pvc.yaml<br>
kubectl apply -f prometheus-deployment.yaml<br>
kubectl apply -f prometheus-service.yaml<br>
kubectl apply -f grafana-pvc.yaml<br>
kubectl apply -f grafana-deployment.yaml<br>
kubectl apply -f grafana-service.yaml<br>

![Requisições](pesquisas.jpg)

## b. Expondo a aplicação
Exponha a aplicação de forma que fique acessível fora do cluster usando NodePort.

em: /contador-vendas/manifests<br>
contador-vendas-service.yaml<br>
![NodePort](nodeport.jpg)

## c. Configuração do banco de dados
Para aplicações que fazem uso de banco de dados, crie um POD com o mesmo e deixe acessível através do ClusterIP.

em: /contador-vendas/manifests<br>
postgres-statefulSet.yaml<br>
postgres-service.yaml<br>
#### Por estar definindo diretamente um Pod (kind: Pod) para o banco de dados PostgreSQL. Este Pod será acessível apenas internamente dentro do cluster Kubernetes e não precisará de um ClusterIP para expor o serviço.<br>
postgres-pod.yaml<br>
![ClusterIP](pod-clusterip.jpg)

## d. Probe para a aplicação
Crie um probe para a aplicação (Readiness ou Liveness).

em: /contador-vendas/manifests<br>
contador-vendas-deployment.yaml<br>

    spec:
      containers:
      - name: contador-vendas
        image: alfecjo/ic-devops:1.0
        ports:
        - containerPort: 8080
        readinessProbe:
          httpGet:
            path: /actuator/health
            port: 8080
          initialDelaySeconds: 50
          periodSeconds: 5
          failureThreshold: 3
        livenessProbe:
          httpGet:
            path: /actuator/health
            port: 8080
          initialDelaySeconds: 50
          periodSeconds: 10
          failureThreshold: 1

### 3. Estrutura de monitoramento com Prometheus e Grafana
## a. Configuração do Prometheus
Utilize um PVC para escrever os dados do Prometheus de maneira persistente.

em: /contador-vendas/manifests<br>
prometheus-pvc.yaml<br>
prometheus-deployment.yaml<br>
prometheus-service.yaml<br>
#### Ao configurar um PVC para o Prometheus, fica garantido que os dados coletados e armazenados pelo Prometheus sejam mantidos em um local persistente, facilitando a recuperação de dados em caso de falhas ou reinicializações.

## b. Configuração do Grafana
Apenas o Grafana deverá ficar acessível para fora do cluster.

em: /contador-vendas/manifests<br>
grafana-pvc.yaml<br>
grafana-deployment.yaml<br>
grafana-service.yaml<br>
#### Ao configurar um PVC para o Grafana, fica garantido que as configurações, dashboards personalizados e outras informações do Grafana sejam mantidos de forma persistente, mesmo que o contêiner seja reiniciado ou atualizado.

## c. Criação de dashboards do Grafana
Crie dashboards do Grafana que exponham dados sensíveis da sua aplicação (memória, CPU, etc...).

![Dashboard](dashboard-graphics.gif)

### 4. Pipeline de entrega do projeto com Jenkins
Utilize o Jenkins ou qualquer outra ferramenta para criar um pipeline de entrega do projeto.

em contador-vendas\jenkins-saida-console.txt está a saída Console Output<br>
em contador-vendas\pipeline-steps.txt está a saída Pipeline Steps<br>
em contador-vendas\jenkinsfile está a montagem<br>

![Jenkins](jenkins.jpg)

### 5. Execução de stress test e monitoramento
Execute um stress test do seu projeto e tire prints do Dashboard sofrendo alterações.

![Dashboard](stress1.jpg)
![Dashboard](stress2.jpg)
![Dashboard](stress3.jpg)
