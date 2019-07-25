package allen.util;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtile {
    public static void main(String[] args) throws FileNotFoundException {
        OutputStream out = new FileOutputStream("E:/78.xls");
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0,ExcelPropertyIndexModel.class);
            List<ExcelPropertyIndexModel> list=new ArrayList<>();
            list.add(new ExcelPropertyIndexModel("chizhenfang",11));
            list.add(new ExcelPropertyIndexModel("aaaaa",12));
            list.add(new ExcelPropertyIndexModel("ttttt",13));
            list.add(new ExcelPropertyIndexModel("rrrrrrr",14));
            writer.write(list, sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
