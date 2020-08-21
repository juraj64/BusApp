<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<h2>MakeConnection</h2>
	<c:url value="/rest/busConnection/make" var="action"/>
	<form:form action="${action}" method="POST" modelAttribute="entity">
	<div id="busConnection_destination">
		<label for="_destination">Destination:</label>
		<form:input cssStyle="width:300px" id="_destination" path="destination"/>
		<br/>
		<form:errors cssClass="errors" id="_destination" path="destination"/>
	</div>
	<div id="busConnection_minSeats">
		<label for="_minSeats">MinSeats:</label>
		<form:input cssStyle="width:300px" id="_minSeats" path="minSeats"/>
		<br/>
		<form:errors cssClass="errors" id="_minSeats" path="minSeats"/>
    </div>
        <div id="busConnection_startHours">
        <label for="_startHours">StartHours:</label>
        <form:input cssStyle="width:300px" id="_startHours" path="startHours"/>
        <br/>
        <form:errors cssClass="errors" id="_startHours" path="startHours"/>
    </div>
	<div id="busConnection_startMinutes">
		<label for="_startMinutes">StartMinutes:</label>
		<form:input cssStyle="width:300px" id="_startMinutes" path="startMinutes"/>
		<br/>
		<form:errors cssClass="errors" id="_startMinutes" path="startMinutes"/>
	</div>
	<div id="busConnection_durationMinutes">
		<label for="_durationMinutes">DurationMinutes:</label>
		<form:input cssStyle="width:300px" id="_durationMinutes" path="durationMinutes"/>
		<br/>
		<form:errors cssClass="errors" id="_durationMinutes" path="durationMinutes"/>
	</div>

	<div class="submit" id="busConnection_submit">
		<input id="proceed" type="submit" value="Submit"/>
	</div>
	</form:form>
</div>

<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>