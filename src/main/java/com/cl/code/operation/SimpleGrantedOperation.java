package com.cl.code.operation;

import org.springframework.util.StringUtils;

/**
 * @author chengliang
 * @date 2022/9/6 17:00
 */
public final class SimpleGrantedOperation implements GrantedOperation {

    private final String operation;

    public SimpleGrantedOperation(String operation) {
        if (!StringUtils.hasText(operation)) {
            throw new IllegalArgumentException("操作不能为空");
        }
        this.operation = operation;
    }

    @Override
    public String getOperation() {
        return operation;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return obj instanceof SimpleGrantedOperation && this.operation.equals(((SimpleGrantedOperation) obj).operation);
        }
    }

    @Override
    public int hashCode() {
        return this.operation.hashCode();
    }

}
