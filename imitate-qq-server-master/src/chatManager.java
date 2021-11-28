import java.util.HashMap;
import java.util.Map;

public class chatManager {
    public chatManager(){

    }
    private static final chatManager cm = new chatManager();
    public static chatManager getCm(){
        return cm;
    }
    Map<String,chatSocket> map = new HashMap<>();  //账号映射客户端请求
    public void add(String account,chatSocket chatsocket){
        map.put(account,chatsocket);
    }
    public void remove(String account){
        map.remove(account);
    }

    public void sendMsg(String from,String to,String msg){
        for (Map.Entry<String,chatSocket> entry : map.entrySet()){
            chatSocket socket = entry.getValue();
            if(entry.getKey().equals(to)){
                socket.out(from + " " + to + " " +msg);
            }
        }
    }
    public void onLine(String dialogName){
        for(Map.Entry<String,chatSocket> entry:map.entrySet()){
            chatSocket socket = entry.getValue();
            if(!entry.getKey().equals(dialogName)){
                socket.out(dialogName+" #### #@@@");
            }
        }

    }
    public void onOut(String name) {

        for (Map.Entry<String, chatSocket> entry : map.entrySet()) {
            chatSocket socket = entry.getValue();
            if (!entry.getKey().equals(name)) {
                socket.out(name + " #### @@@@");
            }
        }
    }

    public void sendApply(String from,String to,String msg){
        for (Map.Entry<String,chatSocket> entry : map.entrySet()){
            chatSocket socket = entry.getValue();
            if(entry.getKey().equals(to)){
                socket.out(from + " " + to + " " + "add" + msg);
            }
        }
    }

    public void accept(String name) {

        for (Map.Entry<String, chatSocket> entry : map.entrySet()) {
            chatSocket socket = entry.getValue();
            if (!entry.getKey().equals(name)) {
                socket.out(name + " #### @@@@");
            }
        }
    }
}
