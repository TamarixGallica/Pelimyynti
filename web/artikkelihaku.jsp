<%@include file="header.jsp" %>
<%@ page import="model.Artikkeli"%>
<%@ page import="java.util.ArrayList"%>

<table>
    <thead>
    <tr>
        <th>Nimi</th>
        <th>Lisätiedot</th>
        <th>Pyyntihinta</th>
    </tr>
    </thead>

    <%
        if(request.getAttribute("artikkelit")!=null) {
            ArrayList<Artikkeli> artikkelit = (ArrayList<Artikkeli>) request.getAttribute("artikkelit");
            for (Artikkeli anArtikkelit : artikkelit) {
                out.print("\n<tr>");
                out.print("<td>" + anArtikkelit.getNimi() + "</td>");
                out.print("<td>" + anArtikkelit.getLisatiedot() + "</td>");
                out.print("<td>" + anArtikkelit.getPyyntihinta() + "</td>");
                out.print("</tr>");
            }
        } else {
            String redirectURL = "/Pelimyynti/Servlet_HaeArtikkelit";
            response.sendRedirect(redirectURL);
        }
    %>

</table>

<form action="Servlet_LisaaArtikkeli" method="post">
    Nimi: <input type="text" name="Nimi"><br>
    Lisätiedot: <input type="text" name="Lisatiedot"><br>
    Pyyntihinta: <input type="text" name="Pyyntihinta"><br/>
    <input type="submit">
</form>





</body>
</html>