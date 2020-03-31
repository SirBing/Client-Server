class ChatClient extends Client {

    public ChatClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
    }

    @Override
    public void processMessage(String pMessage) {
        System.out.println(pMessage);
    }

    public void anmelden(String benutzername, String passwort){
        send("ANMELDUNG:" + benutzername + ":" + passwort);
    }

    public void broadcaste(String nachricht){
        send("BROADCAST:" + nachricht);
    }

    public void logout(){
        send("LOGOUT");
    }

    public static void main(String[] args) throws InterruptedException {
        ChatClient client = new ChatClient("192.168.178.51", 8);
        Thread.sleep(4000);
        client.anmelden("debug000", "0");
        Thread.sleep(4000);
        client.broadcaste("Hallo!");
        Thread.sleep(4000);
        client.logout();
    }



}