def perguntar():
    return input("O que deseja realizar?\n" +
                 "<I> - Para Inserir um usúario\n" +
                 "<P> - Para Pesquisar um usúario\n" +
                 "<E> - Para Excluir um usúario\n" +
                 "<L> - Para Listar um usúario: ").upper()


def inserir(dicionario):
    chave = input("Digite o login: ").upper()
    nome = input("Digite o nome: ").upper()
    data_acesso = input("Digite a última data de acesso: ")
    estacao_acessada = input("Qual a última estação acessada: ").upper()

    dicionario[chave] = (nome, data_acesso, estacao_acessada)
