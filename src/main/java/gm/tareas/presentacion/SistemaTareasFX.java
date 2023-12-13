package gm.tareas.presentacion;

import gm.tareas.TareasApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class SistemaTareasFX extends Application {

    private ConfigurableApplicationContext applicationContext;
//    public static void main(String[] args) {
//        launch(args);
//    }
    @Override
    public void init(){
        this.applicationContext = new SpringApplicationBuilder(TareasApplication.class).run();
    }
    //Permite inicializar nuestra aplicacion de Java fx
    //Se ejecuta despues del metodo init
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(TareasApplication.class.getResource("/templates/index.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        Scene escena = new Scene(loader.load());
        stage.setScene(escena);
        stage.show();
    }

    @Override
    public void stop(){
        applicationContext.close(); //cierra fabrica de spring
        Platform.exit(); //salir de la app de java fx
    }
}
