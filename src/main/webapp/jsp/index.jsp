<%--
  Created by IntelliJ IDEA.
  User: kotle
  Date: 23.12.2018
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Book trade</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="shortcut icon" href="" type="image/x-icon">
    <link rel="icon" href="../Books.ico">

    <!-- Bootstrap CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="../css/fonts.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/animate.css">
    <link rel="stylesheet" href="../css/media.css">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
</head>
<body>

<section id="main">
    <div class="forscroll"></div>
    <div class="navbar-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-8">
                    <ul class="top-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="#main">Main</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#how-it-works">How it works</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#table">Tables</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#contacts">Contacts</a>
                        </li>
                    </ul>
                </div>
                <div class="col-4">
                    <ul class="user-actions float-right">
                        <li><a href="${pageContext.request.contextPath}/signIn" class="login nav-link">Log In</a></li>
                        <li><a href="${pageContext.request.contextPath}/signUp" class="sign-up nav-link">Sign up</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-6">
                <div class="text-container">
                    <h3>Обменивайся прочитанными книгами,</h3>
                    <h4>которые уже давно пылятся на полке</h4>
                    <a href="" class="get-started">Get Started</a>
                </div>
            </div>
            <div class="col-6">
                <div class="image-container">
                    <img src="../img/Books.png" alt="books" class="books">
                </div>
            </div>
        </div>
    </div>

</section>
<section id="table">
    <img src="../img/bookmark.png" alt="bookmark" class="bookmark">
    <div class="container">
        <h3>Table schema</h3>
    </div>
</section>

<section id="contacts">
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <div class="number">+7(123) 456-78-90</div>
                <div class="mail">lionzamanov@gmail.com</div>
            </div>
            <div class="col-md-2 ">
                <div class="social float-right">
                    <a href="https://vk.comlionzam" target="_blank">
                        <div class='box foo'>
                            <i class="fab fa-vk icon"></i>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-auto">
                <div class="designedby"><b>D</b>esigned by <a href="https://vk.com/lionzam" target="_blank"><img
                        src="../img/za.png"
                        alt="ZA"></a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.bundle.min.js"></script>
<script src="../js/scripts.js"></script>
<script src="../js/jquery.mosaicflow.js"></script>

</body>
</html>
