<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="eventBoard.model.Event" %>
<%@ page import="eventBoard.service.EventService" %>

<html>
<head>
    <title>Event Board</title>

    <style>
        body {
            <style>
                body { font-family: 'Inter', sans-serif; background: #f0f2f5; margin: 0; padding: 40px; color: #333; }
                h1 { color: #1a202c; text-align: center; }
                h2 { color: #4a5568; font-size: 1.25rem; }

                form { background: white; padding: 30px; border-radius: 16px; box-shadow: 0 4px 6px -1px rgba(0,0,0,0.1); max-width: 600px; margin: 20px auto; }
                input { width: 100%; padding: 12px; border: 1.5px solid #e2e8f0; border-radius: 8px; margin: 8px 0 16px 0; box-sizing: border-box; }

                button { background: #4f46e5; color: white; border: none; padding: 12px 24px; border-radius: 8px; cursor: pointer; font-weight: 600; width: 100%; }
                button:hover { background: #4338ca; }

                table { width: 100%; border-collapse: separate; border-spacing: 0; background: white; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 6px -1px rgba(0,0,0,0.1); margin-top: 20px; }
                th { background: #f8fafc; color: #64748b; padding: 16px; font-weight: 600; text-transform: uppercase; font-size: 0.8rem; }
                td { padding: 16px; border-bottom: 1px solid #f1f5f9; text-align: center; }

                .delete-btn { background: #fee2e2; color: #dc2626; padding: 6px 12px; border-radius: 6px; font-weight: 500; font-size: 0.9rem; }
                .delete-btn:hover { background: #fecaca; }
                a { color: #4f46e5; font-weight: 500; }
            </style>
    </style>

</head>
<body>

<h1>Event Board</h1>

<h2>Create Event</h2>

<form method="post" action="events">

    <p>
        Title:
        <input type="text" name="title" required>
    </p>

    <p>
        Date:
        <input type="date" name="date" required>
    </p>

    <p>
        Max Seats:
        <input type="number" name="maxSeats" required>
    </p>

    <button type="submit">
        Save Event
    </button>

</form>

<hr>

<h2>Events</h2>

<table>

    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Date</th>
        <th>Free Seats</th>
        <th>Details</th>
        <th>Delete</th>
    </tr>

    <%
        List<Event> events =
                (List<Event>) request.getAttribute("events");

        EventService eventService =
                (EventService) request.getAttribute("eventService");

        if (events != null && !events.isEmpty()) {

            for (Event e : events) {
    %>
    <%
        String error =
                (String) request.getAttribute("error");

        if(error != null){
    %>

    <p style="
color:red;
font-weight:bold;
padding:10px;
background:#ffe6e6;
border-radius:8px;
">
        <%= error %>
    </p>

    <%
        }
    %>

    <tr>

        <td><%= e.getId() %></td>

        <td><%= e.getTitle() %></td>

        <td><%= e.getEventDate() %></td>

        <td><%= eventService.getFreeSeats(e.getId()) %></td>

        <td>
            <a href="event?id=<%= e.getId() %>">
                Open
            </a>
        </td>

        <td>
            <a class="delete-btn"
               href="delete-event?id=<%= e.getId() %>"
               onclick="return confirm('Delete this event?')">
                Delete
            </a>
        </td>

    </tr>

    <%
        }
    } else {
    %>

    <tr>
        <td colspan="6">
            No events found
        </td>
    </tr>

    <%
        }
    %>

</table>

</body>
</html>