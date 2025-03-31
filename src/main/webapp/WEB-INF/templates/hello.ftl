<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Підключаємо Bootstrap -->
    <link rel="stylesheet" href="${contextPath}/assets/css/bootstrap.min.css">
    <script src="${contextPath}/assets/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <link rel="stylesheet" href="${contextPath}/assets/css/style.css">

    <title>Bootstrap demo</title>
</head>
<body>
<h1>${message} test</h1>
<h2>test 2</h2>


<div class="container mt-4">
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
                        <p class="card-text">
                            <#if fruit.description??>${fruit.description}<#else>Опис відсутній</#if>
                        </p>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>

</body>
</html>