package src;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.swing.JFileChooser;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;

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
    private Button botaoRedo;
    
    @FXML
    private Button botaoUndo;

    @FXML
    private ToggleButton botaoSelMagica;
    
    @FXML
    private ToggleButton botaoLapis;
    
    @FXML
    private ToggleButton botaoSelWatershed;
    
    @FXML
    private ToggleButton botaoApagar;
    
    @FXML
    private ToggleButton botaoCortar;
	
	@FXML
    private HTMLEditor htmlEditor;
	
    @FXML
    private Text mousePosText;
    
    @FXML
    private Text rgbText;
    
    @FXML
    private TextField numCamadas;
    
    @FXML
    private TextField valorN;
    
    @FXML
    private TextField valorK;

    @FXML
    private TextField urlText;

    @FXML
    private ImageView imagemIni;

    @FXML
    private Menu menuOperacoes;
    
    @FXML
    private Menu menuEstegano;
    
    @FXML
    private Menu menuRealce;

    @FXML
    private Menu menuTransformar;

    @FXML
    private Menu menuFiltragem;

    @FXML
    private Menu menuSegmentacao;
    
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
    private Pane painelFerramentas;
    
    @FXML
    private Pane NiblackPane;
    
    @FXML
    private ScrollPane paneHTML;
    
    @FXML
    private MenuItem botaoHistograma;

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
    private TextField textoTol;
    
    @FXML
    private TextField textoAmplificar;
    
    @FXML
    private Slider rotacaoSlide;
    
    @FXML
    private Menu menuPseudoCor;
    
    @FXML
    private Slider tamXSlide;
    
    @FXML
    private Pane paneSaturation;
    
    @FXML
    private ColorPicker selecCor;
    
    @FXML
    private ImageView saturationArrow;
    
    @FXML
    private ImageView brightnessArrow;
    
    @FXML
    private ImageView hueArrow;
    
    @FXML
    private Pane paneBrightness;
    
    @FXML
    private Pane panCoord;
    
    @FXML
    private ImageView dicaCoord;
   
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
    private TextField textoT;
    
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
    
    @FXML
    private Pane popupT;
    
    @FXML
    private Pane popupAmplificacao;

    private Processador imagem = new Processador();
    private Processador imgSec = new Processador();
    private Image img;
    
    //lista com as alterações feitas para permitir desfazer uma ação
    private List<BufferedImage> alteracoes = new ArrayList<BufferedImage>();
    
    //lista com as alterações feitas para permitir refazer uma ação
    private List<BufferedImage> refazer = new ArrayList<BufferedImage>();
    
    private boolean selecRegiao = false;
    private boolean selecWatershed = false;
    private boolean limparPixels = false;
    private boolean corteInit = false;
    private boolean corteFinal = false;
    private boolean lapisSelecionado = false;
    private double [] posicoesLapis = new double [4];
    private int rgb_from_ColorPicker;
    
    //posições usadas para corte
    private List<Integer> posCorte = new ArrayList<Integer>();
    
    //posição do mouse na imagem
    private double mouseX;
    private double mouseY;
    
    //posição do mouse na tela
    private double mouseXcena;
    private double mouseYcena;
    
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

        if( imagem.carregarImg() ){
            imgFinal.setImg( imagem.getImg() );
            
            img = SwingFXUtils.toFXImage(imagem.getImg(), null);
                
            //preview da imagem
            imagemIni.setImage(img);
                
            imagemIni.setX( 160.0 );
            imagemIni.setY( 10.0 );
            imagemIni.setFitHeight( 50.0 );
            imagemIni.setFitWidth(  50.0 );
            
            //configura o menu inicial 
            this.configurarMenu();

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
        
        if( imgSec.carregarImg() ){
            img = SwingFXUtils.toFXImage(imgSec.getImg(), null);
            
            imagemSec.setImage(img);
                
            imagemSec.setFitHeight( 50.0 );
            imagemSec.setFitWidth( 50.0 );
                
            imagemSec.setX( 160.0 );
            imagemSec.setY( 70.0 );

            //habilita os botões da interface caso a imagem abra com sucesso
            menuOperacoes.setDisable(false);
            botaoLimparSec.setDisable(false);
        
            colarImg(event);
        }
        
    }

    //--------------------------------------------------------------
    //Métodos de operação aritmética

    @FXML
    void adicionar(ActionEvent event) {
        alteracao();

        if( botaoAcc.isSelected() )
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.addNormal(imgFinal, imgSec) : Operacoes.addTrunc(imgFinal, imgSec) );       
        else
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.addNormal(imagem, imgSec) : Operacoes.addTrunc(imagem, imgSec) );

        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);
            
        imagemFinal.setImage(img);

        lstAct = 1;

    }

    @FXML
    void subtrair(ActionEvent event) {
        alteracao();

        if( botaoAcc.isSelected() )
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.subNormal(imgFinal, imgSec) : Operacoes.subTrunc(imgFinal, imgSec) );       
        else
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.subNormal(imagem, imgSec) : Operacoes.subTrunc(imagem, imgSec) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
  
        lstAct = 2;

    }

    @FXML
    void multiplicar(ActionEvent event) {
        alteracao();

        if( botaoAcc.isSelected() )
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.multiNormal(imgFinal, imgSec) : Operacoes.multiTrunc(imgFinal, imgSec) );       
        else
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.multiNormal(imagem, imgSec) : Operacoes.multiTrunc(imagem, imgSec) );

        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

        lstAct = 3;

    }

    @FXML
    void dividir(ActionEvent event) {
        alteracao();

        if( botaoAcc.isSelected() )
            imgFinal.setImg( Operacoes.divisao(imgFinal, imgSec) );      
        else
            imgFinal.setImg( Operacoes.divisao(imagem, imgSec) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

        lstAct = 0;
       

    }

    @FXML
    void operacaoAnd(ActionEvent event) {
        alteracao();

        if( botaoAcc.isSelected() )
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.andNormal(imgFinal, imgSec) : Operacoes.andTrunc(imgFinal, imgSec) );       
        else
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.andNormal(imagem, imgSec) : Operacoes.andTrunc(imagem, imgSec) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

        lstAct = 4;

    }

    @FXML
    void operacaoOr(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.orNormal(imgFinal, imgSec) : Operacoes.orTrunc(imgFinal, imgSec) );       
        else
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.orNormal(imagem, imgSec) : Operacoes.orTrunc(imagem, imgSec) );

        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
        lstAct = 5;

    }

    @FXML
    void operacaoXor(ActionEvent event) {
        alteracao();

        if( botaoAcc.isSelected() )
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.xorNormal(imgFinal, imgSec) : Operacoes.xorTrunc(imgFinal, imgSec) );       
        else
            imgFinal.setImg( botaoNormal.isSelected() ? 
                Operacoes.xorNormal(imagem, imgSec) : Operacoes.xorTrunc(imagem, imgSec) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

        lstAct = 6;

    }

    @FXML
    void colarImg(ActionEvent event) {
        alteracao();

        if( botaoAcc.isSelected() )
            Operacoes.colarImg(imgFinal, imgSec);      
        else
            Operacoes.colarImg(imagem, imgSec);
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
    }
    
    void pintarPixel( int rgb ) {
        alteracao();

        if( botaoAcc.isSelected() )
            imgFinal.setImg( Operacoes.pintarPixel(imgFinal, posicoesLapis, rgb) );      
        else
            imgFinal.setImg( Operacoes.pintarPixel(imagem, posicoesLapis, rgb) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
    }
    
    //--------------------------------------------------------------
    //Métodos de transformação geométrica

    @FXML
    void espelhoX(ActionEvent event) {
        alteracao();

        if( botaoAcc.isSelected() )
            imgFinal.setImg( Transformacoes.horizontalMirror( imgFinal.getImg() ) );    
        else
            imgFinal.setImg( Transformacoes.horizontalMirror( imagem.getImg() ) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
    }

    @FXML
    void espelhoY(ActionEvent event) {
        alteracao();

        if( botaoAcc.isSelected() )
            imgFinal.setImg( Transformacoes.verticalMirror( imgFinal.getImg() ) );    
        else
            imgFinal.setImg( Transformacoes.verticalMirror( imagem.getImg() ) );

        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
       
    }
    
    @FXML
    void cisalharX(ActionEvent event) {
        popupValor.setVisible(true);
        lstAct = 7;
    }

    void cisX(){
        alteracao();

        if( botaoAcc.isSelected() )
            imgFinal.setImg( Transformacoes.cisalhamentoX( imgFinal.getImg(), valorTransf ) );    
        else
            imgFinal.setImg( Transformacoes.cisalhamentoX( imagem.getImg() , valorTransf ) );

        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
        /**
         * Se acumular estiver selecionado, 
         * o tamanho será o definido nos sliders 
         * de redimensionamento
         **/
        redimensionar();
        
    }

    @FXML
    void cisalharY(ActionEvent event) {
        popupValor.setVisible(true);
        lstAct = 8;
    }

    void cisY(){
        alteracao();

        if( botaoAcc.isSelected() )
            imgFinal.setImg( Transformacoes.cisalhamentoY( imgFinal.getImg(), valorTransf ) );    
        else
            imgFinal.setImg( Transformacoes.cisalhamentoY( imagem.getImg() , valorTransf ) );

        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
        /**
         * Se acumular estiver selecionado, 
         * o tamanho será o definido nos sliders 
         * de redimensionamento
         **/
        redimensionar();
        
    }

    @FXML
    void transladeX(ActionEvent event) {
        imagemFinal.setLayoutX( Double.valueOf(posXText.getText()) );
    	
    	lstAct = 0;
    }

    @FXML
    void transladeY(ActionEvent event) {
        imagemFinal.setLayoutY( Double.valueOf(posYText.getText()) );
    	
    	lstAct = 0;
    }
    
    void redimensionarX() {
    	imagemFinal.setFitWidth( imgFinal.getWidth() * tamXSlide.getValue() );
    	imgFinal.setImg( imagemFinal.getImage());
    	
    	lstAct = 0;
    }
    
    void redimensionarY() {
    	imagemFinal.setFitHeight( imgFinal.getHeight() * tamYSlide.getValue() );
    	imgFinal.setImg( imagemFinal.getImage());
    	
    	lstAct = 0;
    }
 
    void rotacionar() {
    	imagemFinal.setRotate( rotacaoSlide.getValue() );
    	imgFinal.setImg( imagemFinal.getImage() );
    	
    	lstAct = 0;
    }
    
    void cortarImagem() {
        alteracao();

        if( botaoAcc.isSelected() )
            imgFinal.setImg( Transformacoes.cortarImg(imgFinal.getImg(), posCorte) );    
        else
            imgFinal.setImg( Transformacoes.cortarImg(imgFinal.getImg(), posCorte) );

        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
        /**
         * Se acumular estiver selecionado, 
         * o tamanho será o definido nos sliders 
         * de redimensionamento
         **/
        redimensionar();
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
        alteracao();
        
    	if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.grayScale(imgFinal) );    
        else
            imgFinal.setImg( Cores.grayScale(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void negativo(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.negativo(imgFinal) );    
        else
            imgFinal.setImg( Cores.negativo(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    //--------------------------------------------------------------
    //Pseudocoloração
    
    @FXML
    void PseudoRGB(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorGrayScale(imgFinal, 1) );    
        else
            imgFinal.setImg( Cores.pseudoColorGrayScale(imagem, 1) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }	

    @FXML
    void PseudoRBG(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorGrayScale(imgFinal, 2) );    
        else
            imgFinal.setImg( Cores.pseudoColorGrayScale(imagem, 2) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }

    @FXML
    void PseudoBRG(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorGrayScale(imgFinal, 3) );    
        else
            imgFinal.setImg( Cores.pseudoColorGrayScale(imagem, 3) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }

    @FXML
    void PseudoBGR(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorGrayScale(imgFinal, 4) );    
        else
            imgFinal.setImg( Cores.pseudoColorGrayScale(imagem, 4) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }

    @FXML
    void PseudoGRB(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorGrayScale(imgFinal, 5) );    
        else
            imgFinal.setImg( Cores.pseudoColorGrayScale(imagem, 5) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }

    @FXML
    void PseudoGBR(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorGrayScale(imgFinal, 6) );    
        else
            imgFinal.setImg( Cores.pseudoColorGrayScale(imagem, 6) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    //Ignorar uma cor

    @FXML
    void IgnoreBlue(ActionEvent event) {
        alteracao();
        
    	if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorIgnoring(imgFinal, 1) );    
        else
            imgFinal.setImg( Cores.pseudoColorIgnoring(imagem, 1) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void IgnoreRed(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorIgnoring(imgFinal, 2) );    
        else
            imgFinal.setImg( Cores.pseudoColorIgnoring(imagem, 2) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void IgnoreGreen(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorIgnoring(imgFinal, 3) );    
        else
            imgFinal.setImg( Cores.pseudoColorIgnoring(imagem, 3) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    //Aumentar uma cor
    
    @FXML
    void IncBlue(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorIncreasing(imgFinal, 1) );    
        else
            imgFinal.setImg( Cores.pseudoColorIncreasing(imagem, 1) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void IncRed(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorIncreasing(imgFinal, 2) );    
        else
            imgFinal.setImg( Cores.pseudoColorIncreasing(imagem, 2) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void IncGreen(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorIncreasing(imgFinal, 3) );    
        else
            imgFinal.setImg( Cores.pseudoColorIncreasing(imagem, 3) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }

    //Reduzir uma cor
    @FXML
    void ReduBlue(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorReducing(imgFinal, 1) );    
        else
            imgFinal.setImg( Cores.pseudoColorReducing(imagem, 1) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void ReduRed(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorReducing(imgFinal, 2) );    
        else
            imgFinal.setImg( Cores.pseudoColorReducing(imagem, 2) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void ReduGreen(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Cores.pseudoColorReducing(imgFinal, 3) );    
        else
            imgFinal.setImg( Cores.pseudoColorReducing(imagem, 3) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

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
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Realce.NaoLinear(imgFinal, 1) );    
        else
            imgFinal.setImg( Realce.NaoLinear(imagem, 1) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
    }

    @FXML
    void RealceQuad(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Realce.NaoLinear(imgFinal, 3) );    
        else
            imgFinal.setImg( Realce.NaoLinear(imagem, 3) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
    }
    

    @FXML
    void RealceExp(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Realce.NaoLinear(imgFinal, 2) );    
        else
            imgFinal.setImg( Realce.NaoLinear(imagem, 2) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
    }

    @FXML
    void RealceRaiz(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Realce.NaoLinear(imgFinal, 4) );    
        else
            imgFinal.setImg( Realce.NaoLinear(imagem, 4) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
    }
    
    @FXML
    void equalHisto(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Realce.EqualizarHistoGrama(imgFinal.getImg()) );    
        else
            imgFinal.setImg( Realce.EqualizarHistoGrama(imagem.getImg()) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
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
    //Filtragem
    
    @FXML
    void media3x3(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.media3x3(imgFinal) );    
        else
            imgFinal.setImg( Filtros.media3x3(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }

    @FXML
    void media5x5(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.media5x5(imgFinal) );    
        else
            imgFinal.setImg( Filtros.media5x5(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }

    @FXML
    void mediana3x3(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.mediana3x3(imgFinal) );    
        else
            imgFinal.setImg( Filtros.mediana3x3(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }

    @FXML
    void mediana5x5(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.mediana5x5(imgFinal) );    
        else
            imgFinal.setImg( Filtros.mediana5x5(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void maximo(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.maximo(imgFinal) );    
        else
            imgFinal.setImg( Filtros.maximo(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }

    @FXML
    void minimo(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.minimo(imgFinal) );    
        else
            imgFinal.setImg( Filtros.minimo(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void moda(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.moda(imgFinal) );    
        else
            imgFinal.setImg( Filtros.moda(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void kuwahara(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.kuwahara(imgFinal) );    
        else
            imgFinal.setImg( Filtros.kuwahara(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void tomitaTsuji(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.tomitaTsuji(imgFinal) );    
        else
            imgFinal.setImg( Filtros.tomitaTsuji(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void nagaoMatsuyama(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.nagaoMatsuyama(imgFinal) );    
        else
            imgFinal.setImg( Filtros.nagaoMatsuyama(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void somboonkaew(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.somboonkaew(imgFinal) );    
        else
            imgFinal.setImg( Filtros.somboonkaew(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void h1(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.h1(imgFinal) );    
        else
            imgFinal.setImg( Filtros.h1(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void h2(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.h2(imgFinal) );    
        else
            imgFinal.setImg( Filtros.h2(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void m1(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.m1(imgFinal) );    
        else
            imgFinal.setImg( Filtros.m1(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void m2(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.m2(imgFinal) );    
        else
            imgFinal.setImg( Filtros.m2(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void m3(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.m3(imgFinal) );    
        else
            imgFinal.setImg( Filtros.m3(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void highBoost(ActionEvent event) {
        popupAmplificacao.setVisible(true);
    }
    
    @FXML
    void ht2x2(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.ht2x2(imgFinal) );    
        else
            imgFinal.setImg( Filtros.ht2x2(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void ht3x2(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.ht3x2(imgFinal) );    
        else
            imgFinal.setImg( Filtros.ht3x2(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void ht3x3(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.ht3x3(imgFinal) );    
        else
            imgFinal.setImg( Filtros.ht3x3(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

    }
    
    @FXML
    void floydSteinberg(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.floydSteinberg(imgFinal) );    
        else
            imgFinal.setImg( Filtros.floydSteinberg(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void rogers(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.rogers(imgFinal) );    
        else
            imgFinal.setImg( Filtros.rogers(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void jarvisJudiceNinke(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.jarvisJudiceNinke(imgFinal) );    
        else
            imgFinal.setImg( Filtros.jarvisJudiceNinke(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void stucki(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.stucki(imgFinal) );    
        else
            imgFinal.setImg( Filtros.stucki(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void stevensonArce(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.stevensonArce(imgFinal) );    
        else
            imgFinal.setImg( Filtros.stevensonArce(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }

    //--------------------------------------------------------------
    //Segmentação
    
    @FXML
    void detPonto(ActionEvent event) {
        popupT.setVisible(true);
    }
    
    @FXML
    void detRetaHori(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.detRetaHori(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.detRetaHori(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void detRetaVert(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.detRetaVert(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.detRetaVert(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void detReta45(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.detReta45(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.detReta45(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void detReta135(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.detReta135(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.detReta135(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void roberts(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.roberts(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.roberts(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void robertsCruz(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.robertsCruz(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.robertsCruz(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void prewittMag(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.prewittMag(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.prewittMag(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void prewittGX(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.prewittGX(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.prewittGX(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void prewittGY(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.prewittGY(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.prewittGY(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void sobelGX(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.sobelGX(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.sobelGX(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void sobelGY(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.sobelGY(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.sobelGY(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void sobelMag(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.sobelMag(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.sobelMag(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void kirsh(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.kirsh(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.kirsh(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void robison(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.robison(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.robison(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void freiChen(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.freiChen(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.freiChen(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void laplacianoH1(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.laplacianoH1(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.laplacianoH1(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void laplacianoH2(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.laplacianoH2(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.laplacianoH2(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void limiarGlobal(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.limiarGlobal(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.limiarGlobal(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void limiarMedia(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.limiarMedia(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.limiarMedia(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void limiarMin(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.limiarMin(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.limiarMin(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void limiarMax(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.limiarMax(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.limiarMax(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void limiarMinMax(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.limiarMinMax(imgFinal) );    
        else
            imgFinal.setImg( Segmentacao.limiarMinMax(imagem) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    @FXML
    void limiarNiblack(ActionEvent event) {
        NiblackPane.setVisible(true);
    }
    
    void selecRegiao(int x, int y) {
        alteracao();
        
        int tol = Integer.valueOf( textoTol.getText() );
        
        if(selecWatershed) {
            if( botaoAcc.isSelected() )
                imgFinal.setImg( Segmentacao.watershed(imgFinal, x, y, tol, rgb_from_ColorPicker ));    
            else
                imgFinal.setImg( Segmentacao.watershed(imagem, x, y, tol, rgb_from_ColorPicker ));  
        }else {
            if( botaoAcc.isSelected() )
                imgFinal.setImg( Segmentacao.selecRegiao(imgFinal, x, y, tol, rgb_from_ColorPicker ));    
            else
                imgFinal.setImg( Segmentacao.selecRegiao(imagem, x, y, tol, rgb_from_ColorPicker ));
        }
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    void limparPixels(int x, int y) {
        alteracao();
        
        int tol = Integer.valueOf( textoTol.getText() );
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.limparPixels(imgFinal, x, y, tol) );    
        else
            imgFinal.setImg( Segmentacao.limparPixels(imagem, x, y, tol) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);


    }
    
    //--------------------------------------------------------------
    //Mapeamento de bits
    
    @FXML
    void esteganografia(ActionEvent event) {
        htmlEditor.setHtmlText( BitMapping.esteganalise(imgFinal) );
        paneHTML.setVisible(true);
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
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        //detecção da cor selecionada no ColorPicker
        selecCor.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Color c = selecCor.getValue();
                rgb_from_ColorPicker = Processador.getRGB((float)c.getRed(), (float)c.getGreen(), (float)c.getBlue());
            }
        });
        
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
    	    double x = (( mouseEvent.getX() - imagemFinal.getX() ) / tamXSlide.getValue() );
            double y = (( mouseEvent.getY() - imagemFinal.getY() ) / tamYSlide.getValue() );
            
            mouseX = mouseEvent.getX();
            mouseY = mouseEvent.getY();
    	    
    	    if( selecRegiao ) {
    		    selecRegiao((int)x, (int)y);
    		}
    	    else if(limparPixels){
    	        limparPixels((int)x, (int)y);
            }
    	    else if(corteInit){
    	        posCorte.add( (int)x );
    	        posCorte.add( (int)y );
                corteInit = false;
                corteFinal = true;
            }
    	    else if(corteFinal){
    	        posCorte.add( (int)x );
                posCorte.add( (int)y );
                
                cortarImagem();
    	        
                botaoCortar.setStyle( "-fx-background-color:  #666" );
                corteFinal = false;
    	        posCorte.clear();
            }
    	    else if( !lapisSelecionado ){
                dragMode = !dragMode;
            }
    	});
    	
    	imagemFinal.setOnMousePressed( mouseEvent -> {
    	    double x = (( mouseEvent.getX() - imagemFinal.getX() ) / tamXSlide.getValue() );
            double y = (( mouseEvent.getY() - imagemFinal.getY() ) / tamYSlide.getValue() );
    	    
    	    posicoesLapis[0] = x; //x inicial
            posicoesLapis[1] = y; //y inicial
    	});
    	
    	imagemFinal.setOnMouseDragged( mouseEvent -> { 
    	    double x = (( mouseEvent.getX() - imagemFinal.getX() ) / tamXSlide.getValue() );
            double y = (( mouseEvent.getY() - imagemFinal.getY() ) / tamYSlide.getValue() );
            
            posicoesLapis[2] = x; //x final
            posicoesLapis[3] = y; //y final
            
            if( lapisSelecionado ) {
                pintarPixel(rgb_from_ColorPicker);//transparente (temporário)
            }
            
            posicoesLapis[0] = x; //x inicial
            posicoesLapis[1] = y; //y inicial
    	});
    	
    	//captura o rgb na posição que o mouse está na imagem
    	imagemFinal.setOnMouseMoved( mouseEvent -> {
    		//não altera quando está movendo a imagem
    		if(!dragMode && imgFinal.getImg() != null) {
    			int x = (int)(((int)(mouseEvent.getX() - (int)imagemFinal.getX()))/tamXSlide.getValue());
    			int y = (int)(((int)(mouseEvent.getY() - (int)imagemFinal.getY()))/tamYSlide.getValue());
    			
    			if(x >= (int)imagemFinal.getFitWidth()) {
    			    x = (int)imagemFinal.getFitWidth() - 1;
    			}else if(x < 0) {
    			    x = 0;
    			}
    			
    			if(y >= (int)imagemFinal.getFitHeight()) {
                    y = (int)imagemFinal.getFitHeight() - 1;
                }else if(y < 0) {
                    y = 0;
                }
    			
    			int R = imgFinal.nivelRed(x, y);
    			int G = imgFinal.nivelGreen(x, y);
    			int B = imgFinal.nivelBlue(x, y);
    			
    			rgbText.setText( "RGB( " + R + ", " + G + ", " + B + " )" );
    			
    			hueArrow.setLayoutX( (( Cores.Hue(R, G, B) ) / 360.0) * 200.0 );//( valor / 360 ) * 200px
    			
    			paneBrightness.setStyle("-fx-background-color: " + matiz.getHex((int)hueArrow.getLayoutX(), 10) );
                paneSaturation.setStyle("-fx-background-color: " + matiz.getHex((int)hueArrow.getLayoutX(), 10) );
    			saturationArrow.setLayoutX( Cores.Saturation(R, G, B) * 200.0 ); //( valor / 255 ) * 200px
    			brightnessArrow.setLayoutX( (( Cores.Brightness(R, G, B) ) / 255.0) * 200.0 ); //( valor / 255 ) * 200px
    		}
    	});
    	
    	//captura posição do mouse
    	workspace.setOnMouseMoved( mouseEvent ->{
    	    
    	    //esconde paineis de dica
    	    panCoord.setVisible( false );
    	    
    	    mouseXcena = mouseEvent.getX();
            mouseYcena = mouseEvent.getY();
            
            double x = mouseEvent.getX() - mouseX;
    		double y = mouseEvent.getY() - mouseY;
    		
    		mousePosText.setText(  (int)mouseXcena + ", " + (int)mouseYcena + "px" );
    		
    		if( dragMode ) {
    			imagemFinal.setLayoutX( x );
        		imagemFinal.setLayoutY( y );
        		
        		//atualiza os valores nos textos de posição
        		posXText.setText( Double.toString( imagemFinal.getLayoutX() ));
        		posYText.setText( Double.toString( imagemFinal.getLayoutY() ));
    		}
    	});
    	
    	//limpa popups da tela
    	workspace.setOnMouseClicked( mouseEvent ->{
    	    popupAmplificacao.setVisible(false);
    	    popupValor.setVisible(false);
    	    MinMaxPane.setVisible(false);
    	    GammaPane.setVisible(false);
    	    fatiarPanel.setVisible(false);
    	    popupT.setVisible(false);
    	    NiblackPane.setVisible(false);
    	    
    	});
    	
    	//altera estilo dos botões toggle na barra lateral
    	botaoSelMagica.setOnMouseClicked( mouseEvent ->{
    	    if(botaoSelMagica.isSelected()) {
    	        
    	        //---------------------------------------------------------
    	        //desativa outros botões
    	        if(botaoApagar.isSelected()) {
                    botaoApagar.setStyle( "-fx-background-color:  #666" );
                    limparPixels = false;
                }
    	        if(botaoCortar.isSelected()) {
                    botaoCortar.setStyle( "-fx-background-color:  #666" );
                    corteInit = false;
                    corteFinal = false;
                }
    	        if(botaoLapis.isSelected()) {
                    botaoLapis.setStyle( "-fx-background-color:  #666" );
                    lapisSelecionado = false;
                }
    	        //---------------------------------------------------------
    	        
    	        botaoSelMagica.setStyle( "-fx-border-color:  #999; -fx-background-color:  #555" );
    	        selecRegiao = true;
    	        botaoSelWatershed.setDisable(false);
    	    }
    	    else {
    	        botaoSelMagica.setStyle( "-fx-background-color:  #666" );
    	        selecRegiao = false;
    	        botaoSelWatershed.setSelected(false);
    	        botaoSelWatershed.setDisable(true);
    	    }
    	    
    	});
    	
    	//altera estilo do botão watershed na barra lateral
        botaoSelWatershed.setOnMouseClicked( mouseEvent ->{
            if(botaoSelWatershed.isSelected()) {    
                botaoSelWatershed.setStyle( "-fx-border-color:  #999; -fx-background-color:  #555" );
                selecWatershed = true;
            }
            else {
                botaoSelWatershed.setStyle( "-fx-border-color:  #999; -fx-background-color:  #777" );
                selecWatershed = false;
            }
            
        });
    	
    	//altera estilo dos botões toggle na barra lateral
        botaoApagar.setOnMouseClicked( mouseEvent ->{
            if(botaoApagar.isSelected()) {
                
                //---------------------------------------------------------
                //desativa outros botões                
                if(botaoSelMagica.isSelected()) {
                    botaoSelMagica.setStyle( "-fx-background-color:  #666" );
                    selecRegiao = false;
                }
                if(botaoCortar.isSelected()) {
                    botaoCortar.setStyle( "-fx-background-color:  #666" );
                    corteInit = false;
                    corteFinal = false;
                }
                if(botaoLapis.isSelected()) {
                    botaoLapis.setStyle( "-fx-background-color:  #666" );
                    lapisSelecionado = false;
                }
                //---------------------------------------------------------
                                
                botaoApagar.setStyle( "-fx-border-color:  #999; -fx-background-color:  #555" );
                limparPixels = true;
            }
            else {
                botaoApagar.setStyle( "-fx-background-color:  #666" );
                limparPixels = false;
            }
            
        });
        
        //altera estilo dos botões toggle na barra lateral
        botaoCortar.setOnMouseClicked( mouseEvent ->{
            if(botaoCortar.isSelected()) {

                //---------------------------------------------------------
                //desativa outros botões                
                if(botaoSelMagica.isSelected()) {
                    botaoSelMagica.setStyle( "-fx-background-color:  #666" );
                    selecRegiao = false;
                }
                if(botaoApagar.isSelected()) {
                    botaoApagar.setStyle( "-fx-background-color:  #666" );
                    limparPixels = false;
                }
                if(botaoLapis.isSelected()) {
                    botaoLapis.setStyle( "-fx-background-color:  #666" );
                    lapisSelecionado = false;
                }
                //---------------------------------------------------------
                
                botaoCortar.setStyle( "-fx-border-color:  #999; -fx-background-color:  #555" );
                corteInit = true;
            }
            else {
                botaoCortar.setStyle( "-fx-background-color:  #666" );
                corteInit = false;
                corteFinal = false;
            }
            
        });
        
        //altera estilo dos botões toggle na barra lateral
        botaoLapis.setOnMouseClicked( mouseEvent ->{
            if(botaoLapis.isSelected()) {

                //---------------------------------------------------------
                //desativa outros botões                
                if(botaoSelMagica.isSelected()) {
                    botaoSelMagica.setStyle( "-fx-background-color:  #666" );
                    selecRegiao = false;
                }
                if(botaoApagar.isSelected()) {
                    botaoApagar.setStyle( "-fx-background-color:  #666" );
                    limparPixels = false;
                }
                if(botaoLapis.isSelected()) {
                    botaoLapis.setStyle( "-fx-background-color:  #666" );
                    lapisSelecionado = false;
                }
                //---------------------------------------------------------
                
                botaoLapis.setStyle( "-fx-border-color:  #999; -fx-background-color:  #555" );
                lapisSelecionado = true;
            }
            else {
                botaoLapis.setStyle( "-fx-background-color:  #666" );
                lapisSelecionado = false;
            }
            
        });
        
        //exibe painel de dica do corte
        dicaCoord.setOnMouseMoved(mouseEvent ->{
            panCoord.setVisible(true);
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
    	
    	if(imagemFinal.getImage() == null) {
    		img = SwingFXUtils.toFXImage( imagem.getImg(), null);
    		
    		this.configurarImagemFinal();
    	}
    	else if(imagem.getImgPlus() != null)
            imagem.getImgPlus().show();
    		
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
    void botaoAmplificar(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Filtros.highBoost(imgFinal, Float.valueOf( textoAmplificar.getText() )));    
        else
            imgFinal.setImg( Filtros.highBoost(imagem, Float.valueOf( textoAmplificar.getText() )));
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
        popupAmplificacao.setVisible(false);
        textoAmplificar.setText("1");
        
    }
    
    @FXML
    void botaoNiblack(ActionEvent event) {
        alteracao();
        
        float k = Float.valueOf( valorK.getText() );
        int n = Integer.valueOf( valorN.getText() );
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.limiarNiblack(imgFinal, k, n));    
        else
            imgFinal.setImg( Segmentacao.limiarNiblack(imagem, k, n));
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
        NiblackPane.setVisible(false);
        valorK.setText("-0.2");
        valorN.setText("15");
        
    }
    
    @FXML
    void botaoT(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Segmentacao.detPonto(imgFinal, Double.valueOf(textoT.getText())) );    
        else
            imgFinal.setImg( Segmentacao.detPonto(imagem, Double.valueOf(textoT.getText())) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);

        popupT.setVisible(false);
        textoT.setText("");
        
    }
    
    @FXML
    void botaoRealceLinear(ActionEvent event) {
        alteracao();
        
        valorMaximo = Integer.valueOf( valorMax.getText() );
        valorMinimo = Integer.valueOf( valorMin.getText() );
        MinMaxPane.setVisible(false);
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Realce.Linear(imgFinal, valorMinimo, valorMaximo, linearInverso) );    
        else
            imgFinal.setImg( Realce.Linear(imagem, valorMinimo, valorMaximo, linearInverso) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
        valorMax.clear();
        valorMin.clear();
        
    }
    
    @FXML
    void botaoControlGamma(ActionEvent event) {
        alteracao();
        
        fatorC = Double.valueOf( valorFatorC.getText() );
        gamma = Double.valueOf( valorGamma.getText() );
        GammaPane.setVisible(false);
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( Realce.corrigirGama(imgFinal, fatorC, gamma) );    
        else
            imgFinal.setImg( Realce.corrigirGama(imagem, fatorC, gamma) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        
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
        painelFerramentas.setVisible(false);
        menuPseudoCor.setDisable(true);
        menuCores.setDisable(true);
        botaoHistograma.setDisable(true);
        menuRealce.setDisable(true);
        menuEstegano.setDisable(true);
        menuFiltragem.setDisable(true);
        botaoSalvar.setDisable(true);
        menuSegmentacao.setDisable(true);
        
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
        
        configurarImagemFinal();
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
    
    //método que configura o menu no momento em que abre a imagm primária
    void configurarMenu() {
        
        configurarImagemFinal();
        
        //habilita os botões da interface caso a imagem abra com sucesso
        botaoAbrirCamada.setDisable(false);
        menuTransformar.setDisable(false);
        menuLimpar.setDisable(false);
        botaoLimparIni.setDisable(false);
        painelLateral.setVisible(true);
        painelFerramentas.setVisible(true);
        menuPseudoCor.setDisable(false);
        menuCores.setDisable(false);
        botaoHistograma.setDisable(false);
        menuRealce.setDisable(false);
        menuFiltragem.setDisable(false);
        menuEstegano.setDisable(false);
        botaoSalvar.setDisable(false);
        menuSegmentacao.setDisable(false);
        botaoUndo.setDisable(true);
        botaoRedo.setDisable(true);
        
        Color c = selecCor.getValue();
        rgb_from_ColorPicker = Processador.getRGB((float)c.getRed(), (float)c.getGreen(), (float)c.getBlue());
        
        dragMode = false;
        
    }
    
    //método que setta a imagem final exibida com os atributos da imagem primária
    void configurarImagemFinal() {
        
        if( imagemSec.getImage() != null) {
            colarImg(null);
        }
        else {
            img = null;
            img = SwingFXUtils.toFXImage(imagem.getImg(), null);
            
            imagemFinal.setFitHeight( imagem.getHeight() * tamYSlide.getValue() );
            imagemFinal.setFitWidth(  imagem.getWidth() * tamXSlide.getValue() );
            
            //imagem exibida na área de trabalho do software
            imagemFinal.setImage(img);
        }
        
        botaoLimparFinal.setDisable(false);
        
    }
    
    @FXML
    void undo(ActionEvent event) {
        if(alteracoes.size() > 0) {
            BufferedImage tmp = alteracoes.remove( (alteracoes.size() - 1) );
            
            refazer.add( imgFinal.getImg() );
            botaoRedo.setDisable(false);
            
            if(tmp != null) {
                imgFinal.setImg( tmp );
                img = null;
                img = SwingFXUtils.toFXImage(tmp, null);
                
                imagemFinal.setFitHeight( tmp.getHeight() * tamYSlide.getValue() );
                imagemFinal.setFitWidth(  tmp.getWidth() * tamXSlide.getValue() );
                
                //imagem exibida na área de trabalho do software
                imagemFinal.setImage(img);
            } 
        }
        
        //verifica se esvaziou a lista para desabilitar o botão
        if(alteracoes.size() == 0) {
            botaoUndo.setDisable(true);
        }
        
        lstAct = 0;
    }
    
    @FXML
    void redo(ActionEvent event) {
        if(refazer.size() > 0) {
            BufferedImage tmp = refazer.remove( (refazer.size() - 1) );
            
            alteracoes.add( imgFinal.getImg() );
            botaoUndo.setDisable(false);
            
            if(tmp != null) {
                imgFinal.setImg( tmp );
                img = null;
                img = SwingFXUtils.toFXImage(tmp, null);
                
                imagemFinal.setFitHeight( tmp.getHeight() * tamYSlide.getValue() );
                imagemFinal.setFitWidth(  tmp.getWidth() * tamXSlide.getValue() );
                
                //imagem exibida na área de trabalho do software
                imagemFinal.setImage(img);
            } 
        }
        
        //verifica se esvaziou a lista para desabilitar o botão
        if(refazer.size() == 0) {
            botaoRedo.setDisable(true);
        }
        
        lstAct = 0;
    }
    
    void redimensionar() {
        if( botaoAcc.isSelected() ){
            imagemFinal.setFitWidth( imgFinal.getWidth() * tamXSlide.getValue() );
            imagemFinal.setFitHeight( imgFinal.getHeight() * tamYSlide.getValue() );
        }
        else {
            imagemFinal.setFitHeight( imgFinal.getHeight() );
            imagemFinal.setFitWidth( imgFinal.getWidth() );
        }
    }
    
    //método invocado quando realiza uma alteração
    void alteracao() {
        
        //reseta a ultima ação para evitar bugs no normalizar e truncar
        lstAct = 0;
        
        img = null;
        alteracoes.add( imgFinal.getImg() );
        refazer.clear();
        botaoUndo.setDisable(false);
        botaoRedo.setDisable(true);
    }
    
    @FXML
    void gerarHistograma(ActionEvent event) {
        imgFinal.getImgPlus().plotHistogram();
    }

    @FXML
    void salvar(ActionEvent event) {
        imgFinal.salvarImagem();
    }
    
    @FXML
    void salvarHTML(ActionEvent event) {
        alteracao();
        
        if( botaoAcc.isSelected() )
            imgFinal.setImg( BitMapping.esteganografia(imgFinal, htmlEditor.getHtmlText()) );    
        else
            imgFinal.setImg( BitMapping.esteganografia(imagem, htmlEditor.getHtmlText()) );
        
        img = SwingFXUtils.toFXImage( imgFinal.getImg(), null);

        imagemFinal.setImage(img);
        paneHTML.setVisible(false);
    }
    
    @FXML
    void fecharHTML(ActionEvent event) {
        paneHTML.setVisible(false);
    }
    
    @FXML
    void carregarURL(ActionEvent event) {
        if(!urlText.getText().isBlank()) {
            htmlEditor.setHtmlText( carregarURL( urlText.getText() ));
        }
    }
    
    private String carregarURL(String src) {

        String pageCode = "";

        try {
            URL url = new URL(src);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNextLine()) {
                pageCode += scanner.nextLine() + "\n";
            }
            
            scanner.close();

        } catch (MalformedURLException ex) {
            System.out.println("Erro na URL");
        } catch (IOException ex) {
            System.out.println("Erro no carregamento do HTML");
        }

        return pageCode;
    }

    @FXML
    void Sair(ActionEvent event) {
        Main.fechar();
    }
}
