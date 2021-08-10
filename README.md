# java-api-restful-usuarios-login
API RESTful de criação de usuários e login
<br>
<br>
ENPOINTS <br>
@GET /<br>
CRIA UM USUARIO<br>
 Responder o código de status HTTP apropriado<br>
e Em caso de sucesso, retorne o usuário, mais os campos:<br>
id do usuário (pode ser o próprio gerado pelo banco, porém seria<br>
interessante se fosse um UUID)<br>
created: data da criação do usuário<br>
modified! data da última atualização do usuário<br>
last login: data do último login (no caso da criação, será a mesma<br>
que a criação)<br>
O token: token de acesso da API (pode ser um UUID ou um JWT)<br>
e Caso o e-mail já exista, deverá retornar erro com a mensagem "E-mail já<br>
existente”.<br>
e Otoken deverá ser persistido junto com o usuário<br>
<br>
LOGIN<br>
@POST /login<br>
Este endpoint irá receber um objeto com e-mail e senha.<br>
Caso o e-mail e a senha correspondam a um usuário existente, retornar igual
ao endpoint de Criação.<br>
Caso o e-mail não exista, retornar erro com status apropriado mais a<br>
mensagem "Usuário e/ou senha inválidos"<br>
Caso o e-mail exista mas a senha não for correta, retornar o status<br>
apropriado, de não autorizado, mais a mensagem "Usuário e/ou senha<br>
inválidos"<br>

PERFIL<br>
@POST /login <br>
Caso o token não exista, retornar erro com status apropriado com a<br>
mensagem "Não autorizado".<br>
Caso o token exista, buscar o usuário pelo ia passado no path e comparar se<br>
o token no modelo é igual ao token passado no header.<br>
Caso não seja o mesmo token, retornar erro com status apropriado e<br>
mensagem "Não autorizado"<br>
Caso seja o mesmo token, verificar se o último login foi a MENOS que 30<br>
minutos atrás. Caso não seja a MENOS que 30 minutos atrás, retornar erro<br>
com status apropriado com mensagem "Sessão inválida”.<br>
Caso tudo esteja ok, retornar o usuário no mesmo formato do retorno do<br>
<br>
<br>
O que foi utilizado:<br>
Banco de dados em memória hsqldb;<br>
Processo de build via Gradle.<br>
Persistência com Hibernate.<br>
Framework Spring.<br>
Testes unitários e de integração.<br>
<br>
Versão do java:.<br>
11.0.2.<br>

Versão do Gradle:.<br>
7.1.1.<br>

Rodar o projeto:
no diretorio raiz rode:  gradle bootRun


