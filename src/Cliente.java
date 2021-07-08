
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Scanner;

public class Cliente{
	

public static String perguntaArquivo(String F)
{
	Socket clientSocket = null;
    try {
        BufferedReader inFromUser
                = new BufferedReader(new InputStreamReader(System.in));

        clientSocket  = new Socket("127.0.0.1", 6789);

        DataOutputStream outToServer
                = new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer
                = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        clientSocket.setSoTimeout(10000);
        String files = inFromServer.readLine();
        String[] filesSplit = files.split(";");

        System.out.println("Files: ");
        for (String file : filesSplit) {
            System.out.println(file);
        }

        System.out.println("\nEscolha um arquivo");
        String file = inFromUser.readLine();
        F = file;
        outToServer.writeBytes(file + '\n');

        clientSocket.setSoTimeout(10000);
        String reponse = inFromServer.readLine();
        String[] responseSplit = reponse.split(";");
        System.out.println("Server:");
        for (String response : responseSplit) {
            System.out.println(response);
        } 
        
       
       
        //clientSocket.close();
    } catch (Exception e) {
        System.out.println("Tempo excedido");
        try {
            clientSocket.close();
        } catch (IOException ex) {
            System.out.println("Não pode fechar conexão");
        }
    }
    return F;
}
	
/*public static void solicitarArquivo(String message) throws IOException{
		
		byte[] msg = message.getBytes();
		MulticastSocket socket=new MulticastSocket(4321);
		InetAddress group=InetAddress.getByName("230.0.0.0");
		socket.joinGroup(group);
		DatagramPacket packet = new DatagramPacket(msg, msg.length,
		         group,4321);
		socket.send(packet);
		socket.leaveGroup(group);
	    socket.close();
 }*/


    public static void main(String[] args) throws IOException {

    	
        	
    	try {	
    		
    	Socket clientSocket = new Socket("127.0.0.1",  8001);
    	 String file = null;
    	file = perguntaArquivo(file);
    	 BufferedReader inFromUser
         = new BufferedReader(new InputStreamReader(System.in));
    	
    	 //System.out.println("\nEscolha um arquivo");
         //String file = inFromUser.readLine();
         
		DataOutputStream outToServer =
				new DataOutputStream(clientSocket.getOutputStream());
		outToServer.writeBytes(file + '\n');

		
		// Criando arquivo que sera recebido pelo servidor
		FileOutputStream fileOut = new FileOutputStream(file);

		// Criando canal de transferencia
		InputStream socketIn = clientSocket.getInputStream();

		// Lendo o arquivo recebido pelo socket e gravando no
		// arquivo local
	//	System.out.println("Recebendo Arquivo: cliente_teste.txt");

		byte[] cbuffer = new byte[1024];
		int bytesRead;
		while ((bytesRead = socketIn.read(cbuffer)) != -1) {
			fileOut.write(cbuffer, 0, bytesRead);
		}
		fileOut.close();

		clientSocket.close();
		System.out.println("Arquivo Recebido: Arquivo: " +file);
	} catch (Exception ex) {
		System.out.println(ex.getMessage());
	}


}

}
