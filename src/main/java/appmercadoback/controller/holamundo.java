package appmercadoback.controller;

import org.apache.coyote.http11.filters.SavedRequestInputFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class holamundo {
    @GetMapping("/hola")
    public String holamundo(){
        return "holaaa";
    }

    @GetMapping("/saludo/{name}")
    public String saludo(@PathVariable String name){
        return "hola "+name;
    }

    @GetMapping("/division/{value}")
    public int division(@PathVariable String value){
        int num = Integer.parseInt(value);
        return num/2;
    }

    @GetMapping("/palindromo/{palabra}")
    public String palindromo(@PathVariable String palabra) {
        int tamanio = palabra.length();
        String ret = "no";
        int recorrido = tamanio - 1;
        tamanio = tamanio / 2;
        for (int i=0; i<tamanio; i++){
            if(palabra.charAt(i) == palabra.charAt(recorrido)){
                ret = "si";
            }
            else{
                return "no";
            }
            recorrido --;
        }
        return ret;
    }
}
