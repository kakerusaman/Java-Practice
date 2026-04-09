<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログイン</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .auth-step-bar {
            background: #1a2744;
            padding: 16px 0;
        }
        .auth-step-inner {
            max-width: 480px;
            margin: 0 auto;
            padding: 0 24px;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 12px;
        }
        .auth-step {
            display: flex;
            align-items: center;
            gap: 8px;
            opacity: 0.4;
        }
        .auth-step.active {
            opacity: 1;
        }
        .auth-step.done {
            opacity: 0.7;
        }
        .auth-step-badge {
            width: 28px;
            height: 28px;
            border-radius: 50%;
            border: 2px solid #fff;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 13px;
            font-weight: 700;
            color: #fff;
        }
        .auth-step.active .auth-step-badge {
            background: #fff;
            color: #1a2744;
        }
        .auth-step.done .auth-step-badge {
            background: transparent;
            color: #fff;
        }
        .auth-step-label {
            font-size: 13px;
            color: #fff;
            font-weight: 500;
            letter-spacing: 0.04em;
        }
        .auth-step-arrow {
            color: rgba(255,255,255,0.4);
            font-size: 18px;
        }
        .auth-type-badge {
            display: inline-block;
            font-size: 11px;
            font-weight: 700;
            letter-spacing: 0.08em;
            color: #1a2744;
            background: #e8ecf5;
            border-radius: 3px;
            padding: 3px 10px;
            margin-bottom: 16px;
            text-transform: uppercase;
        }
    </style>
</head>
<body>

    <!-- 認証ステップバー -->
    <div class="auth-step-bar">
        <div class="auth-step-inner">
            <div class="auth-step active">
                <div class="auth-step-badge">1</div>
                <span class="auth-step-label">パスワード認証</span>
            </div>
            <span class="auth-step-arrow">›</span>
            <div class="auth-step">
                <div class="auth-step-badge">2</div>
                <span class="auth-step-label">ワンタイムトークン認証</span>
            </div>
        </div>
    </div>

    <div class="login-wrapper">
        <div class="login-card">

            <div style="text-align:center; margin-bottom: 8px;">
                <span class="auth-type-badge">Step 1 &nbsp;—&nbsp; パスワード認証</span>
            </div>

            <h1 class="login-title">ログイン</h1>

            <% if (request.getParameter("error") != null) { %>
            <div class="alert">
                ユーザー名またはパスワードが正しくありません。
            </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/login" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                <div class="login-field">
                    <label class="field-label" for="username" style="display:block; margin-bottom:6px; font-size:13px; color:#555;">ユーザー名</label>
                    <input type="text"
                           id="username"
                           name="username"
                           placeholder="ユーザー名を入力"
                           autocomplete="username"
                           required>
                </div>

                <div class="login-field">
                    <label class="field-label" for="password" style="display:block; margin-bottom:6px; font-size:13px; color:#555;">パスワード</label>
                    <input type="password"
                           id="password"
                           name="password"
                           placeholder="パスワードを入力"
                           autocomplete="current-password"
                           required>
                </div>

                <div class="submit-wrap" style="margin-top:28px;">
                    <button type="submit" class="submit-btn">ログイン</button>
                </div>
            </form>

            <div style="margin-top: 20px; padding: 14px 16px; background: #f7f8fc; border-radius: 4px; border-left: 3px solid #1a2744;">
                <p style="font-size: 12px; color: #666; line-height: 1.7; margin: 0;">
                    パスワード認証が完了すると、<br>
                    <strong style="color:#1a2744;">ワンタイムトークン認証（Step 2）</strong>に進みます。
                </p>
            </div>

        </div>
    </div>

</body>
</html>
