$(document).ready(function () {
    function getBooks(method, url) {
        $.ajax({
            method: method,
            dataType: "json",
            url: url,
            beforeSend: function () {
                $(".books .col-md-3").remove();
                $(".books .col").remove();
                $('#loader').show();
            },
        })
            .done(function (books) {
                $('#loader').hide();
                console.log(books);
                printBooks(books)
            })
    }

    getBooks("GET", "/books.json");

    function printBooks(books) {
        $.each(books, function (index, book) {
            printBook(book);
        })
    }

    $('button').on("click", function () {
        getBooks("POST", "/profile?id=" + $(this).attr("data-id"));
    });

    $("#search").keyup(function (event) {
        if (event.keyCode === 13) {
            getBooks("GET", "/books/search?q=" + this.value);
            currentItem("books");
        }
    });

    function currentItem() {
        $("h1").text("Результат");
        $(".nav-item > .nav-link").removeClass("active");
        $(".nav :nth-child(3)").children().eq(0).addClass("active");

    }

    function printBook(input) {
        let book = input;
        let description = book["description"] === undefined ? " " : book["description"].substring(0, 150) + "...";
        let numPages = book["numPages"] === undefined ? " " : book["numPages"] + " страницы";
        let count = book["count"] === undefined ? " " : book["count"];
        let result = "<div class=\"col-md-3\">\n" +
            "    <div class=\"card mb-4 shadow-sm\">\n" +
            "        <div class=\"img-box\">\n" +
            "            <img class=\"card-img-top\" src=\"" + book["imageUrl"] + "\" alt=\"Card image cap\">\n" +
            "        </div>\n" +
            "        <div class=\"card-body\">\n" +
            "            <p class=\"h3\">" + book["title"] + "</p>\n" +
            // "            <p class=\"h4\">" + book["authors"][0]["name"] + "</p>\n" +
            "            <p class=\"card-text\">" + description + "</p>\n" +
            "            <p class=\"card-text\">" + numPages + "</p>\n" +
            "            <div class=\"d-flex justify-content-between align-items-center\">\n" +
            "                <div class=\"btn-group\">\n" +
            "                    <button type=\"button\" class=\"btn btn-sm btn-outline-danger\">Удалить</button>\n" +
            "                    <button type=\"button\" class=\"btn btn-sm btn-outline-success\" id=\"add-book\" data-id=\" " + book["id"] + " \">Добавить</button>\n" +
            "                    <small class=\"text-muted\">" + count + "</small>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>";
        $(".books").append(result)
    }
});