package View;

import Controller.Controller;
import Model.Database;
import Model.chatManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class mainWindow extends Window{
    private ListView friendlist;
    private ListView group;
    private friendList fl;
    private Vector<friendList> friendListVector;
    private Vector<FriendGroup> groupVector;
    private Vector<MyFriend> fdVector;
    public mainWindow() throws IOException {
        root = FXMLLoader.load(getClass().getResource("Fxml/FriendList.fxml"));
        setScene(new Scene(root,320,597));
        initStyle(StageStyle.TRANSPARENT);
        setTitle("Chat");
        friendlist = ((ListView) $("message"));
        group = ((ListView) $("FriendList"));
        friendListVector = new Vector<>();
        groupVector = new Vector<>();
        fdVector = new Vector<>();
        move();
        quit();
        logout();
        mini();
        setIcon();
        initToolTip();
    }

    public void initToolTip(){
        ((Button) $("setting")).setTooltip(new Tooltip("设置"));
        ((Button) $("addFriend")).setTooltip(new Tooltip("添加好友"));
        ((Button) $("quit")).setTooltip(new Tooltip("退出"));
        ((Button) $("mini")).setTooltip(new Tooltip("最小化"));
        ((Button) $("quit0")).setTooltip(new Tooltip("注销"));
        ((Button) $("myHead")).setTooltip(new Tooltip("更多"));
    }
    public void quit(){
        ((Button) $("quit")).setTooltip(new Tooltip("退出"));
        ((Button) $("quit")).setOnAction(event -> {
            close();
            try {
                Controller.database.exec("delete from dialog where account = ?",Controller.userdata.getAccount());
                try{
                    chatManager.getInstance().sendServer("#### " + Controller.userdata.getAccount() + " ####");
                }catch (IOException e){
                    e.printStackTrace();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            System.exit(0);  //系统退出，程序结束。
        });
    }

    public void mini(){
        ((Button) $("mini")).setTooltip(new Tooltip("最小化"));
        ((Button) $("mini")).setOnAction(event -> {
            setIconified(true);
        });
    }

    public void logout(){
        ((Button) $("quit0")).setOnAction(event -> {
            close();
            try {
                Controller.database.exec("delete from dialog where account = ?",Controller.userdata.getAccount());
                try{
                    chatManager.getInstance().sendServer("#### " + Controller.userdata.getAccount() + " ####");
                }catch (IOException e){
                    e.printStackTrace();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            Controller.dialog.show();
        });
    }
    public void setPersonlPage(String nickname,String label){
        ((Label) $("myAccount")).setText(nickname);
        ((Label) $("myLabel")).setText(label);
    }

    public static void setHeadPorTrait(Button button, String head){
        button.setStyle(String.format("-fx-background-image:url('View/Fxml/CSS/Image/head/%s.jpg')",head));
    }
    public void setHead(String head){
        setHeadPorTrait(((Button) $("myHead")),head);
    }

    public void addFriend(String nickname,String head,String remark,String label) throws IOException{
        int index = friendListVector.size();
        friendListVector.add(new friendList(head,nickname,remark,label));
        friendListVector.get(index).setActionForSend(this,nickname,Controller.userdata.getHead());
        friendListVector.get(index).setActionForMsgTip();
        friendlist.getItems().add(friendListVector.get(friendListVector.size()-1).getPane());
    }

    public void addFriend(String nickname, String head, String remark,String label, Database database,friendPage friendpage) throws IOException{
        int index = friendListVector.size();
        friendListVector.add(new friendList(head,nickname,remark,label));
        friendListVector.get(index).setActionForFriendPage(database,friendpage,nickname,Controller.userdata.getAccount());
        friendListVector.get(index).removeFriend(database,this,Controller.userdata.getAccount());
        friendListVector.get(index).setActionForSend(this,nickname,Controller.userdata.getHead());
        friendListVector.get(index).setActionForMsgTip();
        friendListVector.get(index).clearMsg(this);
        friendlist.getItems().add(friendListVector.get(friendListVector.size()-1).getPane());
    }
    public void addGroup(String labels,String num) throws IOException{
        int index = groupVector.size();
        groupVector.add(new FriendGroup(labels,num));
        group.getItems().add(groupVector.get(groupVector.size()-1).getPane());
    }

    public void addGroupList(String labels){
        try{
            ResultSet ret = Controller.database.execResult("select * from companion where I_account =?and my_group=?",Controller.userdata.getAccount(),labels);
            try{
                while (ret.next()){
                    ResultSet ret1 = Controller.database.execResult("select * from user where account = ?",ret.getString("Y_account"));
                    if(ret1.next()){
                        fdVector.add(new MyFriend(ret1.getString("head"),ret1.getString("nickname"),ret.getString("remark"),ret1.getString("label")));
                        group.getItems().add(fdVector.get(fdVector.size()-1).getPane());
                    }
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Vector<friendList> getFriendVector(){ return friendListVector;}
    public ListView getFriendlist(){ return friendlist;}
    public ListView getGroup(){ return group;}
}
