package model;

public class Alusta {
    private int alusta_id;
    private String nimi;

    public Alusta()
    {
        super();
    }

    public Alusta(int alusta_id, String nimi) {
        super();
        this.alusta_id = alusta_id;
        this.nimi = nimi;
    }

    public int getAlusta_id() {
        return alusta_id;
    }

    public void setAlusta_id(int alusta_id) {
        this.alusta_id = alusta_id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    @Override
    public String toString() {
        return "Alusta [alusta_id=" + alusta_id + ", nimi=" + nimi + "]";
    }
}
