<%@include file="header.jsp" %>
<%@ page import="model.Artikkeli"%>
<%@ page import="java.util.ArrayList"%>

<style>

</style>

<table>
    <thead id="headi">
    <tr>
        <th>Nimi</th>
    </tr>
    </thead>

</table>

<script>

    $(document).ready(function() {

        function print_line(key, val) {
            $("thead").append("<tr><td id='" + key + "'>" + val.Nimi + " <i class=\"fa fa-trash\"></i> <i class=\"fa fa-pencil\"></i></td></tr>");
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