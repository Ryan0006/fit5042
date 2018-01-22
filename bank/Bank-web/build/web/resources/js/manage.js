/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function optionchange() {
    var e = document.getElementById("myForm:option");
    var show = e.options[e.selectedIndex].text  === 'Transfer' ? true : false;
    if (show === true) {
        $("#receiver").show();
    }
    if (show === false) {
        $("#receiver").hide();
    }
}

$(function () {
    $("#receiver").hide();
});




