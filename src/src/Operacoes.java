package src;

import java.awt.image.BufferedImage;

public class Operacoes {
    //--------------------------------------------------------------
    //operações aritméticas

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

    public static BufferedImage addNormal( Processador imgA, Processador imgB ){
        
        Processador imgF = new Processador();
        
        imgF.setImg( imgA.getImg() );

        int r, g, b;
        int maiorR = 0, maiorG = 0, maiorB = 0;
        int menorR = 255, menorG = 255, menorB = 255;

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) + imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) + imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) + imgB.nivelBlue(x, y);
                
                if(r > maiorR)
                    maiorR = r;
                if(g > maiorG)
                    maiorG = g;
                if(b > maiorB)
                    maiorB = b;

                if(r < menorR)
                    menorR = r;
                if(g < menorG)
                    menorG = g;
                if(b < menorB)
                    menorB = b;
            }
        }

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) + imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) + imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) + imgB.nivelBlue(x, y);
             
                r = ( ( ( 255 * (r - menorR) ) / (maiorR - menorR) ) );
                g = ( ( ( 255 * (g - menorG) ) / (maiorG - menorG) ) );
                b = ( ( ( 255 * (b - menorB) ) / (maiorB - menorB) ) );

                imgF.setRGB( x, y, r, g, b );
            }
        }

        return imgF.getImg();
    }

    public static BufferedImage subNormal( Processador imgA, Processador imgB ){
        
        Processador imgF = new Processador();
        
        imgF.setImg( imgA.getImg() );

        int r, g, b;
        int maiorR = 0, maiorG = 0, maiorB = 0;
        int menorR = 255, menorG = 255, menorB = 255;

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) - imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) - imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) - imgB.nivelBlue(x, y);
                
                if(r > maiorR)
                    maiorR = r;
                if(g > maiorG)
                    maiorG = g;
                if(b > maiorB)
                    maiorB = b;

                if(r < menorR)
                    menorR = r;
                if(g < menorG)
                    menorG = g;
                if(b < menorB)
                    menorB = b;
            }
        }

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) - imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) - imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) - imgB.nivelBlue(x, y);
                
                r = ( ( ( 255 * (r - menorR) ) / (maiorR - menorR) ) );
                g = ( ( ( 255 * (g - menorG) ) / (maiorG - menorG) ) );
                b = ( ( ( 255 * (b - menorB) ) / (maiorB - menorB) ) );

                imgF.setRGB( x, y, r, g, b );
            }
        }

        return imgF.getImg();
    }

    public static BufferedImage multiNormal( Processador imgA, Processador imgB ){
        
        Processador imgF = new Processador();
        
        imgF.setImg( imgA.getImg() );

        int r, g, b;
        int maiorR = 0, maiorG = 0, maiorB = 0;
        int menorR = 255, menorG = 255, menorB = 255;

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) * imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) * imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) * imgB.nivelBlue(x, y);
                
                if(r > maiorR)
                    maiorR = r;
                if(g > maiorG)
                    maiorG = g;
                if(b > maiorB)
                    maiorB = b;

                if(r < menorR)
                    menorR = r;
                if(g < menorG)
                    menorG = g;
                if(b < menorB)
                    menorB = b;
            }
        }

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) * imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) * imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) * imgB.nivelBlue(x, y);
             
                r = ( ( ( 255 * (r - menorR) ) / (maiorR - menorR) ) );
                g = ( ( ( 255 * (g - menorG) ) / (maiorG - menorG) ) );
                b = ( ( ( 255 * (b - menorB) ) / (maiorB - menorB) ) );

                imgF.setRGB( x, y, r, g, b );
            }
        }

        return imgF.getImg();
    }

    public static BufferedImage andNormal( Processador imgA, Processador imgB ){
        
        Processador imgF = new Processador();
        
        imgF.setImg( imgA.getImg() );

        int r, g, b;
        int maiorR = 0, maiorG = 0, maiorB = 0;
        int menorR = 255, menorG = 255, menorB = 255;

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) & imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) & imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) & imgB.nivelBlue(x, y);
                
                if(r > maiorR)
                    maiorR = r;
                if(g > maiorG)
                    maiorG = g;
                if(b > maiorB)
                    maiorB = b;

                if(r < menorR)
                    menorR = r;
                if(g < menorG)
                    menorG = g;
                if(b < menorB)
                    menorB = b;
            }
        }

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) & imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) & imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) & imgB.nivelBlue(x, y);
             
                r = ( ( ( 255 * (r - menorR) ) / (maiorR - menorR) ) );
                g = ( ( ( 255 * (g - menorG) ) / (maiorG - menorG) ) );
                b = ( ( ( 255 * (b - menorB) ) / (maiorB - menorB) ) );

                imgF.setRGB( x, y, r, g, b );
            }
        }

        return imgF.getImg();
    }

    public static BufferedImage orNormal( Processador imgA, Processador imgB ){
        
        Processador imgF = new Processador();
        
        imgF.setImg( imgA.getImg() );

        int r, g, b;
        int maiorR = 0, maiorG = 0, maiorB = 0;
        int menorR = 255, menorG = 255, menorB = 255;

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) | imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) | imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) | imgB.nivelBlue(x, y);
                
                if(r > maiorR)
                    maiorR = r;
                if(g > maiorG)
                    maiorG = g;
                if(b > maiorB)
                    maiorB = b;

                if(r < menorR)
                    menorR = r;
                if(g < menorG)
                    menorG = g;
                if(b < menorB)
                    menorB = b;
            }
        }

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) | imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) | imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) | imgB.nivelBlue(x, y);
             
                r = ( ( ( 255 * (r - menorR) ) / (maiorR - menorR) ) );
                g = ( ( ( 255 * (g - menorG) ) / (maiorG - menorG) ) );
                b = ( ( ( 255 * (b - menorB) ) / (maiorB - menorB) ) );

                imgF.setRGB( x, y, r, g, b );
            }
        }

        return imgF.getImg();
    }

    public static BufferedImage xorNormal( Processador imgA, Processador imgB ){
        
        Processador imgF = new Processador();
        
        imgF.setImg( imgA.getImg() );

        int r, g, b;
        int maiorR = 0, maiorG = 0, maiorB = 0;
        int menorR = 255, menorG = 255, menorB = 255;

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) ^ imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) ^ imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) ^ imgB.nivelBlue(x, y);
                
                if(r > maiorR)
                    maiorR = r;
                if(g > maiorG)
                    maiorG = g;
                if(b > maiorB)
                    maiorB = b;

                if(r < menorR)
                    menorR = r;
                if(g < menorG)
                    menorG = g;
                if(b < menorB)
                    menorB = b;
            }
        }

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) ^ imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) ^ imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) ^ imgB.nivelBlue(x, y);
             
                r = ( ( ( 255 * (r - menorR) ) / (maiorR - menorR) ) );
                g = ( ( ( 255 * (g - menorG) ) / (maiorG - menorG) ) );
                b = ( ( ( 255 * (b - menorB) ) / (maiorB - menorB) ) );

                imgF.setRGB( x, y, r, g, b );
            }
        }

        return imgF.getImg();
    }

    /*
     * No truncamento, os valores que extrapolarem o range 0-255 são 
     * cortados (truncados) para se encaixarem nele. Portanto, se:
     * valor > 255 -> valor = 255
     * valor < 0 -> valor = 0 
    */

    public static BufferedImage addTrunc( Processador imgA, Processador imgB ){
        
        Processador imgF = new Processador();
        
        imgF.setImg( imgA.getImg() );

        int r, g, b;

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) + imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) + imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) + imgB.nivelBlue(x, y);
                
                // teste de overflow
                r = r > 255 ? 255 : r; 
                g = g > 255 ? 255 : g;
                b = b > 255 ? 255 : b;
                
                imgF.setRGB( x, y, r, g, b );
            }
        }

        return imgF.getImg();
    }

    public static BufferedImage subTrunc( Processador imgA, Processador imgB ){
        
        Processador imgF = new Processador();
        
        imgF.setImg( imgA.getImg() );

        int r, g, b;

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) - imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) - imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) - imgB.nivelBlue(x, y);
                
                // teste de underflow
                r = r < 0 ? 0 : r; 
                g = g < 0 ? 0 : g;
                b = b < 0 ? 0 : b;
                
                imgF.setRGB( x, y, r, g, b );
            }
        }

        return imgF.getImg();
    }

    public static BufferedImage multiTrunc( Processador imgA, Processador imgB ){
        
        Processador imgF = new Processador();
        
        imgF.setImg( imgA.getImg() );

        int r, g, b;

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) * imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) * imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) * imgB.nivelBlue(x, y);
                
                // teste de overflow
                r = r > 255 ? 255 : r; 
                g = g > 255 ? 255 : g;
                b = b > 255 ? 255 : b;
                
                imgF.setRGB( x, y, r, g, b );
            }
        }

        return imgF.getImg();
    }

    public static BufferedImage andTrunc( Processador imgA, Processador imgB ){
        
        Processador imgF = new Processador();
        
        imgF.setImg( imgA.getImg() );

        int r, g, b;

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) & imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) & imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) & imgB.nivelBlue(x, y);

                // teste de underflow
                r = r < 0 ? 0 : r; 
                g = g < 0 ? 0 : g;
                b = b < 0 ? 0 : b;
                
                // teste de overflow
                r = r > 255 ? 255 : r; 
                g = g > 255 ? 255 : g;
                b = b > 255 ? 255 : b;
                
                imgF.setRGB( x, y, r, g, b );
            }
        }

        return imgF.getImg();
    }

    public static BufferedImage orTrunc( Processador imgA, Processador imgB ){
        
        Processador imgF = new Processador();
        
        imgF.setImg( imgA.getImg() );

        int r, g, b;

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) | imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) | imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) | imgB.nivelBlue(x, y);

                // teste de underflow
                r = r < 0 ? 0 : r; 
                g = g < 0 ? 0 : g;
                b = b < 0 ? 0 : b;
                
                // teste de overflow
                r = r > 255 ? 255 : r; 
                g = g > 255 ? 255 : g;
                b = b > 255 ? 255 : b;
                
                imgF.setRGB( x, y, r, g, b );
            }
        }

        return imgF.getImg();
    }

    public static BufferedImage xorTrunc( Processador imgA, Processador imgB ){
        
        Processador imgF = new Processador();
        
        imgF.setImg( imgA.getImg() );

        int r, g, b;

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                r = imgA.nivelRed(x, y) ^ imgB.nivelRed(x, y); 
                g = imgA.nivelGreen(x, y) ^ imgB.nivelGreen(x, y); 
                b = imgA.nivelBlue(x, y) ^ imgB.nivelBlue(x, y);

                // teste de underflow
                r = r < 0 ? 0 : r; 
                g = g < 0 ? 0 : g;
                b = b < 0 ? 0 : b;
                
                // teste de overflow
                r = r > 255 ? 255 : r; 
                g = g > 255 ? 255 : g;
                b = b > 255 ? 255 : b;
                
                imgF.setRGB( x, y, r, g, b );
            }
        }

        return imgF.getImg();
    }

    /* Divisão não extrapola os limites, portanto 
    não necessita de normalização ou truncamento*/

    public static BufferedImage divisao( Processador imgA, Processador imgB ){
        
        Processador imgF = new Processador();
        
        imgF.setImg( imgA.getImg() );

        int r, g, b;

        for(int x = 0; x < imgA.getWidth() && x < imgB.getWidth(); x++) {
            for(int y = 0; y < imgA.getHeight() && y < imgB.getHeight(); y++) {

                /*Necessário verificar se um dos valores é 
                igual a zero para evitar divisão por zero*/
                r = imgA.nivelRed(x, y) != 0 && imgB.nivelRed(x, y) != 0 ? 
                    imgA.nivelRed(x, y) / imgB.nivelRed(x, y) : 0; 

                g = imgA.nivelGreen(x, y) != 0 && imgB.nivelGreen(x, y) != 0 ? 
                    imgA.nivelGreen(x, y) / imgB.nivelGreen(x, y) : 0; 

                b = imgA.nivelBlue(x, y) != 0 && imgB.nivelBlue(x, y) != 0 ? 
                    imgA.nivelBlue(x, y) / imgB.nivelBlue(x, y) : 0;
                
                imgF.setRGB( x, y, r, g, b );
            }
        }

        return imgF.getImg();
    }

}
