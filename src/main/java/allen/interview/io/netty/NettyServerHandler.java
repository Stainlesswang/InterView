package allen.interview.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.UnsupportedEncodingException;

/**
 * @author AllenWong
 * @date 2019/10/21 3:10 PM
 */
public class NettyServerHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf buf = (ByteBuf) o;

        String recieved = getMessage(buf);
        System.err.println("服务器接收到客户端消息：" + recieved);

        try {
            channelHandlerContext.writeAndFlush(getSendByteBuf("你好，客户端"));
            System.err.println("服务器回复消息：你好，客户端");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /** 从ByteBuf中获取信息 使用UTF-8编码返回*/
    private String getMessage(ByteBuf buf) {

        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ByteBuf getSendByteBuf(String message)
            throws UnsupportedEncodingException {

        byte[] req = message.getBytes("UTF-8");
        ByteBuf pingMessage = Unpooled.buffer();
        pingMessage.writeBytes(req);

        return pingMessage;
    }


}
