from socket import *

servidor = "127.0.0.1"
porta = 43210

obj_socket = socket(AF_INET, SOCK_STREAM)
obj_socket.bind((servidor, porta))
obj_socket.listen(2)

print("Aguardando cliente....")

while True:
    con, cliente = obj_socket.accept()
    print("Conectado com: ", cliente)
    msg_enviada = b'Ola Cliente'
    con.send(msg_enviada)
    break
con.close()
