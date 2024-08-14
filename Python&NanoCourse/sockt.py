import socket

resp = "S"
while resp == "S":
    url = input("Digite uma url: ")
    ip = socket.gethostbyname(url)
    print("O IP referente a url informada é: ", ip)
    resp = input("Digite <s> para continuar: ").upper()
