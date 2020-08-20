<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<c:if test="${not empty result}">
		<div id="seat_id">
    		<label for="_id">Id:</label>
    		<div class="box" id="_id">${result.id}</div>
        </div>
        <br/>
		<div id="seat_seatNo">
			<label for="_seatNo">SeatNo:</label>
			<div class="box" id="_seatNo">${result.seatNo}</div>
		</div>
		<br/>
		<div id="seat_seatStatus">
    		<label for="_seatStatus">SeatStatus:</label>
     		<div class="box" id="_seatStatus">${result.seatStatus}</div>
     	</div>
     	<br/>
		<div id="seat_reservationDate">
			<label for="_reservationDate">ReservationDate:</label>
			<div class="box" id="_reservationDate">${result.reservationDate}</div>
		</div>
		<br/>
		<div id="seat_reservationKey">
			<label for="_reservationKey">ReservationKey:</label>
			<div class="box" id="_reservationKey">${result.reservationKey}</div>
		</div>
		<br/>
		<div id="seat_id">
     		<label for="_direction.id">Direction:</label>
     		<div class="box" id="_direction.id">${result.direction.id}</div>
       		</div>
		<br/>
	</c:if>
	<c:if test="${empty result}">No Seat found with this id.</c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
