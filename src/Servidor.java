
import java.io.File;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

	
    public static void main(String argv[]) throws Exception {
    	/*InetAddress group=InetAddress.getByName("230.0.0.0");
    	byte[] buf = new byte[256];
    	
    	try(MulticastSocket serverSocket = new MulticastSocket(4321)) {
    		serverSocket.joinGroup(group);
    		
    		while(true) {
    			DatagramPacket msgPacket = new DatagramPacket(buf,buf.length);
    			serverSocket.receive(msgPacket);
    			
    			String msg = new String(buf,0,buf.length);
    			System.out.println("Nome do arquivo: " + msg);
    			arq = msg;
    			procurarArquivo();
    			
    		}
    	} catch (IOException ex) {
            ex.printStackTrace();
        } */
    	
    	  String diretorio = "C:\\Users\\mayco\\OneDrive\\Área de Trabalho\\Nova Pasta\\Projeto Final\\Server1";
    	  String diretorio2 = "C:\\Users\\mayco\\OneDrive\\Área de Trabalho\\Nova Pasta\\Projeto Final\\Server2";
          ArrayList<String> files = visualizarArquivos(diretorio2);

          ServerSocket welcomeSocket = new ServerSocket(6789);

          while (true) {            
              Socket connectionSocket = welcomeSocket.accept();
              System.out.println(connectionSocket.getInetAddress());
              Thread t = new Thread(new ThredsServer(connectionSocket, files, diretorio2));           
              
              t.start();
          }
       
    }
    

    
    public static ArrayList<String> visualizarArquivos(String diretorio) throws IOException {
        ArrayList<String> fileName = new ArrayList<>();
        File file = new File(diretorio);
        File files[] = file.listFiles();
        int i = 0;
        for (int j = files.length; i < j; i++) {
            File arquivos = files[i];
            fileName.add(arquivos.getName());
        }
        return fileName;
    }

}	
    