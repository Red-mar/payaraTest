package model.logic;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "song.findOne", query = "select s from SongDO s where s.id = :id"),
        @NamedQuery(name = "song.getAll", query = "select s from SongDO s")
}
)
@Table(name="song")
public class SongDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String url;

    public SongDO(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public SongDO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}