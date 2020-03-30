package Server;

class TShirt {
    String groesse, farbe;
    double preis;

    public TShirt(String pGroesse, String pFarbe, double pPreis){
        groesse = pGroesse;
        farbe = pFarbe;
        preis = pPreis;
    }

    public void setGroesse(String pGroesse){
        groesse = pGroesse;
    }

    public void setFarbe(String pFarbe){
        farbe = pFarbe;
    }

    public void setPreis(double pPreis){
        preis = pPreis;
    }

    public String getGroesse(){
        return groesse;
    }

    public String getFarbe(){
        return farbe;
    }

    public double getPreis(){
        return preis;   
    }

}