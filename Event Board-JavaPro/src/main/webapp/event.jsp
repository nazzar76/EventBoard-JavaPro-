<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="eventBoard.model.Event" %>
<%@ page import="eventBoard.model.Participant" %>

<%
  Event event =
          (Event) request.getAttribute("event");
%>

<html>
<head>
  <title>Event Details</title>
</head>
<body>

<h1><%= event.getTitle() %></h1>

<p>
  <b>Date:</b>
  <%= event.getEventDate() %>
</p>

<hr>

<h2>Participants</h2>

<ul>

  <%
    List<Participant> participants =
            (List<Participant>)
                    request.getAttribute("participants");

    if (participants != null) {

      for (Participant p : participants) {
  %>

  <li>
    <%= p.getStudentName() %>
    -
    <%= p.getStudentEmail() %>
  </li>

  <%
      }
    }
  %>

</ul>

<hr>

<h2>Register for Event</h2>

<form method="post" action="event">

  <input type="hidden"
         name="eventId"
         value="<%= event.getId() %>">

  <label>Name:</label>
  <input type="text"
         name="name"
         required>

  <br><br>

  <label>Email:</label>
  <input type="email"
         name="email"
         required>

  <br><br>

  <button type="submit">
    Register
  </button>

</form>

<br>

<a href="events">
  Back to Events
</a>
<style>
  body{
    body { font-family: 'Inter', system-ui, sans-serif; background: #f0f2f5; margin: 0; padding: 40px; color: #1f2937; }
            .container { max-width: 600px; margin: auto; }
            .card { background: white; padding: 32px; border-radius: 20px; box-shadow: 0 4px 6px -1px rgba(0,0,0,0.1); margin-bottom: 24px; }

            h1 { margin: 0 0 8px 0; color: #111827; }
            .event-date { color: #6b7280; font-weight: 500; margin-bottom: 24px; display: block; }

            h2 { font-size: 1.25rem; margin-top: 0; }
            ul { list-style: none; padding: 0; }
            li { background: #f9fafb; margin: 8px 0; padding: 12px 16px; border-radius: 8px; border-left: 4px solid #4f46e5; }

            input { width: 100%; padding: 12px; border: 1.5px solid #e5e7eb; border-radius: 8px; margin: 8px 0 16px 0; box-sizing: border-box; }
            button { background: #4f46e5; color: white; border: none; padding: 12px; border-radius: 8px; width: 100%; font-weight: 600; cursor: pointer; transition: background 0.2s; }
            button:hover { background: #4338ca; }

            .back-link { display: inline-block; margin-top: 16px; color: #6b7280; text-decoration: none; font-size: 0.9rem; }
            .back-link:hover { color: #111827; }
</style>

</body>
</html>