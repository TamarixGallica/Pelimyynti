<%@include file="header.jsp" %>

<div id="TulostettuLista">

</div>

<script>
    $(document).ready(function() {
        $.getJSON("/Pelimyynti/Servlet_HaeArtikkelit_Ajax", function(artikkelit) {
            var alusta_cur=-1;
            var p;
            $.each(artikkelit, function(artikkelit_ind, artikkeli ) {

                // Jos artikkeli on jo myyty, jätetään se tulostamatta
                if(artikkeli.Myyntihinta != 0)
                    return true;

                var lisatiedot="";
                if(artikkeli.Alusta.Nimi!=alusta_cur) {
                    alusta_cur = artikkeli.Alusta.Nimi;
                    p = $("div#TulostettuLista").append("<p></p>");
                    p.append(artikkeli.Alusta.Nimi + ":<br>");
                }
                if(artikkeli.Lisatiedot.length>0) {
                    lisatiedot = " ("+artikkeli.Lisatiedot+")";
                }
                p.append(artikkeli.Nimi+lisatiedot+" "+artikkeli.Pyyntihinta+"e<br>");
            });
        });
    });
</script>

<%@include file="footer.jsp" %>