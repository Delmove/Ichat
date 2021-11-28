package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Vector;

public class addFriend extends Window{
    private Vector<searchLIst> items;
    private ListView friendlist;
    public static Vector<String> friendVector;
    public addFriend() throws IOException {
        root = FXMLLoader.load(getClass().getResource("Fxml/addFriend.fxml"));
        setScene(new Scene(root,600,400));
        items = new Vector<>();
        friendlist = ((ListView) $("friendList"));
        friendVector = new Vector<>();
        ((TextField) $("textInput")).setTooltip(new Tooltip("输入账号，回车查询"));
        setTitle("Chat");
        setResizable(false);
        move();
        setIcon();
    }
    public void add(String head,String nickname,String account) throws IOException{
        items.add(new searchLIst(head,nickname));
        int index = items.size() - 1;
        friendlist.getItems().add(items.get(index).getPane());
        items.get(index).sendAdd(account);
    }
    public ListView getFriendList(){
        return friendlist;
    }

    public void clear(){
        friendlist.getItems().clear();
        friendVector.clear();
    }
}
