package eventBoard.servlet;

import eventBoard.repository.EventRepository;
import eventBoard.repository.ParticipantRepository;
import eventBoard.service.EventService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/delete-event")
public class DeleteEventServlet extends HttpServlet {

    private final EventService eventService =
            new EventService(
                    new EventRepository(),
                    new ParticipantRepository()
            );

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        eventService.deleteEvent(id);

        response.sendRedirect("events");
    }

}