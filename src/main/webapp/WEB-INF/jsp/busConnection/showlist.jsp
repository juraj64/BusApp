<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
<font size="3" color='saddlebrown'>BusConnections</font>
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
		<th>Driver</th>
		<th>Bus</th>
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
				    ${each.driver.id}
				</td>
				<td>
           		    ${each.bus.id}
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

	<div>
    <br />
    <font size="2" color='saddlebrown'>To run makeConnection method - use curl command, e.g.: </font>
    <br />
    <font>curl -i -H "Content-Type: application/json" -X POST -d "{\"destination\":\"Karlovka\",\"minSeats\":20, \"startHours\":10, \"startMinutes\":22, \"durationMinutes\":40}" http://localhost:8888/rest/busConnection/make</font>

    <br />
    <br />
    <font size="2" color='saddlebrown'>To run freeReservedSeats method - use curl command:</font>
    <br />
    <font>curl -i -H "Content-Type: application/json" -X POST -d "{}" http://localhost:8888/rest/busConnection/free</font>


    </div>

	</c:if>
	<c:if test="${empty result}"><p>There are no BusConnections yet.</p></c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>

