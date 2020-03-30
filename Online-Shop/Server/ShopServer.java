package Server;

class ShopServer extends Server {

    List<Bestellung> bestellungen;

    public ShopServer(int pPortnummer){
        super(pPortnummer);
    }

    public void processNewConnection(String pClientIP, int pClientPort){
        this.send(pClientIP, pClientPort, "Willkommen! Wählen Sie eine Größe und eine Farbe für Ihr T-Shirt.");
        System.out.println(pClientIP + " " + pClientPort + " hat sich verbunden.");
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage){
        System.out.println("CLIENT" + pMessage);
        String[] nachrichtTeil = pMessage.split(":");
        if(nachrichtTeil[0].equals("TSHIRT")){
            this.send(pClientIP, pClientPort, "Die Größe ist" + nachrichtTeil[1] + " , die Farbe ist " + nachrichtTeil[2] + " und es kostet 19,99€! Bitte bestätigen Sie die Bestellung!");
        } else if(nachrichtTeil[0].equals("BESTAETIGUNG")){
            if(nachrichtTeil[1].equals("ja")){
                this.send(pClientIP, pClientPort, "Vielen Dank für Ihre Bestellung.");
                closeConnection(pClientIP, pClientPort);
            } else if(nachrichtTeil[1].equals("nein")){
                closeConnection(pClientIP, pClientPort);
            } else {
                this.send(pClientIP, pClientPort, "Bitte geben sie ja oder nein ein.");
            }
        } else if(nachrichtTeil[0].equals("ABMELDEN")){
            closeConnection(pClientIP, pClientPort);
        } else {
            this.send(pClientIP, pClientPort, "Bitte korrigieren Sie Ihre Eingabe.");
        }
    }

    public void processClosingConnection(String pClientIP, int pClientPort){

    }

    public static void main(String[] args) {
        new ShopServer(7);
    }

}