# Caffeine In-Memory Cache

## Objetivo

Demonstrar cache local em memória usando Spring Cache + Caffeine.

## Arquitetura

Cliente -> Spring Boot -> Caffeine -> PostgreSQL

## Conceitos demonstrados

- Cache local
- Cache hit
- Cache miss
- TTL
- Eviction por tamanho
- Invalidação com @CacheEvict
- Limitação em ambiente com múltiplas instâncias

## Trade-offs

### Vantagens

- Extremamente rápido
- Sem chamada de rede
- Simples de configurar
- Ótimo para reduzir leituras repetidas

### Limitações

- Cache não é compartilhado entre instâncias
- Pode retornar dado obsoleto até o TTL expirar
- Consome memória da própria aplicação
- Requer estratégia de invalidação