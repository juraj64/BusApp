<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<h2>Find Seats</h2>
	<c:url value="/rest/seat/find" var="action"/>
	<form:form action="${action}" method="POST" modelAttribute="entity">

	<div id="seat_seatNo">
		<label for="_seatNo">SeatNo:</label>
		<form:input cssStyle="width:300px" id="_seatNo" path="seatNo"/>
		<br/>
		<form:errors cssClass="errors" id="_seatNo" path="seatNo"/>
	</div>

	<div id="seat_reservationKey">
    		<label for="_reservationKey">ReservationKey:</label>
    		<form:input cssStyle="width:300px" id="_reservationKey" path="reservationKey"/>
    		<br/>
    		<form:errors cssClass="errors" id="_reservationKey" path="reservationKey"/>
    	</div>

	<div class="submit" id="seat_submit">
		<input id="proceed" type="submit" value="Save"/>
	</div>
	</form:form>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>