<%@include file="header.jsp" %>
<%@ page import="model.Artikkeli"%>
<%@ page import="java.util.ArrayList"%>

<div class="information">
    Foo.
</div>

<table>
    <thead id="headi">
    <tr>
        <th data-sort="string" class="th_Nimi">Nimi</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    </tbody>

</table>

<script>

    $(document).ready(function() {

        $("table").stupidtable();

        function confirm_deletion(id) {
            $.getJSON("http://localhost:8080/Pelimyynti/Servlet_HaeAlusta_Ajax?id="+id, function(data) {
                $.each( data, function( key, val ) {
                    $("div.information").html("<p>Haluatko varmasti poistaa alustan "+val.Nimi+"?</p><a href=\"#\" class=\"fa fa-check visible\"></a><a class=\"fa fa-close visible\" href=\"#\"></a></p>");
                    $("div.information").show(500);
                });
                $("a.fa-check.visible").click(function() {
                    $.getJSON("Servlet_PoistaAlusta_Ajax?id="+id, function(data) {
                        if(data[0].status=="OK") {
                            $("td[id="+id+"]").parent().remove();
                            $("div.information").hide(500);
                            //$(callee).parent().parent().remove();
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

        function print_line(key, val) {
            $("tbody").append("<tr><td id=\'" + val.Alusta_id + "\' class=\'nimi\'>" + val.Nimi + "</td><td><a href=\"#\" class=\"fa fa-pencil\"></a><a href=\"#\" class=\"fa fa-check\"></a></td><td><a href=\"#\" class=\"fa fa-trash\"></a><a href=\"#\" class=\"fa fa-close\"></a></td></tr>");

            $("a.fa-trash").last().click(function() {
                confirm_deletion($(this).parent().parent().children("td.nimi")[0].id);
            });

            $("a.fa-pencil").last().click(function() {
                var html = $(this).parent().parent().children("td.nimi").html()
                var input = $('<input type="text" />');
                input.val(html);
                $(this).parent().parent().children("td.nimi").html(input);
                $(this).hide();
                $(this).parent().parent().find("a.fa-check").show();
                $(this).parent().parent().find("a.fa-trash").hide();
                $(this).parent().parent().find("a.fa-close").show();
                $("div.information").hide(500);

            });

            $("a.fa-check").last().click(function() {
                var callee = this;
                $.getJSON("http://localhost:8080/Pelimyynti/Servlet_MuutaAlusta_Ajax?id="+$(this).closest("tr").children("td.nimi")[0].id+"&Nimi="+$(this).closest("tr").find("input").val(), function(data) {
                    if(data[0].status=="OK") {
                        $(callee).parent().parent().find("input").replaceWith($(callee).parent().parent().find("input").val());
                    }
                    $("thead th.th_Nimi").stupidsort();
                    $(callee).parent().parent().find("a.fa-pencil").show();
                    $(callee).parent().parent().find("a.fa-check").hide();
                    $(callee).parent().parent().find("a.fa-trash").show();
                    $(callee).parent().parent().find("a.fa-close").hide();

                });
            });

            $("a.fa-close").last().click(function() {
                var callee = this;
                $.getJSON("http://localhost:8080/Pelimyynti/Servlet_HaeAlusta_Ajax?id="+$(this).parent().parent().children("td.nimi")[0].id, function(data) {
                    $.each( data, function( key, val ) {
                        $(callee).parent().parent().find("input").replaceWith(val.Nimi);
                    });
                });
                $(this).parent().parent().find("a.fa-pencil").show();
                $(this).parent().parent().find("a.fa-check").hide();
                $(this).parent().parent().find("a.fa-trash").show();
                $(this).parent().parent().find("a.fa-close").hide();

            });

        }

        $.getJSON( "http://localhost:8080/Pelimyynti/Servlet_HaeAlustat_Ajax", function( data ) {
            var items = [];
            $.each( data, function( key, val ) {
                print_line(key, val);
            });

        });

        $("#Lisaa").click(function() {
            $.post("Servlet_LisaaAlusta_Ajax", { Nimi: $("[name=Nimi]").val()})
                .done(function(data) {
                    if(data < 0) {

                    }
                    else {
                        $.getJSON("http://localhost:8080/Pelimyynti/Servlet_HaeAlusta_Ajax?id="+data, function(data2) {
                            $.each( data2, function( key, val ) {
                                print_line(key, val);
                            });
                            $("thead th.th_Nimi").stupidsort();
                            $("[name=Nimi]").val("");

                        })
                    }
                });
        });



    })

</script>

<form action="#">
    Nimi: <input type="text" name="Nimi"><br>
    <input type="button" id="Lisaa" name="Lisää" value="Lisää">
</form>


</body>
</html>