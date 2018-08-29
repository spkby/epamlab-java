<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../header.jsp" %>
<div class="jumbotron">
    <div class="container">

        <c:if test="${holidays.size() != 0}">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Employee</th>
                    <th scope="col">From</th>
                    <th scope="col">To</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${holidays}" var="holiday">
                    <tr>
                        <td>${holiday.employee.name}</td>
                        <td>${holiday.dateFrom}</td>
                        <td>${holiday.dateTo}</td>
                        <td>${holiday.status.name}</td>
                        <td><a href="<c:url value="/holiday/view/${holiday.id}"/>">Details</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${list != true && currAccount.id == account.id}">
            <a href="<c:url value="/holiday/add"/>" class="btn btn-success btn-block" role="button"
               aria-pressed="true">Add Holiday</a>
        </c:if>

    </div>
</div>
<%@ include file="../footer.jsp" %>