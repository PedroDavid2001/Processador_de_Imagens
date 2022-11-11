package src;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Realce {
	
	public static BufferedImage Linear(Processador img, int min, int max, boolean invert ) {
		Processador tmp = new Processador();
		
		tmp.setImg( img.getImg() );
		
		int maiorR = 0, maiorG = 0, maiorB = 0;
		int menorR = 255, menorG = 255, menorB = 255;
		
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	
                int r = img.nivelRed(x, y);
                int g = img.nivelGreen(x, y);
                int b = img.nivelBlue(x, y);
            	
                //red
                if( r > maiorR ) {
            		maiorR = r;
            	}
            	if( r < menorR ) {
            		menorR = r;
            	}
            	//blue
            	if( b > maiorB ) {
                    maiorB = b;
                }
                if( b < menorB ) {
                    menorB = b;
                }
                //green
                if( g > maiorG ) {
                    maiorG = g;
                }
                if( g < menorG ) {
                    menorG = g;
                }
            }
		
	
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	
                float r =  (float)( max - min ) / (float)( maiorR - menorR );
            	float red = r * (img.nivelRed(x, y) - menorR) + min;
            	
            	red = red / 255;
            	
            	float g =  (float)( max - min ) / (float)( maiorG - menorG );
                float green = g * (img.nivelGreen(x, y) - menorG) + min;
                
                green = green / 255;
            	
            	float b =  (float)( max - min ) / (float)( maiorB - menorB );
                float blue = b * (img.nivelBlue(x, y) - menorB) + min;
                
                blue = blue / 255;
            	
            	if(invert) {
            	    red = 1 - red;
            	    green = 1 - green;
            	    blue = 1 - blue;
            	}
            		
            	
            	tmp.setRGB(x, y, red, green, blue);
            }
		
		return tmp.getImg();
	}
	
	public static BufferedImage NaoLinear(Processador img, int calculo ) {
		
		Processador tmp = new Processador();
		tmp.setImg( img.getImg() );
		
		int maiorR = 0, maiorG = 0, maiorB = 0;
        int menorR = 255, menorG = 255, menorB = 255;
		double aR = 1, aG = 1, aB = 1;
		double e = Math.E;
		
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	
                int r = img.nivelRed(x, y);
                int g = img.nivelGreen(x, y);
                int b = img.nivelBlue(x, y);
                
                //red
                if( r > maiorR ) {
                    maiorR = r;
                }
                if( r < menorR ) {
                    menorR = r;
                }
                //blue
                if( b > maiorB ) {
                    maiorB = b;
                }
                if( b < menorB ) {
                    menorB = b;
                }
                //green
                if( g > maiorG ) {
                    maiorG = g;
                }
                if( g < menorG ) {
                    menorG = g;
                }
            }
		
		/**
		 * 1 = logaritmo
		 * 2 = exponencial
		 * 3 = quadrado
		 * 4 = raiz quadrada 
		 **/
		
		if(calculo == 1) {
			aR = 255 / (Math.log( 1 + maiorR ));
			aG = 255 / (Math.log( 1 + maiorG ));
			aB = 255 / (Math.log( 1 + maiorB ));
			
			for(int y = 0; y < img.getHeight(); y++) 
	            for(int x = 0; x < img.getWidth(); x++) {
	            	
	            	int red = (int)(aR * Math.log( img.nivelRed(x, y) + 1 ));
	            	int green = (int)(aG * Math.log( img.nivelGreen(x, y) + 1 ));
	            	int blue = (int)(aB * Math.log( img.nivelBlue(x, y) + 1 ));
	            	
	            	tmp.setRGB(x, y, red, green, blue);
	            }
		}
		else if(calculo == 2) {
			aR = (255 / Math.pow( (1 + e),  ( maiorR + menorR ) ));
			aG = (255 / Math.pow( (1 + e),  ( maiorG + menorG ) ));
			aB = (255 / Math.pow( (1 + e),  ( maiorB + menorB ) ));
			
			for(int y = 0; y < img.getHeight(); y++) 
	            for(int x = 0; x < img.getWidth(); x++) {
	            	
	            	double red = ( aR * ( 1 + Math.pow(e, img.nivelRed(x, y)) ));
	            	double green = ( aG * ( 1 + Math.pow(e, img.nivelGreen(x, y)) ));
	            	double blue = ( aB * ( 1 + Math.pow(e, img.nivelBlue(x, y)) ));
	            	
	            	red /= 255;
	            	blue /= 255;
	            	green /= 255;
	            	
	            	tmp.setRGB(x, y, (float)red, (float)green, (float)blue);
	            }
		}
		else if(calculo == 3) {
			aR = ( 255 / Math.pow( maiorR, 2 ));
			aG = ( 255 / Math.pow( maiorG, 2 ));
			aB = ( 255 / Math.pow( maiorB, 2 ));
			
			for(int y = 0; y < img.getHeight(); y++) 
	            for(int x = 0; x < img.getWidth(); x++) {
	            	
	            	float red = (int)( aR * Math.pow(img.nivelRed(x, y), 2) );
	            	float green = (int)( aG * Math.pow(img.nivelGreen(x, y), 2) );
	            	float blue = (int)( aB * Math.pow(img.nivelBlue(x, y), 2) );
	            	
	            	red /= 255;
                    blue /= 255;
                    green /= 255;
	            	
                    tmp.setRGB(x, y, red, green, blue);
	            }
		}
		else if(calculo == 4) {
			aR = 255 / (Math.sqrt(1 + maiorR));
			aG = 255 / (Math.sqrt(1 + maiorG));
			aB = 255 / (Math.sqrt(1 + maiorB));
			
			for(int y = 0; y < img.getHeight(); y++) 
	            for(int x = 0; x < img.getWidth(); x++) {
	            	
	            	int red = (int)( aR * Math.sqrt( img.nivelRed(x, y) ) );
	            	int green = (int)( aG * Math.sqrt( img.nivelGreen(x, y) ) );
	            	int blue = (int)( aB * Math.sqrt( img.nivelBlue(x, y) ) );
	            	
	            	tmp.setRGB(x, y, red, green, blue);
	            }
		}
		
		return tmp.getImg();
		
	}
	
	static int[] calculaHistograma(BufferedImage img){ 
        int[] histograma = new int[256];
        for (int y = 0; y < img.getHeight(); y++) { //executando matriz para calcular histograma
            for (int x = 0; x < img.getWidth(); x++) {
                Color color = new Color(img.getRGB(x, y)); //definindo escala RGB
                int red = color.getRed();
                histograma[red] += 1;
            }
        }
        return histograma; 
    }


    public static int[] calculaHistogramaAcumulado(int[] histograma) {
        int[] acumulado = new int[256];
        acumulado[0] = histograma[0];
        for(int i=1;i < histograma.length;i++) { //definindo loop baseado no cálculo do histograma

            acumulado[i] = histograma[i] + acumulado[i-1]; //definindo valor para histograma acumulado
        }
        return acumulado;
    }

    private static int menorValor(int[] histograma) {
        for(int i=0; i <histograma.length; i++) {
            if(histograma[i] != 0){
                return histograma[i];
            }
        }
        return 0;
    }

    private static int[] calculaMapadeCores(int[] histograma, int pixels) {
        int[] mapaDeCores = new int[256];
        int[] acumulado = calculaHistogramaAcumulado(histograma); 
        float menor = menorValor(histograma);
        for(int i=0; i < histograma.length; i++) { //loop do mapa de cores baseado no tamanho do histograma
            mapaDeCores[i] = Math.round(((acumulado[i] - menor) / (pixels - menor)) * 255); //definindo um valor pra variável mapa de cores
        }
        return mapaDeCores;
    }

    public static BufferedImage EqualizarHistoGrama(BufferedImage img) { //iniciando equalização do histograma
        
        int[] histograma = calculaHistograma(img); //definindo valor ao vetor histograma
        
        int[] mapaDeCores = calculaMapadeCores(histograma, img.getWidth() * img.getHeight()); //definindo valor ao vetor de mapa de cores
        
        BufferedImage out = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB); //atualizando a palheta de cores 
        for (int y = 0; y < img.getHeight(); y++) { //iniciando a matriz para equalização do histograma 
            for (int x = 0; x < img.getWidth(); x++) {
                Color color = new Color(img.getRGB(x, y));
                int tom = color.getRed();
                int newTom = mapaDeCores[tom];
                Color newColor = new Color(newTom, newTom, newTom);
                out.setRGB(x, y, newColor.getRGB());
            }
        }
      
        Processador tmp = new Processador();
        tmp.setImg( out );
        
        tmp.getImgPlus().plotHistogram();
        return tmp.getImg();
    }

	
	public static BufferedImage corrigirGama(Processador img, double c, double gama) {
		Processador tmp = new Processador();
		tmp.setImg( img.getImg() );
		
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	
            	float red = (float)(c * (Math.pow(img.nivelRed(x, y), gama ) ));
            	float green = (float)(c * (Math.pow(img.nivelGreen(x, y), gama ) ));
            	float blue = (float)(c * (Math.pow(img.nivelBlue(x, y), gama ) ));
            	
            	red /= 255;
            	green /= 255;
            	blue /= 255;
            	
            	if(red > 1)
            	    red = 1;
            	else if(red < 0)
            	    red = 0;
            	
            	if(green > 1)
                    green = 1;
                else if(green < 0)
                    green = 0;
            	
            	if(blue > 1)
                    blue = 1;
                else if(blue < 0)
                    blue = 0;
            	
            	tmp.setRGB(x, y, red, green, blue);
            }
		
		return tmp.getImg();
	}
	
	//fatia a imagem em camadas que se distinguem de acordo com a intensidade dos campos do RGB
	public static void fatiarBitsRGB(Processador img, int nCamadas) {
		Processador tmp = new Processador();
		tmp.setImg( img.getImg() );
		
		for (int fatia = 0; fatia < nCamadas; fatia++) {
			
			for(int y = 0; y < img.getHeight(); y++) 
	            for(int x = 0; x < img.getWidth(); x++) {
	            	
	                int um = 1;
	                // realiza operação bit a bit
	                //red
	            	int red = img.nivelRed(x, y) & ( um << fatia ); 
	            	
	            	if(red != 0)
	            	    red = 255;
	            	//green
	            	int green = img.nivelGreen(x, y) & ( um << fatia ); 
                    
                    if(green != 0)
                        green = 255;
                    //blue
                    int blue = img.nivelBlue(x, y) & ( um << fatia ); 
                    
                    if(blue != 0)
                        blue = 255;
                    
	            	
	            	tmp.setRGB(x, y, red, green, blue);
	            }
			
			tmp.setImgPlus();
			tmp.getImgPlus().setTitle( "Camada " + fatia );
			tmp.getImgPlus().show();
		}
	}

    //fatia a imagem em camadas que se distinguem de acordo com a intensidade de iluminação
	public static void fatiarBits(Processador img, int nCamadas) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        for (int fatia = 0; fatia < nCamadas; fatia++) {
            
            for(int y = 0; y < img.getHeight(); y++) 
                for(int x = 0; x < img.getWidth(); x++) {
                    
                    int um = 1;
                    // realiza operação bit a bit
                    int g = img.nivelCinza(x, y) & ( um << fatia ); 
                    
                    if(g != 0)
                        g = 255;
                    
                    tmp.setRGB(x, y, g, g, g);
                }
            
            tmp.setImgPlus();
            tmp.getImgPlus().setTitle( "Camada " + fatia );
            tmp.getImgPlus().show();
        }
    }
}
