///**
// *
// */
//package RestAPICall;
//
//import java.io.ByteArrayOutputStream;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//
//import org.apache.log4j.Logger;
///*import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ilog.rules.res.model.*;
//import com.meyacom.ruleapp.core.engine.sao.ResSAO;
//import com.meyacom.ruleapp.core.engine.sao.ResSAOException;
//import com.meyacom.ruleapp.core.engine.sao.RtsSAO;
//import com.meyacom.ruleapp.core.engine.service.EngineServiceException;
//import com.meyacom.ruleapp.core.engine.service.RuleService;*/
//
///**
// * @author MA Xiaoqiang
// */
////@Service
////	implements RuleService/**/
//public class RulePojoServiceImpl  {
//
//	/**
//	 *
//	 */
//	private Logger logger = org.apache.log4j.Logger.getLogger(this.getClass()
//			.getName());
//
//	/**
//	 *
//	 */
//	/*@Autowired
//	ResSAO resSAO;
//
//	*//**
//	 *
//	 *//*
//	@Autowired
//	RtsSAO rtsSAO;*/
//
//	/**
//	 *
//	 */
//	/*public RulePojoServiceImpl() {
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see
//	 * com.meyacom.rule.core.engine.service.RuleService#getRules(com.meyacom
//	 * .rule.core.engine.RuleEngineServer, java.lang.String)
//	 */
//
//	public Map<String, String> getRules(String ruleSetPath)
//			throws Exception {
//		logger.debug("enter.");
//		if (ruleSetPath == null) {
//			throw new EngineServiceException("ruleSetPath or ruleId is null.");
//		}
//		try {
//			byte[] ruleSetArchive = resSAO.getRulesetArchive(ruleSetPath);
//			ZipInputStream zipIn = new ZipInputStream(new java.io.ByteArrayInputStream(ruleSetArchive));
//
//			Map<String, String> results = new java.util.HashMap<String, String>();
//			for (ZipEntry entry = zipIn.getNextEntry(); entry != null; entry = zipIn.getNextEntry()) {
//				logger.debug("Read files in zip.");
//				if (entry.isDirectory()) {
//					if (logger.isDebugEnabled()) {
//						logger.debug("entery is directory." + entry.getName());
//					}
//					continue;
//				}
//				String name = entry.getName();
//				if (logger.isDebugEnabled()) {
//					logger.debug("name=" + name);
//				}
//				if (name.startsWith(IrlConstants.IRL_ROOT) && name.endsWith(IrlConstants.END_OF_IRL)) {
//					name = name.replaceFirst(IrlConstants.IRL_ROOT, "");
//					name = name.substring(1, name.length() - IrlConstants.END_OF_IRL.length());
//				} else {
//					continue;
//				}
//				name = name.replaceAll("\\/", IrlConstants.DELIMITER);
//				String ruleId = name.substring(name.lastIndexOf(IrlConstants.DELIMITER) + 1, name.length());
//				String simpleRuleId = ruleId.replaceAll("\\$", "");
//				ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
//				byte[] buff = new byte[64];
//				for (int len = zipIn.read(buff); len != -1; len = zipIn.read(buff)) {
//					byteOutput.write(buff, 0, len);
//				}
//				String irlContent = new String(byteOutput.toByteArray(), IrlConstants.IRL_ENCODING);
//				results.put(simpleRuleId, irlContent);
//			}
//			logger.debug("exit.");
//			return results;
//		} catch (Exception ex) {
//			throw new EngineServiceException(ex);
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see
//	 * com.meyacom.rule.core.engine.service.RuleService#getAllRuleSetPathes()
//	 */
//	@Override
//	public Set<String> getAllRuleSetPathes() throws EngineServiceException {
//		logger.debug("enter.");
//		try {
//			List<String> result = resSAO.getAllRuleSetPath();
//			if (logger.isDebugEnabled()) {
//				logger.debug("Result=" + result);
//			}
//			logger.debug("exit.");
//			return new java.util.HashSet<String>(result);
//		} catch (ResSAOException ex) {
//			throw new EngineServiceException("getAllRuleSetPathes failed", ex);
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see com.meyacom.rule.core.engine.service.RuleService#getProjects()
//	 */
//	@Override
//	public List<String> getProjects() throws EngineServiceException {
//		logger.debug("enter.");
//		try {
//			List<String> result = rtsSAO.getProjects();
//			if (result == null) {
//				result = java.util.Collections.emptyList();
//			}
//			logger.debug("exit.");
//			return result;
//		} catch (Exception ex) {
//			throw new EngineServiceException("getProjects failed", ex);
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see com.meyacom.ruleapp.core.engine.service.RuleService#
//	 * getAllShortRuleSetPathes ()
//	 */
//	@Override
//	public Set<String> getAllShortRuleSetPathes() throws EngineServiceException {
//		logger.debug("enter.");
//		try {
//			List<String> pathes = resSAO.getAllRuleAppsAndRuleSetsName();
//			Set<String> result = new java.util.HashSet<String>(pathes);
//			logger.debug("exit.");
//			return result;
//		} catch (Exception ex) {
//			throw new EngineServiceException("getAllShortRuleSetPathes failed",
//					ex);
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see
//	 * com.meyacom.ruleapp.core.engine.service.RuleService#getLastestRuleSetPath
//	 * (java.lang.String)
//	 */
//	@Override
//	public String getLastestRuleSetPath(String shortRuleSetPath)
//			throws EngineServiceException {
//		logger.debug("开始获取最新规则集路径");
//		try {
//			String latestRuleSetPath = resSAO
//					.getLastestRuleSetPath(shortRuleSetPath);
//			if (logger.isDebugEnabled()) {
//				logger.debug("得到规则集路径" + latestRuleSetPath);
//			}
//			logger.debug("获取最新规则集路径结束.");
//			return latestRuleSetPath;
//		} catch (Exception ex) {
//			throw new EngineServiceException("获取最新规则集路径结束失败", ex);
//		}
//	}
//
//	@Override
//	public List<String> getAllBomName(String projectName)
//			throws EngineServiceException {
//		logger.debug("enter.");
//		try {
//			List<String> result = rtsSAO.getAllBomName(projectName);
//			if (logger.isDebugEnabled()) {
//				logger.debug("Result=" + result);
//			}
//			logger.debug("exit.");
//			return new java.util.ArrayList<String>(result);
//		} catch (Exception ex) {
//			throw new EngineServiceException("getAllBomName failed", ex);
//		}
//	}
//
//	@Override
//	public Map<String, String> getDecisionTables(String ruleSetPath)
//			throws EngineServiceException {
//		logger.debug("enter.");
//		if (ruleSetPath == null) {
//			throw new EngineServiceException("ruleSetPath or ruleId is null.");
//		}
//		try {
//			byte[] ruleSetArchive = resSAO.getRulesetArchive(ruleSetPath);
//			ZipInputStream zipIn = new ZipInputStream(
//					new java.io.ByteArrayInputStream(ruleSetArchive));
//
//			Map<String, String> results = new LinkedHashMap<String, String>();
//			for (ZipEntry entry = zipIn.getNextEntry(); entry != null; entry = zipIn
//					.getNextEntry()) {
//				logger.debug("Read files in zip.");
//				if (entry.isDirectory()) {
//					if (logger.isDebugEnabled()) {
//						logger.debug("entery is directory." + entry.getName());
//					}
//					continue;
//				}
//				String name = entry.getName();
//				// System.out.println("entryname:" + name);
//				if (logger.isDebugEnabled()) {
//					logger.debug("name=" + name);
//				}
//				//这里只取初始化下的决策表用来做规则影响关系展示
//				if (name.startsWith(IrlConstants.IRL_ROOT)
//						&& name.contains("初始化")
//						&& name.endsWith(IrlConstants.END_OF_DT)) {
//					name = name.replaceFirst(IrlConstants.IRL_ROOT, "");
//					name = name.substring(1, name.length()
//							- IrlConstants.END_OF_DT.length());
//				} else {
//					continue;
//				}
//				if (name.contains("投保系统相对应渠道")) {// 投保系统相对应渠道决策表没有进行规则初始化操作
//					continue;
//				}
//				name = name.replaceAll("\\/", IrlConstants.DELIMITER);
//				//由于决策表名一致,采用加上包名做区分
//				String tempName = name.replaceAll("\\$", "");
//				String dtName = tempName.replaceAll("48", "0");//48是0的ascii值
//				System.out.println("dtname:" + dtName);
//				// System.out.println("simpleid" + simpleRuleId);
//				ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
//				byte[] buff = new byte[64];
//				for (int len = zipIn.read(buff); len != -1; len = zipIn
//						.read(buff)) {
//					byteOutput.write(buff, 0, len);
//				}
//				String irlContent = new String(byteOutput.toByteArray(),
//						IrlConstants.IRL_ENCODING);
//				results.put(dtName, irlContent);
//			}
//			logger.debug("exit.");
//			return results;
//		} catch (Exception ex) {
//			throw new EngineServiceException(ex);
//		}
//	}
//}