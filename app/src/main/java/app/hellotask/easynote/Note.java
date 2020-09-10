package app.hellotask.easynote;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private boolean favorite;

    private long date;


    public Note(String title, String description, boolean favorite, long date) {
        this.title = title;
        this.description = description;
        this.favorite = favorite;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public long getDate() {
        return date;
    }
}