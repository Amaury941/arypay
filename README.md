// TODO: o que falta...

[x] Definindo propriedades para a fila 
[x] Adicionando a dependência Spring-Amqp
[x] Criando uma Configuração Básica para Beans do Rabbit
[x] Trocando rota para 5433 -p
[x] Dando update na wallet
[x] Criando usuário
[ ] Otimizando as procuras no banco de dados
[ ] Mandando mensagem para o rabbitMQ
[ ] validador de CPF

sudo lsof -i :8080

psql -U admin -d arypay -h localhost

curl -X POST http://localhost:8080/transactions/transfer -H "Content-Type: application/json" -d '{"payer":"eaeca767-ea49-4e89-bba9-8fb8f4687b32","payee":"b938aa72-7ae8-494d-bfe6-801786c58640","value":1}'

// sender não autorizado
curl -X POST http://localhost:8080/transactions/transfer -H "Content-Type: application/json" -d '{"payer":"b938aa72-7ae8-494d-bfe6-801786c58640","payee":"eaeca767-ea49-4e89-bba9-8fb8f4687b32","value":1}'

// receiver não autorizado
curl -X POST http://localhost:8080/transactions/new -H "Content-Type: application/json" -d '{"sender":"147c6d5b-375a-4e2f-983f-87a1feae7211","receiver":"147c6d5b-375a-4e2f-983f-87a1feae7211","amount":1}'

// receiver não autorizado
curl -X POST http://localhost:8080/users/new -H "Content-Type: application/json" -d '{"username": "teste","CPFJ": 5,"email":"teste@teste.com","password":"rocambole"}'