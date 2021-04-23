<%--
  Created by IntelliJ IDEA.
  User: ъуъ
  Date: 22.04.2021
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<div>
    <div>
        <form action="${pageContext.request.contextPath}/rock/add" method="post" style="width: 500px">
            <input type="hidden" name="id" value="${id}" />
            <div>
                <span>Title:</span>
                <input type="text" name="name"/>
            </div>
            <div>
                <span>Duration:</span>
                <input type="text" name="duration"/>
            </div>
            <div>
                <span>Album name:</span>
                <input type="text" name="albumName"/>
            </div>
            <div><span>Author:</span>
                <input type="text" name="author"/>
            </div>
            <div><span>Key:</span>
                <input type="text" name="key"/>
            </div>
            <div>
                <span>Subgenre:</span>
                <input type="text" name="subGenre"/>
            </div>
            <div>
                <span>Guitar effects:</span>
                <input type="text" name="guitarEffects"/>
            </div>
            <div>
                <span>Has extreme vocals?</span>
                <input type="checkbox" name="hasExtremeVocals"/>
            </div>
            <div>
                <span><10:</span>
                <input type="number" name="under10"/>
            </div>
            <div>
                <span>10-18:</span>
                <input type="number" name="10to18"/>
            </div>
            <div>
                <span>18-35:</span>
                <input type="number" name="18to35"/>
            </div>
            <div>
                <span>35-60:</span>
                <input type="number" name="35to60"/>
            </div>
            <div>
                <span>>60:</span>
                <input type="number" name="over60"/>
            </div>
            <div>
                <span>Men:</span>
                <input type="number" name="men"/>
            </div>
            <div>
                <span>Women:</span>
                <input type="number" name="women"/>
            </div>
            <input type="submit">
        </form>
    </div>
</div>
</body>
</html>
