# Estudos com Spring e Ebean

Este repositório contém um estudo do uso do **Spring Web Flux**, com **Ebean** e **Lombok**.


## Compilar e executar

Para compilar o programa tenha o **Java** e **Maven** instalados e disponíveis na linha de comando. 

Execute o comando: `mvn clean package` para criar o arquivo `.jar` executável.

Execute o arquivo `executar.bat` para iniciar o programa.



## Arquivos e pastas

A pasta `.vscode` contém os arquivos de configuração do **VS Code**.  
  
A pasta `.settings` e o arquivo `.classpath` são arquivos de configuração de plugins do **VS Code** que compilam o código **Java**.

O arquivo `execute.bat` contém a configuração usada pelo **Java**, e o código que irá iniciar a execução do **Spring**.

O arquivo `close.bat` contém o código que pode ser usado para parar a execução do **Spring**. Quando o **Spring** inicia sua execução ele criará um arquivo chamado `application.pid` com o ID do processo do **Java**, para que possa ser usado para parar o processo.



## Itens de estudo

- O **Ebean** é usado como **ORM**, e sua configuração de Banco de Dados é carregada através do recursos de configuração do **Spring**.
- O **Lombok** é usado para criar as entidades *JavaBeans*, e para adicionar o atributo privado `log` nas classes que irão escrever mensagens nos arquivos de log _(é usado o **LogBack** como biblioteca de logs)_
- Foi usado o **Spring Web Flux** para disponibilizar os serviços HTTP.
- O arquivo `application.yml` _(da da pasta `resources` do maven)_, junto com a classe `.../entities/Config`, contêm as configurações do sistema. Caso seja adicionado um arquivo `application.yml` na pasta pasta do arquivo `.jar` executável do **Spring**, esse arquivo externo irá conter as configurações que sobreescrevem as configurações dentro do arquivo `.jar` _(ex.: confgurações de conexão com Banco de Dados)_.
- A classe `.../startup/Timers` irá iniciar um agendamento do **Spring** para que uma mensagem será escrita no log a cada alguns segundos.
- Devido as facilidades de configuração do **Spring**, as classes dentro de `.../startup/` precisam ser carregadas considerando que serão usadas no **Framework Spring**, para que recebam as configurações do framework.
- É usado o Banco de Dados **H2** para fazer os testes e executar o código.

