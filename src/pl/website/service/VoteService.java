package pl.website.service;

import pl.website.dao.DAOFactory;
import pl.website.dao.VoteDAO;
import pl.website.model.Vote;
import pl.website.model.VoteType;

import java.util.Date;
import java.sql.Timestamp;

public class VoteService {

    public Vote addVote(long entryId, long userId, VoteType voteType) {
        Vote vote = new Vote();
        vote.setEntryId(entryId);
        vote.setUserId(userId);
        vote.setDate(new Timestamp(new Date().getTime()));
        vote.setVoteType(voteType);
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = factory.getVoteDAO();
        voteDAO.create(vote);
        return vote;
    }

    public Vote updateVote(long entryId, long userId, VoteType voteType) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = factory.getVoteDAO();
        Vote voteToUpdate = voteDAO.getVoteByUserIdEntryId(entryId, userId);
        if (voteToUpdate != null){
            voteToUpdate.setVoteType(voteType);
            voteDAO.update(voteToUpdate);
        }
        return voteToUpdate;
    }

    public Vote addOrUpdateVote(long entryId, long userId, VoteType voteType) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = factory.getVoteDAO();
        Vote vote = voteDAO.getVoteByUserIdEntryId(entryId, userId);
        Vote resultVote = null;
        if (vote == null)
            resultVote = addVote(entryId, userId, voteType);
        else
            resultVote = updateVote(entryId, userId, voteType);
        return resultVote;
    }

    public Vote getVoteByEntryUserId(long entryId, long userId) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDAO = factory.getVoteDAO();
        Vote vote = voteDAO.getVoteByUserIdEntryId(entryId, userId);
        return vote;
    }
}
