package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadClient extends Thread {
    BufferedReader in;

    public ThreadClient(Socket s, BufferedReader in) throws IOException {
        super();
        this.in = new BufferedReader(new InputStreamReader((s.getInputStream())));
    }

    public void run() {
        boolean connessione = true;
        try {
            while (connessione) {
                boolean run = true;
                switch (in.readLine()) {
                    case "L":
                        while (run) {
                            String msg = in.readLine();
                            if (msg != "L") {
                                System.out.println("-" + msg);
                            } else
                                run = false;
                        }
                        break;
                    case "LG":
                        while (run) {
                            String msg = in.readLine();
                            if (msg != "LG") {
                                System.out.println("-" + msg);
                            } else
                                run = false;
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {

        }

    }
}
