package pl.website.controller;

import pl.website.model.Entry;
import pl.website.service.EntryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("")
public class HomeCotroller extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        saveEntriesInRequest(request);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

    }

    private void saveEntriesInRequest(HttpServletRequest request) {
        EntryService entryService = new EntryService();
        List<Entry> allEntries = entryService.getAllEntries(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                int o1Vote = o1.getUpVote() - o1.getDownVote();
                int o2Vote = o2.getUpVote() - o2.getDownVote();
                if (o1Vote < o2Vote)
                    return 1;
                else if (o1Vote > o2Vote)
                    return -1;
                return 0;
            }
        });
        request.setAttribute("entries" , allEntries);
    }
}
