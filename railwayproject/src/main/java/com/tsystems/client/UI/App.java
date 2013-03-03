package com.tsystems.client.UI;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 2/24/13
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */

import com.tsystems.common.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

//import java.util.logging.Level;
//import java.util.logging.Logger;

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
            log.debug("App.start() success\n");
        } catch (Exception ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            log.error("App.start() exception\n" + ex.getMessage());
//            debug(ex.getMessage());
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public boolean userLogging(String email, String password) {

        /*if (Authenticator.validate(userId, password)) {
            loggedUser = User.of(userId);
            gotoRegister();
            return true;
        } else {
            return false;
        } */
        //
        return false;
    }


    public void userRegister() {
        loggedUser = User.of("");
        gotoRegister();
    }

    private void gotoRegister() {
        try {
            replaceSceneContent("/fxml/register.fxml");
            log.debug("App.gotoRegister() success\n");
        } catch (Exception ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            log.error("App.gotoRegister() exception\n" + ex.getMessage());
            debug(ex.getMessage());
        }
    }


    public void userLogout() {
        loggedUser = null;
        gotoLogin();
    }

    private void gotoLogin() {
        try {
            replaceSceneContent("/fxml/login.fxml");
            log.debug("App.gotoLogin success\n");
        } catch (Exception ex) {
            log.error("App.gotoLogin() exception\n" + ex.getMessage());
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void userLogin() {
        gotoProfile();
    }

    private void gotoProfile() {
        try {
            replaceSceneContent("/fxml/viewProfile.fxml");
            log.debug("App.gotoProfile success\n");
        } catch (Exception ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            log.error("App.gotoProfile() exception\n" + ex.getMessage());
            debug(ex.getMessage());
        }
    }


    public void adminLogin() {
        gotoAdminProfile();
    }

    private void gotoAdminProfile() {
        try {
            replaceSceneContent("/fxml/admin/AdminViewProfile.fxml");
            log.debug("App.gotoAdminViewProfile success\n");
        } catch (Exception ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            log.error("App.gotoAdminViewProfile() exception\n" + ex.getMessage());
            debug(ex.getMessage());
        }
    }


    public void adminViewPassengers() {
        gotoViewPassengers();
    }

    private void gotoViewPassengers() {
        try {
            replaceSceneContent("/fxml/admin/viewPassengers.fxml");
            log.debug("App.gotoViewPassenger() success\n");
        } catch (Exception ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            log.error("App.gotoViewPassenger() exception\n" + ex.getMessage());
            debug(ex.getMessage());
        }
    }


    public void adminViewEditTrains() {
        gotoViewEditTrains();
    }

    private void gotoViewEditTrains() {
        try {
            replaceSceneContent("/fxml/admin/viewEditTrains.fxml");
            log.debug("App.gotoViewEditTrains() success\n");
        } catch (Exception ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            log.error("App.gotoViewEditTrains() exception\n" + ex.getMessage());
            debug(ex.getMessage());
        }
    }


    public void adminViewEditStations() {
        gotoViewEditStations();
    }

    private void gotoViewEditStations() {
        try {
            replaceSceneContent("/fxml/admin/viewEditStations.fxml");
            log.debug("App.gotoViewEditStations() success\n");
        } catch (Exception ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            log.error("App.gotoViewEditStations() exception\n" + ex.getMessage());
            debug(ex.getMessage());
        }
    }


    public void viewShedule() {
        gotoViewShedule();
    }

    private void gotoViewShedule() {
        try {
            replaceSceneContent("/fxml/shedule.fxml");
            log.debug("App.gotoViewShedule() success\n");
        } catch (Exception ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            log.error("App.gotoViewShedule() exception\n" + ex.getMessage());
            debug(ex.getMessage());
        }
    }


    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(App.class.getResource(fxml), null, new JavaFXBuilderFactory());

        Scene scene = stage.getScene();
        if (scene == null) {
//            scene = new Scene(page, 700, 550);
            scene = new Scene(page, 700, 550);
            scene.getStylesheets().addAll(App.class.getResource("/styles/simple_calendar.css").toExternalForm());
            scene.getStylesheets().add(App.class.getResource("/styles/style.css").toExternalForm());

            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }

}

