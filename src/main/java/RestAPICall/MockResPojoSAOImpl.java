package RestAPICall;

import java.io.*;
import ilog.rules.engine.*;
import java.util.Arrays;

/**
 * @Created with IntelliJ IDEA
 * @Author: czs
 * @Version 1.0
 * @Date: 2020-12-03
 * @Time: 15:10
 **/

@SuppressWarnings("resource")
public class MockResPojoSAOImpl implements ResSAO {
    public byte[] getRulesetArchive(String ruleSetPath) throws ResSAOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            FileInputStream in = new FileInputStream(new File("D:\\webProject1\\RestODM\\src\\main\\resources\\ruleApp_resdeployment_1.013.jar"));
            byte[] buff = new byte[1024];

            int len = 0;
            int count = 0;
            while (true) {
                count++;
                len = in.read(buff);
                if (len < 1)
                    break;

                System.out.println(count + "=" + len);

                if (len == 1024)
                    out.write(buff, 0, len);
                else {
                    byte[] buff2 = new byte[len];
                    for (int i=0; i<len; i++) {
                        buff2[i] = buff[i];
                    }

                    out.write(buff2, 0, len);
                }
            }
//
//            for (int len = in.read(buff); len != 0; len = in.read(buff)) {
//
//                out.write(buff, 0, len);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取规则应用jar包出错。");
        }
       /* System.out.println(Arrays.toString(out.toByteArray()));*/
        return out.toByteArray();
    }
    public static void main(String[] args) throws ResSAOException {
        MockResPojoSAOImpl mockResPojoSAO = new MockResPojoSAOImpl();
        mockResPojoSAO.getRulesetArchive("/resdeployment/1.0/DeploymentD/1.3");
    }
}
