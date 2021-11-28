package View;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public abstract class Window extends Stage {
    Parent root;
    private double xOffset;
    private double yOffset;
    public void setIcon(){
        getIcons().add(new Image(getClass().getResourceAsStream("/View/Fxml/CSS/Image/Icon/qq.gif")));
    }
    public void move() {
        root.setOnMousePressed(event -> {
            xOffset = getX() - event.getScreenX();
            yOffset = getY() - event.getScreenY();
            getRoot().setCursor(Cursor.CLOSED_HAND);
        });
        root.setOnMouseDragged(event -> {

            setX(event.getScreenX() + xOffset);
            setY(event.getScreenY() + yOffset);
        });
        root.setOnMouseReleased(event -> {
            root.setCursor(Cursor.DEFAULT);

        });
    }
    public Parent getRoot() {
        return root;
    }
    public Object $(String id) {
        return root.lookup("#" + id);
    }
}