package entity;

import java.io.Serializable;

/**
 * @author gan
 * @version 1.0
 * @description entity
 * @date 2018/4/24
 */
public class Result implements Serializable {

    private boolean success;
    private String message;

    public Result(boolean success, String message) {
        super();        this.success = success;

        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
