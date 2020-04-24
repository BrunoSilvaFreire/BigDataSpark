# Spark Gradle Project
Este projeto contém todos os exercícios propóstos pela aula de Big Data que
envolvem o Spark.

A estrutura da distribuição final. `('.zip' ou '.tar')`
* `assets`: Arquivos (provalemente dados) que são consumidos.
* `bin`: Scripts para execução do projeto
* `lib`: Bibliotecas usadas pelo projeto
* `src`: Código fonte original

Para executar o projeto, basta invocar o script que melhor
agrada o seu OS:
* Linux: `bin/spark`
* Windows: `bin/spark.bat`  

Isso irá executar todos os projetos disponíveis.
Caso prefira executar apenas um projeto, é possível passar 
um argumento como filtro dos projetos.

Ex (bash): 
* `bin/spark airportsUsa` irá apenas executar o projeto airportsUsa
* `bin/spark airports` irá apenas executar todos os projeto cujo nome começa com *airports*

É possível listar todos os projetos utilizando `bin/spark --list`.