package eventBoard.servlet;

import eventBoard.model.Participant;
import eventBoard.repository.EventRepository;
import eventBoard.repository.ParticipantRepository;
import eventBoard.service.EventService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/event")
public class EventDetailsServlet
        extends HttpServlet {

    private EventService eventService;

    @Override
    public void init() {

        eventService =
                new EventService(
                        new EventRepository(),
                        new ParticipantRepository()
                );
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        int id =
                Integer.parseInt(
                        req.getParameter("id")
                );

        req.setAttribute(
                "event",
                eventService.getEvent(id)
        );

        req.setAttribute(
                "participants",
                eventService.getParticipants(id)
        );

        req.getRequestDispatcher(
                "/event.jsp"
        ).forward(req, resp);
    }
    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        int eventId =
                Integer.parseInt(
                        req.getParameter("eventId"));

        String name =
                req.getParameter("name");

        String email =
                req.getParameter("email");

        try {

            Participant participant =
                    new Participant();

            participant.setEventId(eventId);
            participant.setStudentName(name);
            participant.setStudentEmail(email);

            boolean success =
                    eventService.registerParticipant(
                            participant);

            if (!success) {
                throw new RuntimeException(
                        "No free seats available");
            }

            resp.sendRedirect(
                    "event?id=" + eventId);

        } catch (RuntimeException e) {

            req.setAttribute(
                    "error",
                    e.getMessage()
            );

            doGet(req, resp);
        }
    }
}