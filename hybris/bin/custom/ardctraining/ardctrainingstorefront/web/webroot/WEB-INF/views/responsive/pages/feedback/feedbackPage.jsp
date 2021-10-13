<%--
  Created by IntelliJ IDEA.
  User: ardc
  Date: 08/10/21
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>

<template:page pageTitle="${pageTitle}">


    <c:url value ="/feedback/save" var="saveFeedback"/>
    <form:form id="feedbackForm" action="${saveFeedback}" method="POST" modelAttribute="feedbackForm">
        <div class="form-group">
            <label for="exampleFormControlInput1">Subject</label>
            <form:input type="text" cssClass="form-control" id="exampleFormControlInput1" maxlength="100" placeholder="Subject" path="subject" />
        </div>
        <div class="form-group">
            <label for="exampleFormControlTextarea1">Message</label>
            <form:textarea class="form-control" id="exampleFormControlTextarea1" rows="3" maxlength="500" path="message"  ></form:textarea>
        </div>
        <button id="cancelButton" type="button" class="btn btn-danger" onclick="cancel()">Cancelar</button>
        <button id="submitButton" type="submit" class="btn btn-primary mb-2">Confirm</button>
    </form:form>
    <div>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Subject</th>
                    <th scope="col">Message</th>
                    <th scope="col">Date</th>
                    <th scope="col">Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${feedbacks}" var="feedback"  varStatus="loop">
                <tr>
                    <td>${feedback.subject}</td>
                    <td>${feedback.message}</td>
                    <td>${feedback.submittedDate}</td>
                    <td>${feedback.status}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script>
        document.getElementById("exampleFormControlTextarea1").required = true;
        document.getElementById("exampleFormControlInput1").required = true;
        function cancel(){
            document.getElementById("exampleFormControlTextarea1").value = "";
            document.getElementById("exampleFormControlInput1").value = "";
        }

    </script>

</template:page>
