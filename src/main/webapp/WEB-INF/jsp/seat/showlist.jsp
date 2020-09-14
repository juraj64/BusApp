<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
<font size="3" color='saddlebrown'>Seats</font>
<br />
<br />
<a href="<c:url value="/rest/seat/form/find" />">Find Seats</a>
<br />
<br />
<font size="2" color='saddlebrown'>To run reserveSeat method - use curl command, e.g.: </font>
<br />
<font>curl -i -H "Content-Type: application/json" -X POST -d "{\"direction\": {\"id\": 1243}, \"seatNo\": 3}" http://localhost:8888/rest/seat/reserve</font>
<br />
<br />
<font size="2" color='saddlebrown'>To run confirmSeat method - use curl command, e.g.:</font>
<br />
<font>curl -i -H "Content-Type: application/json" -X POST -d "{\"direction\": {\"id\": 1243}, \"seatNo\": 3, \"reservationKey\":\"72600855\"}" http://localhost:8888/rest/seat/confirm</font>

</div>
<div>
	<c:if test="${not empty result}">
	<table>
		<thead>
		<th>Id</th>
		<th>SeatNo</th>
		<th>SeatStatus</th>
		<th>ReservationDate</th>
		<th>ReservationKey</th>
		<th>Direction</th>
		<th/>
		<th/>
		</thead>
		<c:forEach items="${result}" var="each" >
			<tr>
				<td>
					<a href="<c:url value="/rest/seat/${each.id}" />">${each.id}</a>
				</td>
				<td>
					${each.seatNo}
				</td>
				<td>
    				${each.seatStatus}
    			</td>
				<td>
					${each.reservationDate}
				</td>
				<td>
					${each.reservationKey}
				</td>
				<td>
      				${each.direction.id}
    			</td>
				<td>
					<a href="<c:url value="/rest/seat/${each.id}" />">Show</a>
				</td>
				<td>
					<c:url value="/rest/seat/${each.id}" var="action"/>
					<form:form action="${action}" method="DELETE">
						<input id="delete" type="submit" value="Delete"/>
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${empty result}"><p>There are no Seats yet.</p></c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>

