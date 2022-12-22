package src;

import java.awt.image.BufferedImage;
import java.util.List;

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

    public static BufferedImage cisalhamentoX(BufferedImage img, float sx){

        /**
        *            | 1  Sx 0 |   
        * Shear(x) = | 0  1  0 |  Sx = valor do cisalhamento horizontal
        *            | 0  0  1 | 
        *
        *
        * | x |   | 1  Sx 0 |   | x + y * Sx |
        * | y | * | 0  1  0 | = |      y     |
        * | 1 |   | 0  0  1 |   |      1     |
        *
        * Apenas o x tem o valor alterado, portanto precisamos ter um 
        * controle apenas desse valor para não ficar abaixo de zero e 
        * resultar em index out of bound!!!
        **/
        
        boolean negative = sx < 0 ? true : false; //verifica se o valor de cisalhamento digitado � negativo
        
        if(negative)
            sx = -sx;//teste necess�rio para evitar acessar coordenada negativa!!!

        BufferedImage tmp = new BufferedImage(((int)(img.getHeight() * sx) + img.getWidth()), img.getHeight(), img.getType());
        int newX;
        int newY;   /*variável usada para controlar o y, 
                    pois utilizar o y normal iria inverter 
                    o processo, perceba que, começando no 
                    y = 0, a imagem vai começar cisalhando 
                    de baixo (efeito que ocorre quando sx 
                    é menor que zero)*/

        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                
                newY = negative ? y : ( img.getHeight() - 1 ) - y; 
                
                newX = (int)(newY * sx) + x;
                tmp.setRGB( newX, y, img.getRGB(x, y) );
            }
        }
    
        return tmp;
    }

    public static BufferedImage cisalhamentoY(BufferedImage img, float sy){

        /**
        *            | 1  0  0 |   
        * Shear(y) = | Sy 1  0 |  Sy = valor do cisalhamento vertical
        *            | 0  0  1 | 
        *
        *
        * | x |   | 1  0  0 |   |      x     |
        * | y | * | Sy 1  0 | = | Sy * x + y |
        * | 1 |   | 0  0  1 |   |      1     |
        *
        * Apenas o y tem o valor alterado, portanto precisamos ter um 
        * controle apenas desse valor para não ficar abaixo de zero e 
        * resultar em index out of bound!!!
        **/
        boolean negative = sy < 0 ? true : false; //verifica se o valor de cisalhamento digitado � negativo
        
        if(negative)
            sy = -sy;//teste necess�rio para evitar acessar coordenada negativa!!!

        BufferedImage tmp = new BufferedImage( img.getWidth() ,((int)(img.getWidth() * sy) + img.getHeight()), img.getType());
        int newY;
        int newX;   /*variável usada para controlar o x, 
                    pois utilizar o x normal iria inverter 
                    o processo, perceba que, começando no 
                    x = 0, a imagem vai começar cisalhando 
                    da esquerda (efeito que ocorre quando 
                    sy é menor que zero)*/

        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                
                newX = negative ? x : ( img.getWidth() - 1 ) - x;
                
                newY = (int)(newX * sy) + y;
                tmp.setRGB( x, newY, img.getRGB(x, y) );
            }
        }
    
        return tmp;
    }
    
    public static BufferedImage cortarImg(BufferedImage img, List<Integer> coord){

        int xInit = coord.get(0);
        int yInit = coord.get(1);
        int xFim = coord.get(2);
        int yFim = coord.get(3);
        int larg = (xFim - xInit);
        int alt = (yFim - yInit);
        
        if(larg < 0)
            larg = -larg;
        if(alt < 0)
            alt = -alt;
        
        BufferedImage tmp = new BufferedImage( (larg + 1), (alt + 1), img.getType() );

        int xTMP, yTMP = 0;
        
        for(int y = yInit; y <= yFim ; y++){
            xTMP = 0;
            for(int x = xInit; x <= xFim; x++){
                
                tmp.setRGB(xTMP, yTMP, img.getRGB(x, y) );
                xTMP++;
                
            }
            
            yTMP++;
        }
    
        return tmp;
    }
}