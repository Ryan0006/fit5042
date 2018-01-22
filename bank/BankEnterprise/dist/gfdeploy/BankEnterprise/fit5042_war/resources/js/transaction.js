/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {

    $(".detail").hide();
    $('button').click(function () {
        $(this).closest('tr').next().find('.detail').toggle();

    });
});

