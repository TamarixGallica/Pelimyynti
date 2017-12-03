<%@include file="header.jsp" %>

<div id="TulostettuLista">

</div>

<script>
    $(document).ready(function() {
        $.getJSON( "http://localhost:8080/Pelimyynti/Servlet_HaeAlustat_Ajax", function( alustat ) {
            $.each( alustat, function( alusta_ind, alusta ) {
                var p;
                $.getJSON("http://localhost:8080/Pelimyynti/Servlet_HaeArtikkelitAlustalle_Ajax?Alusta_id="+alusta.Alusta_id, function( artikkelit ) {
                    if(artikkelit.length > 0) {
                        p = $("div#TulostettuLista").append("<p></p>");
                        p.append(alusta.Nimi + ":<br>");
                    }
                    $.each( artikkelit, function( artikkeli_ind, artikkeli ) {
                       p.append(artikkeli.Nimi+"<br>");
                    });
                });
                $("div#TulostettuLista").append(p);
            });
        });
    });
</script>

<%@include file="footer.jsp" %>