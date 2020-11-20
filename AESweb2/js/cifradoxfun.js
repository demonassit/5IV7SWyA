//como si fuera jquerry

//vue, react, angular

$(function(){
    $("#damelaCrypto").on("click", function(){
        var elhtml = $("#elhtml").val();
        var pass = $("#clave").val();

        //ahora vamos a cifrar
        var encriptar = CryptoJS.AES.encrypt(elhtml, pass).toString();
        //todo se pasa a una cadena para manipular la variable

        $("#encriptado").val(encriptar);

        //imprimirlo en consola
        console.log(encriptar, pass);
    });
});