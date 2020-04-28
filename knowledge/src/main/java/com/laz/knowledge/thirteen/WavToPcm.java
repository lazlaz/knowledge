package com.laz.knowledge.thirteen;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;



public class WavToPcm {
	 /**
     * WAV转PCM文件
     * @param wavfilepath wav文件路径
     * @param pcmfilepath pcm要保存的文件路径及文件名
     * @return
     */
    public static String convertAudioFiles(String wavfilepath,String pcmfilepath){
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        try {
            fileInputStream = new FileInputStream(wavfilepath);
            fileOutputStream = new FileOutputStream(pcmfilepath);
            byte[] wavbyte = InputStreamToByte(fileInputStream);
            System.out.println("wav:");
//            for (byte b : wavbyte) {
//				System.out.print(b+" ");
//			}
            System.out.println(wavbyte.length);
            byte[] pcmbyte = Arrays.copyOfRange(wavbyte, 44, wavbyte.length);
            System.out.println();
            System.out.println("pcm:");
//            for (byte b : wavbyte) {
//				System.out.print(b+" ");
//			}
            System.out.println(pcmbyte.length);
            fileOutputStream.write(pcmbyte);
            IOUtils.closeQuietly(fileInputStream);
            IOUtils.closeQuietly(fileOutputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pcmfilepath;
    }
    
    /**
     * WAV转PCM文件
     * @param wavfilepath wav文件路径
     * @param pcmfilepath pcm要保存的文件路径及文件名
     * @return
     */
    public static String convertAudioFiles2(String wavfilepath,String pcmfilepath){
        FileInputStream fileInputStream;
        FileInputStream fileOutputStream;
        try {
            fileInputStream = new FileInputStream(wavfilepath);
            fileOutputStream = new FileInputStream(pcmfilepath);
            byte[] wavbyte = InputStreamToByte(fileInputStream);
            byte[] wavbyte2 = InputStreamToByte(fileOutputStream);
            System.out.println(wavbyte.length);
            System.out.println(wavbyte2.length);
            IOUtils.closeQuietly(fileInputStream);
            IOUtils.closeQuietly(fileOutputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pcmfilepath;
    }
    /**
     * 输入流转byte二进制数据
     * @param fis
     * @return
     * @throws IOException
     */
    private static byte[] InputStreamToByte(FileInputStream fis) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        long size = fis.getChannel().size();
        byte[] buffer = null;
        if (size <= Integer.MAX_VALUE) {
            buffer = new byte[(int) size];
        } else {
            buffer = new byte[8];
            for (int ix = 0; ix < 8; ++ix) {
                int offset = 64 - (ix + 1) * 8;
                buffer[ix] = (byte) ((size >> offset) & 0xff);
            }
        }
        int len;
        while ((len = fis.read(buffer)) != -1) {
            byteStream.write(buffer, 0, len);
        }
        byte[] data = byteStream.toByteArray();
        IOUtils.closeQuietly(byteStream);
        return data;
    }
 
    public static void main(String[] args) {
        String wavFilePath="D:\\develop\\workspace\\test2\\test222.wav";
        String pcmFilePath="D:\\develop\\workspace\\test2\\test222.pcm";
        convertAudioFiles2(wavFilePath,pcmFilePath);
        System.out.println("OK");
    }
}
