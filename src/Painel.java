package src;

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
import javafx.scene.layout.Pane;

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
    private ImageView imagemIni;

    @FXML
    private Menu menuOperacoes;

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
    private TextField posXText;

    @FXML
    private TextField textoValor;
    
    @FXML
    private Slider rotacaoSlide;
    
    @FXML
    private Slider tamXSlide;
   
    @FXML
    private TextField tamXTexto;
    
    @FXML
    private Slider tamYSlide;
   
    @FXML
    private TextField tamYTexto;
    
    @FXML
    private TextField grauText;

    @FXML
    private MenuItem botaoLimparIni;

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
    
    JFileChooser fileChooser = new JFileChooser();
    
    /*
     * O objeto imgFinal é utilizado para guardar e exibir
     * a imagem resultante. Também é utilizado para quando
     * a opção "acumular" no menu "opções" está selecionada. 
     */
    private Processador imgFinal = new Processador();
    
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
                
            imagemIni.setX( 10.0 );
            imagemIni.setY( 10.0 );
            imagemIni.setFitHeight( 50.0 );
            imagemIni.setFitWidth(  50.0 );
            
            //imagem exibida na área de trabalho do software
            imagemFinal.setImage(img);

            grauText.setText( "0.0" );
            rotacaoSlide.setValue( 0.0 );
            tamXTexto.setText( "1.0" );
            tamYTexto.setText( "1.0" );
            tamXSlide.setValue( 1.0 );
            tamYSlide.setValue( 1.0 );
            posXText.setText( "0.0" );
            posYText.setText( "0.0" );

            imagemFinal.setFitHeight( imagem.getHeight() );
            imagemFinal.setFitWidth(  imagem.getWidth() );
            
            imagemFinal.setX( posXInit );
            imagemFinal.setY( posYInit );
            
            //habilita os botões da interface caso a imagem abra com sucesso
            botaoAbrirCamada.setDisable(false);
            menuTransformar.setDisable(false);
            menuLimpar.setDisable(false);
            botaoLimparIni.setDisable(false);
            painelLateral.setVisible(true);
            menuCores.setDisable(false);
           
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
                
            imagemSec.setX( 10.0 );
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
        imagemFinal.setFitHeight( imgFinal.getHeight() );
        imagemFinal.setFitWidth( imgFinal.getWidth() );
        
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
        imagemFinal.setFitHeight( imgFinal.getHeight() );
        imagemFinal.setFitWidth( imgFinal.getWidth() );
        
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
    
    void redimensionar() {
    	imagemFinal.setFitWidth( imgFinal.getWidth() * tamXSlide.getValue() );
    	imagemFinal.setFitHeight( imgFinal.getHeight() * tamYSlide.getValue() );
    }
    
    //EM DESENVOLVIMENTO
    void rotacionar() {
    	
    	/*double xInit = imagemFinal.getX();
    	double yInit = imagemFinal.getY();
    	
    	imagemFinal.setX( imagemFinal.getX() - (imagemFinal.getFitWidth() / 2.0) );
    	imagemFinal.setY( imagemFinal.getY() - (imagemFinal.getFitHeight() / 2.0) );*/
    	
    	imagemFinal.setRotate( rotacaoSlide.getValue() );
    	
    	/*double newPos[] = Transformacoes.rotacao(rotacaoSlide.getValue(), xInit, yInit);
    	
    	imagemFinal.setX( imagemFinal.getX() + newPos[0] );
    	imagemFinal.setY( imagemFinal.getY() + newPos[1] );*/
    }
    
    @FXML
    void angulo(ActionEvent event) {
    	rotacaoSlide.setValue( Double.valueOf( grauText.getText() ) );
    	rotacionar();
    }

    @FXML
    void tamanhoX(ActionEvent event) {
    	tamXSlide.setValue( Double.valueOf( tamXTexto.getText() ) );
    	redimensionar();
    }
    
    @FXML
    void tamanhoY(ActionEvent event) {
    	tamYSlide.setValue( Double.valueOf( tamYTexto.getText() ) );
    	redimensionar();
    }
    
    //--------------------------------------------------------------
    //Métodos de componentes de cor
    
    @FXML
    void exibirRGB(ActionEvent event) {
    	if( botaoAcc.isSelected() )
    		Cores.RGB( imagem );
    	else
    		Cores.RGB( imgFinal );
    }

    @FXML
    void exibirCMY(ActionEvent event) {

    }

    @FXML
    void exibirCMYK(ActionEvent event) {

    }

    @FXML
    void exibirYUV(ActionEvent event) {

    }
    
    @FXML
    void exibirGray(ActionEvent event) {
    	if( botaoAcc.isSelected() )
    		Cores.grayScale( imagem );
    	else
    		Cores.grayScale( imgFinal );
    }
    
    //--------------------------------------------------------------
    //Métodos extras
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	rotacaoSlide.valueProperty().addListener(new ChangeListener<Number>() {
    		
    		@Override
    		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
    			grauText.setText( Double.toString(rotacaoSlide.getValue()) );
    			rotacionar();
    		}
    	} );
    	
    	tamXSlide.valueProperty().addListener(new ChangeListener<Number>() {
    		
    		@Override
    		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
    			tamXTexto.setText( Double.toString(tamXSlide.getValue()) );
    			redimensionar();
    		}
    	} );
    	
    	tamYSlide.valueProperty().addListener(new ChangeListener<Number>() {
    		
    		@Override
    		public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
    			tamYTexto.setText( Double.toString(tamYSlide.getValue()) );
    			redimensionar();
    		}
    	} );
    	
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
        //System.out.println("\n\nValor: " + Float.valueOf((textoValor.getText())) + "\n\n");

        if(lstAct == 7)
            cisX();
        else if(lstAct == 8)
            cisY();

        textoValor.clear();
    }
    
    @FXML
    void limparPrimaria(ActionEvent event) {
        imagemIni.setImage(null);   
        botaoLimparIni.setDisable(true);
        botaoAbrirCamada.setDisable(true);
        menuTransformar.setDisable(true);
        menuOperacoes.setDisable(true);
        painelLateral.setVisible(false);
        menuCores.setDisable(true);
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
    }

    @FXML
    void limparTudo(ActionEvent event) {
        limparPrimaria(event);
        limparSec(event);
        limparFinal(event);
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
