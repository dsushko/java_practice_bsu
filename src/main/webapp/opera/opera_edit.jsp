<%--
  Created by IntelliJ IDEA.
  User: ъуъ
  Date: 21.04.2021
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<div>
    <div>
        <form action="${pageContext.request.contextPath}/opera/post" method="post" style="width: 500px">
            <input type="hidden" name="id" value="${id}" />
            <div>
                <span>Title:</span>
                <input type="text" value="${song.name}" name="name"/>
            </div>
            <div>
                <span>Duration:</span>
                <input type="text" value="${song.duration}" name="duration"/>
            </div>
            <div>
                <span>Album name:</span>
                <input type="text" value="${song.albumName}" name="albumName"/>
            </div>
            <div><span>Author:</span>
                <input type="text" value="${song.author}" name="author"/>
            </div>
            <div><span>Key:</span>
                <input type="text" value="${song.key}" name="key"/>
            </div>
            <div>
                <span>Release century:</span>
                <input type="number" value="${song.releaseCentury}" name="releaseCentury"/>
            </div>
            <div>
                <span>Orchestra count:</span>
                <input type="number" value="${song.orchestraCount}" name="orchestraCount"/>
            </div>
            <div>
                <span>Voice type required</span>
                <input type="text" value="${song.vocalVoiceTypeRequired}" name="vocalVoiceTypeRequired"/>
            </div>
            <div>
                <span><10:</span>
                <input type="number" value="${song.listeningStats.childrenUnder10Count}" name="under10"/>
            </div>
            <div>
                <span>10-18:</span>
                <input type="number" value="${song.listeningStats.teenagers10To18Count}" name="10to18"/>
            </div>
            <div>
                <span>18-35:</span>
                <input type="number" value="${song.listeningStats.youth18To35Count}" name="18to35"/>
            </div>
            <div>
                <span>35-60:</span>
                <input type="number" value="${song.listeningStats.people35To60Count}" name="35to60"/>
            </div>
            <div>
                <span>>60:</span>
                <input type="number" value="${song.listeningStats.oldsAbove60Count}" name="over60"/>
            </div>
            <div>
                <span>Men:</span>
                <input type="number" value="${song.listeningStats.malesCount}" name="men"/>
            </div>
            <div>
                <span>Women:</span>
                <input type="number" value="${song.listeningStats.femalesCount}" name="women"/>
            </div>
            <input type="submit">
        </form>
    </div>
</div>
</body>
</html>
