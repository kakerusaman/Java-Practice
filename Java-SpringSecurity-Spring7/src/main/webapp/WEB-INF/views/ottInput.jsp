<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ワンタイムトークン認証</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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
            opacity: 0.75;
        }
        .auth-step-badge {
            width: 28px;
            height: 28px;
            border-radius: 50%;
            border: 2px solid rgba(255,255,255,0.6);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 13px;
            font-weight: 700;
            color: rgba(255,255,255,0.6);
        }
        .auth-step.active .auth-step-badge {
            background: #fff;
            border-color: #fff;
            color: #1a2744;
        }
        .auth-step.done .auth-step-badge {
            background: rgba(255,255,255,0.2);
            border-color: rgba(255,255,255,0.5);
            color: #fff;
        }
        .auth-step-label {
            font-size: 13px;
            color: rgba(255,255,255,0.6);
            font-weight: 500;
            letter-spacing: 0.04em;
        }
        .auth-step.active .auth-step-label,
        .auth-step.done .auth-step-label {
            color: #fff;
        }
        .auth-step-arrow {
            color: rgba(255,255,255,0.4);
            font-size: 18px;
        }
        .done-check {
            font-size: 14px;
        }
        .auth-type-badge {
            display: inline-block;
            font-size: 11px;
            font-weight: 700;
            letter-spacing: 0.08em;
            color: #fff;
            background: #1a2744;
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
            <div class="auth-step done">
                <div class="auth-step-badge"><span class="done-check">✓</span></div>
                <span class="auth-step-label">パスワード認証</span>
            </div>
            <span class="auth-step-arrow">›</span>
            <div class="auth-step active">
                <div class="auth-step-badge">2</div>
                <span class="auth-step-label">ワンタイムトークン認証</span>
            </div>
        </div>
    </div>

    <div class="login-wrapper">
        <div class="login-card otp-card">

            <div style="text-align:center; margin-bottom: 8px;">
                <span class="auth-type-badge">Step 2 &nbsp;—&nbsp; ワンタイムトークン認証</span>
            </div>

            <div class="otp-icon">
                <svg viewBox="0 0 56 56" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <circle cx="28" cy="28" r="28" fill="#e8ecf5"/>
                    <rect x="16" y="22" width="24" height="16" rx="3" stroke="#1a2744" stroke-width="2" fill="none"/>
                    <path d="M22 22v-4a6 6 0 0 1 12 0v4" stroke="#1a2744" stroke-width="2" stroke-linecap="round"/>
                    <circle cx="28" cy="30" r="2" fill="#1a2744"/>
                    <line x1="28" y1="32" x2="28" y2="35" stroke="#1a2744" stroke-width="2" stroke-linecap="round"/>
                </svg>
            </div>

            <h1 class="login-title" style="font-size:19px;">トークンを入力してください</h1>

            <% if (request.getParameter("error") != null) { %>
            <div class="alert">
                トークンが無効または期限切れです。再度お試しください。
            </div>
            <% } %>

            <p class="otp-desc">
                パスワード認証が完了しました。<br>
                送信されたワンタイムトークンを入力してください。
            </p>

            <form id="otpForm" action="${pageContext.request.contextPath}/login/ott" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <input type="hidden" id="otpCode" name="token" value="">

                <div class="otp-inputs">
                    <input type="text" class="otp-box" maxlength="1" inputmode="numeric" pattern="[0-9]">
                    <input type="text" class="otp-box" maxlength="1" inputmode="numeric" pattern="[0-9]">
                    <input type="text" class="otp-box" maxlength="1" inputmode="numeric" pattern="[0-9]">
                    <span class="otp-separator">—</span>
                    <input type="text" class="otp-box" maxlength="1" inputmode="numeric" pattern="[0-9]">
                    <input type="text" class="otp-box" maxlength="1" inputmode="numeric" pattern="[0-9]">
                    <input type="text" class="otp-box" maxlength="1" inputmode="numeric" pattern="[0-9]">
                </div>

                <div class="submit-wrap">
                    <button type="submit" id="otpSubmit" class="submit-btn" disabled>認証する</button>
                </div>
            </form>

            <div style="margin-top: 20px; padding: 14px 16px; background: #f7f8fc; border-radius: 4px; border-left: 3px solid #4caf50;">
                <p style="font-size: 12px; color: #666; line-height: 1.7; margin: 0;">
                    <strong style="color:#2e7d32;">✓ Step 1 完了：</strong>パスワード認証済み<br>
                    6桁のワンタイムトークンを入力して認証を完了してください。
                </p>
            </div>

        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/otpInput.js"></script>

</body>
</html>
