class ChatServer extends Server{

    private List<String> angemeldeteNutzer;
    private List<String> loginDaten;

    public ChatServer(int pPort){
        super(pPort);
        loginDaten = new List<String>();
        angemeldeteNutzer = new List<String>();
        loginDaten.append("debug000:0");
        loginDaten.append("debug001:1");
        loginDaten.append("debug002:2");
        loginDaten.append("debug003:3");
        loginDaten.append("debug004:4");

    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        send(pClientIP, pClientPort, "Willkommen auf dem Chatserver! Bitte melden Sie sich an.");

    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        String nachrichtteil[] = pMessage.split(":");
        switch (nachrichtteil[0]) {
            case "ANMELDUNG":
                if(this.pruefeAnmeldung(nachrichtteil[1], nachrichtteil[2])){
                    send(pClientIP, pClientPort, "JA:Der Login war erfolgreich. Sie können jetzt chatten");
                    angemeldeteNutzer.append(pClientIP);
                } else {
                    send(pClientIP, pClientPort, "NEIN:Der Benutzername oder das Passwort war falsch.");
                }
                break;
            case "BROADCAST":
                //Überprüfe ob der Nutzer angemeldet ist
                boolean angemeldet = false;
                angemeldeteNutzer.toFirst();
                while(angemeldeteNutzer.hasAccess()){
                    if(angemeldeteNutzer.getContent().equals(pClientIP)){
                        angemeldet = true;
                        break;
                    } else angemeldeteNutzer.next();
                }
                //Wenn Nutzer angemeldet ist, broadcaste die Nachricht
                if(angemeldet){
                    sendToAll(nachrichtteil[1]);
                } else send(pClientIP, pClientPort, "Du musst dich zuerst anmelden, bevor du chatten kannst.");
                break;
            case "LOGOUT":
                //Melde den Nutzer ab und trenne die Verbindung
                while(angemeldeteNutzer.hasAccess()){
                    if(angemeldeteNutzer.getContent().equals(pClientIP)){
                        angemeldeteNutzer.remove();
                        break;
                    } else angemeldeteNutzer.next();
                }
                closeConnection(pClientIP, pClientPort);
                break;
        }

    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {

    }

    private boolean pruefeAnmeldung(String benutzername, String passwort){
        boolean gueltig = false;
        loginDaten.toFirst();
        while(loginDaten.hasAccess()){
            String[] login = loginDaten.getContent().split(":");
            //login[0] = benutzername und login[1] = passwort
            if(login[0].equals(benutzername) && login[1].equals(passwort)){
                gueltig = true;
                break;
            } else loginDaten.next();
        }
        return gueltig;
    }

    public static void main(String[] args) {
        new ChatServer(8);
    }

}