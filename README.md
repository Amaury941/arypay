[x] Definindo propriedades para a fila 
[x] Adicionando a dependência Spring-Amqp
[x] Criando uma Configuração Básica para Beans do Rabbit
[x] Trocando rota para 5433 -p


curl -X POST http://localhost:8080/transactions/new -H "Content-Type: application/json" -d '{"sender":"e0687b28-e37a-4ac3-b09f-2926fdaa4bd6","receiver":"46e746b7-ef73-4923-b2c4-0e33d27c7352","amount":67.69}'