package com.current.queue;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Author Mr.Pro
 * Date   9/9/17 = 12:42 PM
 */
@Data
@AllArgsConstructor
public class QueueNode {
    QueueNode next;
    Object obj;

    public static QueueNode create(){
        return new QueueNode(null, null);
    }
}
