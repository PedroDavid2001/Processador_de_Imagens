package src;

import java.awt.image.BufferedImage;

/**
 * Classe responsável pela implementação dos métodos 
 * de operações aritméticas bit-a-bit nas imagens. 
 * Todas as operações, exceto a de divisão, possuem 
 * um método com truncamento e normalização para 
 * controlar o range dos campos do RGB.
 * É necessário entender que cada campo varia de 0
 * a 255 e quando as imagens têm esses campos 
 * alterados por operações, necessitam de um controle 
 * dos valores para evitar overflows (campo acima de 255) 
 * ou underflows (campo abaixo de 0). Como já citado, a 
 * divisão é a única operação que não passa por esse 
 * controle, e isso se deve ao fato de que em uma divisão 
 * não é possível obter valores fora do range.
 */
public class Operacoes {
    //--------------------------------------------------------------
    //operações aritméticas

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
                
                //-------------------------------------------------------------
                //verifica��es necess�rias para evitar divis�es por 0 (zero)!!!
                if(maiorR > menorR)
                	r = ( ( ( 255 * (r - menorR) ) / (maiorR - menorR) ) );
                else
                	r = 0;
                
                if(maiorG > menorG)
                	g = ( ( ( 255 * (g - menorG) ) / (maiorG - menorG) ) );
                else
                	g = 0;
                
                if(maiorB > menorB)
                	b = ( ( ( 255 * (b - menorB) ) / (maiorB - menorB) ) );
                else
                	b = 0;
                //-------------------------------------------------------------
                
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
             
                //-------------------------------------------------------------
                //verifica��es necess�rias para evitar divis�es por 0 (zero)!!!
                if(maiorR > menorR)
                	r = ( ( ( 255 * (r - menorR) ) / (maiorR - menorR) ) );
                else
                	r = 0;
                
                if(maiorG > menorG)
                	g = ( ( ( 255 * (g - menorG) ) / (maiorG - menorG) ) );
                else
                	g = 0;
                
                if(maiorB > menorB)
                	b = ( ( ( 255 * (b - menorB) ) / (maiorB - menorB) ) );
                else
                	b = 0;
                //-------------------------------------------------------------

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
