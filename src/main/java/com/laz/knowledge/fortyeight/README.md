# Bitmap 位图 Java实现

### 一、结构思想
以 bit 作为存储单位进行 0、1存取的数据结构。
可用作布尔值存取，比如给定第i位，该bit为1则表示true，为0则表示false。

### 二、使用场景及优点
适用于对布尔或0、1值进行（大量）存取的场景。

如：记录一个用户365天的签到记录，签了为true，没签为false。若是以普通key/value数据结构，每个用户都需要记录365条，当用户量很大时会造成巨大的空间开销。
因此运用位图的话，每天签到记录只占1个位(bit)，一共就365位，则只需48个字节就能容纳一个用户一年的签到记录。

优点：
低空间开销且高效的0、1存取方案
