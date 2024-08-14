⛃🍃♨️☕
# Objetivos do Projeto

O objetivo deste projeto é praticar a modelagem de classes e relacionamentos utilizando o Spring Data JPA.

- **Relacionamento Artista e Música**: 
  - Implementar corretamente a relação entre `Artista` e `Música`, onde um artista pode estar associado a mais de uma música.
  - Garantir que uma música só seja salva no banco de dados caso o artista tenha sido previamente cadastrado.

- **Consultas com Spring Data JPA**:
  - Praticar `derived queries` e `JPQL` para verificar se o artista já está cadastrado.
  - Buscar músicas por um determinado artista.

## Tecnologias Utilizadas

### Spring Data JPA
O Spring Data JPA é uma extensão do Spring Framework que facilita a implementação de repositórios de acesso a dados com JPA (Java Persistence API). Ele reduz a quantidade de código boilerplate necessário para acessar dados e manipular entidades.

### Persistência de Dados
A persistência de dados envolve a definição de entidades (classes que representam tabelas no banco de dados), a criação de repositórios (interfaces que estendem `JpaRepository` para operações CRUD), e a utilização de consultas personalizadas.

### Consultas
Com o Spring Data JPA, é possível criar consultas utilizando métodos de nomeação automática, `JPQL` (Java Persistence Query Language) ou até mesmo consultas nativas SQL, simplificando a criação e execução de consultas complexas.

### Configuração
O projeto inclui configurações de conexão com o banco de dados, como as informações de URL, usuário e senha, além da configuração de JPA, como o provedor de persistência e as estratégias de geração de esquema.
