package test;
		public class dilmpl{
			public static void main(String[] args) {
			int su = sun(5);
			System.out.println(su);
			}
		public static int sun(int n) {
			int s = 1;
			for (int i = 1; i <=n; i++) {
				s=s*i;
			}
			return s;
			
		}
		}

