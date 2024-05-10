package com.example.impl;

import com.example.OperationType;

public record Operation<K, V>(Node<K, V> node, OperationType operationType) {
}
