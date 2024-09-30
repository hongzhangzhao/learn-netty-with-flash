package demoproxy01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class DataHandler2 extends ChannelInboundHandlerAdapter {


    private Channel channel;

    public DataHandler2(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 获取读取的数据， 是一个缓冲。
        ByteBuf readBuffer = (ByteBuf) msg;
//        System.out.println("get data: " + readBuffer.toString(CharsetUtil.UTF_8));
        String hexDump = ByteBufUtil.hexDump(readBuffer);
//        System.out.println("----客户端------: " + hexDump);
        String result = hexToString(hexDump);
//        System.out.println("####客户端######: " + result);
        //这里的复位不能省略,不然会因为计数器问题报错.
        readBuffer.retain();
        //将数据发到远程客户端那边
        channel.writeAndFlush(readBuffer);
    }

    public static String hexToString(String hex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            sb.append((char) Integer.parseInt(str, 16));
        }
        return sb.toString();
    }
}
