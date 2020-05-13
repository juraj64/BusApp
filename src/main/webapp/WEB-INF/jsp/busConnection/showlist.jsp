<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
<a href="<c:url value="/rest/busConnection/form" />">New BusConnection</a>
</div>
<div>
	<c:if test="${not empty result}">
	<table>
		<thead>
		<th>Id</th>
		<th>Destination</th>
		<th>MinSeats</th>
		<th>StartHours</th>
		<th>StartMinutes</th>
		<th>DurationMinutes</th>
		<th/>
		<th/>
		</thead>
		<c:forEach items="${result}" var="each" >
			<tr>
				<td>
					<a href="<c:url value="/rest/busConnection/${each.id}" />">${each.id}</a>
				</td>
				<td>
					${each.destination}
				</td>
				<td>
					${each.minSeats}
				</td>
				<td>
					${each.startHours}
				</td>
				<td>
					${each.startMinutes}
				</td>
				<td>
					${each.durationMinutes}
				</td>
				<td>
					<a href="<c:url value="/rest/busConnection/${each.id}" />">Show</a>
				</td>
				<td>
					<c:url value="/rest/busConnection/${each.id}" var="action"/>
					<form:form action="${action}" method="DELETE">
						<input id="delete" type="submit" value="Delete"/>
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${empty result}"><p>There are no BusConnections yet.</p></c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>

