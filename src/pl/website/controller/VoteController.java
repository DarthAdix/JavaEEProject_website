package pl.website.controller;

import pl.website.model.Entry;
import pl.website.model.User;
import pl.website.model.Vote;
import pl.website.model.VoteType;
import pl.website.service.EntryService;
import pl.website.service.VoteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vote")
public class VoteController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User loggedUser = (User) request.getSession().getAttribute("user");
        if (loggedUser != null) {
            VoteType voteType = VoteType.valueOf(request.getParameter("vote"));
            long userId = loggedUser.getId();
            long entryId = Long.parseLong(request.getParameter("entry_id"));
            updateVote(userId, entryId, voteType);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }

    private void updateVote(long userId, long entryId, VoteType voteType) {
        VoteService voteService = new VoteService();
        Vote existingVote = voteService.getVoteByEntryUserId(entryId, userId);
        Vote updatedVote = voteService.addOrUpdateVote(entryId, userId, voteType);
        if (existingVote != null){
            if (existingVote.getVoteType() != updatedVote.getVoteType())
                updateEntry(entryId, existingVote, updatedVote);
        } else {
            updateEntry(entryId, existingVote, updatedVote);
        }
    }

    private void updateEntry(long entryId, Vote oldVote, Vote newVote) {
        EntryService entryService = new EntryService();
        Entry entryById = entryService.getEntryById(entryId);
        Entry updatedEntry = null;
        if (oldVote == null && newVote != null)
            updatedEntry = addEntryVote(entryById, newVote.getVoteType());
        else if (oldVote != null && newVote != null){
            updatedEntry = removeEntryVote(entryById, oldVote.getVoteType());
            updatedEntry = addEntryVote(updatedEntry, newVote.getVoteType());
        }
        entryService.updateEntry(updatedEntry);
    }

    private Entry removeEntryVote(Entry entry, VoteType voteType) {
        Entry entryCopy = new Entry(entry);
        if (voteType == VoteType.VOTE_UP){
            entryCopy.setUpVote(entryCopy.getUpVote() - 1);
        } else if (voteType == VoteType.VOTE_DOWN){
            entryCopy.setDownVote(entryCopy.getDownVote() - 1);
        }
        return entryCopy;
    }

    private Entry addEntryVote(Entry entry, VoteType voteType) {
        Entry entryCopy = new Entry(entry);
        if (voteType == VoteType.VOTE_UP)
            entryCopy.setUpVote(entryCopy.getUpVote() + 1);
        else if (voteType == VoteType.VOTE_DOWN)
            entryCopy.setDownVote(entryCopy.getDownVote() + 1);
        return entryCopy;
    }
}
