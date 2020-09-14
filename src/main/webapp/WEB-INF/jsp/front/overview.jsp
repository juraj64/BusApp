<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
<p><font size="5" color="saddlebrown">BusApplication version 4.0</font></p>
<ul>
<li><a href="<c:url value="/rest/busConnection" />">BusConnections</a></li>
<li><a href="<c:url value="/rest/bus" />">Bus</a></li>
<li><a href="<c:url value="/rest/driver" />">Drivers</a></li>
<li><a href="<c:url value="/rest/seat" />">Seats</a></li>
</ul>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
