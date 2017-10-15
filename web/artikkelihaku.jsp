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
            for (int i = 0; i < artikkelit.size(); i++) {
                out.print("\n<tr>");
                out.print("<td>" + artikkelit.get(i).getNimi() + "</td>");
                out.print("<td>" + artikkelit.get(i).getLisatiedot() + "</td>");
                out.print("<td>" + artikkelit.get(i).getPyyntihinta() + "</td>");
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