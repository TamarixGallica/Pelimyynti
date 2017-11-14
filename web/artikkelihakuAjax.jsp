<%@include file="header.jsp" %>
<%@ page import="model.Artikkeli"%>
<%@ page import="java.util.ArrayList"%>

<table>
    <thead id="headi">
    <tr>
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

        $.getJSON( "http://localhost:8080/Pelimyynti/Servlet_HaeArtikkeli_Ajax", function( data ) {
            var items = [];
            $.each( data, function( key, val ) {
                // items.push( "<td id='" + key + "'>" + val.Nimi + "</td><td>" + val.Lisatiedot + "</td>" );
//                items.push( "<td id='" + key + "'>" + val.Lisatiedot + "</td>" );
//                items.push( "<td id='" + key + "'>" + val.Pyyntihinta + "</td>" );
                $("thead").append("<tr><td id='" + key + "'>" + val.Nimi + "</td><td>" + val.Lisatiedot + "</td><td>"+val.Pyyntihinta+"</tr>");
            });
//            $.each(data, function( item ) {
//                items.push("<li>" + item.nimi + "</li>");
//            })

            // $("thead").append(items);

            // $( "<tr/>", {
            //    "class": "my-new-list",
            //     html: items.join( "" )
            // }).appendTo( "thead" );
        });
    })
</script>


</body>
</html>