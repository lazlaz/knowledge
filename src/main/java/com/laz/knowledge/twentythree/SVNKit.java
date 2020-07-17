package com.laz.knowledge.twentythree;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNStatus;
import org.tmatesoft.svn.core.wc.SVNStatusType;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SVNKit {
    
    protected static Logger logger = LoggerFactory.getLogger(SVNKit.class);
     
    // 更新状态 true:没有程序在执行更新，反之则反
    private static Boolean DoUpdateStatus = true;
 
    // 声明SVN客户端管理类
    private static SVNClientManager ourClientManager;
    
    /**
     * 检查任务文件是否存在
     * @param userName svn用户名
     * @param password svn用户密码
     * @param svnUrl 文件存放路径
     * @param path 相对路径
     * @return 结果
     * @throws SVNException svn异常
     */
    public static boolean checkPath(String userName,String password,String svnUrl,String path) throws SVNException {
        DAVRepositoryFactory.setup();
        SVNURL repositoryURL = null;
        boolean isExist = false;
        try {
            repositoryURL = SVNURL.parseURIEncoded(svnUrl);
        } catch (SVNException e) {
            logger.warn("svn地址格式错误",e);
            throw new SVNException(e.getErrorMessage());
        }
        SVNRepository svnRepository = SVNRepositoryFactory.create(repositoryURL);
        ISVNAuthenticationManager isvnAuthenticationManager = SVNWCUtil
                .createDefaultAuthenticationManager(userName, password);
        svnRepository.setAuthenticationManager(isvnAuthenticationManager);
        SVNNodeKind node = null;
        node = svnRepository.checkPath(path, -1);
        if(node == SVNNodeKind.DIR) {
            Collection entries = svnRepository.getDir(path, -1, null, (Collection)null);
            Iterator iterator = entries.iterator();
            if(iterator.hasNext()) {
                SVNDirEntry entry = (SVNDirEntry)iterator.next();
                if(entry.getKind() == SVNNodeKind.FILE) {
                    logger.debug("svn中文件路径:"+svnUrl+"/"+path + "下任务文件存在，文件名称：" +entry.getName());
                    isExist = true;
                }
            }
        }
        logger.debug("检查svn中文件路径:"+svnUrl+"/"+path+"下任务文件是否存在,结果："+isExist);
        return isExist;
    }
    
    /**
     * 增加svn目录
     * @param userName svn用户名
     * @param password svn用户密码
     * @param svnUrl svn地址
     * @param adddir 需要增加的目录
     * @return 执行结果
     * @throws SVNException svn处理异常
     */
    public static Boolean addDir(String userName,String password,String svnUrl, String adddir) throws SVNException {

        DAVRepositoryFactory.setup();
        SVNURL repositoryURL = null;
        try {
            repositoryURL = SVNURL.parseURIEncoded(svnUrl);
        } catch (SVNException e) {
            logger.warn("svn地址格式错误",e);
            throw new SVNException(e.getErrorMessage());
        }
        SVNRepository svnRepository = SVNRepositoryFactory.create(repositoryURL);
        ISVNAuthenticationManager isvnAuthenticationManager = SVNWCUtil
                .createDefaultAuthenticationManager(userName, password);
        svnRepository.setAuthenticationManager(isvnAuthenticationManager);

        ISVNEditor editor = null;
        try {
            editor = svnRepository.getCommitEditor("add dir", null, true, null);
            editor.openRoot(-1);
            // 新增目录
            editor.addDir(adddir, null, -1);
            editor.closeDir();
            editor.closeDir();
            editor.closeEdit();
            logger.debug("在svn路径："+svnUrl+"下增加目录：" + adddir +" 成功。");
        } catch (SVNException e) {
            if(editor != null) {
                editor.abortEdit();
            }
            logger.warn("在svn路径："+svnUrl+"下增加目录：" + adddir+" 失败。",e);
            throw new SVNException(e.getErrorMessage());
        }
        return true;
    }
 
    /**
     * svn检出
     * @param userName svn用户名
     * @param password svn用户密码
     * @param svnUrl svn地址
     * @param filePath 检出到文件路径
     * @return 检出结果
     * @throws SVNException svn处理异常
     */
    public static Boolean checkOut(String userName,String password,String svnUrl, String filePath) throws SVNException {
        // 初始化库。 必须先执行此操作。具体操作封装在setupLibrary方法中。
        /*
         * For using over http:// and https://
         */
        DAVRepositoryFactory.setup();
        /*
         * For using over svn:// and svn+xxx://
         */
        //SVNRepositoryFactoryImpl.setup();
 
        /*
         * For using over file:///
         */
        //FSRepositoryFactory.setup();
 
        // 相关变量赋值
        SVNURL repositoryURL = null;
        try {
            repositoryURL = SVNURL.parseURIEncoded(svnUrl);
        } catch (SVNException e) {
            logger.warn("svn地址格式错误",e);
            throw new SVNException(e.getErrorMessage());
        }
 
        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
 
        // 实例化客户端管理类
        ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions) options, userName, password);
 
        // 要把版本库的内容check out到的目录
        File wcDir = new File(filePath);
 
        // 通过客户端管理类获得updateClient类的实例。
        SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
 
        updateClient.setIgnoreExternals(false);
 
        // 执行check out 操作，返回工作副本的版本号。
        long workingVersion = -1;
        workingVersion = updateClient.doCheckout(repositoryURL, wcDir, SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.INFINITY, false);
 
        logger.debug("把版本：" + workingVersion + " check out 到目录：" + wcDir + "中。");
        return true;
 
    }
 
    /**
     * 清理目录下的svn信息
     * @param userName svn用户名
     * @param password svn用户密码
     * @param filePath 文件夹路径
     * @return 执行结果
     */
    public static Boolean doCleanup(String userName,String password,String filePath) {
        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
        // 实例化客户端管理类
        ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions) options, userName, password);
 
        // 要把版本库的内容check out到的目录
        File wcDir = new File(filePath);
        if (wcDir.exists()) {
            try {
                ourClientManager.getWCClient().doCleanup(wcDir);
            } catch (SVNException e) {
//                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
 
    /**
     * 更新svn
     * @param userName svn用户名
     * @param password svn用户密码
     * @param filePath 文件路径
     * @return int(-1更新失败，1成功，0有程序在占用更新)
     */
    public static int doUpdate(String userName,String password,String filePath) {
        if (!DoUpdateStatus) {
            logger.debug("更新程序已经在运行中，不能重复请求！");
            return 0;
        }
        DoUpdateStatus = false;
        /*
         * For using over http:// and https://
         */
        try {
            DAVRepositoryFactory.setup();
 
            ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
            // 实例化客户端管理类
            ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions) options, userName, password);
            // 要更新的文件
            File updateFile = new File(filePath);
            // 获得updateClient的实例
            SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
            updateClient.setIgnoreExternals(false);
            // 执行更新操作
            long versionNum = updateClient.doUpdate(updateFile, SVNRevision.HEAD, SVNDepth.INFINITY, false, false);
            logger.debug("工作副本更新后的版本：" + versionNum);
            DoUpdateStatus = true;
            return 1;
        } catch (SVNException e) {
            DoUpdateStatus = true;
//            e.printStackTrace();
            return -1;
        }
    }
 
    /**
     * 提价svn
     * @param userName svn用户名
     * @param password svn用户密码
     * @param filePath 待提交文件目录
     * @return 提交结果
     * @throws SVNException svn异常
     */
    public static Boolean doCommit(String userName,String password,String filePath) throws SVNException {
        // 注意：执行此操作要先执行checkout操作。因为本地需要有工作副本此范例才能运行。
        // 初始化支持svn://协议的库 和http:// and https://
        SVNRepositoryFactoryImpl.setup();
        DAVRepositoryFactory.setup();
 
        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
        // 实例化客户端管理类
        ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions) options, userName, password);
        // 要提交的文件夹
        File commitFile = new File(filePath);
        // 获取此文件的状态（是文件做了修改还是新添加的文件？）
        SVNStatus status = null;
        File addFile = null;
        if (filePath != null && filePath.trim().length() > 0) {
            addFile = new File(filePath);
            status = ourClientManager.getStatusClient().doStatus(addFile, true);
            // 如果此文件是新增加的则先把此文件添加到版本库，然后提交。
            if (null == status || status.getContentsStatus() == SVNStatusType.STATUS_UNVERSIONED) {
                // 把此文件增加到版本库中
                ourClientManager.getWCClient().doAdd(addFile, false, false, false, SVNDepth.INFINITY, false, false);
                logger.debug("add,新增文件添加到版本库。");
            }
            // 提交此文件
            ourClientManager.getCommitClient().doCommit(new File[] { commitFile }, true, "", null, null, true, false,
                    SVNDepth.INFINITY);
            logger.debug("commit,提交本地路径： " + filePath +"文件成功。");
        }
        // 如果此文件不是新增加的，直接提交。
        else {
            ourClientManager.getCommitClient().doCommit(new File[] { commitFile }, true, "", null, null, true, false,
                    SVNDepth.INFINITY);
            logger.debug("commit,提交本地路径： " + filePath +"文件成功。");
        }
        return true;
    }
 
    /**
     * 将文件导入并提交到svn 同路径文件要是已经存在将会报错
     * 
     * @param userName svn用户名
     * @param password svn用户密码
     * @param dirPath 文件所在文件夹的路径
     * @return Boolean 返回结果
     * @throws SVNException svn异常
     */
    public static Boolean doImport(String userName,String password,String dirPath) throws SVNException {
        /*
         * For using over http:// and https://
         */
        DAVRepositoryFactory.setup();
        // 相关变量赋值
        SVNURL repositoryURL = null;
        try {
            repositoryURL = SVNURL.parseURIEncoded(dirPath);
        } catch (SVNException e) {
            logger.warn("svn地址格式错误",e);
            throw new SVNException(e.getErrorMessage());
        }
 
        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
        // 实例化客户端管理类
        ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions) options, userName, password);
        // 要把此目录中的内容导入到版本库
        File impDir = new File(dirPath);
        // 执行导入操作
        SVNCommitInfo commitInfo = ourClientManager.getCommitClient().doImport(impDir, repositoryURL, "import operation!", null, false, false, SVNDepth.INFINITY);
        
        logger.debug(commitInfo.toString());
        return true;
    }
 
}