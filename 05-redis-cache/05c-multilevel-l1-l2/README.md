# Redis Standalone Cache

## Objetivo

Demonstrar o uso de Redis como cache externo utilizando Spring Boot, PostgreSQL e RedisTemplate.

## Arquitetura

Cliente -> Spring Boot -> Redis -> PostgreSQL

## Estratégia usada

Esta POC usa o padrão Cache-Aside.

Fluxo de leitura:

1. A aplicação consulta o Redis.
2. Se o dado existir, retorna do cache.
3. Se não existir, consulta o PostgreSQL.
4. Salva o resultado no Redis com TTL.
5. Retorna o dado ao cliente.

Fluxo de atualização:

1. A aplicação atualiza o dado no PostgreSQL.
2. Remove a entrada correspondente do Redis.
3. A próxima leitura recarrega o cache.

## Conceitos demonstrados

- Redis standalone
- Cache-aside pattern
- Cache hit
- Cache miss
- TTL
- Invalidação manual
- RedisTemplate
- Serialização JSON
- Separação entre serviço de negócio e serviço de cache

## Trade-offs

### Vantagens

- Cache compartilhado entre múltiplas instâncias
- Reduz carga no banco
- TTL centralizado
- Mais próximo de uma arquitetura distribuída real

### Limitações

- Há chamada de rede entre aplicação e Redis
- Cache pode ficar temporariamente desatualizado
- Exige estratégia explícita de invalidação
- Redis vira uma dependência operacional