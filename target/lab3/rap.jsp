<%--
  Created by IntelliJ IDEA.
  User: ъуъ
  Date: 17.04.2021
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="width: 100%; height: auto; text-align: center; font-size: 25pt; margin: 2.5%">
    <b>
        Rap songs
    </b>
</div>

<div style="width: 100%; justify-content: center">
    <div style="margin-left: 1%; margin-right: 1%; text-align: center;">
        <div style="text-align: center">
            <table border="1" align="center" style=" background: #ecf8ff;">
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Id</th>
                    <th>Duration</th>
                    <th>Album</th>
                    <th>Key</th>
                    <th>BPM</th>
                    <th>Perf. race</th>
                    <th>Perf. alive?</th>
                    <th>Perf. death type</th>
                    <th></th>
                    <th><10</th>
                    <th>10-18</th>
                    <th>18-35</th>
                    <th>35-60</th>
                    <th>>60</th>
                    <th>Men</th>
                    <th>Women</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <c:forEach var="song" items="${songs}">
                    <tr>
                        <td><c:out value="${song.name}" /> </td>
                        <td><c:out value="${song.author}" /> </td>
                        <td><c:out value="${song.id}" /> </td>
                        <td><c:out value="${song.duration}" /> </td>
                        <td><c:out value="${song.albumName}" /> </td>
                        <td><c:out value="${song.key}" /> </td>
                        <td><c:out value="${song.bpm}" /></td>
                        <td><c:out value="${song.performerRace}" /></td>
                        <td><c:out value="${song.performerAlive}" /></td>
                        <td><c:out value="${song.performerDeathType}" /></td>
                        <td></td>
                        <td><c:out value="${song.listeningStats.childrenUnder10Count}" /> </td>
                        <td><c:out value="${song.listeningStats.teenagers10To18Count}" /> </td>
                        <td><c:out value="${song.listeningStats.youth18To35Count}" /> </td>
                        <td><c:out value="${song.listeningStats.people35To60Count}" /> </td>
                        <td><c:out value="${song.listeningStats.oldsAbove60Count}" /> </td>
                        <td><c:out value="${song.listeningStats.malesCount}" /> </td>
                        <td><c:out value="${song.listeningStats.femalesCount}" /> </td>
                        <td><a href="${pageContext.request.contextPath}/rap/edit?id=${song.id}">Edit</a> </td>
                        <td><a href="${pageContext.request.contextPath}/rap/delete?id=${song.id}">Delete</a> </td>
                    </tr>
                </c:forEach>
            </table>
            <a href="${pageContext.request.contextPath}/rap/rap_create.jsp">(+) New</a>
        </div>
    </div>
</div>
<a href="index.jsp">Return to index</a>
</body>
</html>