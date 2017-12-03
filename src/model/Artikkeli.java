package model;

public class Artikkeli {
    private int Artikkeli_id;
    private String Nimi, Lisatiedot;
    private float Pyyntihinta;
    private Alusta Alusta;

    public Artikkeli() {
        super();
    }

    public Artikkeli(int artikkeli_id, String nimi, String lisatiedot, float pyyntihinta) {
        super();
        this.Artikkeli_id = artikkeli_id;
        this.Nimi = nimi;
        this.Lisatiedot = lisatiedot;
        this.Pyyntihinta = pyyntihinta;
        this.Alusta = new Alusta();
    }

    public int getArtikkeli_id() {
        return Artikkeli_id;
    }

    public void setArtikkeli_id(int artikkeli_id) {
        this.Artikkeli_id = artikkeli_id;
    }

    public String getNimi() {
        return Nimi;
    }

    public void setNimi(String nimi) {
        this.Nimi = nimi;
    }

    public String getLisatiedot() {
        return Lisatiedot;
    }

    public void setLisatiedot(String lisatiedot) {
        this.Lisatiedot = lisatiedot;
    }

    public float getPyyntihinta() {
        return Pyyntihinta;
    }

    public void setPyyntihinta(float pyyntihinta) {
        this.Pyyntihinta = pyyntihinta;
    }

    public void setPyyntihinta(String pyyntihinta) { this.Pyyntihinta = Float.parseFloat(pyyntihinta);}

    public Alusta getAlusta() {
        return this.Alusta;
    }

    public void setAlusta(Alusta alusta) {
        this.Alusta = alusta;
    }



    @Override
    public String toString() {
        return "Artikkeli [artikkeli_id=" + Artikkeli_id + ", Nimi=" + Nimi
                + ", Lisatiedot=" + Lisatiedot + ", Pyyntihinta=" + Pyyntihinta + "]";
    }

}
