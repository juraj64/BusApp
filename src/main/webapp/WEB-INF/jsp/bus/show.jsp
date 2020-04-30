<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<c:if test="${not empty result}">
		<div id="bus_busNum">
			<label for="_busNum">BusNum:</label>
			<div class="box" id="_busNum">${result.busNum}</div>
		</div>
		<br/>
		<div id="bus_busSpz">
			<label for="_busSpz">BusSpz:</label>
			<div class="box" id="_busSpz">${result.busSpz}</div>
		</div>
		<br/>
		<div id="bus_numberOfSeats">
			<label for="_numberOfSeats">NumberOfSeats:</label>
			<div class="box" id="_numberOfSeats">${result.numberOfSeats}</div>
		</div>
		<br/>
	</c:if>
	<c:if test="${empty result}">No Bus found with this id.</c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
