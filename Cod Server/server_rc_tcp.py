import socket

def start_tcp_server():
    server_ip = "100.96.244.31" 
    server_port = 5005

    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((server_ip, server_port))
    server_socket.listen(1)

    print(f"[*] Serverul TCP așteaptă conexiunea pe portul {server_port}...")
    conn, addr = server_socket.accept()
    print(f"[*] Conectat la: {addr}")

    while True:
        # Primire mesaj
        data = conn.recv(1024).decode('utf-8')
        print(f"Client: {data}")
        if data.lower() == "exit":
            break

        # Trimitere răspuns
        response = input("Server (tu): ")
        conn.send((response + "\n").encode('utf-8'))
        if response.lower() == "exit":
            break

    conn.close()
    server_socket.close()
    print("[*] Conexiune închisă.")

if __name__ == "__main__":
    start_tcp_server()