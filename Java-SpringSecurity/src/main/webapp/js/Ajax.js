/**
 * 共通Ajaxメソッド
 *
 * @param {Object} options
 * @param {string}   options.url            - リクエスト先URL
 * @param {string}   [options.method]       - HTTPメソッド（デフォルト: 'GET'）
 * @param {Object}   [options.data]         - 送信するデータ
 * @param {Function} [options.onSuccess]    - 成功時のコールバック (data) => {}
 * @param {Function} [options.onError]      - エラー時のコールバック (status, message) => {}
 */
async function ajaxRequest({ url, method = 'GET', data = null, onSuccess, onError }) {
    const headers = { 'Content-Type': 'application/json' };

    // Spring Security の CSRF トークンをメタタグから自動付与
    const csrfTokenMeta = document.querySelector('meta[name="_csrf"]');
    const csrfHeaderMeta = document.querySelector('meta[name="_csrf_header"]');
    if (csrfTokenMeta && csrfHeaderMeta) {
        headers[csrfHeaderMeta.getAttribute('content')] = csrfTokenMeta.getAttribute('content');
    }

    const options = {
        method,
        headers,
    };

    if (data) {
        options.body = JSON.stringify(data);
    }

    try {
        const response = await fetch(url, options);

        if (!response.ok) {
            const message = await response.text();
            if (onError) {
                onError(response.status, message);
            }
            return;
        }

        const result = await response.json();
        if (onSuccess) {
            onSuccess(result);
        }

    } catch (e) {
        if (onError) {
            onError(0, '通信エラーが発生しました');
        }
    }
}
