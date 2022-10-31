package src;

public class Filtros {
    
    //FILTROS PASSA-BAIXA
    
    /**
     * Filtro média 3x3 se trata de uma máscara que 
     * calcula a média da vizinhança e substitui o pixel
     */
    
    public static void media3x3(Processador img ) {
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
                        if(j != y || i != x)
                            rTotal += img.nivelRed(i, j);
                    }
                
                rTotal /= 255;
                rMedio = rTotal / 8;
                
                //green
                gTotal = 0; 
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        if(j != y || i != x)
                            gTotal += img.nivelGreen(i, j);
                    }
                
                gTotal /= 255;
                gMedio = gTotal / 8;
                
                //blue
                bTotal = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        if(j != y || i != x)
                            bTotal += img.nivelBlue(i, j);
                    }
                bTotal /= 255;
                bMedio = bTotal / 8;
                
                tmp.setRGB(x, y, rMedio, gMedio, bMedio);
            }
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "Média 3x3" );
        tmp.getImgPlus().show();
    }
    
    /**
     * Filtro média 5x5 se trata de uma máscara que 
     * calcula a média da vizinhança e substitui o pixel
     */
    
    public static void media5x5(Processador img ) {
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
                        if(j != y || i != x)
                            rTotal += img.nivelRed(i, j);
                    }
                
                rTotal /= 255;
                rMedio = rTotal / 24;
                
                //green
                gTotal = 0; 
                for(int j = y - 2; j < y + 3; j++)
                    for(int i = x - 2; i < x + 3; i++) {
                        if(j != y || i != x)
                            gTotal += img.nivelGreen(i, j);
                    }
                
                gTotal /= 255;
                gMedio = gTotal / 24;
                
                //blue
                bTotal = 0;
                for(int j = y - 2; j < y + 3; j++)
                    for(int i = x - 2; i < x + 3; i++) {
                        if(j != y || i != x)
                            bTotal += img.nivelBlue(i, j);
                    }
                bTotal /= 255;
                bMedio = bTotal / 24;
                
                tmp.setRGB(x, y, rMedio, gMedio, bMedio);
            }
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "Média 5x5" );
        tmp.getImgPlus().show();
    }
    

    /**
     * Filtro mediana 3x3 se trata de uma máscara que 
     * verifica a mediana da vizinhança e substitui o pixel
     */
    
    public static void mediana3x3(Processador img ) {
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
                
                rMedio = mediana(rTotal);
                
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
                
                gMedio = mediana(gTotal);
                
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
                
                bMedio = mediana(bTotal);
                
                tmp.setRGB(x, y, rMedio, gMedio, bMedio);
            }
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "Mediana 3x3" );
        tmp.getImgPlus().show();
    }
    
    /**
     * Filtro mediana 5x5 se trata de uma máscara que 
     * verifica a mediana da vizinhança e substitui o pixel
     */
    
    public static void mediana5x5(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int rTotal[], gTotal[], bTotal[];
        int rMedio, gMedio, bMedio;
        int rI, gI, bI; //identadores
        
        for(int y = 2; y < (img.getHeight() - 2); y++) 
            for(int x = 2; x < (img.getWidth() - 2); x++) {
                
                //red
                rTotal = new int[24];
                rI = 0;
                for(int j = y - 2; j < y + 3; j++)
                    for(int i = x - 2; i < x + 3; i++) {
                        if(j != y || i != x) {
                            rTotal[rI] = img.nivelRed(i, j);
                            rI++;
                        }
                    }
                
                rMedio = mediana(rTotal);
                
                //green
                gTotal = new int[24];
                gI = 0;
                for(int j = y - 2; j < y + 3; j++)
                    for(int i = x - 2; i < x + 3; i++) {
                        if(j != y || i != x) {
                            gTotal[gI] = img.nivelGreen(i, j);
                            gI++;
                        }
                            
                    }
                
                gMedio = mediana(gTotal);
                
                //blue
                bTotal = new int[24];
                bI = 0;
                for(int j = y - 2; j < y + 3; j++)
                    for(int i = x - 2; i < x + 3; i++) {
                        if(j != y || i != x) {
                            bTotal[bI] = img.nivelBlue(i, j);
                            bI++;
                        }
                            
                    }
                
                bMedio = mediana(bTotal);
                
                tmp.setRGB(x, y, rMedio, gMedio, bMedio);
            }
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "Mediana 5x5" );
        tmp.getImgPlus().show();
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
    
    public static void minimo(Processador img ) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "Mínimo" );
        tmp.getImgPlus().show();
    }
    
    /**
     * Filtro máximo se trata de uma máscara que 
     * substitui o pixel pelo de maior intensidade 
     * na vizinhança
     */
    
    public static void maximo(Processador img ) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "Máximo" );
        tmp.getImgPlus().show();
    }
    
    /**
     * Filtro máximo se trata de uma máscara que 
     * substitui o pixel pelo de maior intensidade 
     * na vizinhança
     */
    
    public static void moda(Processador img ) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "Moda" );
        tmp.getImgPlus().show();
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
    
    public static void kuwahara(Processador img ) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "Kuwahara" );
        tmp.getImgPlus().show();
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
    
    public static void tomitaTsuji(Processador img ) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "Tomita e Tsuji" );
        tmp.getImgPlus().show();
    }
    
    /**
     * Filtro Nagao e Matsuyama cria nove máscaras em 
     * relação ao pixel e seleciona a intensidade 
     * média da máscara com menor variância
     */
    
    public static void nagaoMatsuyama(Processador img ) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "Nagao e Matsuyama" );
        tmp.getImgPlus().show();
    }
    
    /**
     * Filtro Somboonkaew cria doze máscaras em 
     * relação ao pixel e seleciona a intensidade 
     * média da máscara com menor variância
     */
    
    public static void somboonkaew(Processador img ) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "Somboonkaew" );
        tmp.getImgPlus().show();
    }
    
    //FILTROS PASSA-ALTA
    
    public static void h1(Processador img ) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "H1" );
        tmp.getImgPlus().show();
    }
    
    public static void h2(Processador img ) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "H2" );
        tmp.getImgPlus().show();
    }
    
    public static void m1(Processador img ) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "M1" );
        tmp.getImgPlus().show();
    }
    
    public static void m2(Processador img ) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "M2" );
        tmp.getImgPlus().show();
    }
    
    public static void m3(Processador img ) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "M3" );
        tmp.getImgPlus().show();
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
    
    public static void highBoost(Processador img, float a) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "High-Boost" );
        tmp.getImgPlus().show();
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
    
    public static void ht2x2(Processador img) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "Ponto Ordenado 2x2" );
        tmp.getImgPlus().show();
    }
    
    public static void ht3x2(Processador img) {
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
        
        tmp.setImgPlus();
        tmp.getImgPlus().setTitle( "Ponto Ordenado 3x2" );
        tmp.getImgPlus().show();
    }
}
