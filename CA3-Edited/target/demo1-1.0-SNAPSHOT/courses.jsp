<%--
  Created by IntelliJ IDEA.
  User: ghazal
  Date: 16.03.21
  Time: 01:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Bolbolestan.Bolbolestan" %>
<%@ page import="Bolbolestan.Student.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Courses</title>
    <style>
        .course_table {
            width: 100%;
            text-align: center;
        }
        .search_form {
            text-align: center;
        }
    </style>
</head>
<body>

<%
    Bolbolestan bolbolestan = Bolbolestan.getInstance();
    Student student = bolbolestan.getStudentById(bolbolestan.getLoggedInId());
%>

<a href="/">Home</a>
<li id="code">Student Id: <%=student.getId()%></li>
<li id="units">Total Selected Units: 16</li>

<br>

<table>
    <tr>
        <th>Code</th>
        <th>Class Code</th>
        <th>Name</th>
        <th>Units</th>
        <th></th>
    </tr>
    <tr>
        <td>8101001</td>
        <td>01</td>
        <td>Advanced Programming</td>
        <td>3</td>
        <td>
            <form action="" method="POST" >
                <input id="form_action" type="hidden" name="action" value="remove">
                <input id="form_course_code" type="hidden" name="course_code" value="8101001">
                <input id="form_class_code" type="hidden" name="class_code" value="01">
                <button type="submit">Remove</button>
            </form>
        </td>
    </tr>
    <tr>
        <td>8101033</td>
        <td>01</td>
        <td>Islamic Thought 1</td>
        <td>2</td>
        <td>
            <form action="" method="POST" >
                <input id="form_action" type="hidden" name="action" value="remove">
                <input id="form_course_code" type="hidden" name="course_code" value="8101033">
                <input id="form_class_code" type="hidden" name="class_code" value="01">
                <button type="submit">Remove</button>
            </form>
        </td>
    </tr>
</table>

<br>

<form action="" method="POST">
    <button type="submit" name="action" value="submit">Submit Plan</button>
    <button type="submit" name="action" value="reset">Reset</button>
</form>

</body>
</html>
