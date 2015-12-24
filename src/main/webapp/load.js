/**
 * 
 */

var href = window.location.href;
var pageName = href.substr(href.lastIndexOf('/') + 1);
var imageLocation = href.replace(pageName, "file/download");

updateImage();

function updateImage() {
	// cache breaker
	var noCacheImageLocation = imageLocation + "?" + new Date().getTime();
	
	var img = $("<img />").attr('id', 'img').attr('src', noCacheImageLocation);
	$("#img").replaceWith(img);
	
	setTimeout(updateImage, 1000);
}