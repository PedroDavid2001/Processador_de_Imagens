package src;

import java.awt.image.BufferedImage;

public class BitMapping {
    
    public static BufferedImage esteganografia(Processador img, String texto) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int index = 0;
        int caractere;
        
        for (int y = 0; y < tmp.getHeight(); y++) {
            for(int x = 0; x < tmp.getWidth() - 8; x += 8) {
                
                if(index < texto.length())
                    caractere = texto.charAt( index++ );
                else
                    caractere = 0;
                
                for(int i = 0; i < 8; i++) {
                    
                    int bit = caractere & (1 << i);
                    int rgb = img.getRGB(x + i, y);
                    
                    if(bit != 0) {
                        rgb = rgb | 1;
                    }
                    else {
                        rgb = rgb & ~1;
                    }
                    
                    tmp.setRGB(x + i, y, rgb);
                }
            }
            
        }
        
        return tmp.getImg();
    }
    
    public static String esteganalise(Processador img) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        String texto = "";
        
        for (int y = 0; y < tmp.getHeight(); y++) {
            for(int x = 0; x < tmp.getWidth() - 8; x += 8) {
                
                String caractere = "";
                
                for(int i = 0; i < 8; i++) {
                    
                    int bit = img.getRGB(x + i, y) & 1;
                    
                    if(bit == 1) {
                        caractere += "1";
                    }
                    else if(bit == 0) {
                        caractere += "0";
                    }
                    
                }
                
                texto = texto.concat( binario(caractere) );
            }
            
        }
        
        return texto;
    }
    
    private static String binario( String bin ) {
        
        String newBin = "";

        for (int i = bin.length() - 1; i >= 0; i--) {
            newBin += Character.toString( bin.charAt(i) );
        }

        int letra = Integer.parseInt(newBin, 2);
        
        if(letra == 0)
            return "";
        
        return Character.toString((char)letra);
    }
    
}
