package model;

public class Alusta {
    private int Alusta_id;
    private String Nimi;

    public Alusta()
    {
        super();
    }

    public Alusta(int alusta_id, String nimi) {
        super();
        this.Alusta_id = alusta_id;
        this.Nimi = nimi;
    }

    public int getAlusta_id() {
        return Alusta_id;
    }

    public void setAlusta_id(int alusta_id) {
        this.Alusta_id = alusta_id;
    }

    public String getNimi() {
        return Nimi;
    }

    public void setNimi(String nimi) {
        this.Nimi = nimi;
    }

    @Override
    public String toString() {
        return "Alusta [Alusta_id=" + Alusta_id + ", Nimi=" + Nimi + "]";
    }
}
