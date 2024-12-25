jQuery(function(){
  jQuery('.deadline').each(function(index, element){
    var deadline = element.innerText;
    var date = new Date().toLocaleDateString("ja-JP", {year: "numeric",month: "2-digit",
   day: "2-digit"}).replaceAll('/', '-');
    let deadline_warning = document.getElementById('deadline_warning');
    console.log('test');
    if (deadline < date) {
      jQuery(this).css({
        'background-color': 'red',
        'color': 'black'});
      deadline_warning.style.display = 'block';
    }
  });
  
    jQuery('.reserving_order').each(function(index, element){
    var reserving_order = element.innerText;
    var borrowing_flg = element.nextElementSibling.innerText;
    let reserving_order_warning = document.getElementById('reserving_order_warning');

    if (reserving_order == "1位" && borrowing_flg =="未貸出") {
      jQuery(this).css({
        'background-color': 'red',
        'color': 'black'});
      element.innerText = "資料の準備ができています。";
      reserving_order_warning.style.display = 'block';
    }
  });

});
