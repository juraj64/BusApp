<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>

</div>
<div>
	<c:if test="${not empty result}">
	<table>
		<thead>
		<th>Id</th>
		<th>SeatNo</th>
		<th>ReservationDate</th>
		<th>ReservationKey</th>
		<th>Destination</th>
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
					${each.reservationDate}
				</td>
				<td>
					${each.reservationKey}
				</td>
				<td>
      				${each.direction.destination}
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

