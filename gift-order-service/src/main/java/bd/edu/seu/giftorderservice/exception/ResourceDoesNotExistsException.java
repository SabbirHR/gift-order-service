package bd.edu.seu.giftorderservice.exception;

public class ResourceDoesNotExistsException extends Exception{
    public ResourceDoesNotExistsException(String resource){
        super(resource + "doesn't exists");
    }
}
