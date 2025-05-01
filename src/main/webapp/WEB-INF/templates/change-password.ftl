<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Зміна паролю</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="${contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <link href="${contextPath}/assets/css/style.css" rel="stylesheet">

    <!-- Bootstrap JS -->
    <script src="${contextPath}/assets/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<#include "navbar.ftl">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="card-title text-center mb-4">Зміна паролю</h3>

                    <form method="post" action="${contextPath}/change-password">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email адреса</label>
                            <input type="email" name="email" class="form-control" required>
                        </div>

                        <div class="mb-3 position-relative">
                            <label for="oldPassword" class="form-label">Старий пароль</label>
                            <input type="password" name="oldPassword" id="oldPassword" class="form-control" required>

                        </div>

                        <div class="mb-3 position-relative">
                            <label for="newPassword" class="form-label">Новий пароль</label>
                            <input type="password" name="newPassword" id="newPassword" class="form-control" required>

                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Змінити пароль</button>
                        </div>

                        <#if error??>
                            <div class="alert alert-danger mt-3">${error}</div>
                        </#if>

                        <#if changePasswordSuccess??>
                            <div class="alert alert-success mt-3">${changePasswordSuccess}</div>
                        </#if>

                        <#if error?? || changePasswordSuccess??>
                            <div class="d-flex justify-content-center mt-3">
                                <a href="${contextPath}/home" class="btn btn-primary">На головну</a>
                            </div>
                        </#if>

                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>