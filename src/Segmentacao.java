package src;

import java.awt.image.BufferedImage;

public class Segmentacao {
    
    public static BufferedImage detPonto(Processador img, double t ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int r;
        
        for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {

                r = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        if(i == x && j == y) {
                            r += (img.nivelCinza(i, j) * 8);
                        }
                        else {
                            if(i >= 0 && j >= 0 && i < img.getWidth() && j < img.getHeight())
                                r += img.nivelCinza(i, j) * -1;
                        }
                    }
                
                if(r < 0)
                    r = -r;
                
                //detecção
                
                if(r > t)
                    r = 255;
                else
                    r = 0;
                
                tmp.setRGB(x, y, r, r, r);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage detRetaHori(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int r;
        
        for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {

                r = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        
                        if(i >= 0 && j >= 0 && i < img.getWidth() && j < img.getHeight()) {
                             if(j == y) {
                                 r += (img.nivelCinza(i, j) * 2);
                             }
                             else {
                                 r += img.nivelCinza(i, j) * -1;
                             }
                        }
                        
                    }
                
                if(r < 0)
                    r = 0;
                
                if(r > 255)
                    r = 255;
                
                tmp.setRGB(x, y, r, r, r);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage detRetaVert(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int r;
        
        for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {

                r = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        
                        if(i >= 0 && j >= 0 && i < img.getWidth() && j < img.getHeight()) {
                             if(i == x) {
                                 r += (img.nivelCinza(i, j) * 2);
                             }
                             else {
                                 r += img.nivelCinza(i, j) * -1;
                             }
                        }
                        
                    }
                
                if(r < 0)
                    r = 0;
                
                if(r > 255)
                    r = 255;
               
                tmp.setRGB(x, y, r, r, r);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage detReta45(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int r, index;
        
        for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {

                r = 0;
                index = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        
                        if(i >= 0 && j >= 0 && i < img.getWidth() && j < img.getHeight()) {
                            switch(index) {
                                case 2: 
                                case 4:
                                case 6:
                                    r += (img.nivelCinza(i, j) * 2);
                                    break;
                                default:
                                    r += (img.nivelCinza(i, j) * -1);
                                    break;
                            }
                        }
                        index++;
                    }
                
                if(r < 0)
                    r = 0;
                
                if(r > 255)
                    r = 255;
                
                tmp.setRGB(x, y, r, r, r);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage detReta135(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        int r, index;
        
        for(int y = 0; y < img.getHeight(); y++) 
            for(int x = 0; x < img.getWidth(); x++) {

                r = 0;
                index = 0;
                for(int j = y - 1; j < y + 2; j++)
                    for(int i = x - 1; i < x + 2; i++) {
                        
                        if(i >= 0 && j >= 0 && i < img.getWidth() && j < img.getHeight()) {
                            switch(index) {
                                case 0: 
                                case 4:
                                case 8:
                                    r += (img.nivelCinza(i, j) * 2);
                                    break;
                                default:
                                    r += (img.nivelCinza(i, j) * -1);
                                    break;
                            }
                        }
                        index++;
                    }
                
                if(r < 0)
                    r = 0;
                
                if(r > 255)
                    r = 255;
                
                tmp.setRGB(x, y, r, r, r);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage roberts(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        float f;
        
        for(int y = 0; y < (img.getHeight() - 1); y++) 
            for(int x = 0; x < (img.getWidth() - 1); x++) {

                float x1 = img.nivelCinza(x, y) - img.nivelCinza(x + 1, y);
                float y1 = img.nivelCinza(x, y) - img.nivelCinza(x, y + 1);
                
                x1 /= 255.0f;
                y1 /= 255.0f;
                
                f = (float)Math.sqrt( Math.pow(x1, 2) + Math.pow(y1, 2) );
                
                if(f < 0)
                    f = 0;
                
                if(f > 1)
                    f = 1;
                
                tmp.setRGB(x, y, f, f, f);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage robertsCruz(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        float f;
        
        for(int y = 0; y < (img.getHeight() - 1); y++) 
            for(int x = 0; x < (img.getWidth() - 1); x++) {

                float x1 = img.nivelCinza(x, y) - img.nivelCinza(x + 1, y + 1);
                float y1 = img.nivelCinza(x, y + 1) - img.nivelCinza(x + 1, y);
                
                x1 /= 255.0f;
                y1 /= 255.0f;
                
                f = (float)Math.sqrt( Math.pow(x1, 2) + Math.pow(y1, 2) );
                
                if(f < 0)
                    f = 0;
                
                if(f > 1)
                    f = 1;
                
                tmp.setRGB(x, y, f, f, f);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage prewittMag(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                int f = 0, f1 = 0, f2 = 0, f3 = 0, f4 = 0;
                
                for(int j = y - 1; j <= y + 1; j++ ) {
                    f1 += img.nivelCinza( x + 1, j );
                }
                
                for(int j = y - 1; j <= y + 1; j++ ) {
                    f2 += img.nivelCinza( x - 1, j );
                }
                
                for(int i = x - 1; i <= x + 1; i++ ) {
                    f3 += img.nivelCinza( i, y + 1 );
                }
                
                for(int i = x - 1; i <= x + 1; i++ ) {
                    f4 += img.nivelCinza( i, y - 1 );
                }
                
                f1 -= f2;
                f2 = f3 - f4;
                
                if(f1 < 0)
                    f1 = -f1;
                if(f2 < 0)
                    f2 = -f2;
                
                f = f1 + f2;
                
                if(f < 0)
                    f = 0;
                
                if(f > 255)
                    f = 255;
                
                tmp.setRGB(x, y, f, f, f);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage prewittGX(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                int f = 0;
                
                for(int j = y - 1; j <= y + 1; j++) {
                    int index = 0;
                    for(int i = x - 1; i <= x + 1; i++) {
                        
                        switch(index) {
                            case 0:
                                f -= img.nivelCinza(i, j);
                                break;
                            case 2:
                                f += img.nivelCinza(i, j);
                                break;
                        }
                        
                        index++;
                    }
                }
                    
                
                if(f < 0)
                    f = 0;
                
                if(f > 255)
                    f = 255;
                
                tmp.setRGB(x, y, f, f, f);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage prewittGY(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                int f = 0;
                
                int index = 0;
                for(int j = y - 1; j <= y + 1; j++) {
                    
                    for(int i = x - 1; i <= x + 1; i++) {
                        
                        switch(index) {
                            case 0:
                                f -= img.nivelCinza(i, j);
                                break;
                            case 2:
                                f += img.nivelCinza(i, j);
                                break;
                        }
                        
                        
                    }
                    index++;
                }
                    
                
                if(f < 0)
                    f = 0;
                
                if(f > 255)
                    f = 255;
                
                tmp.setRGB(x, y, f, f, f);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage sobelGX(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                int f = 0, f1 = 0, f2 = 0;
                
                for(int j = y - 1; j <= y + 1; j++ ) {
                    if(y == j)
                        f1 += (2 * img.nivelCinza( x + 1, j ));
                    else
                        f1 += img.nivelCinza( x + 1, j );
                }
                
                for(int j = y - 1; j <= y + 1; j++ ) {
                    if(y == j)
                        f2 += (2 * img.nivelCinza( x - 1, j ));
                    else
                        f2 += img.nivelCinza( x - 1, j );
                }
                    
                f = f1 - f2;
                
                if(f < 0)
                    f = 0;
                
                if(f > 255)
                    f = 255;
                
                tmp.setRGB(x, y, f, f, f);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage sobelGY(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                int f = 0, f1 = 0, f2 = 0;
                
                for(int i = x - 1; i <= x + 1; i++ ) {
                    if(x == i)
                        f1 += (2 * img.nivelCinza( i, y + 1 ));
                    else
                        f1 += img.nivelCinza( i, y + 1 );
                }
                
                for(int i = x - 1; i <= x + 1; i++ ) {
                    if(x == i)
                        f2 += (2 * img.nivelCinza( i, y - 1 ));
                    else
                        f2 += img.nivelCinza( i, y - 1 );
                }
                    
                f = f1 - f2;
                
                if(f < 0)
                    f = 0;
                
                if(f > 255)
                    f = 255;
                
                tmp.setRGB(x, y, f, f, f);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage sobelMag(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                int f = 0, f1 = 0, f2 = 0, f3 = 0, f4 = 0;
                
                for(int i = x - 1; i <= x + 1; i++ ) {
                    if(x == i)
                        f1 += (2 * img.nivelCinza( i, y + 1 ));
                    else
                        f1 += img.nivelCinza( i, y + 1 );
                }
                
                for(int i = x - 1; i <= x + 1; i++ ) {
                    if(x == i)
                        f2 += (2 * img.nivelCinza( i, y - 1 ));
                    else
                        f2 += img.nivelCinza( i, y - 1 );
                }
                
                for(int j = y - 1; j <= y + 1; j++ ) {
                    if(y == j)
                        f3 += (2 * img.nivelCinza( x + 1, j ));
                    else
                        f3 += img.nivelCinza( x + 1, j );
                }
                
                for(int j = y - 1; j <= y + 1; j++ ) {
                    if(y == j)
                        f4 += (2 * img.nivelCinza( x - 1, j ));
                    else
                        f4 += img.nivelCinza( x - 1, j );
                }
                    
                f1 -= f2;
                f2 = f3 - f4;
                
                if(f1 < 0)
                    f1 = -f1;
                if(f2 < 0)
                    f2 = -f2;
                
                f = f1 + f2;
                
                if(f < 0)
                    f = 0;
                
                if(f > 255)
                    f = 255;
                
                tmp.setRGB(x, y, f, f, f);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage kirsh(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                int f = 0, index;
                int mask[] = new int[8];
                
                //inicializando vetor
                for(int i = 0; i < mask.length; i++) {
                    mask[i] = 0;
                }
                
                //calculo das mascaras
                for(int cur = 1; cur <= 8; cur++) {
                    
                    switch(cur) {
                        case 1:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 0:
                                        case 3:
                                        case 6:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 5);
                                            break;
                                        case 4:
                                            mask[cur - 1] += 0;
                                            break;
                                        default:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 3);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 2:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 3:
                                        case 6:
                                        case 7:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 5);
                                            break;
                                        case 4:
                                            mask[cur - 1] += 0;
                                            break;
                                        default:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 3);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 3:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 6:
                                        case 7:
                                        case 8:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 5);
                                            break;
                                        case 4:
                                            mask[cur - 1] += 0;
                                            break;
                                        default:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 3);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 4:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 5:
                                        case 7:
                                        case 8:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 5);
                                            break;
                                        case 4:
                                            mask[cur - 1] += 0;
                                            break;
                                        default:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 3);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 5:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 2:
                                        case 5:
                                        case 8:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 5);
                                            break;
                                        case 4:
                                            mask[cur - 1] += 0;
                                            break;
                                        default:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 3);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 6:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 1:
                                        case 2:
                                        case 5:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 5);
                                            break;
                                        case 4:
                                            mask[cur - 1] += 0;
                                            break;
                                        default:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 3);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 7:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 0:
                                        case 1:
                                        case 2:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 5);
                                            break;
                                        case 4:
                                            mask[cur - 1] += 0;
                                            break;
                                        default:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 3);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 8:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 0:
                                        case 1:
                                        case 3:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 5);
                                            break;
                                        case 4:
                                            mask[cur - 1] += 0;
                                            break;
                                        default:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 3);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                    }
                }
                
                f = Math.max(mask[0], Math.max(mask[1], Math.max(mask[2], Math.max(mask[3], 
                        Math.max(mask[4], Math.max(mask[5], Math.max(mask[6], mask[7] )))))));
                
                if(f < 0)
                    f = 0;
                
                if(f > 255)
                    f = 255;
                
                tmp.setRGB(x, y, f, f, f);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage robison(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                int f = 0, index;
                int mask[] = new int[8];
                
                //inicializando vetor
                for(int i = 0; i < mask.length; i++) {
                    mask[i] = 0;
                }
                
                //calculo das mascaras
                for(int cur = 1; cur <= 8; cur++) {
                    
                    switch(cur) {
                        case 1:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 0:
                                        case 6:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                        case 3:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 5:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 2:
                                        case 8:
                                            mask[cur - 1] -= img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 2:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 3:
                                        case 7:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                        case 6:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 2:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 1:
                                        case 5:
                                            mask[cur - 1] -= img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 3:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 6:
                                        case 8:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                        case 7:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 1:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 0:
                                        case 2:
                                            mask[cur - 1] -= img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 4:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 5:
                                        case 7:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                        case 8:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 0:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 1:
                                        case 3:
                                            mask[cur - 1] -= img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 5:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 2:
                                        case 8:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                        case 5:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 3:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 0:
                                        case 6:
                                            mask[cur - 1] -= img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 6:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 1:
                                        case 5:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                        case 2:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 6:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 3:
                                        case 7:
                                            mask[cur - 1] -= img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 7:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 0:
                                        case 2:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                        case 1:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 7:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 6:
                                        case 8:
                                            mask[cur - 1] -= img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 8:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 1:
                                        case 3:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                        case 0:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 8:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 5:
                                        case 7:
                                            mask[cur - 1] -= img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                    }
                }
                
                f = Math.max(mask[0], Math.max(mask[1], Math.max(mask[2], Math.max(mask[3], 
                        Math.max(mask[4], Math.max(mask[5], Math.max(mask[6], mask[7] )))))));
                
                if(f < 0)
                    f = 0;
                
                if(f > 255)
                    f = 255;
                
                tmp.setRGB(x, y, f, f, f);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage freiChen(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        float raiz = (float)Math.sqrt(2.0);
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                float f = 0;
                int index;
                float mask[] = new float[9];
                
                //inicializando vetor
                for(int i = 0; i < mask.length; i++) {
                    mask[i] = 0;
                }
                
                //calculo das mascaras
                for(int cur = 1; cur <= mask.length; cur++) {
                    
                    switch(cur) {
                        case 1:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 0:
                                        case 2:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                        case 1:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * raiz);
                                            break;
                                        case 7:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * raiz);
                                            break;
                                        case 6:
                                        case 8:
                                            mask[cur - 1] -= img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 2:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 0:
                                        case 6:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                        case 3:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * raiz);
                                            break;
                                        case 5:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * raiz);
                                            break;
                                        case 2:
                                        case 8:
                                            mask[cur - 1] -= img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 3:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 3:
                                        case 7:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                        case 2:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * raiz);
                                            break;
                                        case 6:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * raiz);
                                            break;
                                        case 1:
                                        case 5:
                                            mask[cur - 1] -= img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 4:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 5:
                                        case 7:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                        case 0:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * raiz);
                                            break;
                                        case 8:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * raiz);
                                            break;
                                        case 1:
                                        case 3:
                                            mask[cur - 1] -= img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 5:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 1:
                                        case 7:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                        case 3:
                                        case 5:
                                            mask[cur - 1] -= img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 6:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 2:
                                        case 6:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                        case 0:
                                        case 8:
                                            mask[cur - 1] -= img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 7:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 1:
                                        case 3:
                                        case 5:
                                        case 7:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 4:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 4);
                                            break;
                                        default:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 8:
                            index = 0;
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    
                                    switch(index) {
                                        case 0:
                                        case 2:
                                        case 6:
                                        case 8:
                                            mask[cur - 1] -= (img.nivelCinza(i, j) * 2);
                                            break;
                                        case 4:
                                            mask[cur - 1] += (img.nivelCinza(i, j) * 4);
                                            break;
                                        default:
                                            mask[cur - 1] += img.nivelCinza(i, j);
                                            break;
                                    }
                                    index++;
                                }
                            break;
                        case 9:
                            for(int j = y - 1; j <= y + 1; j++)
                                for(int i = x - 1; i <= x + 1; i++) {
                                    mask[cur - 1] += img.nivelCinza(i, j);
                                }
                            break;
                    }
                }
                
                //somatorio ponderado das mascaras
                for(int i = 0; i <= 3; i++)
                    f += mask[i] / (2.0 * raiz);
                
                for(int i = 4; i <= 5; i++)
                    f += mask[i] / 2.0;
                
                for(int i = 6; i <= 7; i++)
                    f += mask[i] / 6.0;
                
                f += mask[8] / 3.0;
                
                //divide por 255 para entrar no intervalo 0-1
                f /= 255.0f;
                
                //truncamento
                if(f < 0)
                    f = 0;
                
                if(f > 1)
                    f = 1;
                
                tmp.setRGB(x, y, f, f, f);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage laplacianoH1(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                int f = 0, index = 0;
                
                for(int j = y - 1; j <= y + 1; j++)
                    for(int i = x - 1; i <= x + 1; i++) {
                        
                        switch(index) {
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                                f -= img.nivelCinza(i, j);
                                break;
                            case 4:
                                f += (img.nivelCinza(i, j) * 4);
                                break;
                        }
                        index++;
                }
                
                //truncamento
                if(f < 0)
                    f = 0;
                
                if(f > 255)
                    f = 255;
                
                tmp.setRGB(x, y, f, f, f);
            }
        
        return tmp.getImg();
    }
    
    public static BufferedImage laplacianoH2(Processador img ) {
        Processador tmp = new Processador();
        tmp.setImg( img.getImg() );
        
        for(int y = 1; y < (img.getHeight() - 1); y++) 
            for(int x = 1; x < (img.getWidth() - 1); x++) {
                
                int f = 0, index = 0;
                
                for(int j = y - 1; j <= y + 1; j++)
                    for(int i = x - 1; i <= x + 1; i++) {
                        
                        switch(index) {
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                                f -= (img.nivelCinza(i, j) * 4);
                                break;
                            case 4:
                                f += (img.nivelCinza(i, j) * 20);
                                break;
                            default:
                                f -= img.nivelCinza(i, j);
                                break;
                        }
                        index++;
                }
                
                //truncamento
                if(f < 0)
                    f = 0;
                
                if(f > 255)
                    f = 255;
                
                tmp.setRGB(x, y, f, f, f);
            }
        
        return tmp.getImg();
    }
}
