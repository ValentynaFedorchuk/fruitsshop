<nav class="navbar navbar-expand-lg navbar-light bg-light shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="${contextPath}/products">FruitShop</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNav" aria-controls="navbarNav"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <#if user??>
                    <li class="nav-item">
                        <span class="nav-link disabled"> ${user.name}</span>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/logout">Вийти</a>
                    </li>
                <#else>
                    <li class="nav-item">
                        <a class="nav-link" href="${contextPath}/">Вхід</a>
                    </li>
                </#if>
            </ul>
        </div>
    </div>
</nav>