<%@include file="header.jsp" %>
<%@ page import="model.Artikkeli"%>
<%@ page import="java.util.ArrayList"%>

<style>
    a.fa {
        text-decoration: none;
    }
    a.fa-check {
        display: none;
        color: green;
    }
    a.fa-close {
        display: none;
        color: red;
    }
</style>

<div class="error">
    Foo.
</div>

<table>
    <thead id="headi">
    <tr>
        <th>Nimi</th>
        <th></th>
        <th></th>
    </tr>
    </thead>

</table>

<script>

    $(document).ready(function() {

        function print_line(key, val) {
            $("thead").append("<tr><td id=\'" + val.Alustat_id + "\' class=\'nimi\'>" + val.Nimi + "</td><td><a href=\"#\" class=\"fa fa-pencil\"></a><a href=\"#\" class=\"fa fa-check\"></a></td><td><a href=\"#\" class=\"fa fa-trash\"></a><a href=\"#\" class=\"fa fa-close\"></a></td></tr>");

            $("a.fa-trash").last().click(function(e) {
//                console.log(e.target.parentElement.attributes[0].value);
                console.log($(this).parent().parent().children("td.nimi")[0].id);
                $.getJSON("Servlet_PoistaAlusta_Ajax?id="+$(this).parent().parent().children("td.nimi")[0].id, function(data) {
                    if(data[0].status=="OK") {
                        e.target.parentElement.parentElement.remove();
                    } else {
                        if((data[0].status=="ERROR") && (data[0].message=="1451")) {
                            $("div.error").html("<p>Laitetta ei voitu poistaa, koska sille on lisätty pelejä.</p>");
//                            $("div.error").show();
                            $("div.error").slideDown(1000).delay(1000).slideUp(1000);
//                            $("div.error").hide();
                        }
                    }
                })
            });

            $("a.fa-pencil").last().click(function(e) {
                var html = $(this).parent().parent().children("td.nimi").html()
                var input = $('<input type="text" />');
                input.val(html);
                $(this).parent().parent().children("td.nimi").html(input);
//                $(this).removeClass("fa-pencil").addClass("fa-check");
//                $(this).parent().parent().find("a.fa-trash").removeClass("fa-trash").addClass("fa-close");
                $(this).hide();
                $(this).parent().parent().find("a.fa-check").show();
                $(this).parent().parent().find("a.fa-trash").hide();
                $(this).parent().parent().find("a.fa-close").show();

            });

        }

        $.getJSON( "http://localhost:8080/Pelimyynti/Servlet_HaeAlustat_Ajax", function( data ) {
            var items = [];
            $.each( data, function( key, val ) {
                print_line(key, val);
            });
            $('td').hover(function() {
                $(this).addClass('hover');
                $(this).children().addClass('hover');
//            $(this).children("span").css("display", "inline");
            }, function() {
                $(this).removeClass('hover');
                $(this).children().removeClass('hover');
            });


        });

        $("#Lisaa").click(function() {
//            $("thead").append("<h2>Jotain tämä kuitenkin tekee</h2>");
//            alert("Hep!");
            $.post("Servlet_LisaaAlusta_Ajax", { Nimi: $("[name=Nimi]").val()})
                .done(function(data) {
                    if(data < 0) {

                    }
                    else {
                        $.getJSON("http://localhost:8080/Pelimyynti/Servlet_HaeAlusta_Ajax?id="+data, function(data2) {
                            $.each( data2, function( key, val ) {
                                print_line(key, val);
                            });
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