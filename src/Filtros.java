package src;

import java.awt.image.BufferedImage;

public class Filtros {
    
    //FILTROS PASSA-BAIXA
    
    /**
     * Filtro média 3x3 se trata de uma máscara que 
     * calcula a média da vizinhança e substitui o pixel
     */
    
    public static BufferedImage media3x3(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        float rTotal, gTotal, bTotal;
        float rMedio, gMedio, bMedio;
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {

              //red
                rTotal = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        rTotal += img.nivelRed(i, j);
                    }
                
                rTotal /= 255;
                rMedio = rTotal / 9;
                
                //green
                gTotal = 0; 
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        gTotal += img.nivelGreen(i, j);
                    }
                
                gTotal /= 255;
                gMedio = gTotal / 9;
                
                //blue
                bTotal = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        bTotal += img.nivelBlue(i, j);
                    }
                bTotal /= 255;
                bMedio = bTotal / 9;
                
                tmp.setRGB(x, y, rMedio, gMedio, bMedio);
            }
        
        return tmp.getImg();
    }
    
    /**
     * Filtro média 5x5 se trata de uma máscara que 
     * calcula a média da vizinhança e substitui o pixel
     */
    
    public static BufferedImage media5x5(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        float rTotal, gTotal, bTotal;
        float rMedio, gMedio, bMedio;
        
        for(int y = 2; y < (img.getHeight() - 2); y++) 
            for(int x = 2; x < (img.getWidth() - 2); x++) {
                
                //red
                rTotal = 0;
                for(int j = y - 2; j < y + 3; j++)
                    for(int i = x - 2; i < x + 3; i++) {
                        rTotal += img.nivelRed(i, j);
                    }
                
                rTotal /= 255;
                rMedio = rTotal / 25;
                
                //green
                gTotal = 0; 
                for(int j = y - 2; j < y + 3; j++)
                    for(int i = x - 2; i < x + 3; i++) {
                        gTotal += img.nivelGreen(i, j);
                    }
                
                gTotal /= 255;
                gMedio = gTotal / 25;
                
                //blue
                bTotal = 0;
                for(int j = y - 2; j < y + 3; j++)
                    for(int i = x - 2; i < x + 3; i++) {
                        bTotal += img.nivelBlue(i, j);
                    }
                bTotal /= 255;
                bMedio = bTotal / 25;
                
                tmp.setRGB(x, y, rMedio, gMedio, bMedio);
            }
        
        return tmp.getImg();
    }
    

    /**
     * Filtro mediana 3x3 se trata de uma máscara que 
     * verifica a mediana da vizinhança e substitui o pixel
     */
    
    public static BufferedImage mediana3x3(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int rTotal[], gTotal[], bTotal[];
        int rMedio, gMedio, bMedio;
        int rI, gI, bI; //identadores
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                //red
                rTotal = new int[9];
                rI = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        rTotal[rI] = img.nivelRed(i, j);
                        rI++;
                    }
                
                rMedio = mediana(rTotal);
                
                //green
                gTotal = new int[9];
                gI = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        gTotal[gI] = img.nivelGreen(i, j);
                        gI++;
                            
                    }
                
                gMedio = mediana(gTotal);
                
                //blue
                bTotal = new int[9];
                bI = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        bTotal[bI] = img.nivelBlue(i, j);
                        bI++;
                            
                    }
                
                bMedio = mediana(bTotal);
                
                tmp.setRGB(x, y, rMedio, gMedio, bMedio);
            }
        
        return tmp.getImg();
    }
    
    /**
     * Filtro mediana 5x5 se trata de uma máscara que 
     * verifica a mediana da vizinhança e substitui o pixel
     */
    
    public static BufferedImage mediana5x5(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int rTotal[], gTotal[], bTotal[];
        int rMedio, gMedio, bMedio;
        int rI, gI, bI; //identadores
        
        for(int y = 2; y < (img.getHeight() - 2); y++) 
            for(int x = 2; x < (img.getWidth() - 2); x++) {
                
                //red
                rTotal = new int[25];
                rI = 0;
                for(int j = y - 2; j < y + 3; j++)
                    for(int i = x - 2; i < x + 3; i++) {
                        rTotal[rI] = img.nivelRed(i, j);
                        rI++;
                    }
                
                rMedio = mediana(rTotal);
                
                //green
                gTotal = new int[25];
                gI = 0;
                for(int j = y - 2; j < y + 3; j++)
                    for(int i = x - 2; i < x + 3; i++) {
                        gTotal[gI] = img.nivelGreen(i, j);
                        gI++;
                            
                    }
                
                gMedio = mediana(gTotal);
                
                //blue
                bTotal = new int[25];
                bI = 0;
                for(int j = y - 2; j < y + 3; j++)
                    for(int i = x - 2; i < x + 3; i++) {
                        bTotal[bI] = img.nivelBlue(i, j);
                        bI++;
                            
                    }
                
                bMedio = mediana(bTotal);
                
                tmp.setRGB(x, y, rMedio, gMedio, bMedio);
            }
        
        return tmp.getImg();
    }
    
    //método usado para organizar de forma crescente um vetor e retornar a mediana
    private static int mediana( int [] vetor) {
        
        int i;
        boolean trocou = false;
        int [] novo = vetor;
        int mediana = (int)(vetor.length / 2);
        int tmp;
        
        while(!trocou) {
            i = 0;
            
            while(i < (novo.length - 1)) {
                
                if(novo[i] < novo[i + 1]) {
                    tmp = novo[i];
                    novo[i] = novo[i + 1];
                    novo[i + 1] = tmp;
                    trocou = true;
                }
                
                i++;
            }
            
            if(!trocou)
                break;
            
            trocou = false;
        }
        
        return novo[ mediana ];
    }
    
    /**
     * Filtro mínimo se trata de uma máscara que 
     * substitui o pixel pelo de menor intensidade 
     * na vizinhança
     */
    
    public static BufferedImage minimo(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int rMenor, gMenor, bMenor;
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                //red
                rMenor = 255;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        if(img.nivelRed(i, j) < rMenor)
                            rMenor = img.nivelRed(i, j);
                    }
                
                //green
                gMenor = 255; 
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        if(img.nivelGreen(i, j) < gMenor)
                            gMenor = img.nivelGreen(i, j);
                    }
                
                //blue
                bMenor = 255;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        if(img.nivelBlue(i, j) < bMenor)
                            bMenor = img.nivelBlue(i, j);
                    }
                
                tmp.setRGB(x, y, rMenor, gMenor, bMenor);
            }
        
        return tmp.getImg();
    }
    
    /**
     * Filtro máximo se trata de uma máscara que 
     * substitui o pixel pelo de maior intensidade 
     * na vizinhança
     */
    
    public static BufferedImage maximo(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int rMaior, gMaior, bMaior;
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                //red
                rMaior = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        if(img.nivelRed(i, j) > rMaior)
                            rMaior = img.nivelRed(i, j);
                    }
                
                //green
                gMaior = 0; 
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        if(img.nivelGreen(i, j) > gMaior)
                            gMaior = img.nivelGreen(i, j);
                    }
                
                //blue
                bMaior = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        if(img.nivelBlue(i, j) > bMaior)
                            bMaior = img.nivelBlue(i, j);
                    }
                
                tmp.setRGB(x, y, rMaior, gMaior, bMaior);
            }
        
        return tmp.getImg();
    }
    
    /**
     * Filtro máximo se trata de uma máscara que 
     * substitui o pixel pelo de maior intensidade 
     * na vizinhança
     */
    
    public static BufferedImage moda(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int rTotal[], gTotal[], bTotal[];
        int rMedio, gMedio, bMedio;
        int rI, gI, bI; //identadores
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                //red
                rTotal = new int[8];
                rI = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        if(j != y || i != x) {
                            rTotal[rI] = img.nivelRed(i, j);
                            rI++;
                        }
                    }
                
                rMedio = moda(rTotal);
                
                //green
                gTotal = new int[8];
                gI = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        if(j != y || i != x) {
                            gTotal[gI] = img.nivelGreen(i, j);
                            gI++;
                        }
                            
                    }
                
                gMedio = moda(gTotal);
                
                //blue
                bTotal = new int[8];
                bI = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        if(j != y || i != x) {
                            bTotal[bI] = img.nivelBlue(i, j);
                            bI++;
                        }
                            
                    }
                
                bMedio = moda(bTotal);
                
                tmp.setRGB(x, y, rMedio, gMedio, bMedio);
            }
        
        return tmp.getImg();
    }
    
    //método usado para retornar a moda de um vetor
    private static int moda( int [] vetor) {
        
        int repeticao;
        int maisRepetido = 0;
        int maiorRepeticao = 0;
        int atual;
        
        for(int i = 0; i < vetor.length; i++) {
            atual = vetor[i];
            repeticao = 0;
            
            for(int a = 0; a < vetor.length; a++) {
                if(vetor[a] == atual)
                    repeticao++;
            }
            if(repeticao > maiorRepeticao) {
                maiorRepeticao = repeticao;
                maisRepetido = atual;
            }
               
        }
        
        return maisRepetido;
    }
    /**
     * Filtro kawahara cria quatro máscaras em relação ao 
     * pixel e seleciona a intensidade média da máscara 
     * com menor variância
     */
    
    public static BufferedImage kuwahara(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        
        float mediaVarianciasRed[];
        float mediaVarianciasGreen[];
        float mediaVarianciasBlue[];
        
        float valoresRed[];
        float valoresGreen[];
        float valoresBlue[];
        
        int index;
        
        float r = 0, g = 0, b = 0;
        
        for(int y = 2; y < (img.getHeight() - 2); y++) 
            for(int x = 2; x < (img.getWidth() - 2); x++) {
                
                mediaVarianciasRed = new float[8];
                mediaVarianciasGreen = new float[8];
                mediaVarianciasBlue = new float[8];
                
                valoresRed = new float[9];
                valoresGreen = new float[9];
                valoresBlue = new float[9];
                
                //mascara 1
                index = 0;
                for(int j = y - 2; j <= y; j++) {
                    for(int i = x - 2; i <= x; i++) {
                        valoresRed[index] = img.nivelRed(i, j);
                        valoresGreen[index] = img.nivelGreen(i, j);
                        valoresBlue[index] = img.nivelBlue(i, j);
                        index++;
                    }
                }
                mediaVarianciasRed[0] = media(valoresRed);
                mediaVarianciasRed[1] = variancia(valoresRed, mediaVarianciasRed[0]);
                
                mediaVarianciasGreen[0] = media(valoresGreen);
                mediaVarianciasGreen[1] = variancia(valoresGreen, mediaVarianciasGreen[0]);
                
                mediaVarianciasBlue[0] = media(valoresBlue);
                mediaVarianciasBlue[1] = variancia(valoresBlue, mediaVarianciasBlue[0]);
                
                //mascara 2
                index = 0;
                for(int j = y - 2; j <= y; j++) {
                    for(int i = x; i <= x + 2; i++) {
                        valoresRed[index] = img.nivelRed(i, j);
                        valoresGreen[index] = img.nivelGreen(i, j);
                        valoresBlue[index] = img.nivelBlue(i, j);
                        index++;
                    }
                }
                mediaVarianciasRed[2] = media(valoresRed);
                mediaVarianciasRed[3] = variancia(valoresRed, mediaVarianciasRed[2]);
                
                mediaVarianciasGreen[2] = media(valoresGreen);
                mediaVarianciasGreen[3] = variancia(valoresGreen, mediaVarianciasGreen[2]);
                
                mediaVarianciasBlue[2] = media(valoresBlue);
                mediaVarianciasBlue[3] = variancia(valoresBlue, mediaVarianciasBlue[2]);
                
                //mascara 3
                index = 0;
                for(int j = y; j <= y + 2; j++) {
                    for(int i = x - 2; i <= x; i++) {
                        valoresRed[index] = img.nivelRed(i, j);
                        valoresGreen[index] = img.nivelGreen(i, j);
                        valoresBlue[index] = img.nivelBlue(i, j);
                        index++;
                    }
                }
                mediaVarianciasRed[4] = media(valoresRed);
                mediaVarianciasRed[5] = variancia(valoresRed, mediaVarianciasRed[4]);
                
                mediaVarianciasGreen[4] = media(valoresGreen);
                mediaVarianciasGreen[5] = variancia(valoresGreen, mediaVarianciasGreen[4]);
                
                mediaVarianciasBlue[4] = media(valoresBlue);
                mediaVarianciasBlue[5] = variancia(valoresBlue, mediaVarianciasBlue[4]);
                
                //mascara 4
                index = 0;
                for(int j = y; j <= y + 2; j++) {
                    for(int i = x; i <= x + 2; i++) {
                        valoresRed[index] = img.nivelRed(i, j);
                        valoresGreen[index] = img.nivelGreen(i, j);
                        valoresBlue[index] = img.nivelBlue(i, j);
                        index++;
                    }
                }
                mediaVarianciasRed[6] = media(valoresRed);
                mediaVarianciasRed[7] = variancia(valoresRed, mediaVarianciasRed[6]);
                
                mediaVarianciasGreen[6] = media(valoresGreen);
                mediaVarianciasGreen[7] = variancia(valoresGreen, mediaVarianciasGreen[6]);
                
                mediaVarianciasBlue[6] = media(valoresBlue);
                mediaVarianciasBlue[7] = variancia(valoresBlue, mediaVarianciasBlue[6]);
                
                r =  menorVariancia(mediaVarianciasRed);
                g =  menorVariancia(mediaVarianciasGreen);
                b =  menorVariancia(mediaVarianciasBlue);
                
                tmp.setRGB(x, y, r, g, b);
            }
        
        return tmp.getImg();
    }
    
    private static float media(float valores[]) {
        
        float qnt = 0;
        float total = 0;
        
        for(int i = 0; i < valores.length; i++) {
            if(valores[i] != -1) {
                total += valores[i];
                qnt++;
            }  
        }
        
        return (total / qnt);
        
    }
    
    private static float variancia(float valores[], float media) {
        
        float qnt = 0;
        float total = 0;
        
        for(int i = 0; i < valores.length; i++) {
            if(valores[i] != -1) {
                total += (( valores[i] - media ) * ( valores[i] - media ));
                qnt++;
            }
        }
        
        return (total / qnt);
    }
    
    private static float menorVariancia(float medVar[]) {
        
        float menorMedia = 0;
        float menorVariancia = Float.MAX_VALUE;
        
        for(int i = 1; i < medVar.length; i += 2) {
            if(medVar[i] < menorVariancia) {
                menorMedia = (medVar[i - 1] / 255.0f);
                menorVariancia = medVar[i]; 
            }
                
        }
        
        return menorMedia;
    }
    
    /**
     * Filtro Tomita e Tsuji cria cinco máscaras em 
     * relação ao pixel e seleciona a intensidade 
     * média da máscara com menor variância
     */
    
    public static BufferedImage tomitaTsuji(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        
        float mediaVarianciasRed[];
        float mediaVarianciasGreen[];
        float mediaVarianciasBlue[];
        
        float valoresRed[];
        float valoresGreen[];
        float valoresBlue[];
        
        int index;
        
        float r = 0, g = 0, b = 0;
        
        for(int y = 2; y < (img.getHeight() - 2); y++) 
            for(int x = 2; x < (img.getWidth() - 2); x++) {
                
                mediaVarianciasRed = new float[10];
                mediaVarianciasGreen = new float[10];
                mediaVarianciasBlue = new float[10];
                
                valoresRed = new float[9];
                valoresGreen = new float[9];
                valoresBlue = new float[9];
                
                //mascara 1
                index = 0;
                for(int j = y - 2; j <= y; j++) {
                    for(int i = x - 2; i <= x; i++) {
                        valoresRed[index] = img.nivelRed(i, j);
                        valoresGreen[index] = img.nivelGreen(i, j);
                        valoresBlue[index] = img.nivelBlue(i, j);
                        index++;
                    }
                }
                mediaVarianciasRed[0] = media(valoresRed);
                mediaVarianciasRed[1] = variancia(valoresRed, mediaVarianciasRed[0]);
                
                mediaVarianciasGreen[0] = media(valoresGreen);
                mediaVarianciasGreen[1] = variancia(valoresGreen, mediaVarianciasGreen[0]);
                
                mediaVarianciasBlue[0] = media(valoresBlue);
                mediaVarianciasBlue[1] = variancia(valoresBlue, mediaVarianciasBlue[0]);
                
                //mascara 2
                index = 0;
                for(int j = y - 2; j <= y; j++) {
                    for(int i = x; i <= x + 2; i++) {
                        valoresRed[index] = img.nivelRed(i, j);
                        valoresGreen[index] = img.nivelGreen(i, j);
                        valoresBlue[index] = img.nivelBlue(i, j);
                        index++;
                    }
                }
                mediaVarianciasRed[2] = media(valoresRed);
                mediaVarianciasRed[3] = variancia(valoresRed, mediaVarianciasRed[2]);
                
                mediaVarianciasGreen[2] = media(valoresGreen);
                mediaVarianciasGreen[3] = variancia(valoresGreen, mediaVarianciasGreen[2]);
                
                mediaVarianciasBlue[2] = media(valoresBlue);
                mediaVarianciasBlue[3] = variancia(valoresBlue, mediaVarianciasBlue[2]);
                
                //mascara 3
                index = 0;
                for(int j = y; j <= y + 2; j++) {
                    for(int i = x - 2; i <= x; i++) {
                        valoresRed[index] = img.nivelRed(i, j);
                        valoresGreen[index] = img.nivelGreen(i, j);
                        valoresBlue[index] = img.nivelBlue(i, j);
                        index++;
                    }
                }
                mediaVarianciasRed[4] = media(valoresRed);
                mediaVarianciasRed[5] = variancia(valoresRed, mediaVarianciasRed[4]);
                
                mediaVarianciasGreen[4] = media(valoresGreen);
                mediaVarianciasGreen[5] = variancia(valoresGreen, mediaVarianciasGreen[4]);
                
                mediaVarianciasBlue[4] = media(valoresBlue);
                mediaVarianciasBlue[5] = variancia(valoresBlue, mediaVarianciasBlue[4]);
                
                //mascara 4
                index = 0;
                for(int j = y; j <= y + 2; j++) {
                    for(int i = x; i <= x + 2; i++) {
                        valoresRed[index] = img.nivelRed(i, j);
                        valoresGreen[index] = img.nivelGreen(i, j);
                        valoresBlue[index] = img.nivelBlue(i, j);
                        index++;
                    }
                }
                mediaVarianciasRed[6] = media(valoresRed);
                mediaVarianciasRed[7] = variancia(valoresRed, mediaVarianciasRed[6]);
                
                mediaVarianciasGreen[6] = media(valoresGreen);
                mediaVarianciasGreen[7] = variancia(valoresGreen, mediaVarianciasGreen[6]);
                
                mediaVarianciasBlue[6] = media(valoresBlue);
                mediaVarianciasBlue[7] = variancia(valoresBlue, mediaVarianciasBlue[6]);
                
              //mascara 5
                index = 0;
                for(int j = y - 1; j <= y + 1; j++) {
                    for(int i = x - 1; i <= x + 1; i++) {
                        valoresRed[index] = img.nivelRed(i, j);
                        valoresGreen[index] = img.nivelGreen(i, j);
                        valoresBlue[index] = img.nivelBlue(i, j);
                        index++;
                    }
                }
                mediaVarianciasRed[8] = media(valoresRed);
                mediaVarianciasRed[9] = variancia(valoresRed, mediaVarianciasRed[8]);
                
                mediaVarianciasGreen[8] = media(valoresGreen);
                mediaVarianciasGreen[9] = variancia(valoresGreen, mediaVarianciasGreen[8]);
                
                mediaVarianciasBlue[8] = media(valoresBlue);
                mediaVarianciasBlue[9] = variancia(valoresBlue, mediaVarianciasBlue[8]);
                
                r =  menorVariancia(mediaVarianciasRed);
                g =  menorVariancia(mediaVarianciasGreen);
                b =  menorVariancia(mediaVarianciasBlue);
                
                tmp.setRGB(x, y, r, g, b);
            }
        
        return tmp.getImg();
    }
    
    /**
     * Filtro Nagao e Matsuyama cria nove máscaras em 
     * relação ao pixel e seleciona a intensidade 
     * média da máscara com menor variância
     */
    
    public static BufferedImage nagaoMatsuyama(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        
        float mediaVarianciasRed[];
        float mediaVarianciasGreen[];
        float mediaVarianciasBlue[];
        
        float valoresRed[];
        float valoresGreen[];
        float valoresBlue[];
        
        int index;
        
        float r = 0, g = 0, b = 0;
        
        for(int y = 2; y < (img.getHeight() - 2); y++) 
            for(int x = 2; x < (img.getWidth() - 2); x++) {
                
                mediaVarianciasRed = new float[18];
                mediaVarianciasGreen = new float[18];
                mediaVarianciasBlue = new float[18];
                
                valoresRed = new float[9];
                valoresGreen = new float[9];
                valoresBlue = new float[9];
                
                //mascara 1
                index = 0;
                for(int j = y - 2; j <= y; j++) {
                    for(int i = x - 1; i <= x + 1; i++) {
                        if(index != 6 && index != 8) {
                            valoresRed[index] = img.nivelRed(i, j);
                            valoresGreen[index] = img.nivelGreen(i, j);
                            valoresBlue[index] = img.nivelBlue(i, j);
                        }
                        else {
                            valoresRed[index] = -1;
                            valoresGreen[index] = -1;
                            valoresBlue[index] = -1;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[0] = media(valoresRed);
                mediaVarianciasRed[1] = variancia(valoresRed, mediaVarianciasRed[0]);
                
                mediaVarianciasGreen[0] = media(valoresGreen);
                mediaVarianciasGreen[1] = variancia(valoresGreen, mediaVarianciasGreen[0]);
                
                mediaVarianciasBlue[0] = media(valoresBlue);
                mediaVarianciasBlue[1] = variancia(valoresBlue, mediaVarianciasBlue[0]);
                
                //mascara 2
                index = 0;
                for(int j = y - 1; j <= y + 1; j++) {
                    for(int i = x; i <= x + 2; i++) {
                        if(index != 0 && index != 6) {
                            valoresRed[index] = img.nivelRed(i, j);
                            valoresGreen[index] = img.nivelGreen(i, j);
                            valoresBlue[index] = img.nivelBlue(i, j);
                        }
                        else {
                            valoresRed[index] = -1;
                            valoresGreen[index] = -1;
                            valoresBlue[index] = -1;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[2] = media(valoresRed);
                mediaVarianciasRed[3] = variancia(valoresRed, mediaVarianciasRed[2]);
                
                mediaVarianciasGreen[2] = media(valoresGreen);
                mediaVarianciasGreen[3] = variancia(valoresGreen, mediaVarianciasGreen[2]);
                
                mediaVarianciasBlue[2] = media(valoresBlue);
                mediaVarianciasBlue[3] = variancia(valoresBlue, mediaVarianciasBlue[2]);
                
                //mascara 3
                index = 0;
                for(int j = y; j <= y + 2; j++) {
                    for(int i = x - 1; i <= x + 1; i++) {
                        if(index != 0 && index != 2) {
                            valoresRed[index] = img.nivelRed(i, j);
                            valoresGreen[index] = img.nivelGreen(i, j);
                            valoresBlue[index] = img.nivelBlue(i, j);
                        }
                        else {
                            valoresRed[index] = -1;
                            valoresGreen[index] = -1;
                            valoresBlue[index] = -1;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[4] = media(valoresRed);
                mediaVarianciasRed[5] = variancia(valoresRed, mediaVarianciasRed[4]);
                
                mediaVarianciasGreen[4] = media(valoresGreen);
                mediaVarianciasGreen[5] = variancia(valoresGreen, mediaVarianciasGreen[4]);
                
                mediaVarianciasBlue[4] = media(valoresBlue);
                mediaVarianciasBlue[5] = variancia(valoresBlue, mediaVarianciasBlue[4]);
                
                //mascara 4
                index = 0;
                for(int j = y - 1; j <= y + 1; j++) {
                    for(int i = x - 2; i <= x; i++) {
                        if(index != 2 && index != 8) {
                            valoresRed[index] = img.nivelRed(i, j);
                            valoresGreen[index] = img.nivelGreen(i, j);
                            valoresBlue[index] = img.nivelBlue(i, j);
                        }
                        else {
                            valoresRed[index] = -1;
                            valoresGreen[index] = -1;
                            valoresBlue[index] = -1;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[6] = media(valoresRed);
                mediaVarianciasRed[7] = variancia(valoresRed, mediaVarianciasRed[6]);
                
                mediaVarianciasGreen[6] = media(valoresGreen);
                mediaVarianciasGreen[7] = variancia(valoresGreen, mediaVarianciasGreen[6]);
                
                mediaVarianciasBlue[6] = media(valoresBlue);
                mediaVarianciasBlue[7] = variancia(valoresBlue, mediaVarianciasBlue[6]);
                
              //mascara 5
                index = 0;
                for(int j = y - 1; j <= y + 1; j++) {
                    for(int i = x - 1; i <= x + 1; i++) {
                        valoresRed[index] = img.nivelRed(i, j);
                        valoresGreen[index] = img.nivelGreen(i, j);
                        valoresBlue[index] = img.nivelBlue(i, j);
                        index++;
                    }
                }
                mediaVarianciasRed[8] = media(valoresRed);
                mediaVarianciasRed[9] = variancia(valoresRed, mediaVarianciasRed[8]);
                
                mediaVarianciasGreen[8] = media(valoresGreen);
                mediaVarianciasGreen[9] = variancia(valoresGreen, mediaVarianciasGreen[8]);
                
                mediaVarianciasBlue[8] = media(valoresBlue);
                mediaVarianciasBlue[9] = variancia(valoresBlue, mediaVarianciasBlue[8]);
                
                //mascara 6
                index = 0;
                for(int j = y - 2; j <= y; j++) {
                    for(int i = x - 2; i <= x; i++) {
                        if(index != 2 && index != 6) {
                            valoresRed[index] = img.nivelRed(i, j);
                            valoresGreen[index] = img.nivelGreen(i, j);
                            valoresBlue[index] = img.nivelBlue(i, j);
                        }
                        else {
                            valoresRed[index] = -1;
                            valoresGreen[index] = -1;
                            valoresBlue[index] = -1;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[10] = media(valoresRed);
                mediaVarianciasRed[11] = variancia(valoresRed, mediaVarianciasRed[10]);
                
                mediaVarianciasGreen[10] = media(valoresGreen);
                mediaVarianciasGreen[11] = variancia(valoresGreen, mediaVarianciasGreen[10]);
                
                mediaVarianciasBlue[10] = media(valoresBlue);
                mediaVarianciasBlue[11] = variancia(valoresBlue, mediaVarianciasBlue[10]);
                
                //mascara 7
                index = 0;
                for(int j = y - 2; j <= y; j++) {
                    for(int i = x; i <= x + 2; i++) {
                        if(index != 0 && index != 8) {
                            valoresRed[index] = img.nivelRed(i, j);
                            valoresGreen[index] = img.nivelGreen(i, j);
                            valoresBlue[index] = img.nivelBlue(i, j);
                        }
                        else {
                            valoresRed[index] = -1;
                            valoresGreen[index] = -1;
                            valoresBlue[index] = -1;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[12] = media(valoresRed);
                mediaVarianciasRed[13] = variancia(valoresRed, mediaVarianciasRed[12]);
                
                mediaVarianciasGreen[12] = media(valoresGreen);
                mediaVarianciasGreen[13] = variancia(valoresGreen, mediaVarianciasGreen[12]);
                
                mediaVarianciasBlue[12] = media(valoresBlue);
                mediaVarianciasBlue[13] = variancia(valoresBlue, mediaVarianciasBlue[12]);
                
                //mascara 8
                index = 0;
                for(int j = y; j <= y + 2; j++) {
                    for(int i = x - 2; i <= x; i++) {
                        if(index != 0 && index != 8) {
                            valoresRed[index] = img.nivelRed(i, j);
                            valoresGreen[index] = img.nivelGreen(i, j);
                            valoresBlue[index] = img.nivelBlue(i, j);
                        }
                        else {
                            valoresRed[index] = -1;
                            valoresGreen[index] = -1;
                            valoresBlue[index] = -1;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[14] = media(valoresRed);
                mediaVarianciasRed[15] = variancia(valoresRed, mediaVarianciasRed[14]);
                
                mediaVarianciasGreen[14] = media(valoresGreen);
                mediaVarianciasGreen[15] = variancia(valoresGreen, mediaVarianciasGreen[14]);
                
                mediaVarianciasBlue[14] = media(valoresBlue);
                mediaVarianciasBlue[15] = variancia(valoresBlue, mediaVarianciasBlue[14]);
                
                //mascara 9
                index = 0;
                for(int j = y; j <= y + 2; j++) {
                    for(int i = x; i <= x + 2; i++) {
                        if(index != 2 && index != 6) {
                            valoresRed[index] = img.nivelRed(i, j);
                            valoresGreen[index] = img.nivelGreen(i, j);
                            valoresBlue[index] = img.nivelBlue(i, j);
                        }
                        else {
                            valoresRed[index] = -1;
                            valoresGreen[index] = -1;
                            valoresBlue[index] = -1;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[16] = media(valoresRed);
                mediaVarianciasRed[17] = variancia(valoresRed, mediaVarianciasRed[16]);
                
                mediaVarianciasGreen[16] = media(valoresGreen);
                mediaVarianciasGreen[17] = variancia(valoresGreen, mediaVarianciasGreen[16]);
                
                mediaVarianciasBlue[16] = media(valoresBlue);
                mediaVarianciasBlue[17] = variancia(valoresBlue, mediaVarianciasBlue[16]);
                
                r =  menorVariancia(mediaVarianciasRed);
                g =  menorVariancia(mediaVarianciasGreen);
                b =  menorVariancia(mediaVarianciasBlue);
                
                tmp.setRGB(x, y, r, g, b);
            }
        
        return tmp.getImg();
    }
    
    /**
     * Filtro Somboonkaew cria doze máscaras em 
     * relação ao pixel e seleciona a intensidade 
     * média da máscara com menor variância
     */
    
    public static BufferedImage somboonkaew(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        
        float mediaVarianciasRed[];
        float mediaVarianciasGreen[];
        float mediaVarianciasBlue[];
        
        float valoresRed[];
        float valoresGreen[];
        float valoresBlue[];
        
        int index;
        
        float r = 0, g = 0, b = 0;
        
        for(int y = 2; y < (img.getHeight() - 2); y++) 
            for(int x = 2; x < (img.getWidth() - 2); x++) {
                
                mediaVarianciasRed = new float[24];
                mediaVarianciasGreen = new float[24];
                mediaVarianciasBlue = new float[24];
                
                valoresRed = new float[25];
                valoresGreen = new float[25];
                valoresBlue = new float[25];
                
                //mascara 1
                index = 0;
                for(int j = y - 2; j <= y + 2; j++) {
                    for(int i = x - 2; i <= x + 2; i++) {
                     
                        switch(index) {
                            case 0:
                            case 6:
                            case 8:
                            case 12:
                            case 16:
                            case 18:
                            case 24:
                                valoresRed[index] = img.nivelRed(i, j);
                                valoresGreen[index] = img.nivelGreen(i, j);
                                valoresBlue[index] = img.nivelBlue(i, j);
                                break;
                            default:
                                valoresRed[index] = -1;
                                valoresGreen[index] = -1;
                                valoresBlue[index] = -1;
                                break;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[0] = media(valoresRed);
                mediaVarianciasRed[1] = variancia(valoresRed, mediaVarianciasRed[0]);
                
                mediaVarianciasGreen[0] = media(valoresGreen);
                mediaVarianciasGreen[1] = variancia(valoresGreen, mediaVarianciasGreen[0]);
                
                mediaVarianciasBlue[0] = media(valoresBlue);
                mediaVarianciasBlue[1] = variancia(valoresBlue, mediaVarianciasBlue[0]);
                
                //mascara 2
                index = 0;
                for(int j = y - 2; j <= y + 2; j++) {
                    for(int i = x - 2; i <= x + 2; i++) {
                     
                        switch(index) {
                            case 4:
                            case 6:
                            case 8:
                            case 12:
                            case 16:
                            case 18:
                            case 20:
                                valoresRed[index] = img.nivelRed(i, j);
                                valoresGreen[index] = img.nivelGreen(i, j);
                                valoresBlue[index] = img.nivelBlue(i, j);
                                break;
                            default:
                                valoresRed[index] = -1;
                                valoresGreen[index] = -1;
                                valoresBlue[index] = -1;
                                break;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[2] = media(valoresRed);
                mediaVarianciasRed[3] = variancia(valoresRed, mediaVarianciasRed[2]);
                
                mediaVarianciasGreen[2] = media(valoresGreen);
                mediaVarianciasGreen[3] = variancia(valoresGreen, mediaVarianciasGreen[2]);
                
                mediaVarianciasBlue[2] = media(valoresBlue);
                mediaVarianciasBlue[3] = variancia(valoresBlue, mediaVarianciasBlue[2]);
                
                //mascara 3
                index = 0;
                for(int j = y - 2; j <= y + 2; j++) {
                    for(int i = x - 2; i <= x + 2; i++) {
                     
                        switch(index) {
                            case 7:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 17:
                                valoresRed[index] = img.nivelRed(i, j);
                                valoresGreen[index] = img.nivelGreen(i, j);
                                valoresBlue[index] = img.nivelBlue(i, j);
                                break;
                            default:
                                valoresRed[index] = -1;
                                valoresGreen[index] = -1;
                                valoresBlue[index] = -1;
                                break;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[4] = media(valoresRed);
                mediaVarianciasRed[5] = variancia(valoresRed, mediaVarianciasRed[4]);
                
                mediaVarianciasGreen[4] = media(valoresGreen);
                mediaVarianciasGreen[5] = variancia(valoresGreen, mediaVarianciasGreen[4]);
                
                mediaVarianciasBlue[4] = media(valoresBlue);
                mediaVarianciasBlue[5] = variancia(valoresBlue, mediaVarianciasBlue[4]);
                
                //mascara 4
                index = 0;
                for(int j = y - 2; j <= y + 2; j++) {
                    for(int i = x - 2; i <= x + 2; i++) {
                     
                        switch(index) {
                            case 2:
                            case 7:
                            case 11:
                            case 12:
                            case 13:
                            case 17:
                            case 22:
                                valoresRed[index] = img.nivelRed(i, j);
                                valoresGreen[index] = img.nivelGreen(i, j);
                                valoresBlue[index] = img.nivelBlue(i, j);
                                break;
                            default:
                                valoresRed[index] = -1;
                                valoresGreen[index] = -1;
                                valoresBlue[index] = -1;
                                break;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[6] = media(valoresRed);
                mediaVarianciasRed[7] = variancia(valoresRed, mediaVarianciasRed[6]);
                
                mediaVarianciasGreen[6] = media(valoresGreen);
                mediaVarianciasGreen[7] = variancia(valoresGreen, mediaVarianciasGreen[6]);
                
                mediaVarianciasBlue[6] = media(valoresBlue);
                mediaVarianciasBlue[7] = variancia(valoresBlue, mediaVarianciasBlue[6]);
                
                valoresRed = new float[9];
                valoresGreen = new float[9];
                valoresBlue = new float[9];
                
                //mascara 5
                index = 0;
                for(int j = y - 1; j <= y + 1; j++) {
                    for(int i = x - 1; i <= x + 1; i++) {
                     
                        switch(index) {
                            case 3:
                            case 5:
                                valoresRed[index] = -1;
                                valoresGreen[index] = -1;
                                valoresBlue[index] = -1;
                                break;
                            default:
                                valoresRed[index] = img.nivelRed(i, j);
                                valoresGreen[index] = img.nivelGreen(i, j);
                                valoresBlue[index] = img.nivelBlue(i, j);
                                break;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[8] = media(valoresRed);
                mediaVarianciasRed[9] = variancia(valoresRed, mediaVarianciasRed[8]);
                
                mediaVarianciasGreen[8] = media(valoresGreen);
                mediaVarianciasGreen[9] = variancia(valoresGreen, mediaVarianciasGreen[8]);
                
                mediaVarianciasBlue[8] = media(valoresBlue);
                mediaVarianciasBlue[9] = variancia(valoresBlue, mediaVarianciasBlue[8]);
                
                //mascara 6
                index = 0;
                for(int j = y - 1; j <= y + 1; j++) {
                    for(int i = x - 1; i <= x + 1; i++) {
                     
                        switch(index) {
                            case 1:
                            case 7:
                                valoresRed[index] = -1;
                                valoresGreen[index] = -1;
                                valoresBlue[index] = -1;
                                break;
                            default:
                                valoresRed[index] = img.nivelRed(i, j);
                                valoresGreen[index] = img.nivelGreen(i, j);
                                valoresBlue[index] = img.nivelBlue(i, j);
                                break;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[10] = media(valoresRed);
                mediaVarianciasRed[11] = variancia(valoresRed, mediaVarianciasRed[10]);
                
                mediaVarianciasGreen[10] = media(valoresGreen);
                mediaVarianciasGreen[11] = variancia(valoresGreen, mediaVarianciasGreen[10]);
                
                mediaVarianciasBlue[10] = media(valoresBlue);
                mediaVarianciasBlue[11] = variancia(valoresBlue, mediaVarianciasBlue[10]);
                
                //mascara 7
                index = 0;
                for(int j = y - 1; j <= y + 1; j++) {
                    for(int i = x - 1; i <= x + 1; i++) {
                     
                        switch(index) {
                            case 2:
                            case 6:
                                valoresRed[index] = -1;
                                valoresGreen[index] = -1;
                                valoresBlue[index] = -1;
                                break;
                            default:
                                valoresRed[index] = img.nivelRed(i, j);
                                valoresGreen[index] = img.nivelGreen(i, j);
                                valoresBlue[index] = img.nivelBlue(i, j);
                                break;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[12] = media(valoresRed);
                mediaVarianciasRed[13] = variancia(valoresRed, mediaVarianciasRed[12]);
                
                mediaVarianciasGreen[12] = media(valoresGreen);
                mediaVarianciasGreen[13] = variancia(valoresGreen, mediaVarianciasGreen[12]);
                
                mediaVarianciasBlue[12] = media(valoresBlue);
                mediaVarianciasBlue[13] = variancia(valoresBlue, mediaVarianciasBlue[12]);
                
                //mascara 8
                index = 0;
                for(int j = y - 1; j <= y + 1; j++) {
                    for(int i = x - 1; i <= x + 1; i++) {
                     
                        switch(index) {
                            case 0:
                            case 8:
                                valoresRed[index] = -1;
                                valoresGreen[index] = -1;
                                valoresBlue[index] = -1;
                                break;
                            default:
                                valoresRed[index] = img.nivelRed(i, j);
                                valoresGreen[index] = img.nivelGreen(i, j);
                                valoresBlue[index] = img.nivelBlue(i, j);
                                break;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[14] = media(valoresRed);
                mediaVarianciasRed[15] = variancia(valoresRed, mediaVarianciasRed[14]);
                
                mediaVarianciasGreen[14] = media(valoresGreen);
                mediaVarianciasGreen[15] = variancia(valoresGreen, mediaVarianciasGreen[14]);
                
                mediaVarianciasBlue[14] = media(valoresBlue);
                mediaVarianciasBlue[15] = variancia(valoresBlue, mediaVarianciasBlue[14]);
                
                //mascara 9
                index = 0;
                for(int j = y - 1; j <= y + 1; j++) {
                    for(int i = x - 1; i <= x + 1; i++) {
                     
                        switch(index) {
                            case 0:
                            case 2:
                                valoresRed[index] = -1;
                                valoresGreen[index] = -1;
                                valoresBlue[index] = -1;
                                break;
                            default:
                                valoresRed[index] = img.nivelRed(i, j);
                                valoresGreen[index] = img.nivelGreen(i, j);
                                valoresBlue[index] = img.nivelBlue(i, j);
                                break;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[16] = media(valoresRed);
                mediaVarianciasRed[17] = variancia(valoresRed, mediaVarianciasRed[16]);
                
                mediaVarianciasGreen[16] = media(valoresGreen);
                mediaVarianciasGreen[17] = variancia(valoresGreen, mediaVarianciasGreen[16]);
                
                mediaVarianciasBlue[16] = media(valoresBlue);
                mediaVarianciasBlue[17] = variancia(valoresBlue, mediaVarianciasBlue[16]);
                
              //mascara 10
                index = 0;
                for(int j = y - 1; j <= y + 1; j++) {
                    for(int i = x - 1; i <= x + 1; i++) {
                     
                        switch(index) {
                            case 2:
                            case 8:
                                valoresRed[index] = -1;
                                valoresGreen[index] = -1;
                                valoresBlue[index] = -1;
                                break;
                            default:
                                valoresRed[index] = img.nivelRed(i, j);
                                valoresGreen[index] = img.nivelGreen(i, j);
                                valoresBlue[index] = img.nivelBlue(i, j);
                                break;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[18] = media(valoresRed);
                mediaVarianciasRed[19] = variancia(valoresRed, mediaVarianciasRed[18]);
                
                mediaVarianciasGreen[18] = media(valoresGreen);
                mediaVarianciasGreen[19] = variancia(valoresGreen, mediaVarianciasGreen[18]);
                
                mediaVarianciasBlue[18] = media(valoresBlue);
                mediaVarianciasBlue[19] = variancia(valoresBlue, mediaVarianciasBlue[18]);
                
              //mascara 11
                index = 0;
                for(int j = y - 1; j <= y + 1; j++) {
                    for(int i = x - 1; i <= x + 1; i++) {
                     
                        switch(index) {
                            case 6:
                            case 8:
                                valoresRed[index] = -1;
                                valoresGreen[index] = -1;
                                valoresBlue[index] = -1;
                                break;
                            default:
                                valoresRed[index] = img.nivelRed(i, j);
                                valoresGreen[index] = img.nivelGreen(i, j);
                                valoresBlue[index] = img.nivelBlue(i, j);
                                break;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[20] = media(valoresRed);
                mediaVarianciasRed[21] = variancia(valoresRed, mediaVarianciasRed[20]);
                
                mediaVarianciasGreen[20] = media(valoresGreen);
                mediaVarianciasGreen[21] = variancia(valoresGreen, mediaVarianciasGreen[20]);
                
                mediaVarianciasBlue[20] = media(valoresBlue);
                mediaVarianciasBlue[21] = variancia(valoresBlue, mediaVarianciasBlue[20]);
                
              //mascara 12
                index = 0;
                for(int j = y - 1; j <= y + 1; j++) {
                    for(int i = x - 1; i <= x + 1; i++) {
                     
                        switch(index) {
                            case 0:
                            case 6:
                                valoresRed[index] = -1;
                                valoresGreen[index] = -1;
                                valoresBlue[index] = -1;
                                break;
                            default:
                                valoresRed[index] = img.nivelRed(i, j);
                                valoresGreen[index] = img.nivelGreen(i, j);
                                valoresBlue[index] = img.nivelBlue(i, j);
                                break;
                        }
                        
                        index++;
                    }
                }
                mediaVarianciasRed[22] = media(valoresRed);
                mediaVarianciasRed[23] = variancia(valoresRed, mediaVarianciasRed[22]);
                
                mediaVarianciasGreen[22] = media(valoresGreen);
                mediaVarianciasGreen[23] = variancia(valoresGreen, mediaVarianciasGreen[22]);
                
                mediaVarianciasBlue[22] = media(valoresBlue);
                mediaVarianciasBlue[23] = variancia(valoresBlue, mediaVarianciasBlue[22]);
                
                r =  menorVariancia(mediaVarianciasRed);
                g =  menorVariancia(mediaVarianciasGreen);
                b =  menorVariancia(mediaVarianciasBlue);
                
                tmp.setRGB(x, y, r, g, b);
            }
        
        return tmp.getImg();
    }
    
    //FILTROS PASSA-ALTA
    
    public static BufferedImage h1(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int valR[], valG[], valB[];
        int r, g, b;
        int index; 
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                valR = new int[9];
                valG = new int[9];
                valB = new int[9];
                
                index = 0;
                for(int j = y - 1; j <= y + 1; j++)
                    for(int i = x - 1; i <= x + 1; i++) {
                        switch(index) {
                            case 4:
                                valR[index] = 4 * img.nivelRed(i, j);
                                valG[index] = 4 * img.nivelGreen(i, j);
                                valB[index] = 4 * img.nivelBlue(i, j);
                                break;
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                                valR[index] = -1 * img.nivelRed(i, j);
                                valG[index] = -1 * img.nivelGreen(i, j);
                                valB[index] = -1 * img.nivelBlue(i, j);
                                break;
                            default:
                                valR[index] = 0;
                                valG[index] = 0;
                                valB[index] = 0;
                                break;
                                    
                        }
                        index++;
                    }
                
                r = somatorio(valR);
                g = somatorio(valG);
                b = somatorio(valB);
                
                tmp.setRGB(x, y, r, g, b);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage h2(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int valR[], valG[], valB[];
        int r, g, b;
        int index; 
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                valR = new int[9];
                valG = new int[9];
                valB = new int[9];
                
                index = 0;
                for(int j = y - 1; j <= y + 1; j++)
                    for(int i = x - 1; i <= x + 1; i++) {
                        switch(index) {
                            case 4:
                                valR[index] = 8 * img.nivelRed(i, j);
                                valG[index] = 8 * img.nivelGreen(i, j);
                                valB[index] = 8 * img.nivelBlue(i, j);
                                break;   
                            default:
                                valR[index] = -1 * img.nivelRed(i, j);
                                valG[index] = -1 * img.nivelGreen(i, j);
                                valB[index] = -1 * img.nivelBlue(i, j);
                                break;
                                    
                        }
                        index++;
                    }
                
                r = somatorio(valR);
                g = somatorio(valG);
                b = somatorio(valB);
                
                tmp.setRGB(x, y, r, g, b);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage m1(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int valR[], valG[], valB[];
        int r, g, b;
        int index; 
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                valR = new int[9];
                valG = new int[9];
                valB = new int[9];
                
                index = 0;
                for(int j = y - 1; j <= y + 1; j++)
                    for(int i = x - 1; i <= x + 1; i++) {
                        switch(index) {
                            case 4:
                                valR[index] = 9 * img.nivelRed(i, j);
                                valG[index] = 9 * img.nivelGreen(i, j);
                                valB[index] = 9 * img.nivelBlue(i, j);
                                break;   
                            default:
                                valR[index] = -1 * img.nivelRed(i, j);
                                valG[index] = -1 * img.nivelGreen(i, j);
                                valB[index] = -1 * img.nivelBlue(i, j);
                                break;
                                    
                        }
                        index++;
                    }
                
                r = somatorio(valR);
                g = somatorio(valG);
                b = somatorio(valB);
                
                tmp.setRGB(x, y, r, g, b);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage m2(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int valR[], valG[], valB[];
        int r, g, b;
        int index; 
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                valR = new int[9];
                valG = new int[9];
                valB = new int[9];
                
                index = 0;
                for(int j = y - 1; j <= y + 1; j++)
                    for(int i = x - 1; i <= x + 1; i++) {
                        switch(index) {
                            case 4:
                                valR[index] = 5 * img.nivelRed(i, j);
                                valG[index] = 5 * img.nivelGreen(i, j);
                                valB[index] = 5 * img.nivelBlue(i, j);
                                break;
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                                valR[index] = -2 * img.nivelRed(i, j);
                                valG[index] = -2 * img.nivelGreen(i, j);
                                valB[index] = -2 * img.nivelBlue(i, j);
                                break;
                            default:
                                valR[index] = img.nivelRed(i, j);
                                valG[index] = img.nivelGreen(i, j);
                                valB[index] = img.nivelBlue(i, j);
                                break;
                                    
                        }
                        index++;
                    }
                
                r = somatorio(valR);
                g = somatorio(valG);
                b = somatorio(valB);
                
                tmp.setRGB(x, y, r, g, b);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage m3(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int valR[], valG[], valB[];
        int r, g, b;
        int index; 
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                valR = new int[9];
                valG = new int[9];
                valB = new int[9];
                
                index = 0;
                for(int j = y - 1; j <= y + 1; j++)
                    for(int i = x - 1; i <= x + 1; i++) {
                        switch(index) {
                            case 4:
                                valR[index] = 5 * img.nivelRed(i, j);
                                valG[index] = 5 * img.nivelGreen(i, j);
                                valB[index] = 5 * img.nivelBlue(i, j);
                                break;
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                                valR[index] = -1 * img.nivelRed(i, j);
                                valG[index] = -1 * img.nivelGreen(i, j);
                                valB[index] = -1 * img.nivelBlue(i, j);
                                break;
                            default:
                                valR[index] = 0;
                                valG[index] = 0;
                                valB[index] = 0;
                                break;
                                    
                        }
                        index++;
                    }
                
                r = somatorio(valR);
                g = somatorio(valG);
                b = somatorio(valB);
                
                tmp.setRGB(x, y, r, g, b);
            }
        
        return tmp.getImg();
    }
    
    private static int somatorio(int[] valores) {
        
        int total = 0;
        
        for(int i = 0; i < valores.length; i++) {
            total += valores[i];
        }
        
        if(total > 255)
            total = 255;
        else if(total < 0)
            total = 0;
        
        return total;
    }
    
    public static BufferedImage highBoost(Processador img, float a) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        float valR[], valG[], valB[];
        float r, g, b;
        int index; 
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                valR = new float[9];
                valG = new float[9];
                valB = new float[9];
                
                index = 0;
                for(int j = y - 1; j <= y + 1; j++)
                    for(int i = x - 1; i <= x + 1; i++) {
                        
                        switch(index) {
                            case 4:
                                valR[index] = (9 * a - 1) * img.nivelRed(i, j);
                                valG[index] = (9 * a - 1) * img.nivelGreen(i, j);
                                valB[index] = (9 * a - 1) * img.nivelBlue(i, j);
                                break;   
                            default:
                                valR[index] = -1 * img.nivelRed(i, j);
                                valG[index] = -1 * img.nivelGreen(i, j);
                                valB[index] = -1 * img.nivelBlue(i, j);
                                break;
                                    
                        }
                        index++;
                    }
                
                r = somatorio(valR);
                g = somatorio(valG);
                b = somatorio(valB);
                
                tmp.setRGB(x, y, r, g, b);
            }
        
        return tmp.getImg();
    }
    
    private static float somatorio(float[] valores) {
        
        float total = 0;
        
        for(int i = 0; i < valores.length; i++) {
            total += (valores[i] / 255.0f);
        }
        
        if(total > 1)
            total = 1;
        else if(total < 0)
            total = 0;
        
        return total;
    }
    
    //MEIO TOM (HALFTONING)
    
    public static BufferedImage ht2x2(Processador img) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        float valR[], valG[], valB[];
        float rMedio, gMedio, bMedio;
        float matrizR[], matrizG[], matrizB[];
        int index;
        
        for(int y = 0; y < (img.getHeight() - 1); y += 2) 
            for(int x = 0; x < (img.getWidth() - 1); x += 2) {
                
                valR = new float[4];
                valG = new float[4];
                valB = new float[4];
                
                matrizR = new float[4];
                matrizG = new float[4];
                matrizB = new float[4];
                
                index = 0;
                
                for(int j = y; j <= y + 1; j++)
                    for(int i = x; i <= x + 1; i++) {
                        valR[index] = img.nivelRed(x, y);
                        valG[index] = img.nivelGreen(x, y);
                        valB[index] = img.nivelBlue(x, y);
                        index++;
                    }
                
                rMedio = media(valR);
                gMedio = media(valG);
                bMedio = media(valB);
                
              //red
                if(rMedio <= 51){
                    matrizR[0] = 0;
                    matrizR[1] = 0;
                    matrizR[2] = 0;
                    matrizR[3] = 0;
                }
                else if(rMedio <= 102){
                    matrizR[0] = 0;
                    matrizR[1] = 0;
                    matrizR[2] = 1;
                    matrizR[3] = 0;
                }
                else if(rMedio <= 153){
                    matrizR[0] = 0;
                    matrizR[1] = 1;
                    matrizR[2] = 1;
                    matrizR[3] = 0;
                }
                else if(rMedio <= 204){
                    matrizR[0] = 0;
                    matrizR[1] = 1;
                    matrizR[2] = 1;
                    matrizR[3] = 1;
                }
                else if(rMedio <= 255){
                    matrizR[0] = 1;
                    matrizR[1] = 1;
                    matrizR[2] = 1;
                    matrizR[3] = 1;
                }
                
                //green
                if(gMedio <= 51){
                    matrizG[0] = 0;
                    matrizG[1] = 0;
                    matrizG[2] = 0;
                    matrizG[3] = 0;
                }
                else if(gMedio <= 102){
                    matrizG[0] = 0;
                    matrizG[1] = 0;
                    matrizG[2] = 1;
                    matrizG[3] = 0;
                }
                else if(gMedio <= 153){
                    matrizG[0] = 0;
                    matrizG[1] = 1;
                    matrizG[2] = 1;
                    matrizG[3] = 0;
                }
                else if(gMedio <= 204){
                    matrizG[0] = 0;
                    matrizG[1] = 1;
                    matrizG[2] = 1;
                    matrizG[3] = 1;
                }
                else if(gMedio <= 255){
                    matrizG[0] = 1;
                    matrizG[1] = 1;
                    matrizG[2] = 1;
                    matrizG[3] = 1;
                }
                
                //blue
                if(bMedio <= 51){
                    matrizB[0] = 0;
                    matrizB[1] = 0;
                    matrizB[2] = 0;
                    matrizB[3] = 0;
                }
                else if(bMedio <= 102){
                    matrizB[0] = 0;
                    matrizB[1] = 0;
                    matrizB[2] = 1;
                    matrizB[3] = 0;
                }
                else if(bMedio <= 153){
                    matrizB[0] = 0;
                    matrizB[1] = 1;
                    matrizB[2] = 1;
                    matrizB[3] = 0;
                }
                else if(bMedio <= 204){
                    matrizB[0] = 0;
                    matrizB[1] = 1;
                    matrizB[2] = 1;
                    matrizB[3] = 1;
                }
                else if(bMedio <= 255){
                    matrizB[0] = 1;
                    matrizB[1] = 1;
                    matrizB[2] = 1;
                    matrizB[3] = 1;
                }
                
                tmp.setRGB(x, y,         matrizR[0], matrizG[0], matrizB[0]);
                tmp.setRGB(x + 1, y,     matrizR[1], matrizG[1], matrizB[1]);
                tmp.setRGB(x, y + 1,     matrizR[2], matrizG[2], matrizB[2]);
                tmp.setRGB(x + 1, y + 1, matrizR[3], matrizG[3], matrizB[3]);
                
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage ht3x2(Processador img) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        float valR[], valG[], valB[];
        float rMedio, gMedio, bMedio;
        float matrizR[], matrizG[], matrizB[];
        int index;
        
        for(int y = 0; y < (img.getHeight() - 1); y += 2) 
            for(int x = 0; x < (img.getWidth() - 2); x += 3) {
                
                valR = new float[6];
                valG = new float[6];
                valB = new float[6];
                
                matrizR = new float[6];
                matrizG = new float[6];
                matrizB = new float[6];
                
                index = 0;
                
                for(int j = y; j <= y + 1; j++)
                    for(int i = x; i <= x + 2; i++) {
                        valR[index] = img.nivelRed(x, y);
                        valG[index] = img.nivelGreen(x, y);
                        valB[index] = img.nivelBlue(x, y);
                        index++;
                    }
                
                rMedio = media(valR);
                gMedio = media(valG);
                bMedio = media(valB);
                
              //red
                if(rMedio <= 36.43){
                    matrizR[0] = 0;
                    matrizR[1] = 0;
                    matrizR[2] = 0;
                    matrizR[3] = 0;
                    matrizR[4] = 0;
                    matrizR[5] = 0;
                }
                else if(rMedio <= 72.86){
                    matrizR[0] = 0;
                    matrizR[1] = 0;
                    matrizR[2] = 0;
                    matrizR[3] = 1;
                    matrizR[4] = 0;
                    matrizR[5] = 0;
                }
                else if(rMedio <= 109.29){
                    matrizR[0] = 0;
                    matrizR[1] = 0;
                    matrizR[2] = 1;
                    matrizR[3] = 1;
                    matrizR[4] = 0;
                    matrizR[5] = 0;
                }
                else if(rMedio <= 145.72){
                    matrizR[0] = 1;
                    matrizR[1] = 0;
                    matrizR[2] = 1;
                    matrizR[3] = 1;
                    matrizR[4] = 0;
                    matrizR[5] = 0;
                }
                else if(rMedio <= 182.15){
                    matrizR[0] = 1;
                    matrizR[1] = 0;
                    matrizR[2] = 1;
                    matrizR[3] = 1;
                    matrizR[4] = 1;
                    matrizR[5] = 0;
                }
                else if(rMedio <= 218.58){
                    matrizR[0] = 1;
                    matrizR[1] = 0;
                    matrizR[2] = 1;
                    matrizR[3] = 1;
                    matrizR[4] = 1;
                    matrizR[5] = 1;
                }
                else if(rMedio <= 255){
                    matrizR[0] = 1;
                    matrizR[1] = 1;
                    matrizR[2] = 1;
                    matrizR[3] = 1;
                    matrizR[4] = 1;
                    matrizR[5] = 1;
                }
                
                //green
                if(gMedio <= 36.43){
                    matrizG[0] = 0;
                    matrizG[1] = 0;
                    matrizG[2] = 0;
                    matrizG[3] = 0;
                    matrizG[4] = 0;
                    matrizG[5] = 0;
                }
                else if(gMedio <= 72.86){
                    matrizG[0] = 0;
                    matrizG[1] = 0;
                    matrizG[2] = 0;
                    matrizG[3] = 1;
                    matrizG[4] = 0;
                    matrizG[5] = 0;
                }
                else if(gMedio <= 109.29){
                    matrizG[0] = 0;
                    matrizG[1] = 0;
                    matrizG[2] = 1;
                    matrizG[3] = 1;
                    matrizG[4] = 0;
                    matrizG[5] = 0;
                }
                else if(gMedio <= 145.72){
                    matrizG[0] = 1;
                    matrizG[1] = 0;
                    matrizG[2] = 1;
                    matrizG[3] = 1;
                    matrizG[4] = 0;
                    matrizG[5] = 0;
                }
                else if(gMedio <= 182.15){
                    matrizG[0] = 1;
                    matrizG[1] = 0;
                    matrizG[2] = 1;
                    matrizG[3] = 1;
                    matrizG[4] = 1;
                    matrizG[5] = 0;
                }
                else if(gMedio <= 218.58){
                    matrizG[0] = 1;
                    matrizG[1] = 0;
                    matrizG[2] = 1;
                    matrizG[3] = 1;
                    matrizG[4] = 1;
                    matrizG[5] = 1;
                }
                else if(gMedio <= 255){
                    matrizG[0] = 1;
                    matrizG[1] = 1;
                    matrizG[2] = 1;
                    matrizG[3] = 1;
                    matrizG[4] = 1;
                    matrizG[5] = 1;
                }
                
                //blue
                if(bMedio <= 36.43){
                    matrizB[0] = 0;
                    matrizB[1] = 0;
                    matrizB[2] = 0;
                    matrizB[3] = 0;
                    matrizB[4] = 0;
                    matrizB[5] = 0;
                }
                else if(bMedio <= 72.86){
                    matrizB[0] = 0;
                    matrizB[1] = 0;
                    matrizB[2] = 0;
                    matrizB[3] = 1;
                    matrizB[4] = 0;
                    matrizB[5] = 0;
                }
                else if(bMedio <= 109.29){
                    matrizB[0] = 0;
                    matrizB[1] = 0;
                    matrizB[2] = 1;
                    matrizB[3] = 1;
                    matrizB[4] = 0;
                    matrizB[5] = 0;
                }
                else if(bMedio <= 145.72){
                    matrizB[0] = 1;
                    matrizB[1] = 0;
                    matrizB[2] = 1;
                    matrizB[3] = 1;
                    matrizB[4] = 0;
                    matrizB[5] = 0;
                }
                else if(bMedio <= 182.15){
                    matrizB[0] = 1;
                    matrizB[1] = 0;
                    matrizB[2] = 1;
                    matrizB[3] = 1;
                    matrizB[4] = 1;
                    matrizB[5] = 0;
                }
                else if(bMedio <= 218.58){
                    matrizB[0] = 1;
                    matrizB[1] = 0;
                    matrizB[2] = 1;
                    matrizB[3] = 1;
                    matrizB[4] = 1;
                    matrizB[5] = 1;
                }
                else if(bMedio <= 255){
                    matrizB[0] = 1;
                    matrizB[1] = 1;
                    matrizB[2] = 1;
                    matrizB[3] = 1;
                    matrizB[4] = 1;
                    matrizB[5] = 1;
                }
                
                tmp.setRGB(x, y,         matrizR[0], matrizG[0], matrizB[0]);
                tmp.setRGB(x + 1, y,     matrizR[1], matrizG[1], matrizB[1]);
                tmp.setRGB(x + 2, y,     matrizR[2], matrizG[2], matrizB[2]);
                tmp.setRGB(x, y + 1,     matrizR[3], matrizG[3], matrizB[3]);
                tmp.setRGB(x + 1, y + 1, matrizR[4], matrizG[4], matrizB[4]);
                tmp.setRGB(x + 2, y + 1, matrizR[5], matrizG[5], matrizB[5]);
                
            }
        
        return tmp.getImg();
    }

    public static BufferedImage ht3x3(Processador img) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        float valR[], valG[], valB[];
        float rMedio, gMedio, bMedio;
        float matrizR[], matrizG[], matrizB[];
        int index;
        
        for(int y = 0; y < (img.getHeight() - 2); y += 3) 
            for(int x = 0; x < (img.getWidth() - 2); x += 3) {
                
                valR = new float[9];
                valG = new float[9];
                valB = new float[9];
                
                matrizR = new float[9];
                matrizG = new float[9];
                matrizB = new float[9];
                
                index = 0;
                
                for(int j = y; j <= y + 2; j++)
                    for(int i = x; i <= x + 2; i++) {
                        valR[index] = img.nivelRed(x, y);
                        valG[index] = img.nivelGreen(x, y);
                        valB[index] = img.nivelBlue(x, y);
                        index++;
                    }
                
                rMedio = media(valR);
                gMedio = media(valG);
                bMedio = media(valB);
                
                //red
                if(rMedio <= 25.5){
                    matrizR[0] = 0;
                    matrizR[1] = 0;
                    matrizR[2] = 0;
                    matrizR[3] = 0;
                    matrizR[4] = 0;
                    matrizR[5] = 0;
                    matrizR[6] = 0;
                    matrizR[7] = 0;
                    matrizR[8] = 0;
                }
                else if(rMedio <= 51){
                    matrizR[0] = 0;
                    matrizR[1] = 0;
                    matrizR[2] = 0;
                    matrizR[3] = 0;
                    matrizR[4] = 1;
                    matrizR[5] = 0;
                    matrizR[6] = 0;
                    matrizR[7] = 0;
                    matrizR[8] = 0;
                }
                else if(rMedio <= 76.5){
                    matrizR[0] = 0;
                    matrizR[1] = 0;
                    matrizR[2] = 0;
                    matrizR[3] = 1;
                    matrizR[4] = 1;
                    matrizR[5] = 0;
                    matrizR[6] = 0;
                    matrizR[7] = 0;
                    matrizR[8] = 0;
                }
                else if(rMedio <= 102){
                    matrizR[0] = 0;
                    matrizR[1] = 0;
                    matrizR[2] = 0;
                    matrizR[3] = 1;
                    matrizR[4] = 1;
                    matrizR[5] = 0;
                    matrizR[6] = 0;
                    matrizR[7] = 1;
                    matrizR[8] = 0;
                }
                else if(rMedio <= 127.5){
                    matrizR[0] = 0;
                    matrizR[1] = 0;
                    matrizR[2] = 0;
                    matrizR[3] = 1;
                    matrizR[4] = 1;
                    matrizR[5] = 1;
                    matrizR[6] = 0;
                    matrizR[7] = 1;
                    matrizR[8] = 0;
                }
                else if(rMedio <= 153){
                    matrizR[0] = 0;
                    matrizR[1] = 0;
                    matrizR[2] = 1;
                    matrizR[3] = 1;
                    matrizR[4] = 1;
                    matrizR[5] = 1;
                    matrizR[6] = 0;
                    matrizR[7] = 1;
                    matrizR[8] = 0;
                }
                else if(rMedio <= 178.5){
                    matrizR[0] = 0;
                    matrizR[1] = 0;
                    matrizR[2] = 1;
                    matrizR[3] = 1;
                    matrizR[4] = 1;
                    matrizR[5] = 1;
                    matrizR[6] = 1;
                    matrizR[7] = 1;
                    matrizR[8] = 0;
                }
                else if(rMedio <= 204){
                    matrizR[0] = 1;
                    matrizR[1] = 0;
                    matrizR[2] = 1;
                    matrizR[3] = 1;
                    matrizR[4] = 1;
                    matrizR[5] = 1;
                    matrizR[6] = 1;
                    matrizR[7] = 1;
                    matrizR[8] = 0;
                }
                else if(rMedio <= 229.5){
                    matrizR[0] = 1;
                    matrizR[1] = 0;
                    matrizR[2] = 1;
                    matrizR[3] = 1;
                    matrizR[4] = 1;
                    matrizR[5] = 1;
                    matrizR[6] = 1;
                    matrizR[7] = 1;
                    matrizR[8] = 1;
                }
                else if(rMedio <= 255){
                    matrizR[0] = 1;
                    matrizR[1] = 1;
                    matrizR[2] = 1;
                    matrizR[3] = 1;
                    matrizR[4] = 1;
                    matrizR[5] = 1;
                    matrizR[6] = 1;
                    matrizR[7] = 1;
                    matrizR[8] = 1;
                }
                
                //green
                if(gMedio <= 25.5){
                    matrizG[0] = 0;
                    matrizG[1] = 0;
                    matrizG[2] = 0;
                    matrizG[3] = 0;
                    matrizG[4] = 0;
                    matrizG[5] = 0;
                    matrizG[6] = 0;
                    matrizG[7] = 0;
                    matrizG[8] = 0;
                }
                else if(gMedio <= 51){
                    matrizG[0] = 0;
                    matrizG[1] = 0;
                    matrizG[2] = 0;
                    matrizG[3] = 0;
                    matrizG[4] = 1;
                    matrizG[5] = 0;
                    matrizG[6] = 0;
                    matrizG[7] = 0;
                    matrizG[8] = 0;
                }
                else if(gMedio <= 76.5){
                    matrizG[0] = 0;
                    matrizG[1] = 0;
                    matrizG[2] = 0;
                    matrizG[3] = 1;
                    matrizG[4] = 1;
                    matrizG[5] = 0;
                    matrizG[6] = 0;
                    matrizG[7] = 0;
                    matrizG[8] = 0;
                }
                else if(gMedio <= 102){
                    matrizG[0] = 0;
                    matrizG[1] = 0;
                    matrizG[2] = 0;
                    matrizG[3] = 1;
                    matrizG[4] = 1;
                    matrizG[5] = 0;
                    matrizG[6] = 0;
                    matrizG[7] = 1;
                    matrizG[8] = 0;
                }
                else if(gMedio <= 127.5){
                    matrizG[0] = 0;
                    matrizG[1] = 0;
                    matrizG[2] = 0;
                    matrizG[3] = 1;
                    matrizG[4] = 1;
                    matrizG[5] = 1;
                    matrizG[6] = 0;
                    matrizG[7] = 1;
                    matrizG[8] = 0;
                }
                else if(gMedio <= 153){
                    matrizG[0] = 0;
                    matrizG[1] = 0;
                    matrizG[2] = 1;
                    matrizG[3] = 1;
                    matrizG[4] = 1;
                    matrizG[5] = 1;
                    matrizG[6] = 0;
                    matrizG[7] = 1;
                    matrizG[8] = 0;
                }
                else if(gMedio <= 178.5){
                    matrizG[0] = 0;
                    matrizG[1] = 0;
                    matrizG[2] = 1;
                    matrizG[3] = 1;
                    matrizG[4] = 1;
                    matrizG[5] = 1;
                    matrizG[6] = 1;
                    matrizG[7] = 1;
                    matrizG[8] = 0;
                }
                else if(gMedio <= 204){
                    matrizG[0] = 1;
                    matrizG[1] = 0;
                    matrizG[2] = 1;
                    matrizG[3] = 1;
                    matrizG[4] = 1;
                    matrizG[5] = 1;
                    matrizG[6] = 1;
                    matrizG[7] = 1;
                    matrizG[8] = 0;
                }
                else if(gMedio <= 229.5){
                    matrizG[0] = 1;
                    matrizG[1] = 0;
                    matrizG[2] = 1;
                    matrizG[3] = 1;
                    matrizG[4] = 1;
                    matrizG[5] = 1;
                    matrizG[6] = 1;
                    matrizG[7] = 1;
                    matrizG[8] = 1;
                }
                else if(gMedio <= 255){
                    matrizG[0] = 1;
                    matrizG[1] = 1;
                    matrizG[2] = 1;
                    matrizG[3] = 1;
                    matrizG[4] = 1;
                    matrizG[5] = 1;
                    matrizG[6] = 1;
                    matrizG[7] = 1;
                    matrizG[8] = 1;
                }
                
                //blue
                if(bMedio <= 25.5){
                    matrizB[0] = 0;
                    matrizB[1] = 0;
                    matrizB[2] = 0;
                    matrizB[3] = 0;
                    matrizB[4] = 0;
                    matrizB[5] = 0;
                    matrizB[6] = 0;
                    matrizB[7] = 0;
                    matrizB[8] = 0;
                }
                else if(bMedio <= 51){
                    matrizB[0] = 0;
                    matrizB[1] = 0;
                    matrizB[2] = 0;
                    matrizB[3] = 0;
                    matrizB[4] = 1;
                    matrizB[5] = 0;
                    matrizB[6] = 0;
                    matrizB[7] = 0;
                    matrizB[8] = 0;
                }
                else if(bMedio <= 76.5){
                    matrizB[0] = 0;
                    matrizB[1] = 0;
                    matrizB[2] = 0;
                    matrizB[3] = 1;
                    matrizB[4] = 1;
                    matrizB[5] = 0;
                    matrizB[6] = 0;
                    matrizB[7] = 0;
                    matrizB[8] = 0;
                }
                else if(bMedio <= 102){
                    matrizB[0] = 0;
                    matrizB[1] = 0;
                    matrizB[2] = 0;
                    matrizB[3] = 1;
                    matrizB[4] = 1;
                    matrizB[5] = 0;
                    matrizB[6] = 0;
                    matrizB[7] = 1;
                    matrizB[8] = 0;
                }
                else if(bMedio <= 127.5){
                    matrizB[0] = 0;
                    matrizB[1] = 0;
                    matrizB[2] = 0;
                    matrizB[3] = 1;
                    matrizB[4] = 1;
                    matrizB[5] = 1;
                    matrizB[6] = 0;
                    matrizB[7] = 1;
                    matrizB[8] = 0;
                }
                else if(bMedio <= 153){
                    matrizB[0] = 0;
                    matrizB[1] = 0;
                    matrizB[2] = 1;
                    matrizB[3] = 1;
                    matrizB[4] = 1;
                    matrizB[5] = 1;
                    matrizB[6] = 0;
                    matrizB[7] = 1;
                    matrizB[8] = 0;
                }
                else if(bMedio <= 178.5){
                    matrizB[0] = 0;
                    matrizB[1] = 0;
                    matrizB[2] = 1;
                    matrizB[3] = 1;
                    matrizB[4] = 1;
                    matrizB[5] = 1;
                    matrizB[6] = 1;
                    matrizB[7] = 1;
                    matrizB[8] = 0;
                }
                else if(bMedio <= 204){
                    matrizB[0] = 1;
                    matrizB[1] = 0;
                    matrizB[2] = 1;
                    matrizB[3] = 1;
                    matrizB[4] = 1;
                    matrizB[5] = 1;
                    matrizB[6] = 1;
                    matrizB[7] = 1;
                    matrizB[8] = 0;
                }
                else if(bMedio <= 229.5){
                    matrizB[0] = 1;
                    matrizB[1] = 0;
                    matrizB[2] = 1;
                    matrizB[3] = 1;
                    matrizB[4] = 1;
                    matrizB[5] = 1;
                    matrizB[6] = 1;
                    matrizB[7] = 1;
                    matrizB[8] = 1;
                }
                else if(bMedio <= 255){
                    matrizB[0] = 1;
                    matrizB[1] = 1;
                    matrizB[2] = 1;
                    matrizB[3] = 1;
                    matrizB[4] = 1;
                    matrizB[5] = 1;
                    matrizB[6] = 1;
                    matrizB[7] = 1;
                    matrizB[8] = 1;
                }
                
                tmp.setRGB(x, y,         matrizR[0], matrizG[0], matrizB[0]);
                tmp.setRGB(x + 1, y,     matrizR[1], matrizG[1], matrizB[1]);
                tmp.setRGB(x + 2, y,     matrizR[2], matrizG[2], matrizB[2]);
                
                tmp.setRGB(x, y + 1,     matrizR[3], matrizG[3], matrizB[3]);
                tmp.setRGB(x + 1, y + 1, matrizR[4], matrizG[4], matrizB[4]);
                tmp.setRGB(x + 2, y + 1, matrizR[5], matrizG[5], matrizB[5]);
                
                tmp.setRGB(x, y + 2,     matrizR[6], matrizG[6], matrizB[6]);
                tmp.setRGB(x + 1, y + 2, matrizR[7], matrizG[7], matrizB[7]);
                tmp.setRGB(x + 2, y + 2, matrizR[8], matrizG[8], matrizB[8]);
                
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage floydSteinberg(Processador img) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        float valR[], valG[], valB[];
        float erroR, erroG, erroB;
        
        for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
                
                valR = new float[5];
                valG = new float[5];
                valB = new float[5];
                
                if(tmp.nivelRed(x, y) < 128)
                    valR[0] = 0;
                else
                    valR[0] = 255;
                
                if(tmp.nivelGreen(x, y) < 128)
                    valG[0] = 0;
                else
                    valG[0] = 255;
                
                if(tmp.nivelBlue(x, y) < 128)
                    valB[0] = 0;
                else
                    valB[0] = 255;
                
                erroR = (float)tmp.nivelRed(   x, y) - valR[0];
                erroG = (float)tmp.nivelGreen( x, y) - valG[0];
                erroB = (float)tmp.nivelBlue(  x, y) - valB[0];
                
                if(x < (img.getWidth() - 1) ) {
                    valR[1] = (float)tmp.nivelRed(   x + 1, y) + ((7.0f / 16.0f) * erroR);
                    valG[1] = (float)tmp.nivelGreen( x + 1, y) + ((7.0f / 16.0f) * erroG);
                    valB[1] = (float)tmp.nivelBlue(  x + 1, y) + ((7.0f / 16.0f) * erroB);
                }
                
                if(x >= 1) {
                    valR[2] = (float)tmp.nivelRed(   x - 1, y + 1) + ((3.0f / 16.0f) * erroR);
                    valG[2] = (float)tmp.nivelGreen( x - 1, y + 1) + ((3.0f / 16.0f) * erroG);
                    valB[2] = (float)tmp.nivelBlue(  x - 1, y + 1) + ((3.0f / 16.0f) * erroB);
                }
                
                if(y < (img.getHeight() -1) ) {
                    valR[3] = (float)tmp.nivelRed(   x, y + 1) + ((5.0f / 16.0f) * erroR);
                    valG[3] = (float)tmp.nivelGreen( x, y + 1) + ((5.0f / 16.0f) * erroG);
                    valB[3] = (float)tmp.nivelBlue(  x, y + 1) + ((5.0f / 16.0f) * erroB);
                }
                
                if(x < (img.getWidth() - 1) && y < (img.getHeight() - 1)) {
                    valR[4] = (float)tmp.nivelRed(   x + 1, y + 1) + ((erroR / 16.0f));
                    valG[4] = (float)tmp.nivelGreen( x + 1, y + 1) + ((erroG / 16.0f));
                    valB[4] = (float)tmp.nivelBlue(  x + 1, y + 1) + ((erroB / 16.0f));
                }
                
                for(int i = 0; i < valR.length; i++) {
                    
                    if( valR[i] > 255 )
                        valR[i] = 255;
                    else if( valR[i] < 0 )
                        valR[i] = 0;
                    
                    if( valG[i] > 255 )
                        valG[i] = 255;
                    else if( valG[i] < 0 )
                        valG[i] = 0;
                    
                    if( valB[i] > 255 )
                        valB[i] = 255;
                    else if( valB[i] < 0 )
                        valB[i] = 0;
                    
                    valR[i] /= 255;
                    valG[i] /= 255;
                    valB[i] /= 255;
                }
                
                tmp.setRGB(x,     y,     valR[0], valG[0], valB[0]);
                tmp.setRGB(x + 1, y,     valR[1], valG[1], valB[1]);

                if(x >= 1) 
                    tmp.setRGB(x - 1, y + 1, valR[2], valG[2], valB[2]);
                
                tmp.setRGB(x,     y + 1, valR[3], valG[3], valB[3]);
                tmp.setRGB(x + 1, y + 1, valR[4], valG[4], valB[4]);
                
            }
                
        return tmp.getImg();
    }
    
    public static BufferedImage rogers(Processador img) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        float valR[], valG[], valB[];
        float erroR, erroG, erroB;
        
        for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
                
                valR = new float[4];
                valG = new float[4];
                valB = new float[4];
                
                if(tmp.nivelRed(x, y) < 128)
                    valR[0] = 0;
                else
                    valR[0] = 255;
                
                if(tmp.nivelGreen(x, y) < 128)
                    valG[0] = 0;
                else
                    valG[0] = 255;
                
                if(tmp.nivelBlue(x, y) < 128)
                    valB[0] = 0;
                else
                    valB[0] = 255;
                
                erroR = (float)tmp.nivelRed(   x, y) - valR[0];
                erroG = (float)tmp.nivelGreen( x, y) - valG[0];
                erroB = (float)tmp.nivelBlue(  x, y) - valB[0];
                
                if(x < (img.getWidth() - 1) ) {
                    valR[1] = (float)tmp.nivelRed(   x + 1, y) + ((3.0f / 8.0f) * erroR);
                    valG[1] = (float)tmp.nivelGreen( x + 1, y) + ((3.0f / 8.0f) * erroG);
                    valB[1] = (float)tmp.nivelBlue(  x + 1, y) + ((3.0f / 8.0f) * erroB);
                }
                
                if(y < (img.getHeight() - 1) ) {
                    valR[2] = (float)tmp.nivelRed(   x, y + 1) + ((3.0f / 8.0f) * erroR);
                    valG[2] = (float)tmp.nivelGreen( x, y + 1) + ((3.0f / 8.0f) * erroG);
                    valB[2] = (float)tmp.nivelBlue(  x, y + 1) + ((3.0f / 8.0f) * erroB);
                    
                }
                
                if(x < (img.getWidth() - 1) && y < (img.getHeight() - 1)) {
                    valR[3] = (float)tmp.nivelRed(   x + 1, y + 1) + ((2.0f / 8.0f) * erroR);
                    valG[3] = (float)tmp.nivelGreen( x + 1, y + 1) + ((2.0f / 8.0f) * erroG);
                    valB[3] = (float)tmp.nivelBlue(  x + 1, y + 1) + ((2.0f / 8.0f) * erroB);
                    
                }
                
                for(int i = 0; i < valR.length; i++) {
                    
                    if( valR[i] > 255 )
                        valR[i] = 255;
                    else if( valR[i] < 0 )
                        valR[i] = 0;
                    
                    if( valG[i] > 255 )
                        valG[i] = 255;
                    else if( valG[i] < 0 )
                        valG[i] = 0;
                    
                    if( valB[i] > 255 )
                        valB[i] = 255;
                    else if( valB[i] < 0 )
                        valB[i] = 0;
                    
                    valR[i] /= 255;
                    valG[i] /= 255;
                    valB[i] /= 255;
                }
                
                tmp.setRGB(x,     y,     valR[0], valG[0], valB[0]);
                
                if(x < (img.getWidth() - 1) )
                    tmp.setRGB(x + 1, y,     valR[1], valG[1], valB[1]); 
                
                if(y < (img.getHeight() -1) )
                    tmp.setRGB(x,     y + 1, valR[2], valG[2], valB[2]);
                
                if(x < (img.getWidth() - 1) && y < (img.getHeight() - 1))    
                    tmp.setRGB(x + 1, y + 1, valR[3], valG[3], valB[3]);
                
            }
                
        return tmp.getImg();
    }
    
    public static BufferedImage jarvisJudiceNinke(Processador img) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        float valR[], valG[], valB[];
        float erroR, erroG, erroB;
        
        for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
                
                valR = new float[13];
                valG = new float[13];
                valB = new float[13];
                
                if(tmp.nivelRed(x, y) < 128)
                    valR[0] = 0;
                else
                    valR[0] = 255;
                
                if(tmp.nivelGreen(x, y) < 128)
                    valG[0] = 0;
                else
                    valG[0] = 255;
                
                if(tmp.nivelBlue(x, y) < 128)
                    valB[0] = 0;
                else
                    valB[0] = 255;
                
                erroR = (float)tmp.nivelRed(   x, y) - valR[0];
                erroG = (float)tmp.nivelGreen( x, y) - valG[0];
                erroB = (float)tmp.nivelBlue(  x, y) - valB[0];
                
                if(x < (img.getWidth() - 1) ) {
                    valR[1] = (float)tmp.nivelRed(   x + 1, y) + ((7.0f / 48.0f) * erroR);
                    valG[1] = (float)tmp.nivelGreen( x + 1, y) + ((7.0f / 48.0f) * erroG);
                    valB[1] = (float)tmp.nivelBlue(  x + 1, y) + ((7.0f / 48.0f) * erroB);
                }
                
                if(x < (img.getWidth() - 2) ) {
                    valR[2] = (float)tmp.nivelRed(   x + 2, y) + ((5.0f / 48.0f) * erroR);
                    valG[2] = (float)tmp.nivelGreen( x + 2, y) + ((5.0f / 48.0f) * erroG);
                    valB[2] = (float)tmp.nivelBlue(  x + 2, y) + ((5.0f / 48.0f) * erroB);
                }
                
                if(x >= 2 && y < (img.getHeight() - 1)) {
                    valR[3] = (float)tmp.nivelRed(   x - 2, y + 1) + ((3.0f / 48.0f) * erroR);
                    valG[3] = (float)tmp.nivelGreen( x - 2, y + 1) + ((3.0f / 48.0f) * erroG);
                    valB[3] = (float)tmp.nivelBlue(  x - 2, y + 1) + ((3.0f / 48.0f) * erroB);
                }
                
                if(x >= 1 && y < (img.getHeight() - 1)) {
                    valR[4] = (float)tmp.nivelRed(   x - 1, y + 1) + ((5.0f / 48.0f) * erroR);
                    valG[4] = (float)tmp.nivelGreen( x - 1, y + 1) + ((5.0f / 48.0f) * erroG);
                    valB[4] = (float)tmp.nivelBlue(  x - 1, y + 1) + ((5.0f / 48.0f) * erroB);
                }
                
                if(y < (img.getHeight() - 1)) {
                    valR[5] = (float)tmp.nivelRed(   x, y + 1) + ((7.0f / 48.0f) * erroR);
                    valG[5] = (float)tmp.nivelGreen( x, y + 1) + ((7.0f / 48.0f) * erroG);
                    valB[5] = (float)tmp.nivelBlue(  x, y + 1) + ((7.0f / 48.0f) * erroB);
                }
                
                if(x < (img.getWidth() - 1) && y < (img.getHeight() - 1)) {
                    valR[6] = (float)tmp.nivelRed(   x + 1, y + 1) + ((5.0f / 48.0f) * erroR);
                    valG[6] = (float)tmp.nivelGreen( x + 1, y + 1) + ((5.0f / 48.0f) * erroG);
                    valB[6] = (float)tmp.nivelBlue(  x + 1, y + 1) + ((5.0f / 48.0f) * erroB);
                }
                
                if(x < (img.getWidth() - 2) && y < (img.getHeight() - 1)) {
                    valR[7] = (float)tmp.nivelRed(   x + 2, y + 1) + ((3.0f / 48.0f) * erroR);
                    valG[7] = (float)tmp.nivelGreen( x + 2, y + 1) + ((3.0f / 48.0f) * erroG);
                    valB[7] = (float)tmp.nivelBlue(  x + 2, y + 1) + ((3.0f / 48.0f) * erroB);
                }
                
                if(x >= 2 && y < (img.getHeight() - 2)) {
                    valR[8] = (float)tmp.nivelRed(   x - 2, y + 2) + ((erroR / 48.0f));
                    valG[8] = (float)tmp.nivelGreen( x - 2, y + 2) + ((erroG / 48.0f));
                    valB[8] = (float)tmp.nivelBlue(  x - 2, y + 2) + ((erroB / 48.0f));
                }
                
                if(x >= 1 && y < (img.getHeight() - 2)) {
                    valR[9] = (float)tmp.nivelRed(   x - 1, y + 2) + ((3.0f / 48.0f) * erroR);
                    valG[9] = (float)tmp.nivelGreen( x - 1, y + 2) + ((3.0f / 48.0f) * erroG);
                    valB[9] = (float)tmp.nivelBlue(  x - 1, y + 2) + ((3.0f / 48.0f) * erroB);
                }
                
                if(y < (img.getHeight() - 2)) {
                    valR[10] = (float)tmp.nivelRed(   x, y + 2) + ((5.0f / 48.0f) * erroR);
                    valG[10] = (float)tmp.nivelGreen( x, y + 2) + ((5.0f / 48.0f) * erroG);
                    valB[10] = (float)tmp.nivelBlue(  x, y + 2) + ((5.0f / 48.0f) * erroB);
                }
                
                if(x < (img.getWidth() - 1) && y < (img.getHeight() - 2)) {
                    valR[11] = (float)tmp.nivelRed(   x + 1, y + 2) + ((3.0f / 48.0f) * erroR);
                    valG[11] = (float)tmp.nivelGreen( x + 1, y + 2) + ((3.0f / 48.0f) * erroG);
                    valB[11] = (float)tmp.nivelBlue(  x + 1, y + 2) + ((3.0f / 48.0f) * erroB);
                }
                
                if(x < (img.getWidth() - 2) && y < (img.getHeight() - 2)) {
                    valR[12] = (float)tmp.nivelRed(   x + 2, y + 2) + ((erroR / 48.0f));
                    valG[12] = (float)tmp.nivelGreen( x + 2, y + 2) + ((erroG / 48.0f));
                    valB[12] = (float)tmp.nivelBlue(  x + 2, y + 2) + ((erroB / 48.0f));
                }
                
                for(int i = 0; i < valR.length; i++) {
                    
                    if( valR[i] > 255 )
                        valR[i] = 255;
                    else if( valR[i] < 0 )
                        valR[i] = 0;
                    
                    if( valG[i] > 255 )
                        valG[i] = 255;
                    else if( valG[i] < 0 )
                        valG[i] = 0;
                    
                    if( valB[i] > 255 )
                        valB[i] = 255;
                    else if( valB[i] < 0 )
                        valB[i] = 0;
                    
                    valR[i] /= 255;
                    valG[i] /= 255;
                    valB[i] /= 255;
                }
                
                tmp.setRGB( x, y, valR[0], valG[0], valB[0] );
                
                if(x < (img.getWidth() - 1) ) {
                    tmp.setRGB( x + 1, y, valR[1], valG[1], valB[1] );
                }
                
                if(x < (img.getWidth() - 2) ) {
                    tmp.setRGB( x + 2, y, valR[2], valG[2], valB[2] );
                }
                
                if(x >= 2 && y < (img.getHeight() - 1)) {
                    tmp.setRGB( x - 2, y + 1, valR[3], valG[3], valB[3] );
                }
                
                if(x >= 1 && y < (img.getHeight() - 1)) {
                    tmp.setRGB( x - 1, y + 1, valR[4], valG[4], valB[4] );
                }
                
                if(y < (img.getHeight() - 1)) {
                    tmp.setRGB( x, y + 1, valR[5], valG[5], valB[5] );
                }
                
                if(x < (img.getWidth() - 1) && y < (img.getHeight() - 1)) {
                    tmp.setRGB( x + 1, y + 1, valR[6], valG[6], valB[6] );
                }
                
                if(x < (img.getWidth() - 2) && y < (img.getHeight() - 1)) {
                    tmp.setRGB( x + 2, y + 1, valR[7], valG[7], valB[7] );
                }
                
                if(x >= 2 && y < (img.getHeight() - 2)) {
                    tmp.setRGB( x - 2, y + 2, valR[8], valG[8], valB[8] );
                }
                
                if(x >= 1 && y < (img.getHeight() - 2)) {
                    tmp.setRGB( x - 1, y + 2, valR[9], valG[9], valB[9] );
                }
                
                if(y < (img.getHeight() - 2)) {
                    tmp.setRGB( x, y + 2, valR[10], valG[10], valB[10] );
                }
                
                if(x < (img.getWidth() - 1) && y < (img.getHeight() - 2)) {
                    tmp.setRGB( x + 1, y + 2, valR[11], valG[11], valB[11] );
                }
                
                if(x < (img.getWidth() - 2) && y < (img.getHeight() - 2)) {
                    tmp.setRGB( x + 2, y + 2, valR[12], valG[12], valB[12] );
                }
                
            }
                
        return tmp.getImg();
    }
    
    public static BufferedImage stucki(Processador img) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        float valR[], valG[], valB[];
        float erroR, erroG, erroB;
        
        for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
                
                valR = new float[13];
                valG = new float[13];
                valB = new float[13];
                
                if(tmp.nivelRed(x, y) < 128)
                    valR[0] = 0;
                else
                    valR[0] = 255;
                
                if(tmp.nivelGreen(x, y) < 128)
                    valG[0] = 0;
                else
                    valG[0] = 255;
                
                if(tmp.nivelBlue(x, y) < 128)
                    valB[0] = 0;
                else
                    valB[0] = 255;
                
                erroR = (float)tmp.nivelRed(   x, y) - valR[0];
                erroG = (float)tmp.nivelGreen( x, y) - valG[0];
                erroB = (float)tmp.nivelBlue(  x, y) - valB[0];
                
                if(x < (img.getWidth() - 1) ) {
                    valR[1] = (float)tmp.nivelRed(   x + 1, y) + ((8.0f / 42.0f) * erroR);
                    valG[1] = (float)tmp.nivelGreen( x + 1, y) + ((8.0f / 42.0f) * erroG);
                    valB[1] = (float)tmp.nivelBlue(  x + 1, y) + ((8.0f / 42.0f) * erroB);
                }
                
                if(x < (img.getWidth() - 2) ) {
                    valR[2] = (float)tmp.nivelRed(   x + 2, y) + ((4.0f / 42.0f) * erroR);
                    valG[2] = (float)tmp.nivelGreen( x + 2, y) + ((4.0f / 42.0f) * erroG);
                    valB[2] = (float)tmp.nivelBlue(  x + 2, y) + ((4.0f / 42.0f) * erroB);
                }
                
                if(x >= 2 && y < (img.getHeight() - 1)) {
                    valR[3] = (float)tmp.nivelRed(   x - 2, y + 1) + ((2.0f / 42.0f) * erroR);
                    valG[3] = (float)tmp.nivelGreen( x - 2, y + 1) + ((2.0f / 42.0f) * erroG);
                    valB[3] = (float)tmp.nivelBlue(  x - 2, y + 1) + ((2.0f / 42.0f) * erroB);
                }
                
                if(x >= 1 && y < (img.getHeight() - 1)) {
                    valR[4] = (float)tmp.nivelRed(   x - 1, y + 1) + ((4.0f / 42.0f) * erroR);
                    valG[4] = (float)tmp.nivelGreen( x - 1, y + 1) + ((4.0f / 42.0f) * erroG);
                    valB[4] = (float)tmp.nivelBlue(  x - 1, y + 1) + ((4.0f / 42.0f) * erroB);
                }
                
                if(y < (img.getHeight() - 1)) {
                    valR[5] = (float)tmp.nivelRed(   x, y + 1) + ((8.0f / 42.0f) * erroR);
                    valG[5] = (float)tmp.nivelGreen( x, y + 1) + ((8.0f / 42.0f) * erroG);
                    valB[5] = (float)tmp.nivelBlue(  x, y + 1) + ((8.0f / 42.0f) * erroB);
                }
                
                if(x < (img.getWidth() - 1) && y < (img.getHeight() - 1)) {
                    valR[6] = (float)tmp.nivelRed(   x + 1, y + 1) + ((4.0f / 42.0f) * erroR);
                    valG[6] = (float)tmp.nivelGreen( x + 1, y + 1) + ((4.0f / 42.0f) * erroG);
                    valB[6] = (float)tmp.nivelBlue(  x + 1, y + 1) + ((4.0f / 42.0f) * erroB);
                }
                
                if(x < (img.getWidth() - 2) && y < (img.getHeight() - 1)) {
                    valR[7] = (float)tmp.nivelRed(   x + 2, y + 1) + ((2.0f / 42.0f) * erroR);
                    valG[7] = (float)tmp.nivelGreen( x + 2, y + 1) + ((2.0f / 42.0f) * erroG);
                    valB[7] = (float)tmp.nivelBlue(  x + 2, y + 1) + ((2.0f / 42.0f) * erroB);
                }
                
                if(x >= 2 && y < (img.getHeight() - 2)) {
                    valR[8] = (float)tmp.nivelRed(   x - 2, y + 2) + ((erroR / 42.0f));
                    valG[8] = (float)tmp.nivelGreen( x - 2, y + 2) + ((erroG / 42.0f));
                    valB[8] = (float)tmp.nivelBlue(  x - 2, y + 2) + ((erroB / 42.0f));
                }
                
                if(x >= 1 && y < (img.getHeight() - 2)) {
                    valR[9] = (float)tmp.nivelRed(   x - 1, y + 2) + ((2.0f / 42.0f) * erroR);
                    valG[9] = (float)tmp.nivelGreen( x - 1, y + 2) + ((2.0f / 42.0f) * erroG);
                    valB[9] = (float)tmp.nivelBlue(  x - 1, y + 2) + ((2.0f / 42.0f) * erroB);
                }
                
                if(y < (img.getHeight() - 2)) {
                    valR[10] = (float)tmp.nivelRed(   x, y + 2) + ((4.0f / 42.0f) * erroR);
                    valG[10] = (float)tmp.nivelGreen( x, y + 2) + ((4.0f / 42.0f) * erroG);
                    valB[10] = (float)tmp.nivelBlue(  x, y + 2) + ((4.0f / 42.0f) * erroB);
                }
                
                if(x < (img.getWidth() - 1) && y < (img.getHeight() - 2)) {
                    valR[11] = (float)tmp.nivelRed(   x + 1, y + 2) + ((2.0f / 42.0f) * erroR);
                    valG[11] = (float)tmp.nivelGreen( x + 1, y + 2) + ((2.0f / 42.0f) * erroG);
                    valB[11] = (float)tmp.nivelBlue(  x + 1, y + 2) + ((2.0f / 42.0f) * erroB);
                }
                
                if(x < (img.getWidth() - 2) && y < (img.getHeight() - 2)) {
                    valR[12] = (float)tmp.nivelRed(   x + 2, y + 2) + ((erroR / 42.0f));
                    valG[12] = (float)tmp.nivelGreen( x + 2, y + 2) + ((erroG / 42.0f));
                    valB[12] = (float)tmp.nivelBlue(  x + 2, y + 2) + ((erroB / 42.0f));
                }
                
                for(int i = 0; i < valR.length; i++) {
                    
                    if( valR[i] > 255 )
                        valR[i] = 255;
                    else if( valR[i] < 0 )
                        valR[i] = 0;
                    
                    if( valG[i] > 255 )
                        valG[i] = 255;
                    else if( valG[i] < 0 )
                        valG[i] = 0;
                    
                    if( valB[i] > 255 )
                        valB[i] = 255;
                    else if( valB[i] < 0 )
                        valB[i] = 0;
                    
                    valR[i] /= 255;
                    valG[i] /= 255;
                    valB[i] /= 255;
                }
                
                tmp.setRGB( x, y, valR[0], valG[0], valB[0] );
                
                if(x < (img.getWidth() - 1) ) {
                    tmp.setRGB( x + 1, y, valR[1], valG[1], valB[1] );
                }
                
                if(x < (img.getWidth() - 2) ) {
                    tmp.setRGB( x + 2, y, valR[2], valG[2], valB[2] );
                }
                
                if(x >= 2 && y < (img.getHeight() - 1)) {
                    tmp.setRGB( x - 2, y + 1, valR[3], valG[3], valB[3] );
                }
                
                if(x >= 1 && y < (img.getHeight() - 1)) {
                    tmp.setRGB( x - 1, y + 1, valR[4], valG[4], valB[4] );
                }
                
                if(y < (img.getHeight() - 1)) {
                    tmp.setRGB( x, y + 1, valR[5], valG[5], valB[5] );
                }
                
                if(x < (img.getWidth() - 1) && y < (img.getHeight() - 1)) {
                    tmp.setRGB( x + 1, y + 1, valR[6], valG[6], valB[6] );
                }
                
                if(x < (img.getWidth() - 2) && y < (img.getHeight() - 1)) {
                    tmp.setRGB( x + 2, y + 1, valR[7], valG[7], valB[7] );
                }
                
                if(x >= 2 && y < (img.getHeight() - 2)) {
                    tmp.setRGB( x - 2, y + 2, valR[8], valG[8], valB[8] );
                }
                
                if(x >= 1 && y < (img.getHeight() - 2)) {
                    tmp.setRGB( x - 1, y + 2, valR[9], valG[9], valB[9] );
                }
                
                if(y < (img.getHeight() - 2)) {
                    tmp.setRGB( x, y + 2, valR[10], valG[10], valB[10] );
                }
                
                if(x < (img.getWidth() - 1) && y < (img.getHeight() - 2)) {
                    tmp.setRGB( x + 1, y + 2, valR[11], valG[11], valB[11] );
                }
                
                if(x < (img.getWidth() - 2) && y < (img.getHeight() - 2)) {
                    tmp.setRGB( x + 2, y + 2, valR[12], valG[12], valB[12] );
                }
                
            }
                
        return tmp.getImg();
    }
    
    public static BufferedImage stevensonArce(Processador img) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        float valR[], valG[], valB[];
        float erroR, erroG, erroB;
        float [] erro;
        float disp[] = {
                0, 
                32, 12, 26,
                30, 16, 12,
                26, 12, 5,
                12, 12, 5
        }; 
        
        int xx[] = {
             0, 
             2, -3, -1,
             1,  3, -2,
             0,  2, -3,
            -1,  1,  3
        };
        
        int yy[] = {
                0, 
                0, 1, 1,
                1, 1, 2,
                2, 2, 3,
                3, 3, 3
        };
        
        for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {
                
                valR = new float[13];
                valG = new float[13];
                valB = new float[13];
                erro = new float[3];
                
                if(tmp.nivelRed(x, y) < 128)
                    valR[0] = 0;
                else
                    valR[0] = 1;
                
                if(tmp.nivelGreen(x, y) < 128)
                    valG[0] = 0;
                else
                    valG[0] = 1;
                
                if(tmp.nivelBlue(x, y) < 128)
                    valB[0] = 0;
                else
                    valB[0] = 1;
                
                erroR = (float)tmp.nivelRed(   x, y) - (valR[0] * 255.0f);
                erroG = (float)tmp.nivelGreen( x, y) - (valG[0] * 255.0f);
                erroB = (float)tmp.nivelBlue(  x, y) - (valB[0] * 255.0f);
                
                tmp.setRGB(x, y, valR[0], valG[0], valB[0]);
                
                for(int i = 1; i < disp.length; i++ ) {
                    
                    int posX = x + xx[i];
                    int posY = y + yy[i];
                    
                    if(posX >= 0 && posY >= 0 && posX < img.getWidth() && posY < img.getHeight()) {
                        
                        erro[0] = erroR * (disp[i] / 200.0f);
                        erro[1] = erroG * (disp[i] / 200.0f);
                        erro[2] = erroB * (disp[i] / 200.0f);
                        
                        float [] rgb = dispersao( tmp, posX, posY, erro);
                        
                        valR[i] = rgb[0];
                        valG[i] = rgb[1];
                        valB[i] = rgb[2];
                        
                        if( valR[i] > 255 )
                            valR[i] = 255;
                        else if( valR[i] < 0 )
                            valR[i] = 0;
                        
                        if( valG[i] > 255 )
                            valG[i] = 255;
                        else if( valG[i] < 0 )
                            valG[i] = 0;
                        
                        if( valB[i] > 255 )
                            valB[i] = 255;
                        else if( valB[i] < 0 )
                            valB[i] = 0;
                        
                        valR[i] /= 255;
                        valG[i] /= 255;
                        valB[i] /= 255;
                        
                        tmp.setRGB(posX, posY, valR[i], valG[i], valB[i]); 
                    }
                }
                
            }
                
        return tmp.getImg();
    }
    
    private static float [] dispersao(Processador img, int x, int y, float [] erro) {
        float [] rgb = new float[3];
        
        rgb[0] = (float)img.nivelRed(x, y) + erro[0];
        rgb[1] = (float)img.nivelGreen(x, y) + erro[1];
        rgb[2] = (float)img.nivelBlue(x, y) + erro[2];
        
        return rgb;
    }
}
