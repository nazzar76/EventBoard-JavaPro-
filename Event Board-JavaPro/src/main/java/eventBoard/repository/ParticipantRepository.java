package eventBoard.repository;

import eventBoard.model.Participant;
import eventBoard.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ParticipantRepository {

    public List<Participant> findByEventId(int eventId) {

        List<Participant> participants =
                new ArrayList<>();

        String sql =
                "SELECT * FROM participants WHERE event_id=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, eventId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                participants.add(
                        new Participant(
                                rs.getInt("id"),
                                rs.getInt("event_id"),
                                rs.getString("student_name"),
                                rs.getString("student_email")
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return participants;
    }

    public int countByEventId(int eventId) {

        String sql =
                "SELECT COUNT(*) FROM participants WHERE event_id=?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, eventId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void save(Participant participant) {

        String sql =
                "INSERT INTO participants(event_id,student_name,student_email) VALUES(?,?,?)";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, participant.getEventId());
            stmt.setString(2, participant.getStudentName());
            stmt.setString(3, participant.getStudentEmail());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}