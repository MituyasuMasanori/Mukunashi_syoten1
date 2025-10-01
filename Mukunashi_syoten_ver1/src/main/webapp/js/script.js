//form.jspに組み込む
'use strict';
//cntは画面に表示した本の数
const cnt = $('input[name="cnt"]').val();
//５００ミリ秒ごとに購入冊数に入力した数を確認する
setInterval(function(){
	for(let i = 1; i <= +cnt; i++){
		//表示した本ごとに在庫数が含まれるHTMLの要素を配列（今回は要素数１）で取得
		const td = $('tr:nth-of-type(' + i + ')>td:nth-of-type(6)');
		//購入冊数に入力した数字が在庫数よりも多いか負の数か小数の場合
		if(+$(`[name="num${i}"]`).val() > +td[0].innerText || +$(`[name="num${i}"]`).val() < 0 || +$(`[name="num${i}"]`).val() % 1 != 0){
			//在庫数量の横にエラーと表示する
			$('tr:nth-of-type(' + i + ')>td:nth-of-type(7)').text("*エラー");
		}else{
			//在庫数量の横に何も表示しない
			$('tr:nth-of-type(' + i + ')>td:nth-of-type(7)').text("");
		}
	}
}, 500);