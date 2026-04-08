$(function() {

    // 文字数制限（maxlength属性を削除されても機能する）
    $('#loginId').on('input', function() {
        if ($(this).val().length > 20) {
            $(this).val($(this).val().substring(0, 20));
        }
    });

    $('#password').on('input', function() {
        if ($(this).val().length > 255) {
            $(this).val($(this).val().substring(0, 255));
        }
    });

    // エラー表示ヘルパー
    function showError($input, message) {
        $input.addClass('input-error');
        var $wrap = $input.closest('.field-input-wrap');
        $wrap.find('.js-error').remove();
        $wrap.append('<span class="js-error field-error">' + message + '</span>');
    }

    // エラー解除ヘルパー
    function clearError($input) {
        $input.removeClass('input-error');
        $input.closest('.field-input-wrap').find('.js-error').remove();
    }

    // ログインID：必須
    $('#loginId').on('blur', function() {
        if ($(this).val() === '') {
            showError($(this), 'ログインIDを入力してください。');
        } else {
            clearError($(this));
        }
    });

    // パスワード：必須
    $('#password').on('blur', function() {
        if ($(this).val() === '') {
            showError($(this), 'パスワードを入力してください。');
        } else {
            clearError($(this));
        }
    });

    // メールアドレス：必須のみ
    $('#email').on('blur', function() {
        var val = $(this).val();
        if (val === '') {
            showError($(this), 'メールアドレスを入力してください。');
        } else {
            clearError($(this));
        }
    });

});
