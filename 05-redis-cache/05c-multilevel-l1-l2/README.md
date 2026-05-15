# 05c - Multilevel Cache L1/L2 with Caffeine and Redis

## Objetivo

Esta POC demonstra uma arquitetura de cache em múltiplos níveis usando:

- Caffeine como cache local em memória, também chamado de L1.
- Redis como cache externo e compartilhado, também chamado de L2.
- PostgreSQL como fonte da verdade.

O objetivo é mostrar como uma aplicação pode reduzir latência e carga no banco usando duas camadas de cache.

---

## Arquitetura

```mermaid
flowchart TD
    Client[Client / Postman / Browser]

    subgraph Application["Spring Boot Application"]
        Controller[ProductController]
        Service[ProductService]
        MultiCache[ProductMultilevelCacheService]
        L1[(L1 Cache<br/>Caffeine<br/>In-memory)]
    end

    subgraph ExternalCache["External Cache"]
        L2[(L2 Cache<br/>Redis Standalone)]
    end

    subgraph Database["Database"]
        DB[(PostgreSQL<br/>Source of Truth)]
    end

    Client -->|HTTP Request| Controller
    Controller --> Service
    Service --> MultiCache

    MultiCache -->|1. Try local memory| L1
    L1 -->|L1 HIT| MultiCache

    MultiCache -->|2. If L1 MISS, try Redis| L2
    L2 -->|L2 HIT| MultiCache

    MultiCache -->|3. If L2 MISS, query DB| DB
    DB -->|Product data| MultiCache

    MultiCache -->|Populate L2 with TTL| L2
    MultiCache -->|Populate L1 with shorter TTL| L1

    MultiCache --> Service
    Service --> Controller
    Controller -->|HTTP Response| Client