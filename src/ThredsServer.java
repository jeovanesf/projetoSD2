
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ThredsServer implements Runnable {

    private ArrayList<String> files;
    private Socket connectionSocket;
    private String diretorio;

    public ThredsServer(Socket connectionSocket, ArrayList<String> files, String diretorio) {
        this.connectionSocket = connectionSocket;
        this.files = files;
        this.diretorio = diretorio;
    }

    @Override
    public void run() {
        String clientFile;

        BufferedReader inFromClient;
        DataOutputStream outToClient = null;

        try {            
            outToClient
                    = new DataOutputStream(connectionSocket.getOutputStream());
            inFromClient
                    = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            for (String file : files) {
                outToClient.writeBytes(file + ';');
            }
            outToClient.writeBytes("\n");

            connectionSocket.setSoTimeout(10000);
            clientFile = inFromClient.readLine();

            if (files.contains(clientFile)) {
              
                BufferedReader buffRead = new BufferedReader(new FileReader(diretorio + '\\' + clientFile));
              
                while (true) {
                    
                    outToClient.writeBytes("Arquivo existe\n");
                    
                }
                
            } else {
                outToClient.writeBytes("Arquivo não existe\n");
            }
        } catch (Exception e) {
            try {
                outToClient.writeBytes("Time out\n");
                connectionSocket.close();
            } catch (IOException ex) {
                System.out.println(e.getMessage());
            }
        }
    }

}