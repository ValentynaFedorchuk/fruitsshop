<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Вхід до FruitShop</title>
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
                    <h3 class="card-title text-center mb-4">Вхід до системи</h3>

                    <form method="post" action="${contextPath}/login">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email адреса</label>
                            <input type="email" name="email" class="form-control" required>
                        </div>

                        <div class="mb-3 position-relative">
                            <label for="password" class="form-label">Пароль</label>
                            <input type="password" name="password" id="password" class="form-control" required>
                            <!-- Іконка Bootstrap -->
                            <i class="bi bi-eye-fill position-absolute" id="togglePassword"
                               style="top: 38px; right: 15px; cursor: pointer;"></i>
                        </div>

                        <div class="text-center mt-3">
                            <a href="${contextPath}/change-password">Змінити пароль</a>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Увійти</button>
                        </div>

                        <#if error??>
                            <div class="alert alert-danger mt-3">${error}</div>
                        </#if>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
<script src="${contextPath}/assets/js/password-toggle.js"></script>
</body>
</html>