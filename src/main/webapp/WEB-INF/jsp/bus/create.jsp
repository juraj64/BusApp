<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<h2>New Bus</h2>
	<c:url value="/rest/bus" var="action"/>
	<form:form action="${action}" method="POST" modelAttribute="entity">
	<div id="bus_busNum">
		<label for="_busNum">BusNum:</label>
		<form:input cssStyle="width:300px" id="_busNum" path="busNum"/>
		<br/>
		<form:errors cssClass="errors" id="_busNum" path="busNum"/>
	</div>
	<div id="bus_busSpz">
		<label for="_busSpz">BusSpz:</label>
		<form:input cssStyle="width:300px" id="_busSpz" path="busSpz"/>
		<br/>
		<form:errors cssClass="errors" id="_busSpz" path="busSpz"/>
	</div>
	<div id="bus_numberOfSeats">
		<label for="_numberOfSeats">NumberOfSeats:</label>
		<form:input cssStyle="width:300px" id="_numberOfSeats" path="numberOfSeats"/>
		<br/>
		<form:errors cssClass="errors" id="_numberOfSeats" path="numberOfSeats"/>
	</div>
	<div class="submit" id="bus_submit">
		<input id="proceed" type="submit" value="Save"/>
	</div>
	</form:form>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
