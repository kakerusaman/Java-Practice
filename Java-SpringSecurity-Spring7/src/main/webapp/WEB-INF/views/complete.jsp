<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログイン完了</title>
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
            background: rgba(255,255,255,0.2);
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
    </style>
</head>
<body>

    <!-- 認証ステップバー（全ステップ完了） -->
    <div class="auth-step-bar">
        <div class="auth-step-inner">
            <div class="auth-step">
                <div class="auth-step-badge">✓</div>
                <span class="auth-step-label">パスワード認証</span>
            </div>
            <span class="auth-step-arrow">›</span>
            <div class="auth-step">
                <div class="auth-step-badge">✓</div>
                <span class="auth-step-label">ワンタイムトークン認証</span>
            </div>
        </div>
    </div>

    <div class="complete-container">

        <div class="complete-icon">
            <svg viewBox="0 0 80 80" fill="none" xmlns="http://www.w3.org/2000/svg">
                <circle cx="40" cy="40" r="40" fill="#e8ecf5"/>
                <circle cx="40" cy="40" r="28" fill="#1a2744"/>
                <polyline points="27,40 36,50 54,31"
                          stroke="#fff"
                          stroke-width="4"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                          fill="none"/>
            </svg>
        </div>

        <h1 class="complete-title">ログイン完了</h1>

        <p class="complete-desc">
            パスワード認証とワンタイムトークン認証の<br>
            両方が正常に完了しました。
        </p>

        <div style="margin-bottom: 36px; padding: 16px 24px; background: #f7f8fc; border-radius: 6px; border: 1px solid #e0e4ef; text-align: left; display: inline-block; min-width: 280px;">
            <p style="font-size: 12px; color: #888; margin-bottom: 10px; letter-spacing: 0.05em; font-weight: 700; text-transform: uppercase;">認証済み要素</p>
            <div style="display: flex; flex-direction: column; gap: 8px;">
                <div style="display: flex; align-items: center; gap: 8px; font-size: 13px; color: #333;">
                    <span style="color: #2e7d32; font-weight: 700;">✓</span>
                    <span>パスワード認証 <span style="font-size: 11px; color: #888;">FACTOR_PASSWORD</span></span>
                </div>
                <div style="display: flex; align-items: center; gap: 8px; font-size: 13px; color: #333;">
                    <span style="color: #2e7d32; font-weight: 700;">✓</span>
                    <span>ワンタイムトークン認証 <span style="font-size: 11px; color: #888;">FACTOR_OTT</span></span>
                </div>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <button type="submit" class="submit-btn" style="width: 200px;">ログアウト</button>
        </form>

    </div>

</body>
</html>
