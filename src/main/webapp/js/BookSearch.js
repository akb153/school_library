// ボタンと非表示にする要素を取得
const toggleButton = document.getElementById('toggleButton');
const toggleElement = document.getElementById('toggleElement');

// ボタンクリック時のイベントリスナーを設定
toggleButton.addEventListener('click', () => {
    // 要素の表示状態を切り替える
    if (toggleElement.style.display === 'none' || toggleElement.style.display === '') {
        toggleElement.style.display = 'block';
    } else {
        toggleElement.style.display = 'none';
    }
});