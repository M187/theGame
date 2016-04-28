package com.miso.thegame.Networking.client;

import android.os.AsyncTask;

import com.miso.thegame.Networking.PlayerClientPOJO;
import com.miso.thegame.Networking.transmitionData.TransmissionMessage;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by michal.hornak on 20.04.2016.
 * <p/>
 * Client class to communicate with other game instances.
 * Each registered server should have one instance ready to go.
 * When relevant event occurs, propagate it to every Client to send it.
 */
public class Client extends AsyncTask<TransmissionMessage, Void, Boolean>{

    private Socket myClient;
    private String hostName;
    private String nickname;
    private int portNumber;
    DataInputStream dataInputStream;
    String recievedFrameData;

    public Client(String hostName, int portNumber, String nickname) {
        this.nickname = nickname;
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    public boolean equals(Client client){
        return (this.hostName == client.hostName && this.nickname == client.nickname && this.portNumber == client.portNumber);
    }

    @Override
    public Boolean doInBackground(TransmissionMessage...a){
        try {
            if (this.myClient == null) {
                this.myClient = new Socket(this.hostName, this.portNumber);
                System.out.println(" --- > Connection to server established!");
            }
            sendDataToServer(a[0]);
            return true;
        } catch (IOException e) {
            try {
                this.myClient = new Socket(this.hostName, this.portNumber);
            } catch (IOException ex){return false;}
            sendDataToServer(a[0]);
            return true;
        }
    }

    public void getDataFromServer() {
        try {
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(this.myClient.getInputStream()));
            this.recievedFrameData = inFromServer.readLine();
        } catch (IOException e) {
            this.dataInputStream = null;
            System.out.println(e);
        }
    }

    /**
     * Sends data string message to defined server.
     * @param messageData to be sent.
     */
    public void sendDataToServer(TransmissionMessage messageData) {
        try {
            DataOutputStream output = new DataOutputStream(this.myClient.getOutputStream());
            output.writeUTF(messageData.getPacket());
            output.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void teardown(){
        try {
            this.myClient.close();
        } catch (Exception e){}
    }

    public PlayerClientPOJO getPlayerClientPojo(){
        return new PlayerClientPOJO(this.hostName, this.nickname);
    }
    public String getStringForExtras(){
        return (this.nickname + "|" + this.hostName + ":" + this.portNumber);
    }
}
