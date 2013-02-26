package com.tsystems.client.UI;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/24/13
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */

import com.tsystems.client.UI.model.User;
import com.tsystems.client.UI.security.Authenticator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main Application. This class handles navigation and user session.
 */
public class App extends Application {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    void debug(Object... os) {
        System.err.println(Arrays.deepToString(os));
    }

    private Stage stage;
    private User loggedUser;

    private static App instance;

    public App() {
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            gotoLogin();
            primaryStage.show();

        } catch (Exception ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            log.error("start exception\n" + ex.getMessage());
            debug(ex.getMessage());
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean userLogging(String userId, String password) {
        if (Authenticator.validate(userId, password)) {
            loggedUser = User.of(userId);
            gotoProfile();
            return true;
        } else {
            return false;
        }
    }

    public void userLogout() {
        loggedUser = null;
        gotoLogin();
    }

    private void gotoProfile() {
        try {
            replaceSceneContent("/fxml/profile.fxml");
            //debug("User profile success");
        } catch (Exception ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            log.error("profile exception\n" + ex.getMessage());
            debug(ex.getMessage());
        }
    }

    private void gotoLogin() {
        try {
            replaceSceneContent("/fxml/login.fxml");
            //    debug("User login success");
            //    log.error("User login success");
            log.debug("User login success");
        } catch (Exception ex) {
            log.error("login exception\n" + ex.getMessage());
            debug(ex.getMessage());
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(App.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 700, 550);
            scene.getStylesheets().add(App.class.getResource("/styles/style.css").toExternalForm());
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }
}

