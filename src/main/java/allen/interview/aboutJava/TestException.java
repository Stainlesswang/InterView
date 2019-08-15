package allen.interview.aboutJava;

/**
 * @author WangJianQiang
 * @Description:
 * 异常的嵌套捕获:
 * 捕获异常时分层,最内层被捕获到,上层的catch块是不会走的,只会执行finally代码
 *
 * @date 2019年03月08日 17:08
 */
public  class TestException {
	public TestException() {
	}

	boolean testEx() throws Exception {
		boolean ret = true;
		try {
			ret = testEx1();
		} catch (Exception e) {
			System.out.println("testEx, catch exception");
			ret = false;
			throw e;
		} finally {
			System.out.println("testEx, finally; return value=" + ret);
			return ret;
		}
	}

	boolean testEx1() throws Exception {
		boolean ret = true;
		try {
			ret = testEx2();
			if (!ret) {
				return false;
			}
			System.out.println("testEx1, at the end of try");
			return ret;
		} catch (Exception e) {
			System.out.println("testEx1, catch exception");
			ret = false;
			throw e;
		} finally {
			System.out.println("testEx1, finally; return value=" + ret);
			return ret;
		}
	}

	boolean testEx2() throws Exception {
		boolean ret = true;
		try {
			int b = 12;
			int c;
			for (int i = 2; i >= -2; i--) {
				c = b / i;
				System.out.println("i=" + i);
			}
			return true;
		} catch (Exception e) {
			System.out.println("testEx2, catch exception");
			ret = false;
            try {
                int i = 0;
                int a = 10 / i;
            } catch (Exception ee) {
                System.out.println("catch in catch block is In-------");
            } finally {
                System.out.println("catch in catch block finally----");
            }
			throw e;
		} finally {
			System.out.println("testEx2, finally; return value=" + ret);
            try {
                int i = 0;
                int a = 10 / i;
            } catch (Exception ee) {
                System.out.println("catch in finally block is In-------");
            } finally {
                System.out.println("catch in finally block finally----");
            }
			return ret;
		}
	}

	public static void main(String[] args) {
		TestException testException1 = new TestException();
		try {
			testException1.testEx();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
