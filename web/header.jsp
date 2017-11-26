<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous">
    </script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
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
        a.fa-check.visible {
            display: inline;
            font-size: 200%;
        }
        a.fa-close.visible {
            display: inline;
            font-size: 200%;
        }
        div.information {
            display: none;
        }
    </style>
</head>
<body>
<div>
    <p>
        GET:
        <a href="http://localhost:8080/Pelimyynti/artikkelihaku.jsp">Hae artikkelit</a>
        <a href="http://localhost:8080/Pelimyynti/alustahaku.jsp">Hae alustat</a>
    </p>
    <p>
        AJAX:
        <a href="http://localhost:8080/Pelimyynti/artikkelihakuAjax.jsp">Hae artikkelit</a>
        <a href="http://localhost:8080/Pelimyynti/alustahakuAjax.jsp">Hae alustat</a>
    </p>
</div>
