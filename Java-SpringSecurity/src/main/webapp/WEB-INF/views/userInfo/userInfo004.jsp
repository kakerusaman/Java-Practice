<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>本人確認</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>

    <div class="main-container">

        <!-- 左カラム -->
        <div class="left-col">
            <p class="page-title">本人確認</p>
            <p class="page-desc">本人確認書類をご提出いただきます。</p>
        </div>

        <!-- 右カラム -->
        <div class="right-col">

            <!-- ガイダンス -->
            <div class="guidance-box">
                <p class="guidance-title">📋 身分証明書の提出について</p>
                <ul class="guidance-list">
                    <li>ご本人様名義の身分証明書をご用意ください。</li>
                    <li>有効期限内のものに限ります。</li>
                    <li>記載内容が鮮明に確認できるものをご提出ください。</li>
                    <li>提出いただいた情報は本人確認の目的のみに使用します。</li>
                </ul>
            </div>

            <form action="<c:url value='/userInfo004'/>" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <!-- 身分証明書提出への同意 -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">提出への同意</span>
                        <span class="required-badge">※必須</span>
                    </div>
                    <div class="field-input-wrap">
                        <label class="checkbox-label">
                            <input type="checkbox" name="consentSubmit" value="true" required />
                            <span class="checkbox-text">身分証明書の提出に同意します</span>
                        </label>
                    </div>
                </div>

                <!-- 身分証明書の種類 -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">証明書の種類</span>
                        <span class="required-badge">※必須</span>
                    </div>
                    <div class="field-input-wrap">
                        <select name="idType" class="field-select" onchange="togglePinInput(this)">
                            <option value="">選択してください</option>
                            <option value="driverLicense">運転免許証</option>
                            <option value="passport">パスポート</option>
                            <option value="myNumber">マイナンバーカード</option>
                            <option value="healthInsurance">健康保険証</option>
                            <option value="residenceCard">在留カード</option>
                        </select>
                    </div>
                </div>

                <!-- 暗証番号（マイナンバーカード選択時のみ表示） -->
                <div id="pinInputWrap" class="pin-input-wrap">
                    <div class="field-row">
                        <div class="field-label-wrap">
                            <span class="field-label">暗証番号</span>
                            <span class="required-badge">※必須</span>
                        </div>
                        <div class="field-input-wrap">
                            <input type="password" name="pin" placeholder="暗証番号を入力してください" />
                            <span class="field-hint">数字4桁の暗証番号を入力してください</span>
                        </div>
                    </div>
                </div>

                <!-- 身分証明書番号 -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">証明書番号</span>
                        <span class="required-badge">※必須</span>
                    </div>
                    <div class="field-input-wrap">
                        <input type="text" name="idNumber" placeholder="証明書に記載の番号を入力してください" />
                    </div>
                </div>

                <!-- 有効期限 -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">有効期限</span>
                        <span class="required-badge">※必須</span>
                    </div>
                    <div class="field-input-wrap">
                        <input type="date" name="expiryDate" />
                    </div>
                </div>

                <!-- 利用規約への同意 -->
                <div class="field-row">
                    <div class="field-label-wrap">
                        <span class="field-label">利用規約</span>
                        <span class="required-badge">※必須</span>
                    </div>
                    <div class="field-input-wrap">
                        <label class="checkbox-label">
                            <input type="checkbox" name="consentTerms" value="true" required />
                            <span class="checkbox-text">
                                <a href="#" class="login-link">利用規約</a>および
                                <a href="#" class="login-link">プライバシーポリシー</a>に同意します
                            </span>
                        </label>
                    </div>
                </div>

                <div class="submit-wrap">
                    <button type="button" class="temp-save-btn">一時保存</button>
                    <button type="submit" class="submit-btn">次へ</button>
                </div>

            </form>

        </div>
    </div>

    <script>
        function togglePinInput(select) {
            const pinInputWrap = document.getElementById('pinInputWrap');
            pinInputWrap.style.display = select.value === 'myNumber' ? 'block' : 'none';
        }
    </script>

</body>
</html>
