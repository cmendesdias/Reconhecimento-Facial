package main;

import java.io.File;

public class cadastroConexao{
    
    private String email, name, nivelAcesso;
    
    private File imagem;
    
    public cadastroConexao(String a, String b, String c, File d){

        this.email = a;  
        this.name = b;   
        this.nivelAcesso = c;  
        this.imagem = d; 
    }
}
