<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<c:if test="${not empty result}">
		<div id="busConnection_id">
    		<label for="_id">Id:</label>
       		<div class="box" id="_id">${result.id}</div>
        </div>
        <br/>
		<div id="busConnection_destination">
			<label for="_destination">Destination:</label>
			<div class="box" id="_destination">${result.destination}</div>
		</div>
		<br/>
		<div id="busConnection_minSeats">
			<label for="_minSeats">MinSeats:</label>
			<div class="box" id="_minSeats">${result.minSeats}</div>
		</div>
		<br/>
		<div id="busConnection_startHours">
           <label for="_startHours">StartHours:</label>
           <div class="box" id="_startHours">${result.startHours}</div>
        </div>
        <br/>
		<div id="busConnection_startMinutes">
			<label for="_startMinutes">StartMinutes:</label>
			<div class="box" id="_startMinutes">${result.startMinutes}</div>
		</div>
		<br/>
		<div id="busConnection_durationMinutes">
			<label for="_durationMinutes">DurationMinutes:</label>
			<div class="box" id="_durationMinutes">${result.durationMinutes}</div>
		</div>
		<br/>
		<div id="busConnection_driverId">
        	<label for="_driver.id">Driver:</label>
        	<div class="box" id="_driver.id">${result.driver.id}</div>
        </div>
        <br/>
        <div id="busConnection_busId">
           	<label for="_bus.id">Bus:</label>
          	<div class="box" id="_bus.id">${result.bus.id}</div>
        </div>
        <br/>
	</c:if>
	<c:if test="${empty result}">No BusConnection found with this id.</c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>
