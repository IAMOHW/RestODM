package RestAPICall;

import ilog.rules.dt.*;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.util.Map;


import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//import ilog.rules.res.*;
//import ilog.rules.res.model.IlrCRERulesetArchive;
//import ilog.rules.res.model.internal.IlrCRERulesetArchiveImpl;

/**
 * @Created with IntelliJ IDEA
 * @Author: czs
 * @Version 1.0
 * @Date: 2020-11-26
 * @Time: 11:43
 **/

public class Main {
    private Logger logger = org.apache.log4j.Logger.getLogger(this.getClass()
		.getName());

    @SuppressWarnings("resource")
    public Map<String, String> getRules(String ruleSetPath) throws Exception{
        MockResPojoSAOImpl resSAO = new MockResPojoSAOImpl();
        if (ruleSetPath == null) {
			throw new EngineServiceException("ruleSetPath or ruleId is null.");
		}

        byte[] ruleSetArchive = resSAO.getRulesetArchive(ruleSetPath);
        ZipInputStream zipIn = new ZipInputStream(new java.io.ByteArrayInputStream(ruleSetArchive));

        Map<String, String> results = new java.util.HashMap<String,String>();
        for (ZipEntry entry = zipIn.getNextEntry(); entry != null; entry = zipIn.getNextEntry()){

            if (entry.isDirectory()){
                if(logger.isDebugEnabled()){
                    logger.debug("entery is directory."+ entry.getName());
                }
                continue;
            }
            String name = entry.getName();
            if (logger.isDebugEnabled()){
                logger.debug("name="+name);
            }
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
				byte[] buff = new byte[64];
				for (int len = zipIn.read(buff); len != -1; len = zipIn.read(buff)) {
					byteOutput.write(buff, 0, len);
				}
            String irlContent = new String(byteOutput.toByteArray(), "gb2312");
			results.put("1.3", irlContent);
			System.out.println(irlContent);
        }

        return results;
}

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.getRules("/resdeployment/1.0/DeploymentD/1.3");
    }
}
