package pl.website.dao;

public abstract class DAOFactory {
    public abstract EntryDAO getEntryDAO();

    public abstract UserDAO getUserDAO();

    public abstract VoteDAO getVoteDAO();
}
