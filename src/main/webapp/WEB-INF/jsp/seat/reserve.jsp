<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<h2>ReserveSeat</h2>
	<c:url value="/rest/seat/reserve" var="action"/>
	<form:form action="${action}" method="POST" modelAttribute="entity">

	<div id="direction">
   		<label for="_busConnection.destination">Direction:</label>
   		<form:input cssStyle="width:300px" id="_busConnection.destination" path="busConnection.destination"/>
   		<br/>
   		<form:errors cssClass="errors" id="_busConnection.destination" path="busConnection.destination"/>
   	</div>

	<div id="seat_seatNo">
		<label for="_seatNo">SeatNo:</label>
		<form:input cssStyle="width:300px" id="_seatNo" path="seatNo"/>
		<br/>
		<form:errors cssClass="errors" id="_seatNo" path="seatNo"/>
	</div>

	<div class="submit" id="seat_submit">
		<input id="proceed" type="submit" value="Save"/>
	</div>
	</form:form>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>