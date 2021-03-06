/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Javier
 */
public class FXMLDocumentController implements Initializable {
    
    ArrayList<String> tokens = new ArrayList();
    @FXML
    private Label label;

      @FXML
    private TextArea entrada,resultado;
    

    private void createLexFile(String path){
        File file = new File(path);
        jflex.Main.generate(file);
        System.out.println("--------------- creado ------------");
    }
    
    @FXML
    private void accion(ActionEvent e){
        try {
            probarLexerFile();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Gramatica g = new Gramatica(tokens);
            g.P();
        
    }
   public void probarLexerFile() throws FileNotFoundException, IOException{
        File fichero = new File("fichero.txt");
        PrintWriter writer;
        try {
            writer = new  PrintWriter(fichero);
            writer.print(entrada.getText());
            writer.close();
        } catch (Exception ex) {
            
        }
        Reader reader;
        reader = new BufferedReader(new FileReader("fichero.txt"));
        Lexer lexer = new Lexer(reader);
        String Resultados="";
        
        
        while (true) {            
            Token token = lexer.yylex();
            if(token ==null){
                Resultados = Resultados ;
                resultado.setText(Resultados);
                return;
                
            }
            tokens.add(token+"");
            switch(token){
                case ERROR:
                    Resultados = Resultados + "ERROR, sintaxís erronea \n";
                    break;
                case CAD:
                case CO:
                case ID:
                case INCLUDE:
                case MAIN:
                case RETURN:
                case WHILE:
                case ExpLogica:
                case print:
                case coma: 
                case puntoYComa:
                case tipo:
                case igual:
                case suma: 
                case multiplicacion:
                case division: 
                case resta: 
                case PD:
                case PI:
                case EQUALS:
                case DIF:
                case LD:
                case LI:
                case Numero:
                    Resultados = Resultados + "\nToken: "+token+" "+lexer.save+"\n";
                    break;
                default:
                    Resultados = Resultados + "Token: "+token+"\n";
                    break;
                    
            }
            
            
        }
       
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       String aux = "src/analizadorlexico/lexico.flex";
        createLexFile(aux);
    }    
    
}
