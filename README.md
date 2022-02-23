###Serviço para consumir os eventos vindo do microsserviço employee_publisher
- Criar imagem: docker build -t employee_consumer .
- Para rodar a aplicação, precisamos passar alguns argumentos na linha de comando, execute:<br>
**docker run --rm employee_consumer --AWS_SECRET_KEY=${AWS_SECRET_KEY} --AWS_KEY=${AWS_KEY}**

