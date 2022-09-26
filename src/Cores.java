package src;

public class Cores {
	
	//exibe os components RGB
	public static void RGB(Processador img) {
		
		Processador proR = new Processador(); //Ciano
		Processador proG = new Processador(); //Amarelo
		Processador proB = new Processador(); //Magenta
		
		
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
	
	//exibe os components RGB
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
	                proY.setRGB(x, y, nvlY, nvlY, nvlY); 
	            }
	        }
			
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	            	int nvlU;
	                proU.setRGB(x, y, 0, img.nivelGreen(x, y), 0);//0 G 0
	            }
	        }
		
			for(int y = 0; y < img.getHeight(); y++){
	            for(int x = 0; x < img.getWidth(); x++){
	                proV.setRGB(x, y, 0, 0, img.nivelBlue(x, y));//0 0 B
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
