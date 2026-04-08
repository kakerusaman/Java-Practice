$(function() {

    var $boxes = $('.otp-box');
    var $submit = $('#otpSubmit');
    var $otpCode = $('#otpCode');

    // 数字以外の入力を弾く・1文字入力したら次へ移動
    $boxes.on('input', function() {
        var val = $(this).val().replace(/[^0-9]/g, '');
        $(this).val(val);

        if (val.length === 1) {
            var index = $boxes.index(this);
            if (index < $boxes.length - 1) {
                $boxes.eq(index + 1).focus();
            }
        }

        updateSubmit();
    });

    // バックスペースで前のボックスに戻る
    $boxes.on('keydown', function(e) {
        if (e.key === 'Backspace' && $(this).val() === '') {
            var index = $boxes.index(this);
            if (index > 0) {
                $boxes.eq(index - 1).focus().val('');
                updateSubmit();
            }
        }
    });

    // 貼り付け対応（6桁をコピペしたとき各ボックスに分配）
    $boxes.eq(0).on('paste', function(e) {
        var pasted = (e.originalEvent.clipboardData || window.clipboardData).getData('text');
        var digits = pasted.replace(/[^0-9]/g, '').substring(0, 6);
        if (digits.length === 6) {
            $boxes.each(function(i) {
                $(this).val(digits[i]);
            });
            $boxes.last().focus();
            updateSubmit();
            e.preventDefault();
        }
    });

    // 6桁が全て入力されたらボタンを有効化・hiddenにセット
    function updateSubmit() {
        var code = '';
        $boxes.each(function() {
            code += $(this).val();
        });
        $otpCode.val(code);
        $submit.prop('disabled', code.length < 6);
    }

    // フォーム送信前に6桁をhiddenにセット
    $('#otpForm').on('submit', function() {
        var code = '';
        $boxes.each(function() {
            code += $(this).val();
        });
        $otpCode.val(code);
    });

});
