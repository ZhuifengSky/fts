package hess;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.file.service.impl.FileUpServiceImpl;

public class TestFileUp {

	public static void main(String[] args) {
		FileUpServiceImpl service = new FileUpServiceImpl();
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream("G://personalSettleAppyForm.jsp"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = service.uploadFile( "บวบว", "doc",is);
	}
}
