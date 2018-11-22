package allen.interview.thread.chapter08;

/**
 * @author WangJianQiang
 * @Description:
 * @date 2018年11月12日 10:46
 */
class RunnableDenyException extends RuntimeException {

	RunnableDenyException(String message) {
		super(message);
	}
}
