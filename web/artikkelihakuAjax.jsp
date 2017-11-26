<%@include file="header.jsp" %>
<%@ page import="model.Artikkeli"%>
<%@ page import="java.util.ArrayList"%>

<table>
    <thead id="headi">
    <tr>
        <th>Alusta</th>
        <th>Nimi</th>
        <th>Lisätiedot</th>
        <th>Pyyntihinta</th>
    </tr>
    </thead>

</table>

<form action="Servlet_LisaaArtikkeli" method="post">
    Nimi: <input type="text" name="Nimi"><br>
    Lisätiedot: <input type="text" name="Lisatiedot"><br>
    Pyyntihinta: <input type="text" name="Pyyntihinta"><br/>
    <input type="submit">
</form>
<script>
    $(document).ready(function() {

        $.getJSON( "http://localhost:8080/Pelimyynti/Servlet_HaeArtikkelit_Ajax", function( data ) {
            var items = [];
            $.each( data, function( key, val ) {
                print_line(key, val);
            });
        });

        function print_line(key, val) {
            $("thead").append("<tr><td class=\"Alusta\"></td><td id='" + val.Artikkelit_id + "' class=\"Nimi\">" + val.Nimi + "</td><td>" + val.Lisatiedot + "</td><td>"+val.Pyyntihinta+"</tr>");

            $.getJSON("http://localhost:8080/Pelimyynti/Servlet_HaeAlustaArtikkelille_Ajax?id="+val.Artikkelit_id, function( data2 ) {
                $.each( data2, function( key2, val2 ) {
                    $("td.Nimi[id="+val.Artikkelit_id+"]").parent().find("td.Alusta").html(val2.Alustat_id);
                });
            });
        }
    })
</script>


</body>
</html>