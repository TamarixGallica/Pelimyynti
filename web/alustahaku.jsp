<%@include file="header.jsp" %>
<%@ page import="model.Alusta"%>
<%@ page import="java.util.ArrayList"%>

<table>
    <thead>
    <tr>
        <th>Nimi</th>
    </tr>
    </thead>

    <%
        if(request.getAttribute("alustat")!=null) {
            ArrayList<Alusta> alustat = (ArrayList<Alusta>) request.getAttribute("alustat");
            for (int i = 0; i < alustat.size(); i++) {
                out.print("\n<tr>");
                out.print("<td>" + alustat.get(i).getNimi() + "</td>");
                out.print("</tr>");
            }
        } else {
            String redirectURL = "/Pelimyynti/Servlet_HaeAlustat";
            response.sendRedirect(redirectURL);
        }
    %>

</table>

<form action="Servlet_LisaaAlusta" method="post">
    Nimi: <input type="text" name="Nimi"><br>
    <input type="submit">
</form>



</body>
</html>