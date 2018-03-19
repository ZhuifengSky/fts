package hess;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.file.service.impl.FileUpServiceImpl;

public class TestImageUp {

	public static void main(String[] args) {
		FileUpServiceImpl service = new FileUpServiceImpl();
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream("C:/Users/pc-zw/Pictures/2008625204205.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = service.uploadFile( "บวบว", "jpg",is);
	}
}
