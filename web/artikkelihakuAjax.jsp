<%@include file="header.jsp" %>
<%@ page import="model.Artikkeli"%>
<%@ page import="java.util.ArrayList"%>

<div class="information">
    Foo.
</div>

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

<%--<form action="Servlet_LisaaArtikkeli" method="post">--%>
<form action="#">
    Alusta: <select name="Alusta"></select><br>
    Nimi: <input type="text" name="Nimi"><br>
    Lisätiedot: <input type="text" name="Lisatiedot"><br>
    Pyyntihinta: <input type="text" name="Pyyntihinta"><br/>
    <input type="button" id="Lisaa" name="Lisää" value="Lisää">
</form>
<script>

    function confirm_deletion(id) {
        $.getJSON("http://localhost:8080/Pelimyynti/Servlet_HaeArtikkeli_Ajax?id="+id, function(data) {
            $.each( data, function( key, val ) {
                $("div.information").html("<p>Haluatko varmasti poistaa artikkelin "+val.nimi+"?</p><a href=\"#\" class=\"fa fa-check visible\"></a><a class=\"fa fa-close visible\" href=\"#\"></a></p>")
                    .show(500);
            });

            $("a.fa-check.visible").click(function() {
                $.getJSON("Servlet_PoistaArtikkeli_Ajax?id="+id, function(data) {
                    if(data[0].status=="OK") {
                        $("td[id="+id+"]").parent().remove();
                        $("div.information").hide(500);
                    } else {
                        if((data[0].status=="ERROR") && (data[0].message=="1451")) {
                            $("div.information").html("<p>Laitetta ei voitu poistaa, koska sille on lisätty pelejä.</p>");
                            $("div.information").show(500).delay(2000).hide(500);
                        }
                    }
                })
            });
            $("a.fa-close.visible").click(function() {
                $("div.information").hide(500);
            });
        });
    }

    $(document).ready(function() {

        $.getJSON( "http://localhost:8080/Pelimyynti/Servlet_HaeArtikkelit_Ajax", function( data ) {
            var items = [];
            $.each( data, function( key, val ) {
                print_line(key, val);
            });
        });

        $.getJSON("http://localhost:8080/Pelimyynti/Servlet_HaeAlustat_Ajax", function( data) {
            $.each( data, function( key, val) {
               $("select[name=Alusta]").append("<option value=\""+val.Alustat_id+"\">"+val.Nimi+"</option>");
            });
        });

        function print_line(key, val) {
            $("thead").append("<tr><td class=\"Alusta\" data-alusta-id=\""+val.alusta.alusta_id+"\">"+val.alusta.nimi+"</td><td id='" + val.artikkeli_id + "' class=\"Nimi\">" + val.nimi + "</td><td>" + val.lisatiedot + "</td><td>"+val.pyyntihinta+"</td><td><a href=\"#\" class=\"fa fa-pencil\"></a><td><a href=\"#\" class=\"fa fa-trash\"></a></tr>");

            $("a.fa-trash").last().click(function() {
                confirm_deletion($(this).parent().parent().children("td.Nimi")[0].id);
            });

        }

        $("#Lisaa").click(function() {
            $.post("Servlet_LisaaArtikkeli_Ajax", { Alustat_id: $("select[name=Alusta]").val(), Nimi: $("[name=Nimi]").val(), Lisatiedot: $("[name=Lisatiedot]").val(), Pyyntihinta: $("[name=Pyyntihinta]").val()})
                .done(function(data) {
                    if(data < 0) {

                    }
                    else {
                        $.getJSON("http://localhost:8080/Pelimyynti/Servlet_HaeArtikkeli_Ajax?id="+data, function(data2) {
                            $.each( data2, function( key, val ) {
                                print_line(key, val);
                            });
                            $("[name=Nimi]").val("");
                            $("[name=Lisatiedot]").val("");
                            $("[name=Pyyntihinta]").val("");
                        })
                    }
                });
        });

    });


</script>


</body>
</html>