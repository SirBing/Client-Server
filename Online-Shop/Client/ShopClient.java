package Client;

class ShopClient extends Client {

    public ShopClient(String pServerIP, int pServerPort){
        super(pServerIP, pServerPort);
    }

    public void processMessage(String pMessage){
        System.out.println("SERVER: " + pMessage);
    }

    public void groesseFarbeWaehlen(String pGroesse, String pFarbe){
        this.send("TSHIRT: " + pGroesse + ":" + pFarbe);
    }

    public void bestaetigen(String pAntwort){
        this.send("BESTAETIGUNG: " + pAntwort);
    }

    public void abmelden(){
        this.send("ABMELDEN");
    }



}