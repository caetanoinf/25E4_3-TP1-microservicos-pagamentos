# Microsserviços de Pagamentos

Sistema com dois microsserviços que se comunicam via HTTP.

## Serviços

- **Payments Service** (porta 9081): Processa pagamentos
- **Orders Service** (porta 9080): Gerencia pedidos e consome o Payments Service

## Executar

```bash
docker-compose up --build
```
