package src;

import java.awt.image.BufferedImage;

public class Transformacoes {

    //--------------------------------------------------------------
    //transformações geométricas

    public static BufferedImage horizontalMirror( BufferedImage img ){
        
        BufferedImage tmp = new BufferedImage( img.getWidth(), 
            img.getHeight(), img.getType() );

        for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) 
                tmp.setRGB( x, y, img.getRGB( ( img.getWidth() - 1 ) - x, y ) );
            
        return tmp;
    }

    public static BufferedImage verticalMirror( BufferedImage img ){
        
        BufferedImage tmp = new BufferedImage( img.getWidth(), 
            img.getHeight(), img.getType() );

        for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) 
                tmp.setRGB( x, y, img.getRGB( x, ( img.getHeight() - 1 ) - y ) );
            
        return tmp;
    }
}