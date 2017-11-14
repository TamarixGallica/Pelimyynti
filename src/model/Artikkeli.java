package model;

public class Artikkeli {
    private int artikkeli_id;
    private String nimi, lisatiedot;
    private float pyyntihinta;
    private Alusta alusta;

    public Artikkeli() {
        super();
    }

    public Artikkeli(int artikkeli_id, String nimi, String lisatiedot, float pyyntihinta) {
        super();
        this.artikkeli_id = artikkeli_id;
        this.nimi = nimi;
        this.lisatiedot = lisatiedot;
        this.pyyntihinta = pyyntihinta;
        this.alusta = new Alusta();
    }

    public int getArtikkeli_id() {
        return artikkeli_id;
    }

    public void setArtikkeli_id(int artikkeli_id) {
        this.artikkeli_id = artikkeli_id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getLisatiedot() {
        return lisatiedot;
    }

    public void setLisatiedot(String lisatiedot) {
        this.lisatiedot = lisatiedot;
    }

    public float getPyyntihinta() {
        return pyyntihinta;
    }

    public void setPyyntihinta(float pyyntihinta) {
        this.pyyntihinta = pyyntihinta;
    }

    public void setPyyntihinta(String pyyntihinta) { this.pyyntihinta = Float.parseFloat(pyyntihinta);}

    public int getAlustaId() {
        return alusta.getAlusta_id();
    }

    public void setAlusta(int id) {
        this.alusta.setAlusta_id(id);
    }



    @Override
    public String toString() {
        return "Artikkeli [artikkeli_id=" + artikkeli_id + ", nimi=" + nimi
                + ", lisatiedot=" + lisatiedot + ", pyyntihinta=" + pyyntihinta + "]";
    }

}
