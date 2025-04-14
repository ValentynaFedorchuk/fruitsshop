<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Каталог фруктів</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="${contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/assets/css/style.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <script src="${contextPath}/assets/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<#include "navbar.ftl">

<div class="container mt-4">
    <h1 class="mb-4">Каталог фруктів</h1>
    <div class="row">
        <#list fruits as fruit>
            <div class="col-12 col-md-6 col-lg-4 col-xl-3 mb-4">
                <div class="card h-100">
                    <#if fruit.imageUrl?? && fruit.imageUrl?length gt 0>
                        <img src="${contextPath}/assets/images/${fruit.imageUrl}" class="card-img-top" alt="${fruit.name}">
                    <#else>
                        <img src="${contextPath}/assets/images/default.png" class="card-img-top" alt="Зображення відсутнє">
                    </#if>
                    <div class="card-body">
                        <h5 class="card-title">${fruit.name}</h5>
                        <p class="card-text">${fruit.description!"Опис відсутній"}</p>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>

</body>
</html>