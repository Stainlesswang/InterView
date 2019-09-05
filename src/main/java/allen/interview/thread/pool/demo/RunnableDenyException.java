package allen.interview.thread.pool.demo;

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
