package Management.controller.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ApiBaseResponse setResponse(){
        return new ApiBaseResponse();
    }

    protected ApiBaseResponse setResponse(Object data){
        if (data != null) {

                ApiBaseResponse response = new ApiBaseResponse();
                response.setData(data);
                return response;

        }
        return new ApiBaseResponse();
    }

}
