/**
 * 共通モーダル
 *
 * @param {Object} options
 * @param {string}  options.message          - モーダルに表示するメッセージ
 * @param {string}  [options.type]           - モーダルの種類 'success' or 'error'（デフォルト: 'success'）
 * @param {number}  [options.duration]       - モーダルが自動で消えるまでの時間(ms)。0の場合は自動で消えない（デフォルト: 3000）
 * @param {boolean} [options.reload]         - モーダルが閉じた後にページを更新するか（デフォルト: false）
 */
function showModal({ message, type = 'success', duration = 3000, reload = false }) {

    // 既存のモーダルがあれば削除
    const existing = document.getElementById('common-modal');
    if (existing) existing.remove();

    // オーバーレイ（背景）
    const overlay = document.createElement('div');
    overlay.id = 'common-modal';
    overlay.style.cssText = `
        position: fixed;
        top: 0; left: 0;
        width: 100%; height: 100%;
        background: rgba(0, 0, 0, 0.5);
        display: flex;
        align-items: center;
        justify-content: center;
        z-index: 9999;
    `;

    // モーダル本体
    const modal = document.createElement('div');
    modal.style.cssText = `
        background: #fff;
        border-radius: 8px;
        padding: 32px 40px;
        min-width: 300px;
        text-align: center;
        box-shadow: 0 4px 24px rgba(0,0,0,0.2);
    `;

    // アイコン
    const icon = document.createElement('div');
    icon.style.cssText = `font-size: 40px; margin-bottom: 16px;`;
    icon.textContent = type === 'success' ? '✅' : '❌';

    // メッセージ
    const text = document.createElement('p');
    text.style.cssText = `font-size: 16px; color: #333; margin-bottom: 24px;`;
    text.textContent = message;

    // 閉じるボタン
    const closeBtn = document.createElement('button');
    closeBtn.textContent = '閉じる';
    closeBtn.style.cssText = `
        padding: 8px 24px;
        background: #1a2744;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 14px;
    `;

    const closeModal = () => {
        overlay.remove();
        if (reload) location.reload();
    };

    closeBtn.addEventListener('click', closeModal);

    modal.appendChild(icon);
    modal.appendChild(text);
    modal.appendChild(closeBtn);
    overlay.appendChild(modal);
    document.body.appendChild(overlay);

    // 自動で閉じる（duration が 0 の場合は閉じない）
    if (duration > 0) {
        setTimeout(closeModal, duration);
    }
}
