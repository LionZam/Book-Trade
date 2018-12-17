$(document).ready(function () {
    function getUserBooks() {
        $.ajax({
            method: "GET",
            dataType: "json",
            url: "/books.json",
            beforeSend: function () {
                $('#loader').show();
            },
        })
            .done(function (books) {
                $('#loader').hide();
                console.log(books);
                printMyBooks(books)
            })
    }

    getUserBooks();

    /*$('button').click(function () {
        var bookId = $(this).attr("id");
        $.ajax({
            method: "POST",
            url: "/shop",
            data: {id: bookId}
        })
            .done(function (data) {
                getUserBooks();
            });
    });*/

    function printBook(input) {
        let book = input;
        let result = "<div class=\"col-md-3\">\n" +
            "    <div class=\"card mb-4 shadow-sm\">\n" +
            "        <div class=\"img-box\">\n" +
            "            <img class=\"card-img-top\" src=\"" + book["image_url"] + "\" alt=\"Card image cap\">\n" +
            "        </div>\n" +
            "        <div class=\"card-body\">\n" +
            "            <p class=\"h3\">" + book["work"]["original_title"] + "</p>\n" +
            // "            <p class=\"h4\">" + book["authors"][0]["name"] + "</p>\n" +
            "            <p class=\"card-text\">" + book["description"].substring(0, 150) + "..." + "</p>\n" +
            "            <div class=\"d-flex justify-content-between align-items-center\">\n" +
            "                <div class=\"btn-group\">\n" +
            "                    <button type=\"button\" class=\"btn btn-sm btn-outline-danger\">Удалить</button>\n" +
            "                    <button type=\"button\" class=\"btn btn-sm btn-outline-success\" id=\"add\" data-id=\" " + book["id"] + " \">Добавить еще одну</button>\n" +
            "                    <small class=\"text-muted\">" + book["count"] + "</small>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>";
        if ($(".books").not(':has(.col-md-3)')) {
            $(".books").append(result)
        } else {
            $(".books .col-md-3:last").after().fadeIn(result)
        }
    }

    $("#add").click(function () {
        e.preventDefault();
        var bookId = $(this).attr("data-id");
        console.log("asfsdfsd");
        alert(bookId);
        $.ajax({
            method: "POST",
            url: "/profile",
            data: {id: bookId}
        }).done(function (books) {
            printMyBooks(books)
        });
    });

    /*$('.delete-book').click(function () {
        var bookId = $(this).attr("id");
        $.ajax({
            method: "POST",
            url: "/shop",
            data: {id: bookId}
        })
    });*/

    $("#search").keyup(function (event) {
        if (event.keyCode === 13) {
            $.ajax({
                method: "GET",
                dataType: "json",
                url: "/books/search",
                data: {q: this.value},
                beforeSend: function () {
                    $('#loader').show();
                },
            })
                .done(function (books) {
                    console.log(books);
                    $('#loader').hide();
                    printSearchBooks(books["work"])
                });
        }
    });

    function printSearchBooks(books) {
        $(".books .col-md-3").remove();
        $.each(books, function (index, book) {
            printSearchBook(book["best_book"]);
        })
    }

    function printSearchBook(input) {
        let book = input;
        let result = "<div class=\"col-md-3\">\n" +
            "    <div class=\"card mb-4 shadow-sm\">\n" +
            "        <div class=\"img-box\">\n" +
            "            <img class=\"card-img-top\" src=\"" + book["image_url"] + "\" alt=\"Card image cap\">\n" +
            "        </div>\n" +
            "        <div class=\"card-body\">\n" +
            "            <p class=\"h5\">" + book["title"] + "</p>\n" +
            "            <p class=\"h6\">" + book["author"]["name"] + "</p>\n" +
            "            <div class=\"d-flex justify-content-between align-items-center\">\n" +
            "                <div class=\"btn-group\">\n" +
            "                    <button type=\"button\" class=\"btn btn-sm btn-outline-success add-book\" id=\"add\" data-id=\" " + book["id"] + " \">Добавить к себе</button>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>";
        console.log(result);
        if ($(".books").not(':has(.col-md-3)')) {
            $(".books").append(result)
        } else {
            $(".books .col-md-3:last").after(result)
        }
    }

    function printMyBooks(books) {
        $(".books .col-md-3").remove();
        $.each(books, function (index, book) {
            printBook(book);
        })
    }
});