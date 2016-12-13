package com.bujue.dailybenefit.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * 文件工具类
 */
public class FileUtil {

    private static final String TAG = "FileUtil";
    private static String SDCardRoot;

    static {
        // 获取SD卡路径
        SDCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取sdcard路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return SDCardRoot;
    }

    /**
     * 获取/data/data/files/目录
     *
     * @return
     */
    public static String getDataFilePath(Context context) {
        if (context == null) {
            return "";
        }
        return context.getFilesDir().getAbsolutePath() + File.separator;
    }

    /**
     * 创建目录
     *
     * @param dir
     */
    public static File createSDDir(String dir) {
        File dirFile = new File(SDCardRoot + dir + File.separator);
        dirFile.mkdirs();
        return dirFile;
    }

    /**
     * 检测文件是否存在
     */
    public static boolean isFileExist(String fileName, String path) {
        File file = new File(SDCardRoot + path + File.separator + fileName);
        return file.exists();
    }

    /**
     * 将数据写入/data/data
     *
     * @param dirName
     * @param fileName
     * @param data
     * @return
     */
    public static boolean writeToData(String dirName, String fileName, String data) {
        FileOutputStream fileOutputStream = null;
        try {
            File mgjFile = new File(dirName);
            if (!mgjFile.exists()) {
                mgjFile.mkdirs();
            }
            File configFile = new File(fileName);
            if (!configFile.exists()) {
                configFile.createNewFile();
            }
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(data.getBytes(Charset.forName("UTF-8")));
            fileOutputStream.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取文件内容
     *
     * @param fileName
     * @return
     */
    public static String getFileContent(String fileName) {
        if (fileName == null || TextUtils.isEmpty(fileName)) {
            return null;
        }

        File file = new File(fileName);
        if (file != null && !file.exists()) {
            return null;
        }

        FileInputStream fin = null;
        try {
            fin = new FileInputStream(fileName);
            int length = fin.available();
            byte[] bytes = new byte[length];
            fin.read(bytes);
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取Assets中的配置
     * @param context
     * @param filePath
     * @return
     */
    public static String getAssetsContent(Context context,String filePath){
        if (context == null) {
            return null;
        }
        InputStream inputStream = null;
        try {
            AssetManager assetManager = context.getAssets();
            inputStream = assetManager.open(filePath);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件
     *
     * @param path
     */
    public static void delete(String path) {
        File file = new File(path);
        if (file != null && file.exists()) {
            delete(file);
        }
    }

    /**
     * 删除文件或文件夹
     *
     * @param file
     */
    public static void delete(File file) {
        try {
            if (file == null || !file.exists()) {
                return; // 不存在直接返回
            }

            if (file.isFile()) {
                file.delete(); // 若是文件则删除后返回
                return;
            }

            // 若是目录递归删除后,并最后删除目录后返回
            if (file.isDirectory()) {
                File[] childFiles = file.listFiles();
                if (childFiles == null || childFiles.length == 0) {
                    file.delete(); // 如果是空目录，直接删除
                    return;
                }

                for (int i = 0; i < childFiles.length; i++) {
                    delete(childFiles[i]); // 递归删除子文件或子文件夹
                }
                file.delete(); // 删除最后的空目录
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
