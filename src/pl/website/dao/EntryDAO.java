package pl.website.dao;

import pl.website.model.Entry;

import java.util.List;

public interface EntryDAO extends GenericDAO<Entry, Long> {

    List<Entry> getAll();
}
