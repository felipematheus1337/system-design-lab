# System Design Lab

![Status](https://img.shields.io/badge/status-em%20desenvolvimento-blue)
![System Design](https://img.shields.io/badge/system%20design-lab-orange)
![Docker](https://img.shields.io/badge/docker-ready-2496ED?logo=docker&logoColor=white)
![Nginx](https://img.shields.io/badge/nginx-load%20balancer-009639?logo=nginx&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/postgresql-database-4169E1?logo=postgresql&logoColor=white)
![Redis](https://img.shields.io/badge/redis-cache-DC382D?logo=redis&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/rabbitmq-message%20queue-FF6600?logo=rabbitmq&logoColor=white)
![Prometheus](https://img.shields.io/badge/prometheus-metrics-E6522C?logo=prometheus&logoColor=white)
![Grafana](https://img.shields.io/badge/grafana-observability-F46800?logo=grafana&logoColor=white)
![AWS](https://img.shields.io/badge/aws-cloud%20migration-232F3E?logo=amazonaws&logoColor=white)

Repositório de estudos práticos sobre **System Design**, escalabilidade, disponibilidade, arquitetura distribuída e infraestrutura de aplicações modernas.

A proposta deste laboratório é transformar conceitos clássicos de arquitetura em **POCs práticas**, evoluindo de uma aplicação simples até cenários mais próximos de sistemas escaláveis, com balanceamento de carga, separação de banco, replicação, cache, CDN, aplicações stateless, mensageria, observabilidade e sharding.

Este repositório acompanha uma jornada progressiva de aprendizado: primeiro entendendo os conceitos em ambiente local, geralmente com Docker e ferramentas open source; depois, quando fizer sentido, migrando ou replicando a mesma ideia em cloud.

---

## Objetivo

O objetivo do `system-design-lab` é estudar System Design de forma prática, incremental e documentada.

Em vez de apenas ler conceitos como **load balancer**, **cache**, **database replication** ou **message queue**, cada pasta deste repositório representa uma implementação pequena e isolada desses blocos arquiteturais.

A ideia é responder, na prática, perguntas como:

- O que muda quando saímos de um servidor único para múltiplas instâncias?
- Como um load balancer distribui requisições?
- Por que separar a aplicação do banco de dados?
- Como funciona uma réplica de leitura?
- Quando faz sentido usar cache?
- Como tornar uma aplicação stateless?
- Como desacoplar processos usando filas?
- Como observar a saúde de um sistema?
- Como dividir dados entre múltiplos bancos?

---

## Estrutura do repositório

```text
system-design-lab
├── 01-single-server
├── 02-load-balancer-nginx
├── 03-database-separation
├── 04-database-replication
├── 05-redis-cache
├── 06-cdn-static-assets
├── 07-stateless-web-tier
├── 08-message-queue
├── 09-observability
├── 10-sharding
└── README.md
```

Cada diretório representa uma POC independente, com seu próprio README, arquitetura, comandos de execução e aprendizados.

---

## Jornada de evolução arquitetural

A evolução deste laboratório segue uma linha progressiva:

```text
Servidor único
    ↓
Load Balancer
    ↓
Aplicação e banco separados
    ↓
Replicação de banco
    ↓
Cache
    ↓
CDN / arquivos estáticos
    ↓
Aplicação stateless
    ↓
Mensageria
    ↓
Observabilidade
    ↓
Sharding
    ↓
Cloud
```

A ideia não é criar um sistema perfeito logo no início, mas sim evoluir a arquitetura passo a passo, entendendo o problema que cada novo componente resolve.

---

## Projetos planejados

### 01 — Single Server

Primeira versão da aplicação, com tudo rodando de forma simples.

```text
Cliente
   ↓
Aplicação
   ↓
Banco / memória / armazenamento local
```

Conceitos praticados:

- aplicação monolítica simples;
- servidor único;
- fluxo básico HTTP;
- ponto único de falha;
- limitações de escalabilidade vertical;
- base inicial para comparação com as próximas arquiteturas.

Possíveis tecnologias:

![Spring Boot](https://img.shields.io/badge/spring%20boot-api-6DB33F?logo=springboot&logoColor=white)
![Docker](https://img.shields.io/badge/docker-container-2496ED?logo=docker&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/postgresql-database-4169E1?logo=postgresql&logoColor=white)

---

### 02 — Load Balancer com Nginx

Simulação local de múltiplas instâncias de aplicação atrás de um load balancer.

```text
Cliente
   ↓
Nginx Load Balancer
   ↓
App 1
App 2
App 3
```

Conceitos praticados:

- balanceamento de carga;
- round-robin;
- múltiplas instâncias da mesma aplicação;
- health check;
- failover básico;
- diferença entre acessar a aplicação diretamente e acessar via load balancer;
- comparação futura com Application Load Balancer da AWS.

Possíveis tecnologias:

![Nginx](https://img.shields.io/badge/nginx-load%20balancer-009639?logo=nginx&logoColor=white)
![Docker Compose](https://img.shields.io/badge/docker%20compose-orchestration-2496ED?logo=docker&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring%20boot-api-6DB33F?logo=springboot&logoColor=white)

Equivalente em cloud:

![AWS ALB](https://img.shields.io/badge/aws-application%20load%20balancer-FF9900?logo=amazonaws&logoColor=white)
![EC2](https://img.shields.io/badge/aws-ec2-FF9900?logo=amazonec2&logoColor=white)
![Auto Scaling](https://img.shields.io/badge/aws-auto%20scaling-FF9900?logo=amazonaws&logoColor=white)

---

### 03 — Database Separation

Separação entre camada de aplicação e camada de dados.

```text
Cliente
   ↓
Aplicação
   ↓
Database
```

Conceitos praticados:

- separação entre web tier e data tier;
- banco de dados como serviço independente;
- conexão via variáveis de ambiente;
- rede interna entre containers;
- isolamento de responsabilidades;
- preparação para replicação e escalabilidade da camada de dados.

Possíveis tecnologias:

![Spring Boot](https://img.shields.io/badge/spring%20boot-api-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/postgresql-database-4169E1?logo=postgresql&logoColor=white)
![Docker Compose](https://img.shields.io/badge/docker%20compose-local%20infra-2496ED?logo=docker&logoColor=white)

Equivalente em cloud:

![AWS RDS](https://img.shields.io/badge/aws-rds-527FFF?logo=amazonrds&logoColor=white)
![EC2](https://img.shields.io/badge/aws-ec2-FF9900?logo=amazonec2&logoColor=white)

---

### 04 — Database Replication

Simulação de replicação de banco de dados com instância primária e réplica de leitura.

```text
Aplicação
   ↓ escrita
Primary DB
   ↓ replicação
Read Replica
   ↑ leitura
Aplicação
```

Conceitos praticados:

- primary database;
- read replica;
- separação entre leitura e escrita;
- replicação assíncrona;
- atraso de replicação;
- alta disponibilidade;
- estratégia de failover;
- trade-offs entre consistência e performance.

Possíveis tecnologias:

![PostgreSQL](https://img.shields.io/badge/postgresql-primary%2Freplica-4169E1?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-local%20lab-2496ED?logo=docker&logoColor=white)

Equivalente em cloud:

![AWS RDS](https://img.shields.io/badge/aws-rds%20read%20replica-527FFF?logo=amazonrds&logoColor=white)
![AWS Multi-AZ](https://img.shields.io/badge/aws-multi--az-FF9900?logo=amazonaws&logoColor=white)

---

### 05 — Redis Cache

Adição de uma camada de cache para reduzir chamadas ao banco de dados.

```text
Cliente
   ↓
Aplicação
   ↓
Redis Cache
   ↓
Database
```

Fluxo esperado:

```text
1. Aplicação recebe uma requisição de leitura
2. Verifica se o dado existe no Redis
3. Se existir, retorna do cache
4. Se não existir, busca no banco
5. Salva no Redis com TTL
6. Retorna a resposta
```

Conceitos praticados:

- cache hit;
- cache miss;
- TTL;
- invalidação de cache;
- dados obsoletos;
- redução de carga no banco;
- melhora de latência;
- políticas de expiração.

Possíveis tecnologias:

![Redis](https://img.shields.io/badge/redis-cache-DC382D?logo=redis&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring%20boot-api-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/postgresql-database-4169E1?logo=postgresql&logoColor=white)

Equivalente em cloud:

![ElastiCache](https://img.shields.io/badge/aws-elasticache-DC382D?logo=amazonaws&logoColor=white)

---

### 06 — CDN Static Assets

Separação de conteúdo estático da aplicação.

```text
Cliente
   ↓
CDN
   ↓
Static Assets

Cliente
   ↓
Aplicação
   ↓
API / Dados dinâmicos
```

Conceitos praticados:

- arquivos estáticos;
- CDN;
- origin;
- edge location;
- cache de assets;
- TTL;
- versionamento de arquivos;
- invalidação de cache;
- separação entre conteúdo estático e conteúdo dinâmico.

Possíveis tecnologias locais ou simuladas:

![Nginx](https://img.shields.io/badge/nginx-static%20files-009639?logo=nginx&logoColor=white)
![Docker](https://img.shields.io/badge/docker-local%20cdn%20simulation-2496ED?logo=docker&logoColor=white)

Equivalente em cloud:

![S3](https://img.shields.io/badge/aws-s3-569A31?logo=amazons3&logoColor=white)
![CloudFront](https://img.shields.io/badge/aws-cloudfront-FF9900?logo=amazonaws&logoColor=white)

---

### 07 — Stateless Web Tier

Remoção de estado da camada de aplicação para facilitar escala horizontal.

```text
Cliente
   ↓
Load Balancer
   ↓
App 1
App 2
App 3
   ↓
Shared Session Store / Token / Redis / Database
```

Conceitos praticados:

- aplicação stateful;
- aplicação stateless;
- sessão em memória vs sessão compartilhada;
- JWT;
- session store;
- sticky sessions;
- escalabilidade horizontal;
- tolerância a falhas;
- auto scaling.

Possíveis tecnologias:

![Nginx](https://img.shields.io/badge/nginx-load%20balancer-009639?logo=nginx&logoColor=white)
![Redis](https://img.shields.io/badge/redis-session%20store-DC382D?logo=redis&logoColor=white)
![Spring Security](https://img.shields.io/badge/spring%20security-auth-6DB33F?logo=springsecurity&logoColor=white)

Equivalente em cloud:

![AWS ALB](https://img.shields.io/badge/aws-alb-FF9900?logo=amazonaws&logoColor=white)
![ElastiCache](https://img.shields.io/badge/aws-elasticache-DC382D?logo=amazonaws&logoColor=white)
![Auto Scaling](https://img.shields.io/badge/aws-auto%20scaling-FF9900?logo=amazonaws&logoColor=white)

---

### 08 — Message Queue

Desacoplamento de processos usando fila de mensagens.

```text
API / Producer
   ↓
Message Queue
   ↓
Worker / Consumer
```

Exemplo de fluxo:

```text
1. Cliente cria uma solicitação
2. API salva o registro inicial
3. API publica uma mensagem na fila
4. Worker consome a mensagem
5. Worker processa a tarefa em segundo plano
6. Status é atualizado posteriormente
```

Conceitos praticados:

- producer;
- consumer;
- processamento assíncrono;
- desacoplamento;
- retry;
- dead letter queue;
- idempotência;
- escalabilidade de workers;
- tolerância a falhas.

Possíveis tecnologias:

![RabbitMQ](https://img.shields.io/badge/rabbitmq-message%20broker-FF6600?logo=rabbitmq&logoColor=white)
![Kafka](https://img.shields.io/badge/apache%20kafka-event%20streaming-231F20?logo=apachekafka&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring%20boot-worker-6DB33F?logo=springboot&logoColor=white)

Equivalente em cloud:

![SQS](https://img.shields.io/badge/aws-sqs-FF4F8B?logo=amazonaws&logoColor=white)
![SNS](https://img.shields.io/badge/aws-sns-FF4F8B?logo=amazonaws&logoColor=white)
![EventBridge](https://img.shields.io/badge/aws-eventbridge-FF4F8B?logo=amazonaws&logoColor=white)
![MSK](https://img.shields.io/badge/aws-msk-231F20?logo=apachekafka&logoColor=white)

---

### 09 — Observability

Adição de logs, métricas, dashboards e monitoramento.

```text
Aplicação
   ↓
Logs
Metrics
Health Checks
Tracing
   ↓
Dashboards / Alerts
```

Conceitos praticados:

- logs estruturados;
- métricas técnicas;
- métricas de negócio;
- health checks;
- dashboards;
- alertas;
- monitoramento de CPU, memória e latência;
- visibilidade operacional;
- diferença entre logging, monitoring e observability.

Possíveis tecnologias:

![Spring Actuator](https://img.shields.io/badge/spring%20actuator-health%20checks-6DB33F?logo=springboot&logoColor=white)
![Prometheus](https://img.shields.io/badge/prometheus-metrics-E6522C?logo=prometheus&logoColor=white)
![Grafana](https://img.shields.io/badge/grafana-dashboards-F46800?logo=grafana&logoColor=white)
![Loki](https://img.shields.io/badge/loki-logs-F46800?logo=grafana&logoColor=white)

Equivalente em cloud:

![CloudWatch](https://img.shields.io/badge/aws-cloudwatch-FF4F8B?logo=amazoncloudwatch&logoColor=white)
![X-Ray](https://img.shields.io/badge/aws-x--ray-8C4FFF?logo=amazonaws&logoColor=white)

---

### 10 — Sharding

Divisão horizontal dos dados entre múltiplos bancos.

```text
Aplicação
   ↓
Shard Router
   ↓
DB Shard 0
DB Shard 1
DB Shard 2
DB Shard 3
```

Exemplo de estratégia simples:

```text
shard = user_id % number_of_shards
```

Conceitos praticados:

- sharding;
- partition key;
- distribuição de dados;
- hotspot;
- resharding;
- dificuldade com joins;
- desnormalização;
- escalabilidade horizontal da camada de dados;
- trade-offs de complexidade.

Possíveis tecnologias:

![PostgreSQL](https://img.shields.io/badge/postgresql-shards-4169E1?logo=postgresql&logoColor=white)
![Docker Compose](https://img.shields.io/badge/docker%20compose-multiple%20databases-2496ED?logo=docker&logoColor=white)

Equivalente em cloud:

![AWS RDS](https://img.shields.io/badge/aws-rds%20sharding-527FFF?logo=amazonrds&logoColor=white)
![DynamoDB](https://img.shields.io/badge/aws-dynamodb-partitioning-4053D6?logo=amazondynamodb&logoColor=white)

---

## Estratégia local primeiro, cloud depois

A ideia principal deste repositório é começar com implementações locais e controladas.

Isso permite entender os fundamentos antes de depender de serviços gerenciados.

Exemplo:

```text
Nginx local
   ↓
Application Load Balancer na AWS

PostgreSQL primary/replica local
   ↓
RDS Read Replica

Redis local
   ↓
ElastiCache

RabbitMQ local
   ↓
SQS, SNS ou EventBridge

Prometheus/Grafana local
   ↓
CloudWatch
```

Quando uma POC for migrada para cloud, será criada uma nova pasta específica com o sufixo `-aws`.

Exemplos:

```text
02-load-balancer-nginx
02-load-balancer-aws

04-database-replication
04-database-replication-aws

05-redis-cache
05-redis-cache-aws

08-message-queue
08-message-queue-aws
```

Dessa forma, o repositório mantém as duas visões:

- implementação local, para aprendizado conceitual;
- implementação em cloud, para prática de infraestrutura real.

---

## Padrão esperado para cada POC

Cada projeto deve seguir uma estrutura parecida:

```text
nome-da-poc
├── README.md
├── docker-compose.yml
├── app
├── infra
├── docs
└── scripts
```

O README de cada POC deve conter:

```text
Objetivo
Arquitetura
Tecnologias utilizadas
Conceitos praticados
Como executar
Como testar
Como parar/remover tudo
Problemas encontrados
Aprendizados
Equivalente em cloud
```

---

## Exemplo de documentação por POC

Cada POC deve responder claramente:

### Qual problema essa arquitetura resolve?

Exemplo:

> O load balancer resolve o problema de distribuir requisições entre múltiplas instâncias e evita que o cliente dependa de um único servidor.

### Qual era a limitação da arquitetura anterior?

Exemplo:

> Com apenas uma instância, qualquer falha derruba toda a aplicação. Além disso, todo o tráfego fica concentrado em um único servidor.

### Qual componente foi adicionado?

Exemplo:

> Foi adicionado um Nginx atuando como load balancer na frente de três instâncias da aplicação.

### Qual trade-off foi introduzido?

Exemplo:

> A arquitetura fica mais resiliente, mas passa a ter mais componentes para configurar, monitorar e manter.

---

## Stack principal prevista

Este laboratório poderá utilizar diferentes tecnologias conforme a POC.

### Backend

![Java](https://img.shields.io/badge/java-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring%20boot-6DB33F?logo=springboot&logoColor=white)
![Node.js](https://img.shields.io/badge/node.js-339933?logo=nodedotjs&logoColor=white)

### Infra local

![Docker](https://img.shields.io/badge/docker-2496ED?logo=docker&logoColor=white)
![Docker Compose](https://img.shields.io/badge/docker%20compose-2496ED?logo=docker&logoColor=white)
![Nginx](https://img.shields.io/badge/nginx-009639?logo=nginx&logoColor=white)

### Banco de dados

![PostgreSQL](https://img.shields.io/badge/postgresql-4169E1?logo=postgresql&logoColor=white)
![MongoDB](https://img.shields.io/badge/mongodb-47A248?logo=mongodb&logoColor=white)

### Cache

![Redis](https://img.shields.io/badge/redis-DC382D?logo=redis&logoColor=white)

### Mensageria

![RabbitMQ](https://img.shields.io/badge/rabbitmq-FF6600?logo=rabbitmq&logoColor=white)
![Kafka](https://img.shields.io/badge/apache%20kafka-231F20?logo=apachekafka&logoColor=white)

### Observabilidade

![Prometheus](https://img.shields.io/badge/prometheus-E6522C?logo=prometheus&logoColor=white)
![Grafana](https://img.shields.io/badge/grafana-F46800?logo=grafana&logoColor=white)
![Loki](https://img.shields.io/badge/loki-F46800?logo=grafana&logoColor=white)

### Cloud

![AWS](https://img.shields.io/badge/aws-232F3E?logo=amazonaws&logoColor=white)
![EC2](https://img.shields.io/badge/ec2-FF9900?logo=amazonec2&logoColor=white)
![RDS](https://img.shields.io/badge/rds-527FFF?logo=amazonrds&logoColor=white)
![S3](https://img.shields.io/badge/s3-569A31?logo=amazons3&logoColor=white)
![CloudFront](https://img.shields.io/badge/cloudfront-FF9900?logo=amazonaws&logoColor=white)
![CloudWatch](https://img.shields.io/badge/cloudwatch-FF4F8B?logo=amazoncloudwatch&logoColor=white)

---

## Roadmap

- [ ] Criar POC `01-single-server`
- [ ] Criar POC `02-load-balancer-nginx`
- [ ] Criar POC `03-database-separation`
- [ ] Criar POC `04-database-replication`
- [ ] Criar POC `05-redis-cache`
- [ ] Criar POC `06-cdn-static-assets`
- [ ] Criar POC `07-stateless-web-tier`
- [ ] Criar POC `08-message-queue`
- [ ] Criar POC `09-observability`
- [ ] Criar POC `10-sharding`
- [ ] Criar versões `-aws` das POCs mais relevantes
- [ ] Adicionar diagramas Mermaid em cada projeto
- [ ] Adicionar comandos de teardown em todas as POCs
- [ ] Documentar problemas reais encontrados durante a implementação

---

## Convenção de nomes

Projetos locais:

```text
01-single-server
02-load-balancer-nginx
03-database-separation
```

Projetos migrados para AWS:

```text
02-load-balancer-aws
03-database-separation-aws
04-database-replication-aws
```

A numeração indica a ordem de aprendizado, enquanto o sufixo indica a tecnologia ou ambiente principal.

---

## Filosofia do projeto

Este repositório não tem como objetivo apresentar arquiteturas perfeitas ou definitivas.

O foco é aprendizado progressivo.

Cada POC deve mostrar:

```text
problema → solução → trade-offs → aprendizado
```

A arquitetura deve evoluir naturalmente, sempre respondendo a uma necessidade real:

- mais tráfego;
- mais disponibilidade;
- menor latência;
- menor acoplamento;
- mais resiliência;
- melhor observabilidade;
- melhor escalabilidade.

---

## Observação sobre custos em cloud

As POCs locais podem ser executadas sem custo adicional, usando Docker e ferramentas open source.

As POCs em AWS devem ser executadas com atenção a custos, principalmente ao usar:

- NAT Gateway;
- Load Balancer;
- RDS;
- ElastiCache;
- CloudFront;
- múltiplas instâncias EC2;
- logs e métricas no CloudWatch.

Sempre que uma POC `-aws` for criada, ela deverá conter uma seção específica de teardown, explicando como remover todos os recursos criados.

Exemplo:

```text
Como deletar absolutamente tudo
```

Essa seção é obrigatória para evitar cobranças desnecessárias.

---

## Resultado esperado

Ao final deste laboratório, o objetivo é ter um portfólio técnico demonstrando conhecimento prático em:

- System Design;
- arquitetura escalável;
- redes e balanceamento;
- bancos de dados;
- replicação;
- cache;
- CDN;
- stateless applications;
- filas e processamento assíncrono;
- observabilidade;
- sharding;
- cloud computing;
- documentação técnica.

Este repositório é um laboratório contínuo de evolução arquitetural.

---

## Autor

Desenvolvido como parte de uma jornada prática de estudos em **System Design**, **Arquitetura de Software** e **Cloud Computing**.
