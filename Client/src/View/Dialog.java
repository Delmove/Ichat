package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Dialog extends Window{

    public Dialog() throws IOException{
        root = FXMLLoader.load(getClass().getResource( "Fxml/Dialog.fxml"));
        Scene scene = new Scene(root,448,397);
        initStyle(StageStyle.TRANSPARENT);
        setScene(scene);
        setTitle("Chat");
        ((Button) $("close")).setTooltip(new Tooltip("关闭"));
        ((Button) $("mini")).setTooltip(new Tooltip("最小化"));
        move();
        close1();
        ((Button) $("I_login")).setVisible(false);
        ((Button) $("I_login")).setManaged(false);
        setLogin();
        setSavePass();
        mini();
        setIcon();
    }
    public void setLogin(){
        ((Button) $("I_login1")).setOnAction(event -> {
            ((Button) $("I_login1")).setVisible(false);
            ((Button) $("I_login1")).setManaged(false);
            ((Button) $("I_login")).setVisible(true);
            ((Button) $("I_login")).setManaged(true);
        });
        ((Button) $("I_login")).setOnAction(event -> {
            ((Button) $("I_login")).setVisible(false);
            ((Button) $("I_login")).setManaged(false);
            ((Button) $("I_login1")).setVisible(true);
            ((Button) $("I_login1")).setManaged(true);
        });
    }
    public void setSavePass() {
        ((Button) $("save_pass1")).setOnAction(event -> {
            ((Button) $("save_pass1")).setVisible(false);
            ((Button) $("save_pass1")).setManaged(false);
            ((Button) $("save_pass")).setVisible(true);
            ((Button) $("save_pass")).setManaged(true);
        });
        ((Button) $("save_pass")).setOnAction(event -> {
            ((Button) $("save_pass")).setVisible(false);
            ((Button) $("save_pass")).setManaged(false);
            ((Button) $("save_pass1")).setVisible(true);
            ((Button) $("save_pass1")).setManaged(true);
        });
    }
    public void close1(){
        ((Button) $("close")).setOnAction(event -> {
            this.close();
            this.clear();
        });
    }
    public void mini(){
        ((Button) $("mini")).setOnAction(event -> {
            setIconified(true);
        });
    }
    public void setErrorTip(String id,String Text){
        ((Label) $(id)).setText(Text);
    }
    public void resetErrorTip(){
        setErrorTip("accountError","");
        setErrorTip("passwordError","");
    }
    public void clear(){
        ((TextField) $("UserName")).clear();
        ((PasswordField) $("Password")).clear();
    }
    public void clear(String id){
        ((TextField) $(id)).clear();
    }
}
