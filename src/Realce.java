package src;

public class Realce {
	
	public static void Linear(Processador img, int min, int max, boolean invert ) {
		Processador tmp = new Processador();
		
		tmp.setImg( img.getImg() );
		
		int maior = 0;
		int menor = 255;
		
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	
            	if( img.nivelCinza(x, y) > maior ) {
            		maior = img.nivelCinza(x, y);
            	}else if( img.nivelCinza(x, y) < menor ) {
            		menor = img.nivelCinza(x, y);
            	}
            }
		
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	
            	int gray = (( maior - menor ) / ( max - min )) * ( img.nivelCinza(x, y) - min) + maior;
            	
            	if(invert)
            		gray = 255 - gray;
            	
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
		double a;
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
			a = 255 / Math.pow( (1 + e),  ( maior + menor ) );
			
			for(int y = 0; y < img.getHeight(); y++) 
	            for(int x = 0; x < img.getWidth(); x++) {
	            	
	            	int gray = (int)( a * ( Math.pow(e, img.nivelCinza(x, y)) + 1 ));
	            	
	            	tmp.setRGB(x, y, gray, gray, gray);
	            }
		}
		else if(calculo == 3) {
			a = 255/ (maior * maior);
			
			for(int y = 0; y < img.getHeight(); y++) 
	            for(int x = 0; x < img.getWidth(); x++) {
	            	
	            	int gray = (int)( a * Math.pow(img.nivelCinza(x, y), 2) );
	            	
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
	
	public static void EqualizarHistoGrama( Processador img ) {
		Processador tmp = new Processador();
		tmp.setImg( img.getImg() );
		
		 float maior = 0;
		 float menor = 255;
		 
		 double nPixels = img.getWidth() * img.getHeight();  
		 double niveisCinza[] = new double[256];
		 double porcCinza[] = new double[256];
		 double pixels[] = new double[ (int)nPixels ];
				 
	     	for( int y = 0; y < img.getHeight(); y++ ) 
	            for( int x = 0; x < img.getWidth(); x++ ) {

	                int gray = img.nivelCinza(x, y);
	                
	                if(gray > maior)
	                    maior = gray;

	                if(gray < menor)
	                    menor = gray;
	            }
	     	
	     	int index = 0;
	     	
	     	for( int y = 0; y < img.getHeight(); y++ ) 
	            for( int x = 0; x < img.getWidth(); x++ ) {
	            	
	            	float gray = img.nivelCinza(x, y);
	            	gray = ((( 255 * (gray - menor) ) / (maior - menor)));
	            	
	            	niveisCinza[ (int)gray ] += 1;
	            	
	            	pixels[index] = gray;
	            	
	            	gray /= 255;
	            	tmp.setRGB(x, y, gray, gray, gray);
	            	index++;
	            }
	     	
	     	for(int i = 0; i < 256; i++)
	     		porcCinza[i] = niveisCinza[i] / nPixels;
	     	
	     	index = 0;
	     	
	     	for( int y = 0; y < img.getHeight(); y++ ) 
	            for( int x = 0; x < img.getWidth(); x++ ) {
	            	
	            	double gray; 
	            	float newGray = 0;
	            	
	            	for(int i = 0; i <= index; i++) {
	            		gray = pixels[i];
	            		
	            		newGray += porcCinza[ (int)gray ];
	            	}
	            	
	            	tmp.setRGB(x, y, newGray, newGray, newGray);
	            	index++;
	            }
	     	
	     	tmp.setImgPlus();
			tmp.getImgPlus().setTitle( "Equalização de histograma" );
			tmp.getImgPlus().show();
			tmp.getImgPlus().plotHistogram();
	}
	
	public static void corrigirGama(Processador img, double c, double gama) {
		Processador tmp = new Processador();
		tmp.setImg( img.getImg() );
		
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	
            	int gray = (int)(c * Math.pow(img.nivelCinza(x, y), gama));
            	
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
	            	
	            	// realiza operação bit a bit
	            	int gray = img.nivelCinza(x, y) & fatia; 
	            	
	            	//imagem será binária, portanto ou preto (1), ou branco (0)
	            	if(gray != 0)
	            		gray = 1;
	            	
	            	tmp.setRGB(x, y, gray, gray, gray);
	            }
			
			tmp.setImgPlus();
			tmp.getImgPlus().setTitle( "Camada " + fatia );
			tmp.getImgPlus().show();
		}
	}
}
