package RestAPICall;

/**
 * @Created with IntelliJ IDEA
 * @Author: czs
 * @Version 1.0
 * @Date: 2020-12-03
 * @Time: 14:58
 **/

public class ResSAOException extends Exception {


    private static final long serialVersionUID = -1189590401912409201L;

    public ResSAOException(String message){
        super(message);
    }

    public ResSAOException(Throwable cause){
        super(cause);
    }

    public ResSAOException(String message, Throwable cause){
        super(message,cause);
    }
}
