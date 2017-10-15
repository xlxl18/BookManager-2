<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Books Page</title>
  <link rel="stylesheet"
        href='<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>'>

  <style type="text/css">
    .tg {
      border-collapse: collapse;
      border-spacing: 0;
      border-color: #ccc;
    }

    .tg td {
      font-family: Arial, sans-serif;
      font-size: 14px;
      padding: 10px 5px;
      border-style: solid;
      border-width: 1px;
      overflow: hidden;
      word-break: normal;
      border-color: #ccc;
      color: #333;
      background-color: #fff;
    }

    .tg th {
      font-family: Arial, sans-serif;
      font-size: 14px;
      font-weight: normal;
      padding: 10px 5px;
      border-style: solid;
      border-width: 1px;
      overflow: hidden;
      word-break: normal;
      border-color: #ccc;
      color: #333;
      background-color: #f0f0f0;
    }

    .tg .tg-4eph {
      background-color: #f9f9f9
    }
  </style>
</head>
<body>

<a href="../../index.jsp">Back to main menu</a>

<br/>
<br/>

<h1>Book List</h1>

<c:if test="${!empty listBooks.content}">
  <table class="tg">
    <tr>
      <th width="80">ID</th>
      <th width="120">Title</th>
      <th width="120">Description</th>
      <th width="120">Author</th>
      <th width="60">ISBN</th>
      <th width="60">printYear</th>
      <th width="60">readAlready</th>
      <th width="60">Edit</th>
      <th width="60">Delete</th>
    </tr>
    <c:forEach items="${listBooks.content}" var="book">
      <tr>
        <td>${book.id}</td>
        <td><a href="/bookdata/${book.id}" target="_blank"> ${book.title}</a></td>
        <td>${book.description}</td>
        <td>${book.author}</td>
        <td>${book.isbn}</td>
        <td>${book.printYear}</td>
        <td>
          <c:choose>
            <c:when test="${book.readAlready}">
              Прочитана
            </c:when>
            <c:otherwise>
              Не прочитана
            </c:otherwise>
            </c:choose>
        </td>


        <td><a href="<c:url value='/edit/${book.id}'/>">Edit</a></td>
        <td><a href="<c:url value='/remove/${book.id}'/>">Delete</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
<c:forEach begin="0" end="${listBooks.totalPages - 1}" var="val">
  <a href="<c:url value="/books?page=${val}"/>"><c:out value="${val + 1}"/></a>
</c:forEach>


<h1>Add a Book</h1>

<c:url var="addAction" value="/books/add"/>

<form:form action="${addAction}" modelAttribute="book">
  <table>
    <c:if test="${!empty book.title}">
      <tr>
        <td>
          <form:label path="id">
            <spring:message text="id"/>
          </form:label>
        </td>
        <td>
          <form:input path="id" readonly="true" size="8" disabled="true"/>
          <form:hidden path="id"/>
        </td>
      </tr>
    </c:if>
    <tr>
      <td>
        <form:label path="title">
          <spring:message text="Title"/>
        </form:label>
      </td>
      <td>
        <form:input path="title"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="description">
          <spring:message text="Description"/>
        </form:label>
      </td>
      <td>
        <form:input path="description"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="author">
          <spring:message text="Author"/>
        </form:label>
      </td>
      <td>
        <form:input path="author"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="isbn">
          <spring:message text="ISBN"/>
        </form:label>
      </td>
      <td>
        <form:input path="isbn"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="printYear">
          <spring:message text="printYear"/>
        </form:label>
      </td>
      <td>
        <form:input path="printYear"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="readAlready">
          <spring:message text="readAlready"/>
        </form:label>
      </td>
      <td>
        <form:input path="readAlready"/>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <c:if test="${!empty book.title}">
          <input type="submit"
                 value="<spring:message text="Edit Book"/>"/>
        </c:if>
        <c:if test="${empty book.title}">
          <input type="submit"
                 value="<spring:message text="Add Book"/>"/>
        </c:if>
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>
