package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class friendPage extends Window{
    public friendPage() throws IOException{
        root = FXMLLoader.load(getClass().getResource("Fxml/friendPage.fxml"));
        setScene(new Scene(root,393,502));
        setTitle("Chat");
        initStyle(StageStyle.TRANSPARENT);
        move();
        quit();
        setIcon();
        alterAction();
    }
    public void alterAction(){
        ((Button) $("edit")).setOnAction(event -> {
            ((TextField) $("remark")).setEditable(true);
            ((Button) $("submit")).setVisible(true);
            ((Button) $("submit")).setManaged(true);
        });
    }
    public void setRemark(String remark){
        ((TextField) $("remark")).setText(remark);
    }

    public void setNoAction(){
        ((TextField) $("remark")).setEditable(false);
        ((Button) $("submit")).setVisible(false);
        ((Button) $("submit")).setManaged(false);
    }

    public void setFriendPage(ResultSet resultSet,String remark) throws SQLException {
        ((TextField) $("name")).setText(resultSet.getString("name"));
        ((TextField) $("age")).setText(resultSet.getString("age"));
        if(resultSet.getString("sex").equals("man")){
            ((TextField) $("sex")).setText("男");
        }else {
            ((TextField) $("sex")).setText("女");
        }
        ((TextField) $("address")).setText(resultSet.getString("address"));
        ((TextField) $("phone")).setText(resultSet.getString("phone"));
        ((Label) $("account")).setText(resultSet.getString("account"));
        ((TextField) $("label")).setText(resultSet.getString("label"));
        ((TextField) $("remark")).setText(remark);
        setHead(((Button) $("head")),resultSet.getString("head"));
        setBackground((Pane) $("background"),resultSet.getString("background"));
    }

    public void quit(){
        ((Button) $("quit1")).setOnAction(event -> {
            this.close();
        });
    }

    public void setHead(Button button,String head){
        button.setStyle(String.format("-fx-background-image:url('/View/Fxml/CSS/Image/head/%s.jpg')",head));
    }
    public void setBackground(Pane pane, String background){
        pane.setStyle(String.format("-fx-background-image:url('/View/Fxml/CSS/Image/background/%s.jpg')",background));
    }

    public void setModailty(Window window){
        initModality(Modality.APPLICATION_MODAL);
        initOwner(window);
    }
}
