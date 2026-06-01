package eventBoard.repository;

import eventBoard.model.Event;
import eventBoard.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    public List<Event> findAll() {

        List<Event> events = new ArrayList<>();

        String sql =
                "SELECT * FROM events ORDER BY event_date";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                Event event = new Event();

                event.setId(rs.getInt("id"));
                event.setTitle(rs.getString("title"));
                event.setEventDate(
                        rs.getDate("event_date")
                                .toLocalDate()
                );
                event.setMaxSeats(
                        rs.getInt("max_seats")
                );

                events.add(event);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return events;
    }

    public Event findById(int id) {

        String sql =
                "SELECT * FROM events WHERE id=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Event event = new Event();

                event.setId(rs.getInt("id"));
                event.setTitle(rs.getString("title"));
                event.setEventDate(
                        rs.getDate("event_date")
                                .toLocalDate()
                );
                event.setMaxSeats(
                        rs.getInt("max_seats")
                );

                return event;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save(Event event) {

        String sql =
                "INSERT INTO events(title,event_date,max_seats) VALUES(?,?,?)";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            System.out.println("SAVE START");

            stmt.setString(
                    1,
                    event.getTitle()
            );

            stmt.setDate(
                    2,
                    Date.valueOf(
                            event.getEventDate()
                    )
            );

            stmt.setInt(
                    3,
                    event.getMaxSeats()
            );

            int rows = stmt.executeUpdate();

            System.out.println(
                    "ROWS INSERTED = " + rows
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteById(int id) {
        String sql = "DELETE FROM events WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}