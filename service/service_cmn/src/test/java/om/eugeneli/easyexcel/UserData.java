package om.eugeneli.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @ClassName: UserData
 * @Description:
 * @Author EugeneLi
 * @Date: 2021/12/25
 * @Time: 21:16
 */

@Data
public class UserData {

    @ExcelProperty(value = "用户编号",index = 0)
    private int id;

    @ExcelProperty(value = "用户名",index = 1)
    private String name;
}
