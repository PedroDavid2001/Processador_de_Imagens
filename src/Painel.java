package src;

import javax.swing.JFileChooser;
import ij.io.FileSaver;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

public class Painel {

    @FXML
    private ImageView imagemIni;

    @FXML
    private Menu menuOperacoes;

    @FXML
    private Menu menuTransformar;

    @FXML
    private ImageView imagemSec;

    @FXML
    private MenuItem botaoAbrirCamada;

    @FXML
    private MenuItem botaoSalvar;

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
    private MenuItem botaoLimparIni;

    @FXML
    private MenuItem botaoLimparSec;

    @FXML
    private MenuItem botaoLimparFinal;

    @FXML
    private RadioMenuItem botaoAcc;

    private Processador imagem = new Processador();
    private Processador imgSec = new Processador();
    private Image img;
    
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
     * 1 - soma
     * 2 - subtração
     * 3 - multiplicação
     * 4 - and
     * 5 - or
     * 6 - xor
     */
    private int lstAct;

    //--------------------------------------------------------------
    //Métodos para exibir a imagem na tela

    @FXML
    void abrirImgPrimaria(ActionEvent event) {
        img = null;
         
        if(imagem.carregarImg()){
            imgFinal.setImg( imagem.getImg() );
            
            img = SwingFXUtils.toFXImage(imagem.getImg(), null);
                
            imagemIni.setImage(img);
                
            imagemIni.setX( 10.0 );
            imagemIni.setY( 10.0 );
            imagemIni.setFitHeight( imagem.getHeight() );
            imagemIni.setFitWidth( imagem.getWidth() );

            //habilita os botões da interface caso a imagem abra com sucesso
            botaoAbrirCamada.setDisable(false);
            menuTransformar.setDisable(false);
            menuLimpar.setDisable(false);
            botaoLimparIni.setDisable(false);
        }
    }

    @FXML
    void abrirImgSec(ActionEvent event) {
        img = null;
        
        if(imgSec.carregarImg()){
            img = SwingFXUtils.toFXImage(imgSec.getImg(), null);
            
            imagemSec.setImage(img);
                
            imagemSec.setFitHeight( imgSec.getHeight() );
            imagemSec.setFitWidth( imgSec.getWidth() );
                
            imagemSec.setX( 10.0 );
            imagemSec.setY( imagemIni.getY() + imagemIni.getFitHeight() + 10 );

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
        imagemFinal.setFitHeight( imagem.getHeight() );
        imagemFinal.setFitWidth( imagem.getWidth() );
        imagemFinal.setX( imagemIni.getX() + imagemIni.getFitWidth() + 20 );
        imagemFinal.setY( imagemIni.getY() );

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
        imagemFinal.setFitHeight( imagem.getHeight() );
        imagemFinal.setFitWidth( imagem.getWidth() );
        imagemFinal.setX( imagemIni.getX() + imagemIni.getFitWidth() + 20 );
        imagemFinal.setY( imagemIni.getY() );

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
        imagemFinal.setFitHeight( imagem.getHeight() );
        imagemFinal.setFitWidth( imagem.getWidth() );
        imagemFinal.setX( imagemIni.getX() + imagemIni.getFitWidth() + 20 );
        imagemFinal.setY( imagemIni.getY() );


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
        imagemFinal.setFitHeight( imagem.getHeight() );
        imagemFinal.setFitWidth( imagem.getWidth() );
        imagemFinal.setX( imagemIni.getX() + imagemIni.getFitWidth() + 20 );
        imagemFinal.setY( imagemIni.getY() );
       
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
        imagemFinal.setFitHeight( imagem.getHeight() );
        imagemFinal.setFitWidth( imagem.getWidth() );
        imagemFinal.setX( imagemIni.getX() + imagemIni.getFitWidth() + 20 );
        imagemFinal.setY( imagemIni.getY() );

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
        imagemFinal.setFitHeight( imagem.getHeight() );
        imagemFinal.setFitWidth( imagem.getWidth() );
        imagemFinal.setX( imagemIni.getX() + imagemIni.getFitWidth() + 20 );
        imagemFinal.setY( imagemIni.getY() );

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
        imagemFinal.setFitHeight( imagem.getHeight() );
        imagemFinal.setFitWidth( imagem.getWidth() );
        imagemFinal.setX( imagemIni.getX() + imagemIni.getFitWidth() + 20 );
        imagemFinal.setY( imagemIni.getY() );

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
        imagemFinal.setFitHeight( imagem.getHeight() );
        imagemFinal.setFitWidth( imagem.getWidth() );
        imagemFinal.setX( imagemIni.getX() + imagemIni.getFitWidth() + 20 );
        imagemFinal.setY( imagemIni.getY() );

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
        imagemFinal.setFitHeight( imagem.getHeight() );
        imagemFinal.setFitWidth( imagem.getWidth() );
        imagemFinal.setX( imagemIni.getX() + imagemIni.getFitWidth() + 20 );
        imagemFinal.setY( imagemIni.getY() );

        botaoLimparFinal.setDisable(false);
        botaoSalvarComo.setDisable(false);
    }

    
    //--------------------------------------------------------------
    //Métodos extras

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
    void limparPrimaria(ActionEvent event) {
        imagemIni.setImage(null);   
        botaoLimparIni.setDisable(true);
        botaoAbrirCamada.setDisable(true);
        menuTransformar.setDisable(true);
        menuOperacoes.setDisable(true);

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
        img = null;
        imagem.destruir();
        imgFinal.destruir();
        imgSec.destruir();
        Main.fechar();
    }
}
