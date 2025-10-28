import java.io.*;
import java.net.*;

public class server{

    public static void main(String args[]){

        ServerSocket serverSocket;
        Socket clientSocket;
        try{
            serverSocket = new ServerSocket(5550);
            System.out.println("Sunucu baslatildi, port dinleniyor...");

            while(true){
                clientSocket = serverSocket.accept();
                System.out.println("Istemci baglandi...");
                handleClient(clientSocket);
            }
        }catch( IOException ex){
            System.err.println(ex);
        }
    }

    public static void handleClient(Socket clientSocket){
        try(
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)){
                String message;
                int merhabaCounter = 0;
                int threshold = 10;

                while(merhabaCounter < threshold && (message = input.readLine()) != null){
                    if(message.equalsIgnoreCase("merhaba")){
                        merhabaCounter++;
                    System.out.println("Sayac: " + merhabaCounter);
                    output.println("sana da merhaba");
                    }
                }
                if(merhabaCounter >= threshold){
                    System.out.println("Esik asildi...");
                    output.println("derdin ne senin?");

                    String studentNumber = input.readLine();
                    if(!studentNumber.isEmpty()){
                        System.out.println("Ogrenci numarasi alindi..." + studentNumber);

                        output.println("kaydedildi gorusuruz");
                        System.out.println("Ogrenci no kaydedildi...");
                    }
                }
            }    catch(IOException e){
                    e.getMessage();
                }
            }

    }

