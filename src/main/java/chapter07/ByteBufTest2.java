package chapter07;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author Chanmoey
 * @date 2022年08月30日
 */
public class ByteBufTest2 {
    public static void main(String[] args) {

        ByteBuf byteBuf2 = ByteBufAllocator.DEFAULT.ioBuffer();  // 创建直接内存, 不受JVM控制, 效率高
        System.out.println("buffer1: " + byteBuf2.capacity());
        System.out.println("buffer1: " + byteBuf2.maxCapacity());

        ByteBuf buffer1 = ByteBufAllocator.DEFAULT.buffer();
        System.out.println("buffer1: " + buffer1.capacity());
        System.out.println("buffer1: " + buffer1.maxCapacity());

        // 9是初始容量, 100是max容量
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);

        // 查看容量
        System.out.println("capacity: " + buffer.capacity());
        System.out.println("maxCapacity: " + buffer.maxCapacity());
        // 查看可读容量
        System.out.println("readableBytes: " + buffer.readableBytes());
        System.out.println("isReadable: " + buffer.isReadable());
        // 查看可写容量
        System.out.println("writableBytes: " + buffer.writableBytes());  // 可写容量是capacity不是maxCapacity
        System.out.println("isWritable: " + buffer.isWritable());

        // 指针
        System.out.println("readerIndex: " + buffer.readerIndex());  // 获取指针位置
        System.out.println("writerIndex: " + buffer.writerIndex());  // 获取指针位置

        // 读写

        buffer.writeBytes(new byte[] {1, 2, 3, 4});  // 写改变写指针
        System.out.println(buffer);

        // int 4 个字节
        buffer.writeInt(12);
        System.out.println("writeInt(12): " + buffer);


        // 再写一个字节，buffer不可写
        buffer.writeBytes(new byte[]{5});
        System.out.println("writeBytes(5)" + buffer);

        // 再写，可以扩容，则扩容
        buffer.writeBytes(new byte[]{6});
        System.out.println("writeBytes(6)" + buffer);

        // get 不改变读写指针
        System.out.println("getByte(3) return " + buffer.getByte(3));
        System.out.println("getShort(3) return " + buffer.getShort(3));
        System.out.println("getInt(3) return " + buffer.getInt(3));
        System.out.println("getByte()" + buffer);

        // set 不改变读写指针
        buffer.setByte(buffer.readableBytes() + 1, 0);
        System.out.println("setByte()" + buffer);

        // read方法改变读写指针
        byte[] dst = new byte[buffer.readableBytes()];
        buffer.readBytes(dst);
        System.out.println("readBytes(" + dst.length + ")" + buffer);
    }


}
