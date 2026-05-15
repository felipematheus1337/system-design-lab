# 06 - CDN Static Assets On-Premise com Spring Boot + Nginx

## Objetivo

Esta POC tem como objetivo simular localmente uma arquitetura de entrega de arquivos estáticos parecida com o papel de uma CDN.

A aplicação Spring Boot é responsável pela regra de negócio e pela geração de relatórios em PDF. Depois de gerar o arquivo, ela salva o PDF em uma pasta local de assets e retorna uma URL pública apontando para o Nginx.

O Nginx, por sua vez, atua como um servidor de arquivos estáticos, entregando o PDF ao cliente com headers HTTP de cache.

Esta POC não é uma CDN real, pois não possui edge locations, distribuição geográfica ou roteamento global. Ela simula localmente os principais papéis arquiteturais envolvidos:

```text
Aplicação dinâmica -> Storage de assets -> Camada de entrega estática
```

Em ambiente cloud, essa arquitetura poderia ser equivalente a:

```text
Spring Boot -> S3 -> CloudFront
```

Nesta POC local/on-premise, usamos:

```text
Spring Boot -> Pasta local assets -> Nginx
```

---

## Conceito principal

A ideia central é separar duas responsabilidades:

```text
Spring Boot:
- cria usuários;
- executa regras de negócio;
- gera relatórios em PDF;
- salva arquivos na pasta de assets;
- retorna a URL pública do arquivo.

Nginx:
- não conhece regra de negócio;
- não gera PDF;
- apenas lê arquivos estáticos;
- entrega esses arquivos via HTTP;
- adiciona headers de cache.
```

Ou seja, o endpoint do Spring **não devolve o PDF diretamente**. Ele apenas gera o arquivo e devolve uma URL para que o cliente baixe esse arquivo através do Nginx.

---

## Arquitetura

```mermaid
flowchart TD
    Client[Cliente / Postman / Browser]

    subgraph SpringApp["Spring Boot Application"]
        Controller[UserReportController]
        UseCase[GenerateUserReportUseCase]
        Gateway[UserReportFileGateway]
        UserRepository[User Repository / PostgreSQL]
    end

    subgraph Storage["Origin Storage Local"]
        Assets[(Pasta ./assets/reports)]
    end

    subgraph StaticServer["Nginx - CDN Local Simulation"]
        Nginx[Nginx Static File Server<br/>localhost:8081]
    end

    Client -->|POST /users/{id}/reports| Controller
    Controller --> UseCase
    UseCase -->|Busca usuário| UserRepository
    UseCase --> Gateway
    Gateway -->|Gera e salva PDF| Assets
    UseCase -->|Retorna assetUrl| Controller
    Controller -->|JSON com URL do relatório| Client

    Client -->|GET /assets/reports/report.pdf| Nginx
    Nginx -->|Lê arquivo estático| Assets
    Nginx -->|PDF + Cache-Control| Client
```

---

## Fluxo resumido

1. Cliente chama `POST /users/{id}/reports`
2. Spring busca o usuário
3. Spring gera o PDF
4. Spring salva em `assets/reports`
5. Spring devolve uma URL apontando para o Nginx (`8081`)
6. Cliente acessa a URL
7. Nginx entrega o arquivo

---

## Estrutura esperada

```text
06-cdn-static-assets-on-premise-nginx
├── assets
│   └── reports
├── nginx
│   └── default.conf
├── src
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

## Docker Compose

Consulte o `docker-compose.yml` do projeto com comentários explicando os volumes compartilhados e a integração Spring + Nginx.

---

## Configuração do Nginx

Arquivo:

```text
nginx/default.conf
```

Exemplo:

```nginx
server {
    listen 80;

    server_name localhost;

    location /assets/ {
        alias /usr/share/nginx/assets/;

        add_header Cache-Control "public, max-age=300";
        add_header X-CDN-Simulation "nginx-local-static-assets";
        add_header X-Content-Type-Options "nosniff";

        try_files $uri =404;
    }
}
```

### Papel do `alias`

Transforma:

```text
/assets/reports/user-report.pdf
```

em:

```text
/usr/share/nginx/assets/reports/user-report.pdf
```

O Nginx usa esse caminho físico para encontrar o arquivo no container.

---

## Como executar

### 1. Gerar o JAR

```bash
mvn clean package -DskipTests
```

### 2. Subir containers

```bash
docker compose up --build
```

### 3. Gerar relatório

```bash
curl -X POST http://localhost:8080/users/1/reports
```

### 4. Abrir assetUrl retornada

Exemplo:

```text
http://localhost:8081/assets/reports/user-report-1.pdf
```

### 5. Validar headers do Nginx

```bash
curl -I http://localhost:8081/assets/reports/NOME_DO_ARQUIVO.pdf
```

Esperado:

```http
Server: nginx
Cache-Control: public, max-age=300
X-CDN-Simulation: nginx-local-static-assets
```

---

## Equivalência com cloud

Local:

```text
Spring Boot -> assets/ -> Nginx
```

Cloud:

```text
Spring Boot -> S3 -> CloudFront
```

A principal ideia da POC é aprender a separar:

```text
Aplicação dinâmica ≠ servidor de assets estáticos
```
