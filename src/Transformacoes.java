package src;

import java.awt.image.BufferedImage;

/**
* Para multiplicar duas matrizes, A e B, devemos multiplicar 
* os elementos de das linhas de A pelas colunas de B e somar 
* cada produto.
* 
* - Para exemplificar, utilizaremos as matrizes A(2 x 3) e B(3 x 2)
* 
* | a  b  c |   | A  B  C |   | (a*A + b*D + c*G)  (a*B + c*H + c*E)  (a*C + c*F + c*I) | 
* | d  e  f | x | D  E  F | = | (d*A + e*D + f*G)  (d*B + e*H + f*E)  (d*C + e*F + f*I) |
* | g  h  i |   | G  H  I |   | (g*A + h*D + i*G)  (g*B + h*H + i*E)  (g*C + h*F + i*I) |
* 
* - Em uma imagem, podemos entender cada elemento como:
* 
*       | Img(0,0)  Img(1,0)  Img(2,0) |   
* Img = | Img(0,1)  Img(1,1)  Img(2,1) |  
*       | Img(0,2)  Img(1,2)  Img(2,2) |   
* 
*        | Img2(0,0)  Img2(1,0)  Img2(2,0) |   
* Img2 = | Img2(0,1)  Img2(1,1)  Img2(2,1) |   
*        | Img2(0,2)  Img2(1,2)  Img2(2,2) |
* 
*        | (a*A + b*D + c*G)  (a*B + c*H + c*E)  (a*C + c*F + c*I) | 
* ImgF = | (d*A + e*D + f*G)  (d*B + e*H + f*E)  (d*C + e*F + f*I) |
*        | (g*A + h*D + i*G)  (g*B + h*H + i*E)  (g*C + h*F + i*I) |
* 
**/

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