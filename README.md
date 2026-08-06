[x] Definindo propriedades para a fila 
[x] Adicionando a dependência Spring-Amqp
[x] Criando uma Configuração Básica para Beans do Rabbit
[x] Trocando rota para 5433 -p
[ ] Dando update na wallet

psql -U admin -d arypay -h localhost

curl -X POST http://localhost:8080/transactions/new -H "Content-Type: application/json" -d '{"sender":"147c6d5b-375a-4e2f-983f-87a1feae7211","receiver":"39c090af-a7e4-4a56-b710-8d7d0696e28b","amount":1}'

// sender não autorizado
curl -X POST http://localhost:8080/transactions/new -H "Content-Type: application/json" -d '{"sender":"39c090af-a7e4-4a56-b710-8d7d0696e28b","receiver":"147c6d5b-375a-4e2f-983f-87a1feae7211","amount":1}'

// receiver não autorizado
curl -X POST http://localhost:8080/transactions/new -H "Content-Type: application/json" -d '{"sender":"147c6d5b-375a-4e2f-983f-87a1feae7211","receiver":"147c6d5b-375a-4e2f-983f-87a1feae7211","amount":1}'