<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<h2>New Seat</h2>
	<c:url value="/rest/seat" var="action"/>
	<form:form action="${action}" method="POST" modelAttribute="entity">
	<div id="seat_seatNo">
		<label for="_seatNo">SeatNo:</label>
		<form:input cssStyle="width:300px" id="_seatNo" path="seatNo"/>
		<br/>
		<form:errors cssClass="errors" id="_seatNo" path="seatNo"/>
	</div>
	<div id="seat_reservationDate">
		<label for="_reservationDate">ReservationDate:</label>
		<form:input cssStyle="width:300px" id="_reservationDate" path="reservationDate"/>
		<br/>
		<form:errors cssClass="errors" id="_reservationDate" path="reservationDate"/>
	</div>
	<div id="seat_reservationKey">
		<label for="_reservationKey">ReservationKey:</label>
		<form:input cssStyle="width:300px" id="_reservationKey" path="reservationKey"/>
		<br/>
		<form:errors cssClass="errors" id="_reservationKey" path="reservationKey"/>
	</div>
	<div id="seat_destination">
       	<label for="_direction.destination">Destination:</label>
       	<form:input cssStyle="width:300px" id="_direction.destination" path="direction.destination"/>
       	<br/>
       	<form:errors cssClass="errors" id="_direction.destination" path="direction.destination"/>
    </div>
	<div class="submit" id="seat_submit">
		<input id="proceed" type="submit" value="Save"/>
	</div>
	</form:form>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
