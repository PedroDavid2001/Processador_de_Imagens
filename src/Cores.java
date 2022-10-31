package src;

public class Cores {
	
	//exibe os components RGB
	public static void RGB(Processador img) {
		
		Processador proR = new Processador(); 
		Processador proG = new Processador(); 
		Processador proB = new Processador(); 
		
		
		proR.setImg( img.getImg() );
		proG.setImg( img.getImg() );
		proB.setImg( img.getImg() );
		
		//Red
		for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                proR.setRGB(x, y, img.nivelRed(x, y), 0, 0); //R 0 0
            }
        }
		
		//Green
		for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                proG.setRGB(x, y, 0, img.nivelGreen(x, y), 0);//0 G 0
            }
        }
		
		//Blue
		for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                proB.setRGB(x, y, 0, 0, img.nivelBlue(x, y));//0 0 B
            }
        }
		
		//inicializa as image plus
		proR.setImgPlus();
		proG.setImgPlus();
		proB.setImgPlus();
		
		//exibe as image plus
		proR.getImgPlus().setTitle("Red");
		proG.getImgPlus().setTitle("Green");
		proB.getImgPlus().setTitle("Blue");
		
		proR.getImgPlus().show();
		proG.getImgPlus().show();
		proB.getImgPlus().show();
		
	}
	
	//exibe os components CMY
	public static void CMY(Processador img) {
			
			/**
			 * C = 255 - R 	
			 * M = 255 - G 
			 * Y = 255 - B
			 * 
			 **/
			
			Processador proC = new Processador(); 
			Processador proM = new Processador(); 
			Processador proY = new Processador(); 
			
			
			proC.setImg( img.getImg() );
			proM.setImg( img.getImg() );
			proY.setImg( img.getImg() );
			
			//Cyan
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	            	int cyan = 255 - img.nivelRed(x, y) ;
	            	
	                proC.setRGB(x, y, 0, cyan, cyan); //0, C, C
	            }
	        }
			
			//Magenta
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	            	int magenta = 255 - img.nivelGreen(x, y);
	                
	            	proM.setRGB(x, y, magenta, 0, magenta); //M, 0, M
	            }
	        }
			
			//Yellow
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	            	int yellow = 255 - img.nivelBlue(x, y);
	                
	            	proY.setRGB(x, y, yellow, yellow, 0); //Y, Y, 0
	            }
	        }
			
			//inicializa as image plus
			proC.setImgPlus();
			proM.setImgPlus();
			proY.setImgPlus();
			
			//exibe as image plus
			proC.getImgPlus().setTitle("Cyan");
			proC.getImgPlus().show();
			
			proM.getImgPlus().setTitle("Magenta");
			proM.getImgPlus().show();
			
			proY.getImgPlus().setTitle("Yellow");
			proY.getImgPlus().show();
			
		}
		
	//exibe os components CMY-K
	public static void CMYK(Processador img) {
					
		/**
		 * C = (255 - R - K) / (255 - K)	
		 * M = (255 - G - K) / (255 - K)
		 * Y = (255 - B - K) / (255 - K)
		 * 
		 **/
		
		Processador proC = new Processador(); 
		Processador proM = new Processador(); 
		Processador proY = new Processador();
		Processador proK = new Processador();
		
		
		proC.setImg( img.getImg() );
		proM.setImg( img.getImg() );
		proY.setImg( img.getImg() );
		proK.setImg( img.getImg() );
		
		//Cyan
		for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
            	
            	float cyan = 1 - ((float)img.nivelRed(x, y) / 255);
            	float key = (float)img.getKey(x, y) / 255;
            	
            	if(key == 1) //verificação para evitar divisão por 0 (zero)
                	cyan = 0;
                else
                	cyan = ( cyan - key ) / (1 - key );
            	
            	if(cyan < 0)
            		cyan = -cyan; //verificação para evitar valores negativos
            	
                proC.setRGB(x, y, 0, cyan, cyan); //0, C, C
            }
        }
		
		//Magenta
		for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
            	
            	float magenta = 1 - ((float)img.nivelGreen(x, y) / 255);
            	float key = (float)img.getKey(x, y) / 255;
                
            	if(key == 1) //verificação para evitar divisão por 0 (zero)
                	magenta = 0;
                else
                	magenta = ( magenta - key ) / (1 - key );
            	
            	if(magenta < 0)
            		magenta = -magenta; //verificação para evitar valores negativos
            	
            	proM.setRGB(x, y, magenta, 0, magenta); //M, 0, M
            }
        }
		
		//Yellow
		for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
            	
            	float yellow = 1 - ((float)img.nivelBlue(x, y) / 255);
            	float key = (float)img.getKey(x, y) / 255;
                
            	if(key == 1) //verificação para evitar divisão por 0 (zero)
            		yellow = 0;
            	else
            		yellow = ( yellow - key ) / ( 1 - key );
            	
            	if(yellow < 0)
            		yellow = -yellow; //verificação para evitar valores negativos
            	
            	proY.setRGB(x, y, yellow, yellow, 0); //Y, Y, 0
            }
        }
		
		//Key
		for(int y = 0; y < img.getHeight(); y++){
			for(int x = 0; x < img.getWidth(); x++){
				int key = img.getKey(x, y);
				
				proK.setRGB(x, y, key, key, key); //K, K, K
		    }
		}
		
		//inicializa as image plus
		proC.setImgPlus();
		proM.setImgPlus();
		proY.setImgPlus();
		proK.setImgPlus();
		
		//exibe as image plus
		proC.getImgPlus().setTitle("Cyan");
		proC.getImgPlus().show();
		
		proM.getImgPlus().setTitle("Magenta");
		proM.getImgPlus().show();
		
		proY.getImgPlus().setTitle("Yellow");
		proY.getImgPlus().show();
		
		proK.getImgPlus().setTitle("Black / Key");
		proK.getImgPlus().show();
		
	}
	
	//exibe a imagem em escala de cinza equivalente
	public static void grayScale(Processador img) {
		
		Processador gray = new Processador(); 
		
		gray.setImg( img.getImg() );
		
		for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
            	
                int nvl = img.nivelCinza(x, y);
            	gray.setRGB(x, y, nvl, nvl, nvl); 
            	
            }
        }
		
		//inicializa as image plus
		gray.setImgPlus();
		
		//exibe as image plus
		gray.getImgPlus().setTitle("Gray Scale");
		gray.getImgPlus().show();
	}
	
	//exibe os components YUV
	public static void YUV(Processador img) {
			
			/**
		    * | Y |   |   0.299     0.587    0.114 |   | R |
		    * | U | = | -0.14713  -0.28886   0.436 | * | G |
		    * | V |   |   0.615   -0.51499  -0.312 |   | B |
		    *
		    **/
			
			Processador proY = new Processador(); 
			Processador proU = new Processador(); 
			Processador proV = new Processador(); 
			
			
			proY.setImg( img.getImg() );
			proU.setImg( img.getImg() );
			proV.setImg( img.getImg() );
			
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	            	float nvlY = (float)(0.299 * img.nivelRed(x, y) + 0.587 * img.nivelGreen(x, y) + 0.114 * img.nivelBlue(x, y));
	            	
	            	nvlY /= 255; 
	            	
	                proY.setRGB(x, y, nvlY, nvlY, nvlY); //Y Y Y
	            }
	        }
			
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	            	float nvlU = (float) (-0.14713 * img.nivelRed(x, y) - 0.28886 * img.nivelGreen(x, y) + 0.436 * img.nivelBlue(x, y));
	            	
	            	nvlU /= 255;
	            	
	            	if(nvlU < 0)
	            		nvlU = 0;//verificação para evitar valores negativos
	            	
	            	proU.setRGB(x, y, 0, nvlU, nvlU);//0 U U
	            }
	        }
		
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	            	float nvlV = (float) (0.615 * img.nivelRed(x, y) - 0.51499 * img.nivelGreen(x, y) + 0.312 * img.nivelBlue(x, y));
	            	
	            	nvlV /= 255;
	            	
	            	if(nvlV < 0)
	            		nvlV = 0;//verificação para evitar valores negativos
	            	
	                proV.setRGB(x, y, nvlV, nvlV, 0);//V V 0
	            }
	        }
			
			//inicializa as image plus
			proY.setImgPlus();
			proU.setImgPlus();
			proV.setImgPlus();
			
			//exibe as image plus
			proY.getImgPlus().setTitle("Iluminância");
			proY.getImgPlus().show();
			
			proU.getImgPlus().setTitle("U");
			proU.getImgPlus().show();
			
			proU.getImgPlus().setTitle("V");
			proV.getImgPlus().show();
			
		}
	
	public static double Hue(int r, int g, int b) {
		/**
		 * Para o HSB precisamos calcular cada componente após descobrir 
		 * o menor e maior valor entre os campos RGB do pixel analisado.
		 * 
		 * I passo:
		 * 
		 * Math.max(R', G', B')
		 * Math.min(R', G', B')
		 * 
		 * II passo:
		 * 
		 * Delta = max - min
		 * 
		 * III passo (matiz / hue):
		 * 
		 * hue = 0, if delta == 0
		 * hue = 60 * ((( G' - B' ) / delta) mod 6 ), if max == R
		 * hue = 60 * ((( B' - R' ) / delta) + 2 ), if max == G
		 * hue = 60 * ((( R' - G' ) / delta) + 4 ), if max == B
		 **/
		
		double newR = (double)r;
		double newG = (double)g;
		double newB = (double)b;
		
		double maior = Math.max(newR, Math.max(newG, newB));
		double menor = Math.min(newR, Math.min(newG, newB));
		
		double delta = maior - menor;
		
		double hue;
		
		if(delta == 0.0)
			hue = 0.0;
		else if(maior == newR)
			hue = 60 * ((( newG - newB ) / delta) % 6 );
		else if(maior == newG)
			hue = 60 * ((( newB - newR ) / delta) + 2 );
		else 
			hue = 60 * ((( newR - newG ) / delta) + 4 );
		
		if(hue < 0)
		    hue = 0;
		else if(hue > 360)
		    hue = 360;
		return hue;
	}
	
	public static double Saturation(int r, int g, int b) {
		/**
		 * Para o HSB precisamos calcular cada componente após descobrir 
		 * o menor e maior valor entre os campos RGB do pixel analisado.
		 * 
		 * I passo:
		 * 
		 * Math.max(R', G', B')
		 * Math.min(R', G', B')
		 * 
		 * II passo:
		 * 
		 * Delta = max - min
		 * 
		 * III passo (saturação / saturation):
		 * 
		 * sat = 0, if == 0
		 * sat = delta / max
		 **/
		
		double newR = (double)r;
		double newG = (double)g;
		double newB = (double)b;
		
		double maior = Math.max(newR, Math.max(newG, newB));
		double menor = Math.min(newR, Math.min(newG, newB));
		
		double delta = maior - menor;
		
		double saturation;
		
		if(delta == 0.0)
			saturation = 0.0;
		else
			saturation = delta / maior;
		
		return saturation;
	}
	
	public static double Brightness(int r, int g, int b) {
		/**
		 * Para o HSB precisamos calcular cada componente após descobrir 
		 * o menor e maior valor entre os campos RGB do pixel analisado.
		 * 
		 * I passo:
		 * 
		 * Math.max(R', G', B')
		 * 
		 * II passo (brilho / brightness):
		 * 
		 * brightness = max
		 **/
		
		double newR = (double)r;
		double newG = (double)g;
		double newB = (double)b;
		
		double maior = Math.max(newR, Math.max(newG, newB));
		
		return maior;
		
	}
	
	//Pseudocoloração de imagem 
	
	/**
	 * Para imagens em escala de cinza(preto e branco),
	 * São escolhidas 7 subdivisões de  densidades de brilho
	 * (de 0 a 255) e cada campo é predominante em uma dessas 
	 * divisões, portanto, se o azul está predominante nas 
	 * mais baixas, em locais mais escuros da imagem, o 
	 * azul será mais destacado.
	 **/
	
	public static void pseudoColorGrayScale( Processador img, int opt ) {
		
		Processador tmp = new Processador();
		
		tmp.setImg( img.getImg() );
		
		int brilho;
		String result = "";
		
		//	prim = camada mais clara
		//  seg  = camada intermediaria 
		//  ter  = camada mais escura 
		float prim = 0;
		float seg = 0;
		float ter = 0;
		
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	
            	brilho = img.nivelCinza(x, y);
            	
            	if( brilho >= 0 && brilho <= 36) {
            		prim = 0;
            		seg = brilho * (float)0.36;
            		ter = brilho;
            	}
            	else if( brilho >= 37 && brilho <= 73) {
            		prim = 0;
            		seg = brilho * (float)0.51;
            		ter = brilho * (float)0.85;
            	}
            	else if( brilho >= 74 && brilho <= 110) {
            		prim = 0;
            		seg = brilho * (float)0.85;
            		ter = brilho * (float)0.51;
            	}
            	else if( brilho >= 111 && brilho <= 147) {
            		prim = brilho * (float)0.36;
            		seg = brilho;
            		ter = brilho * (float)0.36;
            	}
            	else if( brilho >= 148 && brilho <= 184) {
            		prim = brilho * (float)0.51;
            		seg = brilho * (float)0.85;
            		ter = 0;
            	}
            	else if( brilho >= 185 && brilho <= 221) {
            		prim = brilho * (float)0.85;
            		seg = brilho * (float)0.51;
            		ter = 0;
            	}
            	else if( brilho >= 222 && brilho <= 255) {
            		prim = brilho;
            		seg = brilho * (float)0.36;
            		ter = 0;
            	}
            	
            	// teste de underflow
            	prim = prim < 0 ? 0 : prim; 
            	seg = seg < 0 ? 0 : seg;
                ter = ter < 0 ? 0 : ter;
                
                // teste de overflow
                prim = prim > 255 ? 255 : prim; 
                seg = seg > 255 ? 255 : seg;
                ter = ter > 255 ? 255 : ter;
            	
                /**
                 * RGB = 1
                 * RBG = 2
                 * 
                 * BGR = 3
                 * BRG = 4
                 * 
                 * GRB = 5
                 * GBR = 6
                 **/
                
                if(opt == 1) {
                	result = "RGB";
                	tmp.setRGB(x, y, prim/255, seg/255, ter/255);
                }
                else if(opt == 2) {
                	result = "RBG";
                	tmp.setRGB(x, y, prim/255, ter/255, seg/255);
                }
                else if(opt == 3) {
                	result = "BGR";
                	tmp.setRGB(x, y, ter/255, seg/255, prim/255);
                }
                else if(opt == 4) {
                	result = "BRG";
                	tmp.setRGB(x, y, ter/255, prim/255, seg/255);
                }
                else if(opt == 5) {
                	result = "GRB";
                	tmp.setRGB(x, y, seg/255, prim/255, ter/255);
                }
                else if(opt == 6) {
                	result = "GBR";
                	tmp.setRGB(x, y, seg/255, ter/255, prim/255);
                }
            }
		
		tmp.setImgPlus();
		tmp.getImgPlus().setTitle( result );
		tmp.getImgPlus().show();
	}
	
	/**
	 * Para pseudocoloração das imagens coloridas 
	 * um campo é removido (iguala a zero) quando 
	 * estiver abaixo OU acima de um limiar. 
	 * Por exemplo: Se o tom azul na imagem estiver 
	 * abaixo de 128, será removido naquele pixel.
	 * 
	 **/
	
	public static void pseudoColorIgnoring( Processador img, int opt ) {
		
		Processador tmp = new Processador();
		
		tmp.setImg( img.getImg() );
		
		String result = "";
		
		int r = 0;
		int g = 0;
		int b = 0;
		
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	r = img.nivelRed(x, y);
            	g = img.nivelGreen(x, y);
            	b = img.nivelBlue(x, y);
            	
            	if(opt == 1) {
                	result = "Ignorando Blue";
                	if(b > 128)
                		b = 0;
                }
                else if(opt == 2) {
                	result = "Ignorando Red";
                	if(r > 128)
                		r = 0;
                }
                else if(opt == 3) {
                	result = "Ignorando Green";
                	if(g > 128)
                		g = 0;
                }
            	
            	tmp.setRGB(x, y, r, g, b);
            }
	
		tmp.setImgPlus();
		tmp.getImgPlus().setTitle( result );
		tmp.getImgPlus().show();
	}
	
	public static void pseudoColorReducing( Processador img, int opt ) {
		
		Processador tmp = new Processador();
		
		tmp.setImg( img.getImg() );
		
		String result = "";
		
		int r = 0;
		int g = 0;
		int b = 0;
		
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	r = img.nivelRed(x, y);
            	g = img.nivelGreen(x, y);
            	b = img.nivelBlue(x, y);
            	
            	if(opt == 1) {
                	result = "Reduzindo Blue";
                	if(b > 128)
                		b = b / 2;
                }
                else if(opt == 2) {
                	result = "Reduzindo Red";
                	if(r > 128)
                		r = r / 2;
                }
                else if(opt == 3) {
                	result = "Reduzindo Green";
                	if(g > 128)
                		g = g / 2;
                }
            	
            	tmp.setRGB(x, y, r, g, b);
            }
	
		tmp.setImgPlus();
		tmp.getImgPlus().setTitle( result );
		tmp.getImgPlus().show();
	}
	
	public static void pseudoColorIncreasing( Processador img, int opt ) {
		
		Processador tmp = new Processador();
		
		tmp.setImg( img.getImg() );
		
		String result = "";
		
		int r = 0;
		int g = 0;
		int b = 0;
		
		for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
            	r = img.nivelRed(x, y);
            	g = img.nivelGreen(x, y);
            	b = img.nivelBlue(x, y);
            	
            	if(opt == 1) {
                	result = "Aumentando Blue";
                	if(b < 127)
                		b = (b * 2) + 1;
                }
                else if(opt == 2) {
                	result = "Aumentando Red";
                	if(r < 127)
                		r = (r * 2) + 1;
                }
                else if(opt == 3) {
                	result = "Aumentando Green";
                	if(g < 127)
                		g = (g * 2) + 1;
                }
            	
            	tmp.setRGB(x, y, r, g, b);
            }
	
		tmp.setImgPlus();
		tmp.getImgPlus().setTitle( result );
		tmp.getImgPlus().show();
	}
}
