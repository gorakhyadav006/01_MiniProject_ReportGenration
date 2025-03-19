<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap demo</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<title>Report App</title>
</head>
<body>

	<div class="container">

		<h2 class="mb-3 pt-3">Report Application</h2>

		<form:form action="search" modelAttribute="search" method="POST">

			<table>
				<tr>
					<td class="mb-3 pt-3">Plan Name:</td>
					<td><form:select path="planName">
							<form:option value="">-select-</form:option>
							<form:options items="${names}" />
						</form:select></td>

					<td class="mb-3 pt-3">Plan Status:</td>
					<td><form:select path="planStatus">
							<form:option value="">-select-</form:option>
							<form:options items="${status}" />
						</form:select></td>

					<td class="mb-3 pt-3">Gender:</td>
					<td><form:select path="gender">
							<form:option value="">-select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Fe-Male">Fe-Male</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td class="mb-3 pt-3">Start Date:</td>
					<td><form:input path="planStartDate" type="date"
							data-date-format="mm/dd/yyyy" /></td>


					<td class="mb-3 pt-3">End Date:</td>
					<td><form:input path="planEndDate" type="date"
							data-date-format="mm/dd/yyyy" /></td>
				</tr>

				<tr>
				    <td class="mb-3 pt-3">
						<a href="/" class="btn btn-secondary">ReSet</a>
					</td>
				
					<td class="mb-3 pt-3">
						<button class="btn btn-primary">Search</button>
					</td>
				</tr>
			</table>
		</form:form>
		<hr />
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<td><b>ID</b></td>
					<td><b>Holder Name</b></td>
					<td><b>Plan Name</b></td>
					<td><b>Gender</b></td>
					<td><b>Plan Status</b></td>
					<td><b>Start Date</b></td>
					<td><b>End Date</b></td>
					<td><b>Benefit Amt.</b></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${plans}" var="plan" varStatus ="index">
					<tr>
						<td>${index.count}</td>
						<td>${plan.citizenName}</td>		
						<td>${plan.planName}</td>
						<td>${plan.gender}</td>
						<td>${plan.planStatus}</td>
						<td>${plan.planStartDate}</td>
						<td>${plan.planEndDate}</td>
						<td>${plan.benefitAmt}</td>
					</tr>
				</c:forEach>	
				
				<tr>
				<td colspan="8" style="text-align: center;">
				<c:if test="${empty plans}">
					No record Found
				</c:if>
				</td>
				</tr>	
			</tbody>

		</table>
			
		<hr/>
		
		Export: <a  href="excel">ImportToExcel</a> <h1></h1><a href="pdf">ImportToPdf</a>
		
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>

</body>
</html>