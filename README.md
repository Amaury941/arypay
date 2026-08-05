[x] Definindo propriedades para a fila 
[x] Adicionando a dependência Spring-Amqp
[x] Criando uma Configuração Básica para Beans do Rabbit
[x] Trocando rota para 5433 -p
[ ] Dando update na wallet

curl -X POST http://localhost:8080/transactions/new -H "Content-Type: application/json" -d '{"sender":"5ba6a7fb-713d-4482-9841-a07ced5fb4e9","receiver":"ca862d64-8712-4479-930e-5e63dd8a3c9e","amount":67.69}'

curl -X POST http://localhost:8080/transactions/new -H "Content-Type: application/json" -d '{"sender":"9a4caaee-8071-4183-918c-07b39e90eb32","receiver":"2abc2476-27ba-4a9e-ad6c-40c78ed8e815","amount":67.69}'

curl -X POST http://localhost:8080/transactions/new -H "Content-Type: application/json" -d '{"sender":"2abc2476-27ba-4a9e-ad6c-40c78ed8e815","receiver":"9a4caaee-8071-4183-918c-07b39e90eb32","amount":67.69}'