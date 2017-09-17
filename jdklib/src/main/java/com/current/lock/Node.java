package com.current.lock;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Author Mr.Pro
 * Date   9/17/17 = 3:25 PM
 */
@Data
@Accessors(chain = true)
public class Node {
    public enum Type{
        SHARE,
        SINGLE,
        TERMINATE
    }

   private Type type;
   private Thread currentThread;
   private Node nextNode;
}
