package chapter08.packet;

import lombok.Data;

/**
 * @author Chanmoey
 * @date 2022年08月30日
 */
@Data
public abstract class Packet {

    private Byte version = 1;  // 版本号

    public abstract Byte getCommand();
}
