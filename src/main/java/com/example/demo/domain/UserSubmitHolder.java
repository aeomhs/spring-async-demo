package com.example.demo.domain;

import java.util.Objects;

public class UserSubmitHolder {

    private final Long id;

    private final String msg;

    public UserSubmitHolder(Long id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSubmitHolder)) return false;
        UserSubmitHolder that = (UserSubmitHolder) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
