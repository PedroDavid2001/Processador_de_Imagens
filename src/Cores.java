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
			
			Processador proC = new Processador(); 
			Processador proM = new Processador(); 
			Processador proY = new Processador(); 
			
			
			proC.setImg( img.getImg() );
			proM.setImg( img.getImg() );
			proY.setImg( img.getImg() );
			
			//Cyan
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	                proC.setRGB(x, y, 0, 255 - img.nivelRed(x, y), 255 - img.nivelRed(x, y)); //1-R, G, B
	            }
	        }
			
			//Magenta
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	                proM.setRGB(x, y, 255 - img.nivelGreen(x, y), 0, 255 - img.nivelGreen(x, y));//R, 1-G, B
	            }
	        }
			
			//Yellow
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	                proY.setRGB(x, y, 255 - img.nivelBlue(x, y), 255 - img.nivelBlue(x, y), 0);//R, G, 1-B
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
	            	int nvlY = (int) (0.299 * img.nivelRed(x, y) + 0.587 * img.nivelGreen(x, y) + 0.114 * img.nivelBlue(x, y));
	                proY.setRGB(x, y, nvlY, nvlY, nvlY); //Y Y Y
	            }
	        }
			
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	            	int nvlU = (int) (-0.14713 * img.nivelRed(x, y) - 0.28886 * img.nivelGreen(x, y) + 0.436 * img.nivelBlue(x, y));
	            	
	            	if(nvlU < 0)
	            		nvlU = 0;//verificação para evitar valores negativos
	            	
	            	proU.setRGB(x, y, 0, nvlU, nvlU);//0 U U
	            }
	        }
		
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	            	int nvlV = (int) (0.615 * img.nivelRed(x, y) - 0.51499 * img.nivelGreen(x, y) + 0.312 * img.nivelBlue(x, y));
	            	
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
