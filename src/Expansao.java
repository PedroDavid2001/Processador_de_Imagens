package src;

import java.awt.Point;
import java.util.Stack;

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
    
    public void expandir(Processador img, int x, int y, int tol, int rgbInit) {
        
        /* 
         * Uma pilha(stack) armazena os pontos na imagem. 
         * O primeiro ponto é passado pelo parâmetro.
         * 
         */
        
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(x, y));
        
        
        /*
         * A cada iteração é resgatado o último 
         * ponto armanzenado na pilha e as 
         * coordenadas dele são passadas para 
         * as variáveis inteiras "px" e "py".
         */
        while (!stack.isEmpty()) {
            Point p = stack.pop();
            int px = p.x;
            int py = p.y;

            if (vetor[px][py] != 0) {
                continue;
            }

            int red = Processador.getRed(rgbInit);
            int green = Processador.getGreen(rgbInit);
            int blue = Processador.getBlue(rgbInit);

            if (!img.isNull(px, py)) {
                if (img.escalaAlpha(px, py) == 0) {
                    continue;
                }

                int r = img.nivelRed(px, py);
                int g = img.nivelGreen(px, py);
                int b = img.nivelBlue(px, py);

                if (r == 0 && g == 0 && b == 0) {
                    continue;
                }

                int rDif = Math.abs(r - red);
                int gDif = Math.abs(g - green);
                int bDif = Math.abs(b - blue);

                if (rDif > tol || gDif > tol || bDif > tol) {
                    // Valor do pixel vai além da tolerância, 
                    // portanto não poderá ser alterado.
                    vetor[px][py] = 2;
                    continue;
                }
                
                // Pixel se encaixa na tolerância, 
                // portanto poderá ser alterado.
                vetor[px][py] = 3;

                /* 
                 * Verifica-se a vizinhança do pixel.
                 * Os vizinhos "aptos" serão armazenados 
                 * na pilha para serem verificados nas 
                 * próximas iterações.
                 */
                if (py >= 1 && vetor[px][py - 1] == 0) {
                    stack.push(new Point(px, py - 1));
                }
                if (py < (img.getHeight() - 1) && vetor[px][py + 1] == 0) {
                    stack.push(new Point(px, py + 1));
                }
                if (px >= 1 && vetor[px - 1][py] == 0) {
                    stack.push(new Point(px - 1, py));
                }
                if (px < (img.getWidth() - 1) && vetor[px + 1][py] == 0) {
                    stack.push(new Point(px + 1, py));
                }
            }
        }
    }
        
    public int [][] getArray(){
        return vetor;
    }
}
