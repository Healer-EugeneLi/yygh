package om.eugeneli.easyexcel;

import com.alibaba.excel.EasyExcel;

/**
 * @ClassName: TestRead
 * @Description:
 * @Author EugeneLi
 * @Date: 2021/12/25
 * @Time: 21:43
 */
public class TestRead {

    public static void main(String[] args) {

        String name="UserData.xlsx";
        EasyExcel.read(name,UserData.class,new ExcelListener()).sheet().doRead();
    }
}
