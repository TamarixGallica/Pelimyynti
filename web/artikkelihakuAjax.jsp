<%@include file="header.jsp" %>
<%@ page import="model.Artikkeli"%>
<%@ page import="java.util.ArrayList"%>

<div class="information">
    Foo.
</div>

<table>
    <thead id="headi">
    <tr>
        <th data-sort="string" class="th_Alusta">Alusta</th>
        <th data-sort="string" class="th_Nimi">Nimi</th>
        <th>Lisätiedot</th>
        <th data-sort="float">Pyyntihinta</th>
    </tr>
    </thead>
    <tbody>
    </tbody>

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

        $("table").stupidtable();

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
            $("tbody").append("<tr>" +
                "<td class=\"Alusta\" data-alusta-id=\""+val.Alusta.Alusta_id+"\">"+val.Alusta.Nimi+"</td>" +
                "<td id='" + val.Artikkeli_id + "' class=\"Nimi\">" + val.Nimi + "</td>" +
                "<td class=\"Lisatiedot\">" + val.Lisatiedot + "</td>" +
                "<td>"+val.Pyyntihinta+"</td><td><a href=\"#\" class=\"fa fa-pencil\"></a><a href=\"#\" class=\"fa fa-check\"></a></td>" +
                "<td><a href=\"#\" class=\"fa fa-trash\"></a><a href=\"#\" class=\"fa fa-close\"></a></td>" +
                "</tr>");

            $("a.fa-trash").last().click(function() {
                confirm_deletion($(this).closest("tr").children("td.Nimi")[0].id);
            });

            $("a.fa-pencil").last().click(function() {
                <%-- Haetaan artikkelin nykyinen nimi --%>
                var html = $(this).closest("tr").children("td.Nimi").html()
                <%-- Luodaan input-tagi, joka korvaa nykyisen solun --%>
                var input = $('<input type="text" class=\"Nimi\"/>');
                <%-- Kirjoitetaan artikkelin nykyinen nimi valmiiksi syötekenttään --%>
                input.val(html);
                <%-- Sijoitetaan input-tagi arvoineen soluun, jossa artikkelin nimi on --%>
                $(this).closest("tr").children("td.Nimi").html(input);

                <%-- Muutetaan Lisätiedot-kenttä vastaavasti kuin yllä oleva --%>
                var html = $(this).closest("tr").children("td.Lisatiedot").html()
                var input = $('<input type="text" class=\"Lisatiedot\"/>');
                input.val(html);
                $(this).closest("tr").children("td.Lisatiedot").html(input);


                var Alusta_id = $(this).closest("tr").find("td.Alusta").attr("data-alusta-id")

                $(this).closest("tr").find("td.Alusta").replaceWith("<select name=\"Alusta_muok\" data-alusta-id=\""+Alusta_id+"\"></select>");


                var callee = this;

                $.getJSON("http://localhost:8080/Pelimyynti/Servlet_HaeAlustat_Ajax", function( data) {
                    $.each( data, function( key, val) {
                        //$("select[name=Alusta]").append("<option value=\""+val.Alustat_id+"\">"+val.Nimi+"</option>");
                        $(callee).closest("tr").find("select[name=Alusta_muok]").append("<option value=\""+val.Alusta_id+"\">"+val.Nimi+"</option>");
                    });
                    $(callee).closest("tr").find("option[value="+Alusta_id+"]").attr("selected","selected");
                });


                $(this).hide();
                $(this).closest("tr").find("a.fa-check").show();
                $(this).closest("tr").find("a.fa-trash").hide();
                $(this).closest("tr").find("a.fa-close").show();
                $("div.information").hide(500);

            });

            $("a.fa-check").last().click(function() {
                var callee = this;
                $.getJSON("http://localhost:8080/Pelimyynti/Servlet_MuutaArtikkeli_Ajax?Artikkeli_id="+$(this).closest("tr").children("td.Nimi")[0].id+"&Artikkeli_nimi="+$(this).closest("tr").find("input.Nimi").val()+"&Alusta_id="+$(this).closest("tr").find("select").val()+"&Artikkeli_lisatiedot="+$(this).closest("tr").find("input.Lisatiedot").val(), function(data) {
                    if(data[0].status=="OK") {
                        $.getJSON("http://localhost:8080/Pelimyynti/Servlet_HaeArtikkeli_Ajax?id="+$(callee).closest("tr").children("td.Nimi")[0].id, function(data2) {

                            var rowNumber = $("td.Nimi#154").closest("tr").index();
                            $.each(data2, function(key, val) {
                                $(callee).closest("tr").remove();
                                print_line(key, val);
                            });

                            $("thead th.th_Nimi").stupidsort();
                            $("thead th.th_Alusta").stupidsort();

                        });

                    }
                    $(callee).closest("tr").find("a.fa-pencil").show();
                    $(callee).closest("tr").find("a.fa-check").hide();
                    $(callee).closest("tr").find("a.fa-trash").show();
                    $(callee).closest("tr").find("a.fa-close").hide();

                });
            });

            $("a.fa-close").last().click(function() {
                var callee = this;
                $.getJSON("http://localhost:8080/Pelimyynti/Servlet_HaeArtikkeli_Ajax?id="+$(this).closest("tr").children("td.Nimi")[0].id, function(data) {
                    $.each( data, function( key, val ) {
                        $(callee).closest("tr").find("input.Nimi").replaceWith(val.Nimi);
                        $(callee).closest("tr").find("input.Lisatiedot").replaceWith(val.Lisatiedot);
                    });
                });
                $(this).closest("tr").find("a.fa-pencil").show();
                $(this).closest("tr").find("a.fa-check").hide();
                $(this).closest("tr").find("a.fa-trash").show();
                $(this).closest("tr").find("a.fa-close").hide();

                $.getJSON("http://localhost:8080/Pelimyynti/Servlet_HaeAlusta_Ajax?id="+$(this).closest("tr").find("select").attr("data-alusta-id"), function(data) {
                    $.each( data, function( key, val ) {
                        console.log(val);
                        $(callee).closest("tr").find("select[name=Alusta_muok]").replaceWith("<td class=\"Alusta\" data-alusta-id=\""+val.Alustat_id+"\">"+val.Nimi+"</td>");
                    });
//                    $("[name=Nimi]").val("");

                });

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