package src;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Realce {
	
	public static void Linear(Processador img, int min, int max, boolean invert ) {
		Processador tmp = new Processador();
		
		tmp.setImg( img.getImg() );
		
		int maior = 0;
		int menor = 255;
		
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	
                int gray = img.nivelCinza(x, y);
            	
                if( gray > maior ) {
            		maior = gray;
            	}
            	if( gray < menor ) {
            		menor = gray;
            	}
            }
		
	
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	
                float a =  (float)( max - min ) / (float)( maior - menor );
            	float gray = a * (img.nivelCinza(x, y) - menor) + min;
            	
            	gray = gray / 255;
            	
            	if(invert)
            		gray = 1 - gray;
            	
            	tmp.setRGB(x, y, gray, gray, gray);
            }
		
		tmp.setImgPlus();
		tmp.getImgPlus().setTitle( "Transformação linear" );
		tmp.getImgPlus().show();
	}
	
	public static void NaoLinear(Processador img, int calculo ) {
		
		Processador tmp = new Processador();
		tmp.setImg( img.getImg() );
		
		int maior = 0;
		int menor= 255;
		double a = 1;
		double e = Math.E;
		
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	
            	if( img.nivelCinza(x, y) > maior ) {
            		maior = img.nivelCinza(x, y);
            	}else if( img.nivelCinza(x, y) < menor ) {
            		menor = img.nivelCinza(x, y);
            	}
            }
		
		/**
		 * 1 = logaritmo
		 * 2 = exponencial
		 * 3 = quadrado
		 * 4 = raiz quadrada 
		 **/
		
		if(calculo == 1) {
			a = 255 / (Math.log( 1 + maior ));
			
			for(int y = 0; y < img.getHeight(); y++) 
	            for(int x = 0; x < img.getWidth(); x++) {
	            	
	            	int gray = (int)(a * Math.log( img.nivelCinza(x, y) + 1 ));
	            	
	            	tmp.setRGB(x, y, gray, gray, gray);
	            }
		}
		else if(calculo == 2) {
			a = (255 / Math.pow( (1 + e),  ( maior + menor ) ));
			
			for(int y = 0; y < img.getHeight(); y++) 
	            for(int x = 0; x < img.getWidth(); x++) {
	            	
	            	double gray = ( a * ( 1 + Math.pow(e, img.nivelCinza(x, y)) ));
	            	
	            	gray /= 255;
	            	
	            	tmp.setRGB(x, y, (float)gray, (float)gray, (float)gray);
	            }
		}
		else if(calculo == 3) {
			a = ( 255 / Math.pow( maior, 2 ));
			
			for(int y = 0; y < img.getHeight(); y++) 
	            for(int x = 0; x < img.getWidth(); x++) {
	            	
	            	float gray = (int)( a * Math.pow(img.nivelCinza(x, y), 2) );
	            	
	            	gray /= 255;
	            	
	            	tmp.setRGB(x, y, gray, gray, gray);
	            }
		}
		else if(calculo == 4) {
			a = 255 / (Math.sqrt(1 + maior));
			
			for(int y = 0; y < img.getHeight(); y++) 
	            for(int x = 0; x < img.getWidth(); x++) {
	            	
	            	int gray = (int)( a * Math.sqrt( img.nivelCinza(x, y) ) );
	            	
	            	tmp.setRGB(x, y, gray, gray, gray);
	            }
		}
		
		tmp.setImgPlus();
		tmp.getImgPlus().setTitle( "Transformação não-linear" );
		tmp.getImgPlus().show();
		
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

    public static void EqualizarHistoGrama(BufferedImage img) { //iniciando equalização do histograma
        
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "Equalização do histograma" );
        tmp.getImgPlus().show();
        tmp.getImgPlus().plotHistogram();
    }

	
	public static void corrigirGama(Processador img, double c, double gama) {
		Processador tmp = new Processador();
		tmp.setImg( img.getImg() );
		
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	
            	float gray = (float)(c * (Math.pow(img.nivelCinza(x, y), gama ) ));
            	
            	gray /= 255;
            	
            	if(gray > 1)
            	    gray = 1;
            	else if(gray < 0)
            	    gray = 0;
            	
            	tmp.setRGB(x, y, gray, gray, gray);
            }
		
		tmp.setImgPlus();
		tmp.getImgPlus().setTitle( "Correção de gama" );
		tmp.getImgPlus().show();
	}
	
	public static void fatiarBits(Processador img, int nCamadas) {
		Processador tmp = new Processador();
		tmp.setImg( img.getImg() );
		
		for (int fatia = 0; fatia < nCamadas; fatia++) {
			
			for(int y = 0; y < img.getHeight(); y++) 
	            for(int x = 0; x < img.getWidth(); x++) {
	            	
	                int um = 1;
	                // realiza operação bit a bit
	            	int gray = img.nivelCinza(x, y) & ( um << fatia ); 
	            	
	            	if(gray != 0)
	            	    gray = 255;
	            	
	            	tmp.setRGB(x, y, gray, gray, gray);
	            }
			
			tmp.setImgPlus();
			tmp.getImgPlus().setTitle( "Camada " + fatia );
			tmp.getImgPlus().show();
		}
	}
}
