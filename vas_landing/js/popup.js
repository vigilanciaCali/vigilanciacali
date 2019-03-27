// Get the modal
/*

var modal = document.getElementById('modal1');

// Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById('video1');
var captionText = document.getElementById("caption");
img.onclick = function(){
    modal.style.display = "block";
    captionText.innerHTML = this.alt;
}
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
// When the user clicks on <span> (x), close the modal
span.onclick = function() { 
    modal.style.display = "none";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
        }
    }
*/

//----------------------------------------------------------------

// Get the modal
//var modal1 = document.getElementById("modal1");
var modal2 = document.getElementById("modal2");
var modal3 = document.getElementById("modal3");
//var modal4 = document.getElementById("modal4");

// Get the div that opens the modal
//var video1 = document.getElementById("video1");
var video2 = document.getElementById("video2");
var video3 = document.getElementById("video3");
//var video4 = document.getElementById("video4");


//var btn2 = document.getElementById("myBtn2");

// Get the button that opens the modal
//var btn1 = document.getElementById("myBtn1");
//var btn2 = document.getElementById("myBtn2");


// Get the <span> element that closes the modal
//var span1 = document.getElementById("close");
var span2 = document.getElementById("close2");
var span3 = document.getElementById("close3");
//var span4 = document.getElementById("close4");
//var span2 = document.getElementsByClassName("close2")[0];


// When the user clicks the button, open the modal
/*video1.onclick = function() {
    modal1.style.display = "block";
}*/
video2.onclick = function() {
    modal2.style.display = "block";
}
video3.onclick = function() {
    modal3.style.display = "block";
}
/*video4.onclick = function() {
    modal4.style.display = "block";
}*/

// When the user clicks on <span> (x), close the modal
/*span1.onclick = function() {
    modal1.style.display = "none";
}*/

span2.onclick = function() {
    modal2.style.display = "none";
}

span3.onclick = function() {
    modal3.style.display = "none";
}

/*span4.onclick = function() {
    modal4.style.display = "none";
}*/

window.onclick = function(event) {
    /*if (event.target == modal1) {
        modal1.style.display = "none";
    }*/
    if (event.target == modal2) {
        modal2.style.display = "none";
    }
    if (event.target == modal3) {
        modal3.style.display = "none";
    }
    /*if (event.target == modal4) {
        modal4.style.display = "none";
    }*/
}