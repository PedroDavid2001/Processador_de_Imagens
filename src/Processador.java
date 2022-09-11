package src;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Color;
import ij.IJ;
import ij.ImagePlus;

/**
 * Classe responsável por carregar uma imagem a partir do path do arquivo.
 * O objeto do tipo BufferedImage será incializado com o retorno do método 
 * read() da classe ImageIO caso seja uma imagem no formato PNG e nos demais 
 * casos será inicializado pelo método getBufferedImage() de um objeto da 
 * classe ImagePlus. 
 * Esses dois métodos de abertura foram selecionados por dois motivos: o 
 * método da ImagePlus não obteve êxito carregando imagens no formato PNG, 
 * pois não passava o campo Alpha dos pixels da imagem corretamente e o método 
 * da ImageIO dispara exceção na abertura de formato PGM. A priori o arquivo 
 * deverá estar contido no pacote "Imagens" dentro da pasta assets na raíz do 
 * projeto. O método de carregamento/abertura retornará um boolean informando 
 * se obteve êxito na abertura do arquivo.
 * 
 * ---------------- Captura dos campos do RGB ---------------------------
 * 
 * (CAPTURAR BLUE)
 * 0xff -> 1111 1111 -> 255
 * 
 * (CAPTURAR GREEN) 
 * 0xff00 -> 1111 1111 0000 0000 -> 65.820
 * 
 * (CAPTURAR RED) 
 * 0xff0000 -> 1111 1111 0000 0000 0000 0000 -> 16.711.680 
 * 
 * (CAPTURAR ALPHA)
 * 0xff000000 -> 1111 1111 0000 0000 0000 0000 0000 0000 -> 4.278.190.080 
 * 
 * ----------------------------------------------------------------------
 * 
 * - Cada elemento do rgb vai de 0000 0000 (0) à 1111 1111 (255)
 * 
 * - Para capturar os valores, é necessário utilizar o operador 
 *   binário & (and) e um valor hex
 *
 * - Rgb(R, G, B) -> ff, ff, ff -> 1111 1111, 1111 1111, 1111 1111
 *  
 *  CAPTURAR BLUE (ultimos 8 bits) 
 *  Rgb(150, 150, 150) & 0xff -> 
 *  ( 150 & 00, 
 *    150 & 00, 
 *    150 & ff ) ->
 *  ( 1001 0110 & 0000 0000, 
 *    1001 0110 & 0000 0000, 
 *    1001 0110 & 1111 1111 ) -> 
 *  ( 0000 0000
 *    0000 0000
 *    1001 0110 ) 
 *
 *  CAPTURAR GREEN (8 bits do meio) 
 *  Rgb(150, 150, 150) & 0xff00 -> 
 *  ( 150 & 00, 
 *    150 & ff, 
 *    150 & 00 ) ->
 *  ( 1001 0110 & 0000 0000, 
 *    1001 0110 & 1111 1111, 
 *    1001 0110 & 0000 0000) -> 
 *  ( 0000 0000 
 *    1001 0110 
 *    0000 0000 )
 *  
 * - Após realizar a operação AND, é necessário mover os bits, 
 *   pois o valor resultado não está correto
 *   1001 0110 0000 0000 = 38.400 !!!
 * 
 * - Para transformar em 150, é necessário mover 8 bits para 
 *   direita: 
 *   38.400 >> 8 -> 1001 0110 0000 0000 >> 8 -> 
 *   0000 0000 1001 0110 = { 150 } 
 *      
 **/

public class Processador {

    private BufferedImage arquivo;
    private ImagePlus imagePlus;
    
    public boolean carregarImg(){
        String path;
        
        imagePlus = IJ.openImage(); //abre o explorador de arquivo
       
        if(imagePlus != null){  //verifica se obteve sucesso na abertura do arquivo
            
            path = imagePlus.getOriginalFileInfo().getFilePath();   /*passa o path da imagem selecionada
                                                                    para a String*/

            String format = path.substring(path.length() - 4, path.length());   /*passa os quatro últimos caracteres 
                                                                                para a String para verificar o formato*/
            
            try {
                File file = new File(path);
                
                if(format.compareTo(".png") != 0){
                    arquivo = imagePlus.getBufferedImage();    
                }else{
                    arquivo = ImageIO.read(file);
                }
                return true;

            } catch (Exception exc) {

                exc.printStackTrace();
                System.out.println("Ocorreu um erro na abertura do arquivo!!");
              
                return false;

            }
        }else{
            return false;
        } 
    }
    
    public int nivelCinza(int x, int y){
        
        double cinza;

        /*
         * Para gerar uma escala de cinza, basta settar todos os campos 
         * do RGB com o mesmo valor, portanto Rgb(127, 127, 127) gera um 
         * pixel plotado com a cor cinza, mas Rgb(155, 22, 67) não. Para 
         * converter qualquer campo Rgb para seu equivalente na escala de 
         * cinza (gray scale), é necessário utilizar a seguinte fórmula:
         * 
         * gray = 0.299*R + 0.587*G + 0.114*B.
         * 
         * Para o exemplo usado Rgb(155, 22, 67), o equivalente será:
         * 
         * gray = ( 0.299 * 155 ) + (0.587 * 22 ) + ( 0.114 * 67 ) ->
         * gray = ( 46,345 ) + ( 12,914 ) + ( 7,638 ) ->
         * gray = 66,897
         * 
         * Fazendo um arredondamento chegaremos ao seguinte resultado
         * 
         * gray = 67 -> 
         * GrayScale(67, 67, 67)  
        */

        cinza = ( nivelBlue(x, y) * 0.114 ) + 
            ( nivelGreen(x, y) * 0.587 ) + ( nivelRed(x, y) * 0.299 ); 
        
        return (int) cinza;
    } 

    /*
     * Retornar os campos do ARGB (RGB caso não possua Alpha).
    */

    public int escalaAlpha(int x, int y){
        return ( arquivo.getRGB( x, y ) & 0xff000000 ) >> 24 ;
    }

    public int nivelRed(int x, int y){
        return ( arquivo.getRGB( x, y ) & 0xff0000 ) >> 16 ;
    }

    public int nivelGreen(int x, int y){
        return ( arquivo.getRGB( x, y ) & 0xff00 ) >> 8 ;
    }

    public int nivelBlue(int x, int y){
        return arquivo.getRGB( x, y ) & 0xff;
    }

    /*
     * Getters e Setters do objeto BufferedImage
    */

    public BufferedImage getImg(){
        if(arquivo == null)
            return null;
        return arquivo;
    }
    
    public void setImg(BufferedImage img){

        arquivo = new BufferedImage(
            img.getWidth(), 
            img.getHeight(), 
            img.getType() );

        for(int x = 0; x < img.getWidth() ; x++) {
            for(int y = 0; y < img.getHeight(); y++) {
                arquivo.setRGB( x, y, img.getRGB(x, y) );
            }
        }
    }

    public ImagePlus getImgPlus(){
        if(imagePlus == null)
            return null;
        return imagePlus;
    }

    public void apagarImg(){
        this.arquivo = null;
    }

    public int getType(){
        return arquivo.getType();
    }

    public double getWidth(){
        return arquivo.getWidth();
    }

    public double getHeight(){
        return arquivo.getHeight();
    } 

    public int getRGB(int x, int y){
        return arquivo.getRGB(x, y);
    }
    
    public void setRGB(int x, int y, int r, int g, int b){
        
        Color cor = new Color(r, g, b);
        if(escalaAlpha(x, y) != 0)
            arquivo.setRGB(x, y, cor.getRGB());
        
    }

}