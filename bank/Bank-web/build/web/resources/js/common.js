/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
    $("#MyTable tr").each(function () {
        $(this).children('td:eq(5)').hide();
        $(this).children('td:eq(6)').hide();
    });
    $("#view").click(function () {
        $("#MyTable tr").each(function () {
            $(this).children('td:eq(5)').hide();
            $(this).children('td:eq(6)').hide();
        });
    });
    $("#edit").click(function () {
        $("#MyTable tr").each(function () {
            $(this).children('td:eq(5)').show();
            $(this).children('td:eq(6)').hide();
        });
    });
    $("#delete").click(function () {
        $("#MyTable tr").each(function () {
            $(this).children('td:eq(5)').hide();
            $(this).children('td:eq(6)').show();
        });
    });
});


