package com.til.co;

import java.util.List;

/**
 * Created by Satya on 31-07-2018.
 */
public class HeadLineCO {
    private String country;
    private String category;
    private String q;
    private int pageSize=20;
    private int page;
    private List<String> sources;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    @Override
    public String toString() {
        return "HeadLineCO{" +
                "country='" + country + '\'' +
                ", category='" + category + '\'' +
                ", q='" + q + '\'' +
                ", pageSize=" + pageSize +
                ", page=" + page +
                ", sources=" + sources +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HeadLineCO that = (HeadLineCO) o;

        if (pageSize != that.pageSize) return false;
        if (page != that.page) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (q != null ? !q.equals(that.q) : that.q != null) return false;
        return sources != null ? sources.containsAll(that.sources) : that.sources == null;
    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (q != null ? q.hashCode() : 0);
        result = 31 * result + pageSize;
        result = 31 * result + page;
        result = 31 * result + (sources != null ? sources.hashCode() : 0);
        return result;
    }
}
