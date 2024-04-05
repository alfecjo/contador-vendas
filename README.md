#Trabalho da disciplina: Integração Contínua e DevOps<br>
##Este documento descreve os passos necessários para a execução do trabalho da disciplina de Integração Contínua e DevOps.

1. Criação e publicação da imagem Docker
Utilize o Docker para criar uma imagem personalizada de alguma aplicação previamente construída e publique a imagem no Dockerhub.

docker build -t alfecjo/bd-postgres:1.0 -f Dockerfile.postgres .<br>
docker push alfecjo/bd-postgres:1.0

docker build -t alfecjo/ic-devops:1.7 .<br>
docker push alfecjo/ic-devops:1.7

2. Subindo a imagem em um cluster Kubernetes
a. Deployment
Suba a aplicação com 4 réplicas utilizando um Deployment.

contador-vendas-deployment.yaml

b. Expondo a aplicação
Exponha a aplicação de forma que fique acessível fora do cluster usando NodePort.

contador-vendas-service.yaml

c. Configuração do banco de dados
Para aplicações que fazem uso de banco de dados, crie um POD com o mesmo e deixe acessível através do ClusterIP.

postgres-statefulSet.yaml
postgres-service.yaml
(quando você define um serviço sem especificar explicitamente o tipo, o tipo padrão é ClusterIP.)
postgres-pod.yaml

d. Probe para a aplicação
Crie um probe para a aplicação (Readiness ou Liveness).

em contador-vendas-deployment.yaml
spec:
      containers:
      - name: contador-vendas
        image: alfecjo/ic-devops:1.7
        ports:
        - containerPort: 8080
        
        readinessProbe:
          httpGet:
            path: /health
            port: 8080
          initialDelaySeconds: 5
          periodSeconds: 10
          failureThreshold: 3

        livenessProbe:
          httpGet:
            path: /health
            port: 8080
          initialDelaySeconds: 30
          periodSeconds: 10
          failureThreshold: 1

3. Estrutura de monitoramento com Prometheus e Grafana
a. Configuração do Prometheus
Utilize um PVC para escrever os dados do Prometheus de maneira persistente.

prometheus-pvc.yaml
prometheus-deployment.yaml
prometheus-service.yaml

b. Configuração do Grafana
Apenas o Grafana deverá ficar acessível para fora do cluster.

grafana-pvc.yaml
grafana-deployment.yaml
grafana-service.yaml

c. Criação de dashboards do Grafana
Crie dashboards do Grafana que exponham dados sensíveis da sua aplicação (memória, CPU, etc...).

?????????????????????????????????????????????????????????

4. Pipeline de entrega do projeto com Jenkins
Utilize o Jenkins ou qualquer outra ferramenta para criar um pipeline de entrega do projeto.

??????????????????????????????????????????????????????????

5. Execução de stress test e monitoramento
Execute um stress test do seu projeto e tire prints do Dashboard sofrendo alterações.

??????????????????????????????????????????????????????????