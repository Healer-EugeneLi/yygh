package om.eugeneli.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @ClassName: ExcelListener
 * @Description:
 * @Author EugeneLi
 * @Date: 2021/12/25
 * @Time: 21:41
 */
public class ExcelListener extends AnalysisEventListener<UserData> {
    @Override
    public void invoke(UserData userData, AnalysisContext analysisContext) {

        System.out.println(userData);
    }

    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

        System.out.println(headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
