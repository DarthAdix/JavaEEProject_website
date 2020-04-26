package pl.website.dao;

public class MySqlDAOFactory extends DAOFactory {

    @Override
    public EntryDAO getEntryDAO() {
        return new EntryDAOImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public VoteDAO getVoteDAO() {
        return new VoteDAOImpl();
    }
}
