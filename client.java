import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client{
        static String serverMesaji;
        
    public static void main(String[] args) {

        String host = "10.142.1.191";
        int port = 5550;
        String ogrenciNumarasi = "23060533";

        try (Socket serverSocket = new Socket(host, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);


            // 1. Döngü: Sunucu "sana da merhaba" diyene kadar merhaba gönder
            for(int i =0; i<10; i++) {
                    out.println("merhaba");
                    System.out.println("istemci: merhaba " );

               // if (in.ready()) {  // Sunucudan mesaj gelmiş mi kontrol
                    serverMesaji = in.readLine();
                    // System.out.println("Sunucu: " + serverMesaji);
                    
                    if (serverMesaji.equalsIgnoreCase("sana da merhaba")) {
                     System.out.println("Sunucu: " + serverMesaji);
                    }

                //}
            }

            while (true) {
                serverMesaji = in.readLine();
                System.out.println("Sunucu: " + serverMesaji);

                if (serverMesaji.equalsIgnoreCase("Derdin ne senin?")) {
                    out.println(ogrenciNumarasi); // öğrenci numarası gönder
                    System.out.println("İstemci: " + ogrenciNumarasi);

                } else if (serverMesaji.equalsIgnoreCase("Kaydedildi görüşürüz.")) {
                    System.out.println("sunucu " + serverMesaji);
               
                }break;
            }

            }catch (IOException ex) {
            ex.printStackTrace();
        }
    
    }
}