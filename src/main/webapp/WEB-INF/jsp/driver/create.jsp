<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<h2>New Driver</h2>
	<c:url value="/rest/driver" var="action"/>
	<form:form action="${action}" method="POST" modelAttribute="entity">
	<div id="driver_name">
		<label for="_name">Name:</label>
		<form:input cssStyle="width:300px" id="_name" path="name"/>
		<br/>
		<form:errors cssClass="errors" id="_name" path="name"/>
	</div>
	<div id="driver_age">
		<label for="_age">Age:</label>
		<form:input cssStyle="width:300px" id="_age" path="age"/>
		<br/>
		<form:errors cssClass="errors" id="_age" path="age"/>
	</div>
	<div class="submit" id="driver_submit">
		<input id="proceed" type="submit" value="Save"/>
	</div>
	</form:form>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
