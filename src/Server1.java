import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server1 {
	 public static void main(String argv[]) throws Exception {
			try{
				String diretorio = "C:\\Users\\mayco\\OneDrive\\Área de Trabalho\\Nova Pasta\\Projeto Final\\Server2";
				ServerSocket server = new ServerSocket(8001);
				Socket s = server.accept();
				BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(s.getInputStream()));
				//aguardando recebimento do nome do arquivo - timeout 10 segundos
				String arquivo = inFromClient.readLine();
				//verifica se o arquivo existe
				//se existir envia o arquivo 
		        FileInputStream fileIn = new FileInputStream(diretorio+ "\\" +arquivo);           
		        OutputStream socketOut = s.getOutputStream();
				 // Criando tamanho de leitura
		        byte[] cbuffer = new byte[1024];
		        int bytesRead;

		        // Lendo arquivo criado e enviado para o canal de transferencia
		        System.out.println("Enviando Arquivo: "+arquivo);
		        while ((bytesRead = fileIn.read(cbuffer)) != -1) {
		            socketOut.write(cbuffer, 0, bytesRead);
		            socketOut.flush();
		        }
		        fileIn.close();
		        s.close();
		        System.out.println("Arquivo Enviado!");

				
				
				}catch(Exception ex)
				{
					
				}
	 }

}
