

//vamos a crear una funcion que se encargue del cifrado de cesar
//let 
var cesar = cesar || (function(){
    //funcion anonima :3 
    //callback

    var doStaff = function(txt, desp, action){
        var replace = (function(){
            //mi abecedario
            var abc = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                        'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 
                    's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
            var l = abc.length;

            //funcion que se encarga de cifrar
            return function(c){
                var i = abc.indexOf(c.toLowerCase());
                //vamos a verificar que no este vacio
                if(i != -1){
                    var pos = i;
                    if(action){
                        //avanzar
                        //en el algoritmo cesar se cifra por desplazamiento
                        pos += desp;
                        pos -= (pos>=1)?1:0;
                    }else{
                        //retrocedo
                        //descifrar por le mismo desplazamiento
                        pos -= desp;
                        pos += (pos < 0)?1:0;
                    }
                    return abc[pos];
                }
                return c;
            };
        })();

        //aqui es donde tenemos que hacer el match
        var re = (/([a-z])/ig);
        return String(txt).replace(re, function(match){
            return replace(match);
        });
    };
    //ahora solo falta saber si quiero cifrar o descifrar
    return{
        encode : function(txt, desp){
            return doStaff(txt, desp, true);
        },

        decode : function(txt, desp){
            return doStaff(txt, desp, false);
        }
    };
})();

//realizar una funcion que se encargue de codificar y decodificar

function codificar(){
    //obtener el texto del textarea
    document.getElementById("resultado").innerHTML = cesar.encode(
        document.getElementById("cadena").value, 3);
      
}

function decodificar(){
    //obtener el texto del textarea
    document.getElementById("resultado").innerHTML = cesar.decode(
        document.getElementById("cadena").value, 3);
      
}

