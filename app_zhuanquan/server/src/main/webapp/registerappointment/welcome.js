/**
 * Created by army on 2017/7/10.
 */

var $window = $(window);
var WIDTH = 1980;
var HEIGHT = 1080;
var RATIO = WIDTH / HEIGHT;

var $rainbow = $('#rainbow');

function resize() {
  var width = $window.width();
  var height = $window.height();
  // console.log(width / height, RATIO);
  if(width / height >= RATIO) {
    $rainbow.css('width', '22%');
    $rainbow.css('top', width * 0.28);
  }
  else {
    $rainbow.css('width', height * 0.5);
    $rainbow.css('top', height * 0.55);
  }
}
resize();
$window.on('resize', resize);

var $bird1 = $('#bird1');
var $bird2 = $('#bird2');
var $bird3 = $('#bird3');
var count = 0;
setInterval(function() {
  switch (count) {
    case 0:
      $bird1.addClass('bird-hide');
      $bird2.removeClass('bird-hide');
      break;
    case 1:
      $bird2.addClass('bird-hide');
      $bird3.removeClass('bird-hide');
      break;
    case 2:
      $bird3.addClass('bird-hide');
      $bird2.removeClass('bird-hide');
      break;
    case 3:
      $bird2.addClass('bird-hide');
      $bird1.removeClass('bird-hide');
      break;
  }
  count++;
  if(count == 4) {
    count = 0;
  }
}, 1000);

var $yuyue = $('#yuyue');
var $favor = $('#favor');
// $yuyue.on('click', function(e) {
//   e.preventDefault();
//   window.open(redirectUrl);
// });
$favor.on('click', function(e) {
  e.preventDefault();
  $yuyue.parent().parent().removeClass('fn-hide');
  $favor.parent().parent().addClass('fn-hide');
});
if(location.search.indexOf('finish=1') > -1) {
  $favor.parent().parent().removeClass('fn-hide');
}
else {
  $yuyue.parent().parent().removeClass('fn-hide');
}

var now = new Date();
var hour = now.getHours();
if(hour >= 19 || hour < 6) {
  $(document.body).addClass('night');
  $bird1.addClass('bbird1');
  $bird2.addClass('bbird2');
  $bird3.addClass('bbird3');
  $rainbow.remove();
}

function loginSuccess() {
  $yuyue.parent().parent().hide();
  $favor.parent().parent().removeClass('fn-hide');
}