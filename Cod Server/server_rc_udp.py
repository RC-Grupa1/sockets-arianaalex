import socket

def start_udp_server():
    server_ip = "100.96.244.31" 
    server_port = 5005

    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_socket.bind((server_ip, server_port))

    print(f"[*] Serverul UDP gata pe portul {server_port}...")

    while True:
        data, client_addr = server_socket.recvfrom(1024)
        message = data.decode('utf-8')
        print(f"Client ({client_addr}): {message}")

        if message.lower() == "exit":
            break
        
        response = input("Server (tu): ")
        server_socket.sendto(response.encode('utf-8'), client_addr)
        
        if response.lower() == "exit":
            break

    server_socket.close()

if __name__ == "__main__":
    start_udp_server()