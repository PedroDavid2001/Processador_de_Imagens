package src;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.swing.JFileChooser;
import ij.io.FileSaver;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Classe controladora da tela principal. A priori, o
 * o software possuirá apenas uma tela para manipular 
 * as imagens e nela estarão os botões de operações, 
 * transformações, realces, abrir e salvar arquivos.
 * Os métodos estão separados por comentários, e, no
 * último bloco, estão os métodos adicionais, tais 
 * como, salvar arquivos, acumular operações, limpeza 
 * da área de trabalho e sair do programa. 
 */

public class Painel implements Initializable{

	@FXML
    private AnchorPane workspace;
	
    @FXML
    private Text mousePosText;
    
    @FXML
    private Text rgbText;
    
    @FXML
    private TextField numCamadas;

    @FXML
    private ImageView imagemIni;

    @FXML
    private Menu menuOperacoes;
    
    @FXML
    private Menu menuRealce;

    @FXML
    private Menu menuTransformar;
    
    @FXML
    private Menu menuCores;

    @FXML
    private ImageView imagemSec;

    @FXML
    private MenuItem botaoAbrirCamada;

    @FXML
    private MenuItem botaoSalvar;
    
    @FXML
    private Pane painelLateral;
    
    @FXML
    private MenuItem botaoHistograma;

    @FXML
    private MenuItem botaoSalvarComo;

    @FXML
    private ImageView imagemFinal;

    @FXML
    private RadioMenuItem botaoNormal;

    @FXML
    private RadioMenuItem botaoTrunc;

    @FXML
    private Menu menuLimpar;
    
    @FXML
    private TextField posYText;
    
    @FXML
    private TextField valorFatorC;
    
    @FXML
    private TextField valorGamma;
    
    @FXML
    private TextField posXText;

    @FXML
    private TextField textoValor;
    
    @FXML
    private Slider rotacaoSlide;
    
    @FXML
    private Menu menuPseudoCor;
    
    @FXML
    private Slider tamXSlide;
    
    @FXML
    private Pane paneSaturation;
    
    @FXML
    private ImageView saturationArrow;
    
    @FXML
    private ImageView brightnessArrow;
    
    @FXML
    private ImageView hueArrow;
    
    @FXML
    private Pane paneBrightness;
   
    @FXML
    private TextField tamXTexto;
    
    @FXML
    private Slider tamYSlide;
   
    @FXML
    private TextField tamYTexto;
    
    @FXML
    private TextField grauText;
    
    @FXML
    private TextField valorMin;
    
    @FXML
    private TextField valorMax;

    @FXML
    private MenuItem botaoLimparIni;

    @FXML
    private Pane MinMaxPane;
    
    @FXML
    private ImageView matizImg;
    
    @FXML
    private Pane GammaPane;
    
    @FXML
    private Pane fatiarPanel;
    
    @FXML
    private MenuItem botaoLimparSec;

    @FXML
    private MenuItem botaoLimparFinal;

    @FXML
    private RadioMenuItem botaoAcc;

    @FXML
    private Pane popupValor;

    private Processador imagem = new Processador();
    private Processador imgSec = new Processador();
    private Image img;
    
    //Posição inicial da imagem exibida na área de trabalho do software
    private double posXInit = 100.0;
    private double posYInit = 100.0;
    
    //posições do mouse na tela
    private double mouseXpos;
    private double mouseYpos;
    
  //posições do mouse na imagem
    private double mouseX;
    private double mouseY;
    
    //boolean para ativar/desativar opção de arrastar
    private boolean dragMode = false;
    
    //boolean que decide qual transformação linear será utilizada
    private boolean linearInverso;
    
    //valores minimos e maximos usados nas transformações lineares
    private int valorMinimo;
    private int valorMaximo;
    
    //valores usados na correção de gamma
    private double fatorC;
    private double gamma;
    
    //numero de camadas usadas no fatiamento de bits
    private int qntCamadas;
    
    JFileChooser fileChooser = new JFileChooser();
    
    /*
     * O objeto imgFinal é utilizado para guardar e exibir
     * a imagem resultante. Também é utilizado para quando
     * a opção "acumular" no menu "opções" está selecionada. 
     */
    private Processador imgFinal = new Processador();
    
    //processador usado para armazenar a faixa de matiz na tabela lateral
    private Processador matiz = new Processador();
    
    /*
     * Inteiro que armazena qual foi a ultima ação
     * para auxiliar em métodos adicionais.
     * 0  - divis�o (ZERA PARA N�O ALTERAR NA DIVIS�O!!!)
     * 1  - soma
     * 2  - subtração
     * 3  - multiplicação
     * 4  - and
     * 5  - or
     * 6  - xor
     * 7  - CisX
     * 8  - CisY
     */
    private int lstAct;

    /*Variável ponto flutuante que armazena o 
    último valor digitado para uma transformação */
    private float valorTransf;

    //--------------------------------------------------------------
    //Métodos para exibir a imagem na tela

    @FXML
    void abrirImgPrimaria(ActionEvent event) {
        img = null;
        imagem.carregarImg();

        if(imagem.getImg() != null){
            imgFinal.setImg( imagem.getImg() );
            
            img = SwingFXUtils.toFXImage(imagem.getImg(), null);
                
            //preview da imagem
            imagemIni.setImage(img);
                
            imagemIni.setX( 160.0 );
            imagemIni.setY( 10.0 );
            imagemIni.setFitHeight( 50.0 );
            imagemIni.setFitWidth(  50.0 );
            
            //configura a imagem exibida na área de trabalho
            this.configurarImgFinal();

            grauText.setText( "0.0" );
            rotacaoSlide.setValue( 0.0 );
            tamXTexto.setText( "1.0" );
            tamYTexto.setText( "1.0" );
            tamXSlide.setValue( 1.0 );
            tamYSlide.setValue( 1.0 );
            posXText.setText( "0.0" );
            posYText.setText( "0.0" );
            
            matiz.setImg( matizImg.getImage() );

        }
    }

    @FXML
    void abrirImgSec(ActionEvent event) {
        img = null;
        imgSec.carregarImg();

        if(imgSec.getImg() != null){
            img = SwingFXUtils.toFXImage(imgSec.getImg(), null);
            
            imagemSec.setImage(img);
                
            imagemSec.setFitHeight( 50.0 );
            imagemSec.setFitWidth( 50.0 );
                
            imagemSec.setX( 160.0 );
            imagemSec.setY( 70.0 );

            //habilita os botões da interface caso a imagem abra com sucesso
            menuOperacoes.setDisable(false);
            botaoLimparSec.setDisable(false);
        }
    }

    //--------------------------------------------------------------
    //Métodos de operação aritmética

    @FXML
    void adicionar(ActionEvent event) {
        img = null;

        if( botaoAcc.isSelected() )
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.addNormal(imgFinal, imgSec) : Operacoes.addTrunc(imgFinal, imgSec) );       
        else
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.addNormal(imagem, imgSec) : Operacoes.addTrunc(imagem, imgSec) );

        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);
            
        imagemFinal.setImage(img);
        imagemFinal.setFitHeight( imgFinal.getHeight() );
        imagemFinal.setFitWidth( imgFinal.getWidth() );
       
        lstAct = 1;
        botaoLimparFinal.setDisable(false);
        botaoSalvarComo.setDisable(false);

    }

    @FXML
    void subtrair(ActionEvent event) {
        img = null;

        if( botaoAcc.isSelected() )
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.subNormal(imgFinal, imgSec) : Operacoes.subTrunc(imgFinal, imgSec) );       
        else
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.subNormal(imagem, imgSec) : Operacoes.subTrunc(imagem, imgSec) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        imagemFinal.setFitHeight( imgFinal.getHeight() );
        imagemFinal.setFitWidth( imgFinal.getWidth() );
        
        lstAct = 2;
        botaoLimparFinal.setDisable(false);
        botaoSalvarComo.setDisable(false);

    }

    @FXML
    void multiplicar(ActionEvent event) {
        img = null;

        if( botaoAcc.isSelected() )
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.multiNormal(imgFinal, imgSec) : Operacoes.multiTrunc(imgFinal, imgSec) );       
        else
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.multiNormal(imagem, imgSec) : Operacoes.multiTrunc(imagem, imgSec) );

        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        imagemFinal.setFitHeight( imgFinal.getHeight() );
        imagemFinal.setFitWidth( imgFinal.getWidth() );
       
        lstAct = 3;
        botaoLimparFinal.setDisable(false);
        botaoSalvarComo.setDisable(false);

    }

    @FXML
    void dividir(ActionEvent event) {
        img = null;

        if( botaoAcc.isSelected() )
            imgFinal.setImg( Operacoes.divisao(imgFinal, imgSec) );      
        else
            imgFinal.setImg( Operacoes.divisao(imagem, imgSec) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        imagemFinal.setFitHeight( imgFinal.getHeight() );
        imagemFinal.setFitWidth( imgFinal.getWidth() );
        
        lstAct = 0;
       
        botaoLimparFinal.setDisable(false);
        botaoSalvarComo.setDisable(false);

    }

    @FXML
    void operacaoAnd(ActionEvent event) {
        img = null;

        if( botaoAcc.isSelected() )
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.andNormal(imgFinal, imgSec) : Operacoes.andTrunc(imgFinal, imgSec) );       
        else
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.andNormal(imagem, imgSec) : Operacoes.andTrunc(imagem, imgSec) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        imagemFinal.setFitHeight( imgFinal.getHeight() );
        imagemFinal.setFitWidth( imgFinal.getWidth() );
        
        lstAct = 4;
        botaoLimparFinal.setDisable(false);
        botaoSalvarComo.setDisable(false);

    }

    @FXML
    void operacaoOr(ActionEvent event) {
        img = null;
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.orNormal(imgFinal, imgSec) : Operacoes.orTrunc(imgFinal, imgSec) );       
        else
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.orNormal(imagem, imgSec) : Operacoes.orTrunc(imagem, imgSec) );

        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        imagemFinal.setFitHeight( imgFinal.getHeight() );
        imagemFinal.setFitWidth( imgFinal.getWidth() );
       
        lstAct = 5;
        botaoLimparFinal.setDisable(false);
        botaoSalvarComo.setDisable(false);

    }

    @FXML
    void operacaoXor(ActionEvent event) {
        img = null;

        if( botaoAcc.isSelected() )
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.xorNormal(imgFinal, imgSec) : Operacoes.xorTrunc(imgFinal, imgSec) );       
        else
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.xorNormal(imagem, imgSec) : Operacoes.xorTrunc(imagem, imgSec) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        imagemFinal.setFitHeight( imgFinal.getHeight() );
        imagemFinal.setFitWidth( imgFinal.getWidth() );
       
        lstAct = 6;
        botaoLimparFinal.setDisable(false);
        botaoSalvarComo.setDisable(false);

    }

    //--------------------------------------------------------------
    //Métodos de transformação geométrica

    @FXML
    void espelhoX(ActionEvent event) {
        img = null;

        if( botaoAcc.isSelected() )
            imgFinal.setImg( Transformacoes.horizontalMirror( imgFinal.getImg() ) );    
        else
            imgFinal.setImg( Transformacoes.horizontalMirror( imagem.getImg() ) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        imagemFinal.setFitHeight( imgFinal.getHeight() );
        imagemFinal.setFitWidth( imgFinal.getWidth() );
        
        botaoLimparFinal.setDisable(false);
        botaoSalvarComo.setDisable(false);

    }

    @FXML
    void espelhoY(ActionEvent event) {
        img = null;

        if( botaoAcc.isSelected() )
            imgFinal.setImg( Transformacoes.verticalMirror( imgFinal.getImg() ) );    
        else
            imgFinal.setImg( Transformacoes.verticalMirror( imagem.getImg() ) );

        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        imagemFinal.setFitHeight( imgFinal.getHeight() );
        imagemFinal.setFitWidth( imgFinal.getWidth() );
       
        botaoLimparFinal.setDisable(false);
        botaoSalvarComo.setDisable(false);
    }
    
    @FXML
    void cisalharX(ActionEvent event) {
        popupValor.setVisible(true);
        lstAct = 7;
    }

    void cisX(){
        
    	img = null;

        if( botaoAcc.isSelected() )
            imgFinal.setImg( Transformacoes.cisalhamentoX( imgFinal.getImg(), valorTransf ) );    
        else
            imgFinal.setImg( Transformacoes.cisalhamentoX( imagem.getImg() , valorTransf ) );

        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
        /*Se acumular estiver selecionado, o tamanho 
        após cisalhamento será o definido nos sliders 
        de redimensionamento*/
        if( botaoAcc.isSelected() ){
        	imagemFinal.setFitWidth( imgFinal.getWidth() * tamXSlide.getValue() );
        	imagemFinal.setFitHeight( imgFinal.getHeight() * tamYSlide.getValue() );
        }
        else {
        	imagemFinal.setFitHeight( imgFinal.getHeight() );
        	imagemFinal.setFitWidth( imgFinal.getWidth() );
        }
        
        botaoLimparFinal.setDisable(false);
        botaoSalvarComo.setDisable(false);
    }

    @FXML
    void cisalharY(ActionEvent event) {
        popupValor.setVisible(true);
        lstAct = 8;
    }

    void cisY(){
        
    	img = null;

        if( botaoAcc.isSelected() )
            imgFinal.setImg( Transformacoes.cisalhamentoY( imgFinal.getImg(), valorTransf ) );    
        else
            imgFinal.setImg( Transformacoes.cisalhamentoY( imagem.getImg() , valorTransf ) );

        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
        /*Se acumular estiver selecionado, o tamanho 
        após cisalhamento será o definido nos sliders 
        de redimensionamento*/
        if( botaoAcc.isSelected() ) {
        	imagemFinal.setFitWidth( imgFinal.getWidth() * tamXSlide.getValue() );
        	imagemFinal.setFitHeight( imgFinal.getHeight() * tamYSlide.getValue() );
        }
        else {
        	imagemFinal.setFitHeight( imgFinal.getHeight() );
        	imagemFinal.setFitWidth( imgFinal.getWidth() );
        }
        
        botaoLimparFinal.setDisable(false);
        botaoSalvarComo.setDisable(false);
    }

    @FXML
    void transladeX(ActionEvent event) {
    	if( botaoAcc.isSelected() )
    		imagemFinal.setX( imagemFinal.getX() + Double.valueOf(posXText.getText()) );
    	else
    		imagemFinal.setX( posXInit + Double.valueOf(posXText.getText()) );
    }

    @FXML
    void transladeY(ActionEvent event) {
    	if( botaoAcc.isSelected() )
    		imagemFinal.setY( imagemFinal.getY() + Double.valueOf(posYText.getText()) );
    	else
    		imagemFinal.setY( posYInit + Double.valueOf(posYText.getText()) );
    }
    
    void redimensionarX() {
    	imagemFinal.setFitWidth( imagem.getWidth() * tamXSlide.getValue() );
    }
    
    void redimensionarY() {
    	imagemFinal.setFitHeight( imagem.getHeight() * tamYSlide.getValue() );
    }
 
    void rotacionar() {
    	imagemFinal.setRotate( rotacaoSlide.getValue() );
    	imgFinal.setImg( imagemFinal.getImage() );
    }
    
    //--------------------------------------------------------------
    //Métodos de componentes de cor
    
    @FXML
    void exibirRGB(ActionEvent event) {
    	if( botaoAcc.isSelected() )
    		Cores.RGB( imgFinal );
    	else
    		Cores.RGB( imagem );
    }

    @FXML
    void exibirCMY(ActionEvent event) {
    	if( botaoAcc.isSelected() )
    		Cores.CMY( imgFinal );
    	else
    		Cores.CMY( imagem );
    }

    @FXML
    void exibirCMYK(ActionEvent event) {
    	if( botaoAcc.isSelected() )
    		Cores.CMYK( imgFinal );
    	else
    		Cores.CMYK( imagem );
    	
    }

    @FXML
    void exibirYUV(ActionEvent event) {
    	if( botaoAcc.isSelected() )
    		Cores.YUV( imgFinal );
    	else
    		Cores.YUV( imagem );
    }
    
    @FXML
    void exibirGray(ActionEvent event) {
    	if( botaoAcc.isSelected() )
    		Cores.grayScale( imgFinal );
    	else
    		Cores.grayScale( imagem );
    }
    
    //--------------------------------------------------------------
    //Pseudocoloração
    
    @FXML
    void PseudoRGB(ActionEvent event) {
    	Cores.pseudoColorGrayScale(imgFinal, 1);
    }	

    @FXML
    void PseudoRBG(ActionEvent event) {
    	Cores.pseudoColorGrayScale(imgFinal, 2);
    }

    @FXML
    void PseudoBRG(ActionEvent event) {
    	Cores.pseudoColorGrayScale(imgFinal, 3);
    }

    @FXML
    void PseudoBGR(ActionEvent event) {
    	Cores.pseudoColorGrayScale(imgFinal, 4);
    }

    @FXML
    void PseudoGRB(ActionEvent event) {
    	Cores.pseudoColorGrayScale(imgFinal, 5);
    }

    @FXML
    void PseudoGBR(ActionEvent event) {
    	Cores.pseudoColorGrayScale(imgFinal, 6);
    }
    
    //Ignorar uma cor

    @FXML
    void IgnoreBlue(ActionEvent event) {
    	Cores.pseudoColorIgnoring(imgFinal, 1);
    }
    
    @FXML
    void IgnoreRed(ActionEvent event) {
    	Cores.pseudoColorIgnoring(imgFinal, 2);
    }
    
    @FXML
    void IgnoreGreen(ActionEvent event) {
    	Cores.pseudoColorIgnoring(imgFinal, 3);
    }
    
    //Aumentar uma cor
    
    @FXML
    void IncBlue(ActionEvent event) {
    	Cores.pseudoColorIncreasing(imgFinal, 1);
    }
    
    @FXML
    void IncRed(ActionEvent event) {
    	Cores.pseudoColorIncreasing(imgFinal, 2);
    }
    
    @FXML
    void IncGreen(ActionEvent event) {
    	Cores.pseudoColorIncreasing(imgFinal, 3);
    }

    //Reduzir uma cor
    @FXML
    void ReduBlue(ActionEvent event) {
    	Cores.pseudoColorReducing(imgFinal, 1);
    }
    
    @FXML
    void ReduRed(ActionEvent event) {
    	Cores.pseudoColorReducing(imgFinal, 2);
    }
    
    @FXML
    void ReduGreen(ActionEvent event) {
    	Cores.pseudoColorReducing(imgFinal, 3);
    }
    
    //--------------------------------------------------------------
    //Realces
    
    @FXML
    void RealceLinear(ActionEvent event) {
        linearInverso = false;
        MinMaxPane.setVisible(true);
    }

    @FXML
    void RealceInverso(ActionEvent event) {
       linearInverso = true;
       MinMaxPane.setVisible(true);
    }
    
    /**
     * 1 = logaritmo
     * 2 = exponencial
     * 3 = quadrado
     * 4 = raiz quadrada 
     **/
    
    @FXML
    void RealceLog(ActionEvent event) {
        Realce.NaoLinear(imgFinal, 1);
    }

    @FXML
    void RealceQuad(ActionEvent event) {
        Realce.NaoLinear(imgFinal, 3);
    }
    

    @FXML
    void RealceExp(ActionEvent event) {
        Realce.NaoLinear(imgFinal, 2);
    }

    @FXML
    void RealceRaiz(ActionEvent event) {
        Realce.NaoLinear(imgFinal, 4);
    }
    
    @FXML
    void equalHisto(ActionEvent event) {
        Realce.EqualizarHistoGrama(imgFinal.getImg());
    }
    
    @FXML
    void corrigirGama(ActionEvent event) {
        GammaPane.setVisible(true);
    }
    
    @FXML
    void fatiarBits(ActionEvent event) {
        fatiarPanel.setVisible(true);
    }
    
    //--------------------------------------------------------------
    //Métodos extras
    
    @FXML
    void angulo(ActionEvent event) {
    	rotacaoSlide.setValue( Double.valueOf( grauText.getText() ) );
    	rotacionar();
    }

    @FXML
    void tamanhoX(ActionEvent event) {
    	tamXSlide.setValue( Double.valueOf( tamXTexto.getText() ) );
    	redimensionarX();
    }
    
    @FXML
    void tamanhoY(ActionEvent event) {
    	tamYSlide.setValue( Double.valueOf( tamYTexto.getText() ) );
    	redimensionarY();
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	//detecção de rotação
    	rotacaoSlide.valueProperty().addListener(new ChangeListener<Number>() {
    		
    		@Override
    		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
    			grauText.setText( Double.toString(rotacaoSlide.getValue()) );
    			rotacionar();
    		}
    	} );
    	
    	//detecção do slider X
    	tamXSlide.valueProperty().addListener(new ChangeListener<Number>() {
    		
    		@Override
    		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
    			tamXTexto.setText( Double.toString(tamXSlide.getValue()) );
    			redimensionarX();
    		}
    	} );
    	
    	//detecção do slider Y
    	tamYSlide.valueProperty().addListener(new ChangeListener<Number>() {
    		
    		@Override
    		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
    			tamYTexto.setText( Double.toString(tamYSlide.getValue()) );
    			redimensionarY();
    		}
    	} );
    	
    	//pega a posição inicial do mouse no momento em que clica na imagem
    	imagemFinal.setOnMouseClicked( mouseEvent -> {
    		dragMode = !dragMode;
    		mouseX = mouseEvent.getX() - imagemFinal.getX();
    		mouseY = mouseEvent.getY() - imagemFinal.getY();
    	});
    	
    	//captura o rgb na posição que o mouse está na imagem
    	imagemFinal.setOnMouseMoved( mouseEvent -> {
    		//não altera quando está movendo a imagem
    		if(!dragMode && imgFinal.getImg() != null) {
    			int x = (int)(mouseEvent.getX() - (int)imagemFinal.getX());
    			int y = (int)(mouseEvent.getY() - (int)imagemFinal.getY());
    			
    			int R = imgFinal.nivelRed(x, y);
    			int G = imgFinal.nivelGreen(x, y);
    			int B = imgFinal.nivelBlue(x, y);
    			
    			rgbText.setText( "RGB( " + R + ", " + G + ", " + B + " )" );
    			
    			hueArrow.setLayoutX( (( Cores.Hue(R, G, B) ) / 360.0) * 200.0 );//( valor / 360 ) * 200px
    			
    			paneBrightness.setStyle("-fx-background-color: #" + matiz.getHex((int)hueArrow.getLayoutX(), 10) );
                paneSaturation.setStyle("-fx-background-color: #" + matiz.getHex((int)hueArrow.getLayoutX(), 10) );
    			saturationArrow.setLayoutX( Cores.Saturation(R, G, B) * 200.0 ); //( valor / 255 ) * 200px
    			brightnessArrow.setLayoutX( (( Cores.Brightness(R, G, B) ) / 255.0) * 200.0 ); //( valor / 255 ) * 200px
    		}
    	});
    	
    	//captura posição do mouse
    	workspace.setOnMouseMoved( mouseEvent ->{
    		mouseXpos = mouseEvent.getX();
    		mouseYpos = mouseEvent.getY();
    		
    		mousePosText.setText(  (int)mouseXpos + ", " + (int)mouseYpos + "px" );
    		
    		if( dragMode ) {
    			imagemFinal.setX( mouseXpos - mouseX);
        		imagemFinal.setY( mouseYpos - mouseY);
        		
        		//atualiza os valores nos textos de posição
        		posXText.setText( Double.toString( imagemFinal.getX() ));
        		posYText.setText( Double.toString( imagemFinal.getY() ));
    		}
    	});
    }

    /*
     * As cadeias de if's e else's presentes nos 
     * métodos Normalizar() e Truncar() serve para 
     * alterar o aspecto da imagem final assim 
     * que trocar entre as opções.
     */

    @FXML
    void Normalizar(ActionEvent event) {
        botaoTrunc.setSelected(false);
        
        if(lstAct == 1)
            adicionar(event);
        else if(lstAct == 2)
            subtrair(event);
        else if(lstAct == 3)
            multiplicar(event);
        else if(lstAct == 4)
            operacaoAnd(event);
        else if(lstAct == 5)
            operacaoOr(event);
        else if(lstAct == 6)
            operacaoXor(event);
        
    }

    @FXML
    void Truncar(ActionEvent event) {
        botaoNormal.setSelected(false);

        if(lstAct == 1)
            adicionar(event);
        else if(lstAct == 2)
            subtrair(event);
        else if(lstAct == 3)
            multiplicar(event);
        else if(lstAct == 4)
            operacaoAnd(event);
        else if(lstAct == 5)
            operacaoOr(event);
        else if(lstAct == 6)
            operacaoXor(event);
        
    }
    
    @FXML
    void exibirImgPrimaria(ActionEvent event) {
    	if(imagem.getImgPlus() != null)
    		imagem.getImgPlus().show();
    	
    	if(imagemFinal.getImage() == null) {
    		img = SwingFXUtils.toFXImage( imagem.getImg(), null);
    		
    		this.configurarImgFinal();
    	}
    		
    }

    @FXML
    void exibirImgSecundaria(ActionEvent event) {
    	if(imgSec.getImgPlus() != null)
    		imgSec.getImgPlus().show();
    }
    
    @FXML
    void botaoValor(ActionEvent event) {
        valorTransf = Float.valueOf( textoValor.getText() );
        popupValor.setVisible(false);

        if(lstAct == 7)
            cisX();
        else if(lstAct == 8)
            cisY();

        textoValor.clear();
    }
    
    @FXML
    void botaoRealceLinear(ActionEvent event) {
        valorMaximo = Integer.valueOf( valorMax.getText() );
        valorMinimo = Integer.valueOf( valorMin.getText() );
        MinMaxPane.setVisible(false);
        
        Realce.Linear(imgFinal, valorMinimo, valorMaximo, linearInverso);
        
        valorMax.clear();
        valorMin.clear();
    }
    
    @FXML
    void botaoControlGamma(ActionEvent event) {
        fatorC = Double.valueOf( valorFatorC.getText() );
        gamma = Double.valueOf( valorGamma.getText() );
        GammaPane.setVisible(false);
        
        Realce.corrigirGama(imgFinal, fatorC, gamma);
    }
    
    @FXML
    void botaoFatiarBits(ActionEvent event) {
        qntCamadas = Integer.valueOf( numCamadas.getText() );
        fatiarPanel.setVisible(false);
        
        Realce.fatiarBits(imgFinal, qntCamadas);
    }
    
    @FXML
    void limparPrimaria(ActionEvent event) {
        imagemIni.setImage(null);   
        botaoLimparIni.setDisable(true);
        botaoAbrirCamada.setDisable(true);
        menuTransformar.setDisable(true);
        menuOperacoes.setDisable(true);
        painelLateral.setVisible(false);
        menuPseudoCor.setDisable(true);
        menuCores.setDisable(true);
        botaoHistograma.setDisable(true);
        menuRealce.setDisable(true);
        /*
         * Se restar apenas a imagem primária na 
         * tela, desativará o menu de limpeza.
         */
        if( imagemSec.getImage() == null && imagemFinal.getImage() == null )
            menuLimpar.setDisable(true);
    }

    @FXML
    void limparSec(ActionEvent event) {
        imagemSec.setImage(null);
        botaoLimparSec.setDisable(true);
        menuOperacoes.setDisable(true);

        /*
         * Se restar apenas a imagem secundária 
         * na tela, desativará o menu de limpeza.
         */
        if( imagemIni.getImage() == null && imagemFinal.getImage() == null )
            menuLimpar.setDisable(true);
    }

    @FXML
    void limparFinal(ActionEvent event) {
        imagemFinal.setImage(null);
        botaoLimparFinal.setDisable(true);

        /*
         * Se restar apenas a imagem final na 
         * tela, desativará o menu de limpeza.
         */
        if( imagemIni.getImage() == null && imagemSec.getImage() == null )
            menuLimpar.setDisable(true);

        /*
         * Quando a imagem final for limpa, a 
         * proxima ação será feita em cima da 
         * imagem inicial. Isso é necessário 
         * caso o botão acumular esteja ativado.
         */
        imgFinal.setImg( imagem.getImg() );
        dragMode = false;
    }

    @FXML
    void limparTudo(ActionEvent event) {
        limparPrimaria(event);
        limparSec(event);
        limparFinal(event);
    }
    
    //método que setta a imagem final exibida com os atributos da imagem primária
    void configurarImgFinal() {
    	
    	imagemFinal.setFitHeight( imagem.getHeight() );
        imagemFinal.setFitWidth(  imagem.getWidth() );
        
        imagemFinal.setX( posXInit );
        imagemFinal.setY( posYInit );
        
        //imagem exibida na área de trabalho do software
        imagemFinal.setImage(img);
        
        //habilita os botões da interface caso a imagem abra com sucesso
        botaoAbrirCamada.setDisable(false);
        menuTransformar.setDisable(false);
        menuLimpar.setDisable(false);
        botaoLimparIni.setDisable(false);
        painelLateral.setVisible(true);
        menuPseudoCor.setDisable(false);
        menuCores.setDisable(false);
        botaoHistograma.setDisable(false);
        menuRealce.setDisable(false);
        dragMode = false;
        
    }
    
    @FXML
    void gerarHistograma(ActionEvent event) {
        imgFinal.getImgPlus().plotHistogram();
    }

    @FXML
    void salvar(ActionEvent event) {
        
        FileSaver fSaver = new FileSaver( imagem.getImgPlus() );
        fSaver.save();
    }

    @FXML
    void salvarComo(ActionEvent event) {
        int response = fileChooser.showSaveDialog(null); 
       
        if(response == JFileChooser.APPROVE_OPTION) {
            FileSaver fSaver = new FileSaver( imgFinal.getImgPlus());
            fSaver.saveAsPng(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    @FXML
    void Sair(ActionEvent event) {
        Main.fechar();
    }
}
