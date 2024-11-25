package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("inserici IP");

        Socket s = new Socket(input.nextLine(), 3000);
        BufferedReader in = new BufferedReader(new InputStreamReader((s.getInputStream())));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        boolean ins = true;
        while (ins) {
            System.out.println("inserisci nome senza @ o :");
            String nome = input.nextLine();
            for (int i = 0; i < nome.length(); i++) {
                if (nome.charAt(i) == '@' || nome.charAt(i) == ':') {
                    break;
                }
            }
            out.writeBytes("USERNAME@" + nome + '\n');
            if (in.readLine().equals("SUs"))
                ins = false;
            else
                System.out.println("nome già inserito");
        }

        ThreadClient td = new ThreadClient(s, in);
        td.start();
        System.out.println("Spiegazione comandi");
        System.out.println("\\list lista utenti");
        System.out.println("\\p <destinatario>:<messaggio> messaggio privato a destinatario");
        System.out.println("\\exit esci dalla chat");
        boolean connessione = true;
        while (connessione) {
            
            String action = input.nextLine();
            switch (action.charAt(0)) {
                case '\\':
                String [] split = action.split(" ", 2);
                switch(split[0]){
                    case "\\list":
                        out.writeBytes("LIST" + '\n');
                        break;
                    case "\\p":
                        try {
                            split = split[1].split(":", 2);
                        out.writeBytes("PRIVATE@" + split[0] + "@" + split[1] + '\n');
                        } catch (Exception e) {
                            System.out.println("comando non completo");
                        }
                        
                        break;
                    case "\\exit":
                        out.writeBytes("EXIT" + '\n');
                        s.close();
                        connessione = false;
                        break;
                    default:
                        System.out.println("comando non riconosciuto");
                        break;
                }
                    
                    break;
                default:
                    out.writeBytes("ALL@" + action + '\n');
                    break;
                
            }

        }
    }
}