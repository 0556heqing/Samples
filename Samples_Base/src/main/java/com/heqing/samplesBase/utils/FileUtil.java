package com.heqing.samplesBase.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.heqing.samplesBase.bean.io.ScrambledInputStream;
import com.heqing.samplesBase.bean.io.ScrambledOutputStream;

public class FileUtil {

    public static boolean checkDiskSpace(String path, float limit, String errorMessage, boolean forbitC) { 
        File f = new File(path);
        if (forbitC && path.toLowerCase().startsWith("c:")) { 
            System.out.println("警告:我们不推荐您使用C盘(系统盘)来作为数据目录，使用C盘会造成系统性能下降，在电脑损坏或重装系统的时候容易造成数据丢失。");
            return false;
        }
        float gb = f.getFreeSpace()/1024f/1024f;
        if (gb<10240) { 
        	System.out.println("警告:"+path+"剩余空间只有"+gb+"GB! ");
            return false;
        }
        return true;
    }
    
    public static void createFile(String path, String fileName, byte[] content) throws IOException {
        createFile(new File(path+"/"+fileName),content);
    }
    
    public static void createFile(String path, byte[] content) throws IOException {
        createFile(new File(path),content);
    }
    
    public static void createFile(File f, byte[] content) throws IOException {
        File dir = f.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream fout = new FileOutputStream(f);
        BufferedOutputStream out = new BufferedOutputStream(fout);
        out.write(content);
        out.flush();
        out.close();
        fout.close();
    }

    public static void copyResourceToFile(String resourceName, File target) throws IOException { 
        InputStream in = FileUtil.class.getResourceAsStream(resourceName);
        byte[] buffer = new byte[1024];
        FileOutputStream fout = new FileOutputStream(target);
        while(true) { 
            int c = in.read(buffer);
            if (c<=0) break;
            fout.write(buffer,0,c);
        }
        fout.flush();
        fout.close();
    }
    
    public static byte[][] getFileContents(File path, FilenameFilter filter) throws IOException { 
        if (!path.exists()) return new byte[0][];
        File[] files = path.listFiles(filter);
        byte[][] data = new byte[files.length][];
        for(int i=0; i<files.length; ++i) { 
            data[i] = getBytes(files[i]);
        }
        return data;
    }
    
    public static byte[][] decodeConcatenatedFiles(File f) throws IOException {
        FileInputStream fin = new FileInputStream(f);
        DataInputStream din = new DataInputStream(new BufferedInputStream(fin));
        int len = din.readInt();
        byte[][] out = new byte[len][];
        for (int i = 0; i < len; ++i) {
            byte[] c = new byte[din.readInt()];
            din.readFully(c);
            out[i] = c;
        }
        fin.close();
        return out;
    }

    public static void concatTempFiles(Collection<File> files, File output) throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream(output);
        DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(fout));
        dout.writeInt(files.size());
        for (File f : files) {
            byte[] content = FileUtil.getBytes(new File(f.getPath()));
            dout.writeInt(content.length);
            dout.write(content);
        }
        dout.flush();
        fout.close();
    }

    public static void concatFiles(Collection<File> files, File output) throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream(output);
        DataOutputStream dout = new DataOutputStream(fout);
        dout.writeInt(files.size());
        for (File f : files) {
            byte[] content = FileUtil.getBytes(f);
            dout.writeInt(content.length);
            dout.write(content);
        }
        dout.flush();
        fout.close();
    }

    public static void concatFiles(File[] files, File output) throws FileNotFoundException, IOException {
        FileOutputStream fout = new FileOutputStream(output);
        DataOutputStream dout = new DataOutputStream(fout);
        dout.writeInt(files.length);
        for (File f : files) {
            byte[] content = FileUtil.getBytes(f);
            dout.writeInt(content.length);
            dout.write(content);
        }
        dout.flush();
        fout.close();
    }

    public static void getAllFilesRecursive(File dir, ArrayList<File> result) {
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                getAllFilesRecursive(f, result);
            }
            result.add(f);
        }
    }

    public static final FilenameFilter DCM_FILE_FILTER = new FilenameFilter() {
        public boolean accept(File f, String fname) {
            return fname.endsWith(".dcm");
        }
    };

    public static byte[] readProtectedContent(InputStream in, long key) throws IOException {
        ScrambledInputStream sin = new ScrambledInputStream(in, key);
        GZIPInputStream gin = new GZIPInputStream(sin);
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        while (true) {
            int c = gin.read(buffer);
            if (c < 0) {
                break;
            }
            bout.write(buffer, 0, c);
        }
        return bout.toByteArray();
    }

    public static void writeProtectedContent(byte[] content, OutputStream out, long key) throws IOException {
        ScrambledOutputStream sout = new ScrambledOutputStream(out, key);
        GZIPOutputStream gout = new GZIPOutputStream(sout);
        gout.write(content);
        gout.flush();
        gout.finish();
        out.flush();
    }
    
    public static void writeProtectedContent(File fileIn, OutputStream out, long key) throws IOException {
        byte[] content = FileUtil.getBytes(fileIn);
        writeProtectedContent(content,out,key);
    }

    public static byte[] getGZIPCompressedContent(File f) throws IOException {
        FileInputStream fin = new FileInputStream(f);
        BufferedInputStream bin = new BufferedInputStream(fin);
        byte[] temp = new byte[(int) f.length()];
        long c = 0;
        while (c < temp.length) {
            c += bin.read(temp);
        }
        fin.close();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        GZIPOutputStream gout = new GZIPOutputStream(bout);
        gout.write(temp);
        gout.flush();
        gout.close();
        fin.close();
        return bout.toByteArray();
    }

    public static void createFile(String fileName, byte[] header, byte[] data) throws IOException {
        FileOutputStream fout = new FileOutputStream(fileName);
        fout.write(header);
        fout.flush();
        fout.write(data);
        fout.flush();
        fout.close();
    }

    public static void setupStandardStream() {
        if ("true".equalsIgnoreCase(System.getProperty("redirectIO"))) {
            try {
                System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(System.getProperty("BaseDir") + "/output.log"))));
                System.setErr(new PrintStream(new BufferedOutputStream(new FileOutputStream(System.getProperty("BaseDir") + "/error.log"))));
            } catch (Exception ex) {
            }
        }
    }

    public static void deleteFile(File file) {
        if (file.exists()) {
            if (!file.isDirectory()) {
                file.delete();
            } else {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
                file.delete();
            }
        }

    }

    public static void deleteFile(String filePath) {
        deleteFile(new File(filePath));
    }

    public static void clearFolder(String path) {
        File[] fs = new File(path).listFiles();
        for (File f : fs) {
            f.delete();
        }
    }

    public static byte[] getBytes(File source) throws IOException {
        long length = source.length();
        if (length==0) return new byte[0];
        FileInputStream fin = new FileInputStream(source);
        BufferedInputStream in = new BufferedInputStream(fin, (int)length);
        byte[] data = new byte[(int) source.length()];
        in.read(data, 0, data.length);
        fin.close();
        return data;
    }

    public static void copy(File source, File dest) throws IOException {
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        FileInputStream fin = new FileInputStream(source);
        BufferedInputStream in = new BufferedInputStream(fin);
        FileOutputStream fout = null;
        if (dest.isDirectory()) {
            fout = new FileOutputStream(dest.getAbsolutePath() + "/" + source.getName());
        } else {
            fout = new FileOutputStream(dest);
        }
        BufferedOutputStream out = new BufferedOutputStream(fout);
        int a;
        while ((a = in.read()) != -1) {
            out.write(a);
        }
        out.flush();
        fout.flush();
        fout.close();
        fin.close();
    }

    public static void copy(File source, String dest) throws IOException {
        FileInputStream fin = new FileInputStream(source);
        BufferedInputStream in = new BufferedInputStream(fin);
        FileOutputStream fout = new FileOutputStream(dest);
        BufferedOutputStream out = new BufferedOutputStream(fout);
        int a;
        while ((a = in.read()) != -1) {
            out.write(a);
        }
        out.flush();
        fout.flush();
        fout.close();
        fin.close();
    }
    
    public static void copy(File source, String dest, boolean append) throws IOException {
        FileInputStream fin = new FileInputStream(source);
        BufferedInputStream in = new BufferedInputStream(fin);
        FileOutputStream fout = new FileOutputStream(dest,append);
        BufferedOutputStream out = new BufferedOutputStream(fout);
        int a;
        while ((a = in.read()) != -1) {
            out.write(a);
        }
        out.flush();
        fout.flush();
        fout.close();
        fin.close();
    }

    public static File[] getFilesWithExtension(File[] files, String ext) {
        ArrayList<File> fs = new ArrayList<File>();
        for (File f : files) {
            if (f.getName().toLowerCase().endsWith(ext)) {
                fs.add(f);
            }
        }
        File[] data = new File[fs.size()];
        fs.toArray(data);
        return data;
    }

    public static void copyTree(String source, String dest) throws IOException {
        File target = new File(dest);
        if (!target.exists()) {
            target.mkdirs();
        }
        copyTree(new File(source), target);
    }

    public static void copyTree(File dir, File targetDir) throws IOException {
        File[] files = dir.listFiles();
        for (File f : files) {
            System.out.println(f);
            if (f.isDirectory()) {
                File subDir = new File(targetDir.getAbsolutePath() + "/" + f.getName());
                if (!subDir.exists()) {
                    subDir.mkdirs();
                }
                copyTree(f, subDir);
            } else {
                copy(f, targetDir.getAbsolutePath() + "/" + f.getName());
            }
        }
    }

    public static void copy(String source, String dest) throws IOException {
        FileInputStream fin = new FileInputStream(source);
        BufferedInputStream in = new BufferedInputStream(fin);
        FileOutputStream fout = new FileOutputStream(dest);
        BufferedOutputStream out = new BufferedOutputStream(fout);
        int a;
        while ((a = in.read()) != -1) {
            out.write(a);
        }
        out.flush();
        fout.flush();
        fout.close();
        fin.close();
    }

    /**
     * 递归查找文件
     *
     * @param baseDirName 查找的文件夹路径
     * @param targetFileName 需要查找的文件名
     * @param fileList 查找到的文件集合
     */
    public static void findFiles(String baseDirName, String targetFileName, List<File> fileList) {

        File baseDir = new File(baseDirName);       // 创建一个File对象   
        if (!baseDir.exists() || !baseDir.isDirectory()) {  // 判断目录是否存在   
            System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");
            return;
        }
        String tempName = null;
        //判断目录是否存在      
        File tempFile;
        File[] files = baseDir.listFiles();
        if (files == null) {
            return;
        }
        for (int i = 0; i < files.length; i++) {
            tempFile = files[i];
            if (tempFile.isDirectory()) {
                findFiles(tempFile.getAbsolutePath(), targetFileName, fileList);
            } else if (tempFile.isFile()) {
                tempName = tempFile.getName();
                if (wildcardMatch(targetFileName, tempName)) {
                    // 匹配成功，将文件名添加到结果集   
                    fileList.add(tempFile.getAbsoluteFile());
                }
            }
        }
    }

    /**
     * 通配符匹配
     *
     * @param pattern 通配符模式
     * @param str 待匹配的字符串
     * @return 匹配成功则返回true，否则返回false
     */
    private static boolean wildcardMatch(String pattern, String str) {
        int patternLength = pattern.length();
        int strLength = str.length();
        int strIndex = 0;
        char ch;
        for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
            ch = pattern.charAt(patternIndex);
            if (ch == '*') {
                //通配符星号*表示可以匹配任意多个字符      
                while (strIndex < strLength) {
                    if (wildcardMatch(pattern.substring(patternIndex + 1),
                            str.substring(strIndex))) {
                        return true;
                    }
                    strIndex++;
                }
            } else if (ch == '?') {
                //通配符问号?表示匹配任意一个字符      
                strIndex++;
                if (strIndex > strLength) {
                    //表示str中已经没有字符匹配?了。      
                    return false;
                }
            } else {
                if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
                    return false;
                }
                strIndex++;
            }
        }
        return (strIndex == strLength);
    }

    public static boolean isVideoFile(File file){
        String fileName = file.getName();
        if(fileName!=null && !fileName.isEmpty()){
            String filetype = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
            switch(filetype){
                case "avi":
                case "wmv":
                case "3gp":
                case "mpeg":
                case "mkv":
                case "flv":
                case "rmvb":
                case "mp4":
                    return true;
            }
        }
        return false;
    }
    
    public static boolean isImageFile(File file){
        String fileName = file.getName();
        if(fileName!=null && !fileName.isEmpty()){
            String filetype = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
            switch(filetype){
                case "jpg":
                case "jpeg":
                case "png":
                case "bmp":
                case "gif":
                    return true;
            }
        }
        return false;
    }

    //根据byte数组，生成文件 
    public static void createFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                   e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
    } 
    
    public static String getExtension(File f) { 
        String name = f.getName();
        return name.substring(name.lastIndexOf(".")+1);
    }
    
    public static String readLines(Reader in) {
        StringBuilder buff = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(in);
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                buff.append(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return buff.toString();
    }
    
    public static String readLinesFromStream(InputStream stream) { 
        return readLines(new InputStreamReader(stream));
    }
    
    public static String readLinesFromFile(String fileName) {
        File file = new File(fileName);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String out = readLines(reader);
            fr.close();
            return out;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try { fr.close(); } catch (IOException e1) {}
            }
        }
        return null;
    }
    
    public static String loadFromStream(InputStream in) { 
        StringBuilder buff = new StringBuilder();
        try {
            LineNumberReader lines = new LineNumberReader(new InputStreamReader(in));
            String line = null;
            while((line=lines.readLine())!=null) { 
                buff.append(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return buff.toString();
    }
    
}
