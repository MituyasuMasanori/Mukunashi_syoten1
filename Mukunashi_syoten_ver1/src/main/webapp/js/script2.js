//payment.jspに組み込む
'use strict';
$('span').html("<input type=\"submit\" id=\"btn\" value=\"購入をキャンセルする\">");
$('em').html(`<br>5秒以内なら注文をキャンセルできます`);
let count = 4;

//payment.jspを表示してから、5秒から1秒までカウントダウンを表示する
const timerID = setInterval(function(){
		//0秒になったら何も表示しないでカウントダウンを止める
		if(count == 0){
			$('em').html('');
			return clearInterval(timerID);
		}
		$('em').html(`<br>${count}秒以内なら注文をキャンセルできます`);
		count--;
	},
	1000
);

//メールが送信される、payment.jspを表示してから五秒後に「購入をキャンセルする」ボタンが表示されなくなる
setTimeout(
	function(){
		$('span').html("<input type=\"hidden\" id=\"btn\" value=\"購入をキャンセルする\">");
	},
	5000
);