package org.example.epam.backend.model;

import java.util.Objects;

/**
 * POJO Session
 *
 * @version 1.01 05 May 2020
 * @author Uladzislau Biadrytski
 */
public class Session {

    private Integer id;
    private String time;
    private String page;

    public Session() {}

    public Session(String time, String page) {
        this.time = time;
        this.page = page;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) &&
                Objects.equals(time, session.time) &&
                Objects.equals(page, session.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, page);
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}
