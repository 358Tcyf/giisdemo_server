package simple.project.giis.dto;


/**
 * @author Daylight
 * @date 2018/12/13 15:30
 */
public class RetResponseOld {
    public static BaseResponse success() {
        return new BaseResponse();
    }

    public static BaseResponse success(String msg) {
        BaseResponse response = new BaseResponse();
        response.setMsg(msg);
        return response;
    }

    public static BaseResponse success(String msg, Object obj) {
        BaseResponse response = new BaseResponse();
        response.setMsg(msg);
        response.setObject(obj);
        return response;
    }

    public static BaseResponse error() {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(400);
        response.setMsg("error");
        return response;
    }

    public static BaseResponse error(String msg) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(400);
        response.setMsg(msg);
        return response;
    }

    public static BaseResponse error(int code, String msg, Object obj) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(code);
        response.setMsg(msg);
        response.setObject(obj);
        return response;
    }
}
