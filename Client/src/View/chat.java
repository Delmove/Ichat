package View;

import Controller.Controller;
import Model.Database;
import Model.bubbleTool;
import Model.chatManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class chat extends Window{
    private ListView chatlist;
    private friendList fl;
    public chat() throws IOException{
        root = FXMLLoader.load(getClass().getResource("Fxml/MainWindow.fxml"));
        setScene(new Scene(root,638,567));
        initStyle(StageStyle.TRANSPARENT);
        chatlist = ((ListView) $("ChatList"));
        move();
        quit();
        quit2();
        mini();
        setIcon();
        initToolTip();
    }
    public void initToolTip(){
        ((Button) $("quit1")).setTooltip(new Tooltip("退出"));
        ((Button) $("minimiser1")).setTooltip(new Tooltip("最小化"));
    }
    public void quit(){
        ((Button) $("quit1")).setOnAction(event -> {
            this.close();
        });
    }
    public void quit2(){
        ((Button) $("quit2")).setOnAction(event -> {
            this.close();
        });
    }
    public void mini(){
        ((Button) $("minimiser1")).setOnAction(event -> {
            setIconified(true);
        });
    }
    public void addLeft(String fHead,String fMsg){
        chatlist.getItems().add(new chatList().setLeft(fHead,fMsg, bubbleTool.getWidth(fMsg),bubbleTool.getHeight(fMsg)));
    }
    public void addRight(String iHead,String iMsg){
        chatlist.getItems().add(new chatList().setRight(iHead,iMsg, bubbleTool.getWidth(iMsg),bubbleTool.getHeight(iMsg)));
    }
    public void setModailty(Window window){
        initModality(Modality.APPLICATION_MODAL);
        initOwner(window);
    }
    public void friendMore(String account, String remark, Database database, friendPage friendpage){
        ((Button) $("friendMore")).setOnAction(event -> {
            if(account.equals("好友")){
                ((TextField) friendpage.$("name")).setText("友人A");
                ((TextField) friendpage.$("age")).setText("19");
                ((TextField) friendpage.$("sex")).setText("男");
                ((TextField) friendpage.$("address")).setText("软工01班");
                ((TextField) friendpage.$("phone")).setText("13330114338");
                ((Label) friendpage.$("account")).setText("8002117371");
                ((TextField) friendpage.$("label")).setText("好兄弟");
                ((TextField) friendpage.$("remark")).setText("友人");
                friendpage.setHead(((Button) friendpage.$("head")),"head9");
                friendpage.setBackground((Pane) friendpage.$("background"),"background6");
                friendpage.setNoAction();
                friendpage.show();
            }else {
                if(friendpage.isShowing()){
                    friendpage.close();
                }
                try{
                    ResultSet resultSet = database.execResult("select * from user where account = ?",account);
                    if(resultSet.next()){
                        try {
                            fl = new friendList(resultSet.getString("head"),resultSet.getString("account"),remark,resultSet.getString("label"));
                            friendpage.setFriendPage(resultSet,remark);
                            fl.editFriendRemark(friendpage,database,account,Controller.userdata.getAccount());
                            friendpage.setNoAction();
                            friendpage.show();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
