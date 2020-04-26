package pl.website.dao;

import pl.website.model.Vote;

public interface VoteDAO extends GenericDAO<Vote, Long> {

    public Vote getVoteByUserIdEntryId(long userId, long entryId);
}
