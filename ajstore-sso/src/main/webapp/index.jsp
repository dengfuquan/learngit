<html>
<body>
<h2>Hello World!</h2>
<%
String data=(String)request.getAttribute("data");
out.println(data);
String username=(String)request.getAttribute("username");
out.println("username="+username);
%>

</body>
</html>
