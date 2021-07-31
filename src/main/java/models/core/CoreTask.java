package models.core;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Node;
import presenters.Controller;
import views.gui.PrincipalApp;

import java.util.ArrayList;

public class CoreTask extends Task<ArrayList<Roll>> {

    private Controller controller;
    private PrincipalApp app;
    private ObservableList<Node> paneElements;

    public CoreTask(Controller controller, PrincipalApp app, ObservableList<Node> paneElements) {
        this.controller = controller;
        this.app = app;
        this.paneElements = paneElements;
    }

    @Override
    protected ArrayList<Roll>  call() throws Exception {
        return controller.optimization(app.getStripsSelected(paneElements), app.getMethod());
    }
}