package com.miso.thegame.Networking;

/**
 * Created by Miso on 22.4.2016.
 */
public class PlayerClientPOJO {

    private String hostName;
    private String id;

    public PlayerClientPOJO(String hostName, String id) {
        this.hostName = hostName;
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public String getId() {
        return id;
    }

    public static PlayerClientPOJO getClientDataFromExtrasString(String playerData){
        return new PlayerClientPOJO(playerData.split("|")[1], playerData.split("|")[0]);
    }
    public String getStringForExtras(){
        return (this.id + "|" + this.hostName);
    }
}
