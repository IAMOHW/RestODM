package RestAPICall;

/**
 * @Created with IntelliJ IDEA
 * @Author: czs
 * @Version 1.0
 * @Date: 2020-12-03
 * @Time: 16:00
 **/

public class  EngineServiceException extends Exception {

    private static final long serialVersionUID = -8720826661201767955L;

    public EngineServiceException(){

    }

    public EngineServiceException(String message){
        super(message);
    }

    public EngineServiceException(Throwable cause){super(cause);}

    public EngineServiceException(String message, Throwable cause){super(message,cause);}

}
