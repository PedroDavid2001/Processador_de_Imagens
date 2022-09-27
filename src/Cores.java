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
			proC.getImgPlus().show();
			proM.getImgPlus().show();
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
		proC.getImgPlus().show();
		proM.getImgPlus().show();
		proY.getImgPlus().show();
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
	            	float nvlY = (int) (0.299 * img.nivelRed(x, y) + 0.587 * img.nivelGreen(x, y) + 0.114 * img.nivelBlue(x, y));
	            	
	            	nvlY /= 255; 
	            	
	                proY.setRGB(x, y, nvlY, nvlY, nvlY); //Y Y Y
	            }
	        }
			
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	            	float nvlU = (int) (-0.14713 * img.nivelRed(x, y) - 0.28886 * img.nivelGreen(x, y) + 0.436 * img.nivelBlue(x, y));
	            	
	            	nvlU /= 255;
	            	
	            	if(nvlU < 0)
	            		nvlU = 0;//verificação para evitar valores negativos
	            	
	            	proU.setRGB(x, y, 0, nvlU, nvlU);//0 U U
	            }
	        }
		
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	            	float nvlV = (int) (0.615 * img.nivelRed(x, y) - 0.51499 * img.nivelGreen(x, y) + 0.312 * img.nivelBlue(x, y));
	            	
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
			proY.getImgPlus().show();
			proU.getImgPlus().show();
			proV.getImgPlus().show();
			
		}
}
