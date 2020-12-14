# ANGULAR-CRUD-RESTful

1. Três and-point de consulta. Consulta uma lista completa por produto, consulta por id produto, e consulta por nome do produto. / GET
2. Um and-point para salvar produto. / POST

## Rota[GET]
- [GET] http://{host-url}/produto/lista
- localhost:8080/produto/lista

### Resposta:
```
{
    "id": 1,
    "name": "Produto01",
    "price": 100
}
```
## Rota [POST]
- [POST] http://{host-url}/produto/create
- localhost:8080/produto/create

### Body:
```
{
  "name": "Produto01",
  "price": 100
}
```

### Resposta:
```
{
    "message": "Produto criado"
}
```

## Requisitos:
- [x] Java 11<br>
- [x] DTO<br>
- [x] Java Swagger2 Swagger-ui<br>
- [x] Java 11

