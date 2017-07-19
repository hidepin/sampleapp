<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="rs" dataSource="jdbc/dbDB">
select i_id, i_im_id, i_name from item limit 10
</sql:query>

<html>
  <head>
    <title>DB Test</title>
  </head>
  <body>

  <h2>Results</h2>

<c:forEach var="row" items="${rs.rows}">
    i_id ${row.i_id} i_id ${row.i_im_id} i_name ${row.i_name}<br/>
</c:forEach>

  </body>
</html>
