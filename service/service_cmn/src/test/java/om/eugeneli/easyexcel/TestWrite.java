package om.eugeneli.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.eugeneli.yygh.model.acl.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestWrite
 * @Description:
 * @Author EugeneLi
 * @Date: 2021/12/25
 * @Time: 21:16
 */
public class TestWrite {

    public static void main(String[] args) {

        //准备数据 Excel
        List<UserData> list=new ArrayList<>();

        for (int i=0;i<10;i++){
            UserData userData=new UserData();
            userData.setId(i);
            userData.setName("name:"+i);
            list.add(userData);
        }

        //设置Excel文件路径和文件名称
        String fileName="UserData.xlsx";
        EasyExcel.write(fileName, UserData.class).sheet("用户信息").doWrite(list);


    }


}
