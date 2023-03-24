package src;

public class Expansao {
    int [][] vetor;
    
    public Expansao( double w, double h ) {
        vetor = new int[(int)w][(int)h];
        zerar();
    }
    
    public void zerar() {
    
        for(int i = 0; i < vetor.length; i++) {
            for(int j = 0; j < vetor[i].length; j++) {
                vetor[i][j] = 0;
            }
        }
    }
    
    public void expandir( Processador img, int x, int y, int tol, int rgbInit ) {
        
        if(vetor[x][y] != 0) {
            return;
        }
        
        /**
         * Estados de um pixel na matriz[x][y]
         * 00(0) - estado inicial
         * 10(2) - alterado, mas não atende ao limite da tolerância
         * 11(3) - alterado e atende ao limite da tolerancia 
        **/
        int rDif, gDif, bDif;
        
        int red = Processador.getRed(rgbInit);
        int green = Processador.getGreen(rgbInit);
        int blue = Processador.getBlue(rgbInit);
        
        if(!img.isNull(x, y)) {
            
                if(img.escalaAlpha(x, y) == 0){
                    return;
                }

                int r = img.nivelRed(x, y);
                int g = img.nivelGreen(x, y);
                int b = img.nivelBlue(x, y);
                
                if(r == 0 && g == 0 && b == 0) {
                    return;
                }
                
                rDif = (r - red);
                gDif = (g - green);
                bDif = (b - blue);
                
                if(rDif < 0)
                    rDif = -rDif;
                if(gDif < 0)
                    gDif = -gDif;
                if(bDif < 0)
                    bDif = -bDif;
                
                if(rDif > tol || gDif > tol || bDif > tol) {
                    vetor[x][y] = 2;
                    return;
                }
                    
                vetor[x][y] = 3;
                
                if(y >= 1 && vetor[x][y - 1] == 0) {
                    expandir(img, x, y - 1, tol, rgbInit);
                }
                if(y < (img.getHeight() - 1) && vetor[x][y + 1] == 0) {
                    expandir(img, x, y + 1, tol, rgbInit);
                }
                if(x >= 1 && vetor[x - 1][y] == 0) {
                    expandir(img, x - 1, y, tol, rgbInit);
                }       
                if(x < (img.getWidth() - 1) && vetor[x + 1][y] == 0) {
                    expandir(img, x + 1, y, tol, rgbInit);
                }           
        }
        
    }
    
    public int [][] getArray(){
        return vetor;
    }
}
