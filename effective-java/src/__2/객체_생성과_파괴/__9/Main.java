package __2.객체_생성과_파괴.__9;

public class Main {
	public static void main(String[] args) {
	}

	//case1) try~catch 
//	static void copy(String src, String dst) throws IOException {
//        InputStream in = new FileInputStream(src);
//        try {
//            OutputStream out = new FileOutPutStream(dst);
//            try {
//                byte[] buf = new byte[BUFFER_SIZE];
//                int n;
//                while ((n = in .read(buf)) >= 0) out.write(buf, 0, n);
//            } finally {
//                out.close();
//            } finally {
//                in .close();
//            }
//        }
//	}
	
	
	//case2) try-with-resources
//	static void copy(String src, String dst) throws IOException {
//	    InputStream in = new FileInputStream(src);
//	    try (InputStream in = new FileInputStream(src); OutputStream out = new FileOutPutStream(dst)) {
//	        byte[] buf = new byte[BUFFER_SIZE];
//	        int n;
//	        while ((n = in .read(buf)) >= 0) out.write(buf, 0, n);
//	    }
//	}

}

/*

---
instead of try~catch, use try-with-resources


try~catch 쓰면, 반환하는 자원이 2개 이상이어서 nested하게 처리할 때, 문제 발생

코드가 깔끔하지 못하고, nested안쪽 두번째 자원에서 에러나면, 첫번째 자원의 finally문 처리 안하는 등...





*/