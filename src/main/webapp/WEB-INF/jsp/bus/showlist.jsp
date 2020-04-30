<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
<a href="<c:url value="/rest/bus/form" />">New Bus</a>
</div>
<div>
	<c:if test="${not empty result}">
	<table>
		<thead>
		<th>Id</th>
		<th>BusNum</th>
		<th>BusSpz</th>
		<th>NumberOfSeats</th>
		<th/>
		<th/>
		</thead>
		<c:forEach items="${result}" var="each" >
			<tr>
				<td>
					<a href="<c:url value="/rest/bus/${each.id}" />">${each.id}</a>
				</td>
				<td>
					${each.busNum}
				</td>
				<td>
					${each.busSpz}
				</td>
				<td>
					${each.numberOfSeats}
				</td>
				<td>
					<a href="<c:url value="/rest/bus/${each.id}" />">Show</a>
				</td>
				<td>
					<c:url value="/rest/bus/${each.id}" var="action"/>
					<form:form action="${action}" method="DELETE">
						<input id="delete" type="submit" value="Delete"/>
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${empty result}"><p>There are no Bus yet.</p></c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>

