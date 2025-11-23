package util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.TilesRequestProcessor;

public class EncodingRequestProcessor extends TilesRequestProcessor {

    @Override
    protected boolean processPreprocess(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");

            response.setContentType("text/html; charset=UTF-8");

        } catch (UnsupportedEncodingException e) {
                    // Log lỗi
//                    throw new RuntimeException("Server cùi bắp không hỗ trợ UTF-8", e);
                    
                    // Hoặc dùng.
                //    getServlet().log("Lỗi nghiêm trọng: Server không hỗ trợ UTF-8", e);
            }
            

        return true;
    }

}
