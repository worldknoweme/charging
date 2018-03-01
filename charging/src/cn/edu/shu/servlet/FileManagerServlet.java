package cn.edu.shu.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String uri="";
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求参数： 区分不同的操作类型
				String method = request.getParameter("method");
				if ("upload".equals(method)) {
					// 上传
					upload(request,response);
				}
				
				else if ("downlist".equals(method)) {
					// 进入下载列表
					downList(request,response);
				}
				
				else if ("down".equals(method)) {
					// 下载
					down(request,response);
				}	

	}


//1.文件列表
	private void downList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 实现思路：先获取upload目录下所有文件的文件名，再保存；跳转到down.jsp列表展示
		
				//1. 初始化map集合Map<包含唯一标记的文件名, 简短文件名>  ;
				Map<String,String> fileNames = new HashMap<String,String>();
				
				//2. 获取上传目录，及其下所有的文件的文件名
				String bathPath = getServletContext().getRealPath("/upload");
				// 目录
				File file = new File(bathPath);
				// 目录下，所有文件名
				String list[] = file.list();
				// 遍历，封装
				if (list != null && list.length > 0){
					for (int i=0; i<list.length; i++){
						// 全名
						String fileName = list[i];
						// 短名
						String shortName = fileName.substring(fileName.lastIndexOf("#")+1);
						// 封装
						fileNames.put(fileName, shortName);
					}
				}
				
				// 3. 保存到request域
				request.setAttribute("fileNames", fileNames);
				// 4. 转发
				request.getRequestDispatcher("/WEB-INF/downList.jsp").forward(request, response);

}
//2.上传
	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileItemFactory fac =new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(fac);
		upload.setSizeMax(5*1024*1024*1024);
		upload.setHeaderEncoding("UTF-8");	
		if(upload.isMultipartContent(request)){
			try {
				List<FileItem> list=upload.parseRequest(request);
				for(FileItem item : list){
					// 判断：普通文本数据
					if(item.isFormField()){
						String fileName=item.getFieldName();
						String value=item.getString();
						System.out.println(fileName+value);
						// 文件表单项
					}else{
						String name=item.getName();
						// ----处理上传文件名重名问题----
						// a1. 先得到唯一标记
						String id = UUID.randomUUID().toString();
						// a2. 拼接文件名
						name = id + "#" + name;	
						String basePath=this.getServletContext().getRealPath("/upload");
						File file=new File(basePath,name);
						try {
							item.write(file);
						} catch (Exception e) {
							e.printStackTrace();
						}
						item.delete();
					}
				}
			} catch (FileUploadException e) {
				uri="/WEB-INF/error.jsp";
				e.printStackTrace();
				request.getRequestDispatcher(uri).forward(request, response);
			}
			
		}
		else{
			System.out.println("当前表单不是文件上传表单，不处理");
		}
		uri="/fileManager.jsp";
		
		request.getRequestDispatcher(uri).forward(request, response);
}
	
	/**
	 *  3. 处理下载
	 */
	private void down(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		// 获取用户下载的文件名称(url地址后追加数据,get)
		String fileName = request.getParameter("fileName");
		fileName = new String(fileName.getBytes("ISO8859-1"),"UTF-8");
		
		// 先获取上传目录路径
		String basePath = getServletContext().getRealPath("/upload");
		// 获取一个文件流
		InputStream in = new FileInputStream(new File(basePath,fileName));
		
		// 如果文件名是中文，需要进行url编码
		fileName = URLEncoder.encode(fileName, "UTF-8");
		// 设置下载的响应头
		response.setHeader("content-disposition", "attachment;fileName=" + fileName);
		
		// 获取response字节流
		OutputStream out = response.getOutputStream();
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = in.read(b)) != -1){
			out.write(b, 0, len);
		}
		// 关闭
		out.close();
		in.close();
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}

