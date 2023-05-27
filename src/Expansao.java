package src;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Expansao {
    /*
     * Matriz que informa o estado de cada pixel 
     * na imagem após uma ação de expansão de cor 
     * ou remoção de pixels.
     * 0 = estado inicial
     * 1 = contorno 
     * 2 = não será alterado
     * 3 = sreá alterado
     */
    int [][] matriz_pixels;
    
    public Expansao( double w, double h ) {
        matriz_pixels = new int[(int)w][(int)h];
        zerar();
    }
    
    public void zerar() {
    
        for(int i = 0; i < matriz_pixels.length; i++) {
            for(int j = 0; j < matriz_pixels[i].length; j++) {
                matriz_pixels[i][j] = 0;
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

            if (matriz_pixels[px][py] != 0) {
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
                    matriz_pixels[px][py] = 2;
                    continue;
                }
                
                // Pixel se encaixa na tolerância, 
                // portanto poderá ser alterado.
                matriz_pixels[px][py] = 3;

                /* 
                 * Verifica-se a vizinhança do pixel.
                 * Os vizinhos "aptos" serão armazenados 
                 * na pilha para serem verificados nas 
                 * próximas iterações.
                 */
                if (py >= 1 && matriz_pixels[px][py - 1] == 0) {
                    stack.push(new Point(px, py - 1));
                }
                if (py < (img.getHeight() - 1) && matriz_pixels[px][py + 1] == 0) {
                    stack.push(new Point(px, py + 1));
                }
                if (px >= 1 && matriz_pixels[px - 1][py] == 0) {
                    stack.push(new Point(px - 1, py));
                }
                if (px < (img.getWidth() - 1) && matriz_pixels[px + 1][py] == 0) {
                    stack.push(new Point(px + 1, py));
                }
            }
        }
    }
    
    public void criarContorno() {
        List<Point> contorno = new ArrayList<>();
        
        // Percorre a matriz
        for (int i = 0; i < matriz_pixels.length; i++) {
            for (int j = 0; j < matriz_pixels[0].length; j++) {
                // Verifica se o pixel atual é um ponto de contorno
                if (ehPontoContorno(matriz_pixels, i, j)) {
                    // Adiciona o pixel à lista de pontos de contorno
                    contorno.add(new Point(i, j));
                }
            }
        }
        
        for(Point pix : contorno) {
            matriz_pixels[pix.x][pix.y] = 1;
        }
    }

    public boolean ehPontoContorno(int[][] matriz, int x, int y) {
        // Verifica se o pixel atual é diferente de zero
        if (matriz[x][y] != 0) {
            // Verifica se o pixel tem pelo menos um vizinho que está fora da área selecionada
            if (temVizinhoFora(matriz, x, y)) {
                return true; // É um ponto de contorno
            }
        }
        
        return false; // Não é um ponto de contorno
    }

    public boolean temVizinhoFora(int[][] matriz, int x, int y) {
        int[] dx = {-1, 0, 1, 0}; // Deslocamento em x para os vizinhos (esquerda, cima, direita, baixo)
        int[] dy = {0, -1, 0, 1}; // Deslocamento em y para os vizinhos (esquerda, cima, direita, baixo)
        
        // Verifica cada vizinho
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            // Verifica se o vizinho está fora dos limites da matriz
            if (nx < 0 || nx >= matriz.length || ny < 0 || ny >= matriz[0].length) {
                return true; // Vizinho está fora da matriz, é um ponto de contorno
            }
            
            // Verifica se o vizinho é zero (fora da área selecionada)
            if (matriz[nx][ny] == 0) {
                return true; // Vizinho está fora da área selecionada, é um ponto de contorno
            }
        }
        
        return false; // Todos os vizinhos estão dentro da área selecionada
    }
        
    public int [][] getArray(){
        return matriz_pixels;
    }
}
