<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<c:if test="${not empty result}">
		<div id="driver_name">
			<label for="_name">Name:</label>
			<div class="box" id="_name">${result.name}</div>
		</div>
		<br/>
		<div id="driver_age">
			<label for="_age">Age:</label>
			<div class="box" id="_age">${result.age}</div>
		</div>
		<br/>
	</c:if>
	<c:if test="${empty result}">No Driver found with this id.</c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
