<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<h2>Find Drivers</h2>
	<c:url value="/rest/driver/find" var="action"/>
	<form:form action="${action}" method="POST" modelAttribute="entity">

	<div class="submit" id="seat_submit">
		<input id="proceed" type="submit" value="Save"/>
	</div>
	</form:form>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>