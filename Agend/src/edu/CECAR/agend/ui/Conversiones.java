/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.CECAR.agend.ui;

/**
 *
 * @author Yesid Lazaro Mayoriano
 */
public class Conversiones {
    
      final static float pesos=(float) 1907.76;
      final static float euro=(float) 2481.23;
      final static float dolar_euro=(float) 0.7704;
      final static float euro_dolar=(float) 1.2980;
      
    
    
    public static float dolarToPesos(float p_dolar){
        
        return (float) (p_dolar * pesos);        
    }  
    
     public static float pesosToDolar(float p_pesos){
        
        return (float) (p_pesos/pesos);        
    }
    
    public static float euroToPesos(float p_euro){
        
        return (float) (p_euro * euro);        
    } 
    
    public static float pesosToEuro(float p_pesos){
        
        return (float) (p_pesos/euro);        
    }
    
    public static float dolarToEuro(float p_dolar){
        
        return (float) (p_dolar * dolar_euro);
        
     }
    
    public static float euroToDolar(float p_euro){
        
        return (float) (p_euro * 1.2980);
        
     }
    
    
}




