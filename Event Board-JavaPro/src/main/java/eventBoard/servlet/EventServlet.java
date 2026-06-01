package eventBoard.servlet;

import eventBoard.model.Event;
import eventBoard.repository.EventRepository;
import eventBoard.repository.ParticipantRepository;
import eventBoard.service.EventService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;


@WebServlet("/events")
public class EventServlet extends HttpServlet {

    private EventService eventService;

    @Override
    public void init() {

        EventRepository eventRepository =
                new EventRepository();

        ParticipantRepository participantRepository =
                new ParticipantRepository();

        eventService =
                new EventService(
                        eventRepository,
                        participantRepository
                );
    }
    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws IOException {

        String title =
                req.getParameter("title");

        String date =
                req.getParameter("date");

        int maxSeats =
                Integer.parseInt(
                        req.getParameter("maxSeats"));

        Event event = new Event();

        event.setTitle(title);
        event.setEventDate(
                java.time.LocalDate.parse(date));
        event.setMaxSeats(maxSeats);

        new EventRepository().save(event);

        resp.sendRedirect("events");
    }
    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute(
                "events",
                eventService.getAllEvents()
        );

        req.setAttribute(
                "eventService",
                eventService
        );

        req.getRequestDispatcher(
                "/events.jsp"
        ).forward(req, resp);
    }

}