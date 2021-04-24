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
        Opera songs
    </b>
</div>

<div style="width: 100%; justify-content: center">
    <div style="margin-left: 1%; margin-right: 1%; text-align: center;">
        <div style="text-align: center">
            <table border="1" align="center" style=" background: #e2ffd9" >
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Id</th>
                    <th>Duration</th>
                    <th>Album</th>
                    <th>Key</th>
                    <th></th>
                    <th>Century</th>
                    <th>Orch count</th>
                    <th>Voice type</th>
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
                        <td><c:out value="${song.releaseCentury}" /></td>
                        <td><c:out value="${song.orchestraCount}" /></td>
                        <td><c:out value="${song.vocalVoiceTypeRequired}" /></td>
                        <td></td>
                        <td><c:out value="${song.listeningStats.childrenUnder10Count}" /> </td>
                        <td><c:out value="${song.listeningStats.teenagers10To18Count}" /> </td>
                        <td><c:out value="${song.listeningStats.youth18To35Count}" /> </td>
                        <td><c:out value="${song.listeningStats.people35To60Count}" /> </td>
                        <td><c:out value="${song.listeningStats.oldsAbove60Count}" /> </td>
                        <td><c:out value="${song.listeningStats.malesCount}" /> </td>
                        <td><c:out value="${song.listeningStats.femalesCount}" /> </td>
                        <td><a href="${pageContext.request.contextPath}/opera/edit?id=${song.id}">Edit</a> </td>
                        <td><a href="${pageContext.request.contextPath}/opera/delete?id=${song.id}">Delete</a> </td>
                    </tr>
                </c:forEach>
            </table>
            <a href="${pageContext.request.contextPath}/opera/opera_create.jsp">(+) New</a>
        </div>
    </div>
</div>
<a href="index.jsp">Return to index</a>
</body>
</html>
