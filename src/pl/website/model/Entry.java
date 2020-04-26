package pl.website.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Entry {
    private long id;
    private String name;
    private String description;
    private String url;
    private Timestamp timestamp;
    private User user;
    private int upVote;
    private int downVote;

    public Entry() {
    }

    public Entry(long id, String name, String description, String url, Timestamp timestamp, User user, int upVote, int downVote) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.timestamp = timestamp;
        this.user = user;
        this.upVote = upVote;
        this.downVote = downVote;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", upVote=" + upVote +
                ", downVote=" + downVote +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return id == entry.id &&
                upVote == entry.upVote &&
                downVote == entry.downVote &&
                Objects.equals(name, entry.name) &&
                Objects.equals(description, entry.description) &&
                Objects.equals(url, entry.url) &&
                Objects.equals(timestamp, entry.timestamp) &&
                Objects.equals(user, entry.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, url, timestamp, user, upVote, downVote);
    }
}
