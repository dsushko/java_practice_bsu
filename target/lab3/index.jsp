<%@ page isELIgnored="false"%>
<html>
<body style="font-family: 'Roboto Light'">
    <div style="width: 100%; height: auto; text-align: center; font-size: 25pt; margin: 2.5%">
        <b>
            What music do you prefer?
        </b>
        <div>
            We can offer 3 types:
        </div>
    </div>
    <div style="display: flex; justify-content: center; font-size: 25pt">
        <div style="width: 30%; margin-left: 1%; margin-right: 1%; text-align: center;">
            <div style=" background: indianred; padding: 5px">
                <a href="${pageContext.request.contextPath}/rock"> Rock</a>
            </div>
            <div style="background: #ffdfde; width: 100%;font-size: 18pt; ">
                Certain definition and description of this genre does not exist, but everyone knows what kind of music we're
                talking about. Though it's not necessary to feel some powerful energy that is emitted through such song, so that's
                the proof that this kind of music is truly wide.
            </div>
        </div>
        <div style="width: 30%; margin-left: 1%; margin-right: 1%; text-align: center">
            <div style="background: lightskyblue; padding: 5px">
                <a href="${pageContext.request.contextPath}/rap"> Rap</a>
            </div>
            <div style="background: #ecf8ff; width: 100%;font-size: 18pt;">
                Popular music with beat as its main part. It may appear simple, but interesting and melodic simultaneously.
                Usually, every second tact is accented by clap - single sound same to sound of handclap, snare - sound of
                leading drum, clear and short.
            </div>
        </div>
        <div style="width: 30%; margin-left: 1%; margin-right: 1%; text-align: center">
            <div style="background: lightgreen; padding: 5px">
                <a href="${pageContext.request.contextPath}/opera">Opera </a>
            </div>
            <div style="background: #e2ffd9; width: 100%;font-size: 18pt;">
                Classical stuff that uses and requires academical vocals. It's made up from different instrumental parts that
                make up thorough arrangement together.
            </div>
        </div>
    </div>
</body>
</html>
