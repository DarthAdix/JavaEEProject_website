package pl.website.service;

import pl.website.dao.DAOFactory;
import pl.website.dao.EntryDAO;
import pl.website.model.Entry;
import pl.website.model.User;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class EntryService {

    public void addEntry(String name, String description, String url, User user) {
        Entry entry = createEntryObject(name, description, url, user);
        DAOFactory factory = DAOFactory.getDAOFactory();
        EntryDAO entryDAO = factory.getEntryDAO();
        entryDAO.create(entry);
    }

    private Entry createEntryObject(String name, String description, String url, User user) {
        Entry entry = new Entry();
        entry.setName(name);
        entry.setDescription(description);
        entry.setUrl(url);
        User userCopy = new User(user);
        entry.setUser(userCopy);
        entry.setTimestamp(new Timestamp(new Date().getTime()));
        return entry;
    }

    public Entry getEntryById(long entryId) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        EntryDAO entryDAO = factory.getEntryDAO();
        Entry entry = entryDAO.read(entryId);
        return entry;
    }

    public boolean updateEntry(Entry entry) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        EntryDAO entryDAO = factory.getEntryDAO();
        boolean result = entryDAO.update(entry);
        return result;
    }

    public List<Entry> getAllEntries() {
        return getAllEntries(null);
    }

    public List<Entry> getAllEntries(Comparator<Entry> comparator) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        EntryDAO entryDAO = factory.getEntryDAO();
        List<Entry> entries = entryDAO.getAll();
        if (comparator != null && entries != null) {
            entries.sort(comparator);
        }
        return entries;
    }

}
