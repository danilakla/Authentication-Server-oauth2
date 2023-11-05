package com.example.authserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthServerApplicationTests {
	private String getFileExtensionFromFileName(String fileName){
		int indexLastPoint=0;
		for (int i = 0; i < fileName.length(); i++) {
			if(fileName.charAt(i)=='.'){
				indexLastPoint=i;
			}
		}

		return  fileName.substring(indexLastPoint+1);
	}
	@Test
	void contextLoads() {
		String d= getFileExtensionFromFileName("312.dasdas.png");
		String dd= getFileExtensionFromFileName("cvxv.mp3");
	}

}
