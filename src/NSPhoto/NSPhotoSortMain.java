package NSPhoto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NSPhotoSortMain {
	
	private static int count = 0;

	public static void main(String[] args) {

		String sourceDir = "E:\\NS备份\\Nintendo\\Album";
		String resultDir = "D:\\Users\\Logic\\Pictures\\Switch";
		String[] split = sourceDir.split("\\\\");

		NSPhotoSortFun(sourceDir, resultDir,split.length,0);
		System.out.println(sourceDir+"下"+count+"个NS照片全部复制整理完成！");
	}

	@SuppressWarnings("unused")
	public static void NSPhotoSortFun(String source, String resultDir,int len,int x) {
		FileInputStream is = null;
		FileOutputStream os = null;
		try {

			File f = new File(source);
			if (f.isDirectory()) {
				File[] listFiles = f.listFiles();
				for (int i = 0; i < listFiles.length; i++) {
					File file = listFiles[i];
					NSPhotoSortFun(file.getPath(), resultDir,len,i);
				}
			} else {
				String filename = f.getPath();
				String[] split = filename.split("\\\\");
				String suffix = split[len+3].split("\\.")[1];
				File resultFile = new File(resultDir+File.separator+"NSPhoto"+"-"+split[len]+split[len+1]+split[len+2]+"-"+(++x)+"."+suffix);
				is = new FileInputStream(f);
				os = new FileOutputStream(resultFile);
				byte[] b = new byte[2048];
				int i = 0;
				while ((i = is.read(b)) != -1) {
					os.write(b);
					os.flush();
				}
				count++;
				System.out.println("["+count+"]"+filename+"--->"+resultFile.getPath()+"复制成功！");
			}
		} catch (FileNotFoundException e) {
			System.out.println("文件或文件夹不存在！请检查！");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Excetion!");
			e.printStackTrace();
		} finally {
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					System.out.println("输出流关闭异常！");
					e.printStackTrace();
				} finally {
					os = null;
				}
			}
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					System.out.println("输入流关闭异常！");
					e.printStackTrace();
				} finally {
					is = null;
				}
			}
			
		}

	}

}
