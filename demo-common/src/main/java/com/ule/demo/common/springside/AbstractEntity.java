package com.ule.demo.common.springside;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @ClassName AbstractEntity
 * @Author fanxl
 * @Description 实体对象基类
 * @Date 14:55  2019/1/28
 * @Version 1.0
 **/
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {
    private static final long serialVersionUID = 877372633259875579L;
    /**
     *根据排序的字段
     */
    @Transient
    public String sort;
    /**
     *排序的方式 desc asc
     */
    @Transient
    public String order;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public abstract ID getId();

    /**
     * Sets the id of the entity.
     */
    public abstract void setId(final ID id);

    public boolean isNew() {

        return null == getId();
    }

    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        AbstractEntity<?> that = (AbstractEntity<?>) obj;

        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
}
