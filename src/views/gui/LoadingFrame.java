package views.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoadingFrame extends Thread{

    private Stage loadingStage;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXProgressBar loadingBar;

    public LoadingFrame(){

    }

    public void start(){
        try {
            loadingStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/loadingFrame.fxml"));
            loader.setController(this);
            Parent root = loader.load();
            loadingStage.initStyle(StageStyle.UNDECORATED);
            loadingStage.setScene(new Scene(root, 450, 100));
            loadingStage.setMaxWidth(450);
            loadingStage.setMaxHeight(100);
            loadingStage.setMinWidth(450);
            loadingStage.setMinHeight(100);
            loadingStage.setResizable(false);
            loadingStage.setAlwaysOnTop(true);
            cancelButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    loadingStage.close();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setVisible(boolean visible){
        if(visible)
            loadingStage.show();
        else
            loadingStage.close();
    }
}
