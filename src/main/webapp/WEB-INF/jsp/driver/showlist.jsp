<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
<a href="<c:url value="/rest/driver/form" />">New Driver</a>
<br />
<br />
<a href="<c:url value="/rest/driver/form/find" />">Find Drivers</a>
</div>
<div>
	<c:if test="${not empty result}">
	<table>
		<thead>
		<th>Id</th>
		<th>Name</th>
		<th>Age</th>
		<th/>
		<th/>
		</thead>
		<c:forEach items="${result}" var="each" >
			<tr>
				<td>
					<a href="<c:url value="/rest/driver/${each.id}" />">${each.id}</a>
				</td>
				<td>
					${each.name}
				</td>
				<td>
					${each.age}
				</td>
				<td>
					<a href="<c:url value="/rest/driver/${each.id}" />">Show</a>
				</td>
				<td>
					<c:url value="/rest/driver/${each.id}" var="action"/>
					<form:form action="${action}" method="DELETE">
						<input id="delete" type="submit" value="Delete"/>
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${empty result}"><p>There are no Drivers yet.</p></c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>

