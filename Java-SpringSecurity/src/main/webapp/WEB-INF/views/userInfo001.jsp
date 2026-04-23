<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>本人情報</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>

    <div class="main-container">

        <!-- 左カラム -->
        <div class="left-col">
            <p class="page-title">本人情報</p>
            <p class="page-desc">ご本人の情報を入力してください。</p>
        </div>

        <!-- 右カラム -->
        <div class="right-col">

            <form action="<c:url value='/userInfo'/>" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <!-- 苗字 -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">苗字</span>
                        <span class="required-badge">※必須</span>
                    </div>
                    <div class="field-input-wrap">
                        <input type="text" name="lastName" placeholder="例）山田" />
                    </div>
                </div>

                <!-- 名前 -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">名前</span>
                        <span class="required-badge">※必須</span>
                    </div>
                    <div class="field-input-wrap">
                        <input type="text" name="firstName" placeholder="例）太郎" />
                    </div>
                </div>

                <!-- 苗字カナ -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">苗字カナ</span>
                        <span class="required-badge">※必須</span>
                    </div>
                    <div class="field-input-wrap">
                        <input type="text" name="lastNameKana" placeholder="例）ヤマダ" />
                    </div>
                </div>

                <!-- 名前カナ -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">名前カナ</span>
                        <span class="required-badge">※必須</span>
                    </div>
                    <div class="field-input-wrap">
                        <input type="text" name="firstNameKana" placeholder="例）タロウ" />
                    </div>
                </div>

                <!-- 住所 -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">住所</span>
                        <span class="required-badge">※必須</span>
                    </div>
                    <div class="field-input-wrap">
                        <input type="text" name="address" placeholder="例）東京都新宿区新宿" />
                    </div>
                </div>

                <!-- 番地 -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">番地</span>
                        <span class="required-badge">※必須</span>
                    </div>
                    <div class="field-input-wrap">
                        <input type="text" name="streetNumber" placeholder="例）1-2-3" />
                    </div>
                </div>

                <!-- 生年月日 -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">生年月日</span>
                        <span class="required-badge">※必須</span>
                    </div>
                    <div class="field-input-wrap">
                        <input type="date" name="birthDate" />
                    </div>
                </div>

                <div class="submit-wrap">
                    <button type="submit" class="submit-btn">登録する</button>
                </div>

            </form>

        </div>
    </div>

</body>
</html>
