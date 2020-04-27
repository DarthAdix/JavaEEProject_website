package pl.website.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Vote {
    private long id;
    private long entryId;
    private long userId;
    private Timestamp date;
    private VoteType voteType;

    public Vote() {
    }

    public Vote(Vote vote) {
        this.id = vote.id;
        this.entryId = vote.entryId;
        this.userId = vote.userId;
        this.date = new Timestamp(vote.date.getTime());
        this.voteType = vote.voteType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", entryId=" + entryId +
                ", userId=" + userId +
                ", date=" + date +
                ", voteType=" + voteType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        if (date == null){
            if (vote.date != null)
                return false;
        } else if (!date.equals(vote.date))
            return false;
        if (entryId != vote.entryId)
            return false;
        if (id != vote.id)
            return false;
        if (userId != vote.userId)
            return false;
        if (voteType != vote.voteType)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entryId, userId, date, voteType);
    }
}
