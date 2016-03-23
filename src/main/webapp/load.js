/**
 * 
 */

var href = window.location.href;
var pageName = href.substr(href.lastIndexOf('/') + 1);
var imageLocation = href.replace(pageName, "file/download");

refresh();

function refresh() {
    // create new image obj
    var image = new Image();

    // replace the existing image once the new image has loaded
    image.onload = function () {
        document.images["pic"].src = image.src;
    }

    // set the source of the new image to trigger the load 
    var noCacheImageLocation = imageLocation + "?" + new Date().getTime();
    image.src = noCacheImageLocation;
    
    setTimeout(refresh, 500);
}