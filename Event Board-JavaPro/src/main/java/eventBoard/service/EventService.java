package eventBoard.service;

import eventBoard.model.Event;
import eventBoard.model.Participant;
import eventBoard.repository.EventRepository;
import eventBoard.repository.ParticipantRepository;

import java.util.List;

public class EventService {

    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;

    public EventService(
            EventRepository eventRepository,
            ParticipantRepository participantRepository) {

        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEvent(int id) {
        return eventRepository.findById(id);
    }

    public List<Participant> getParticipants(int eventId) {
        return participantRepository.findByEventId(eventId);
    }

    public int getFreeSeats(int eventId) {

        Event event =
                eventRepository.findById(eventId);

        int registered =
                participantRepository.countByEventId(eventId);

        return event.getMaxSeats() - registered;
    }

    public boolean registerParticipant(
            Participant participant) {

        int freeSeats =
                getFreeSeats(participant.getEventId());

        if (freeSeats <= 0) {
            return false;
        }

        participantRepository.save(participant);
        return true;
    }
    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }

}