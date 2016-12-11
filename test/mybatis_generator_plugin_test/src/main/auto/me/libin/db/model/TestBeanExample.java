package me.libin.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestBeanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /** 分页参数偏移量 */
    protected int offset = 0;

    /** 分页参数数量 */
    protected int rows = 0;

    /** 悲观锁 */
    protected boolean forUpdate = false;

    public TestBeanExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /** 分页参数偏移量 */
    public void setOffset(int offset) {
        this.offset=offset;
    }

    /** 分页参数偏移量 */
    public int getOffset() {
        return offset;
    }

    /** 分页参数数量 */
    public void setRows(int rows) {
        this.rows=rows;
    }

    /** 分页参数数量 */
    public int getRows() {
        return rows;
    }

    /** 悲观锁 */
    public void setForUpdate(boolean forUpdate) {
        this.forUpdate=forUpdate;
    }

    /** 悲观锁 */
    public boolean getForUpdate() {
        return forUpdate;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAaIsNull() {
            addCriterion("aa is null");
            return (Criteria) this;
        }

        public Criteria andAaIsNotNull() {
            addCriterion("aa is not null");
            return (Criteria) this;
        }

        public Criteria andAaEqualTo(Date value) {
            addCriterion("aa =", value, "aa");
            return (Criteria) this;
        }

        public Criteria andAaNotEqualTo(Date value) {
            addCriterion("aa <>", value, "aa");
            return (Criteria) this;
        }

        public Criteria andAaGreaterThan(Date value) {
            addCriterion("aa >", value, "aa");
            return (Criteria) this;
        }

        public Criteria andAaGreaterThanOrEqualTo(Date value) {
            addCriterion("aa >=", value, "aa");
            return (Criteria) this;
        }

        public Criteria andAaLessThan(Date value) {
            addCriterion("aa <", value, "aa");
            return (Criteria) this;
        }

        public Criteria andAaLessThanOrEqualTo(Date value) {
            addCriterion("aa <=", value, "aa");
            return (Criteria) this;
        }

        public Criteria andAaIn(List<Date> values) {
            addCriterion("aa in", values, "aa");
            return (Criteria) this;
        }

        public Criteria andAaNotIn(List<Date> values) {
            addCriterion("aa not in", values, "aa");
            return (Criteria) this;
        }

        public Criteria andAaBetween(Date value1, Date value2) {
            addCriterion("aa between", value1, value2, "aa");
            return (Criteria) this;
        }

        public Criteria andAaNotBetween(Date value1, Date value2) {
            addCriterion("aa not between", value1, value2, "aa");
            return (Criteria) this;
        }

        public Criteria andBbIsNull() {
            addCriterion("bb is null");
            return (Criteria) this;
        }

        public Criteria andBbIsNotNull() {
            addCriterion("bb is not null");
            return (Criteria) this;
        }

        public Criteria andBbEqualTo(Long value) {
            addCriterion("bb =", value, "bb");
            return (Criteria) this;
        }

        public Criteria andBbNotEqualTo(Long value) {
            addCriterion("bb <>", value, "bb");
            return (Criteria) this;
        }

        public Criteria andBbGreaterThan(Long value) {
            addCriterion("bb >", value, "bb");
            return (Criteria) this;
        }

        public Criteria andBbGreaterThanOrEqualTo(Long value) {
            addCriterion("bb >=", value, "bb");
            return (Criteria) this;
        }

        public Criteria andBbLessThan(Long value) {
            addCriterion("bb <", value, "bb");
            return (Criteria) this;
        }

        public Criteria andBbLessThanOrEqualTo(Long value) {
            addCriterion("bb <=", value, "bb");
            return (Criteria) this;
        }

        public Criteria andBbIn(List<Long> values) {
            addCriterion("bb in", values, "bb");
            return (Criteria) this;
        }

        public Criteria andBbNotIn(List<Long> values) {
            addCriterion("bb not in", values, "bb");
            return (Criteria) this;
        }

        public Criteria andBbBetween(Long value1, Long value2) {
            addCriterion("bb between", value1, value2, "bb");
            return (Criteria) this;
        }

        public Criteria andBbNotBetween(Long value1, Long value2) {
            addCriterion("bb not between", value1, value2, "bb");
            return (Criteria) this;
        }

        public Criteria andCcIsNull() {
            addCriterion("cc is null");
            return (Criteria) this;
        }

        public Criteria andCcIsNotNull() {
            addCriterion("cc is not null");
            return (Criteria) this;
        }

        public Criteria andCcEqualTo(Short value) {
            addCriterion("cc =", value, "cc");
            return (Criteria) this;
        }

        public Criteria andCcNotEqualTo(Short value) {
            addCriterion("cc <>", value, "cc");
            return (Criteria) this;
        }

        public Criteria andCcGreaterThan(Short value) {
            addCriterion("cc >", value, "cc");
            return (Criteria) this;
        }

        public Criteria andCcGreaterThanOrEqualTo(Short value) {
            addCriterion("cc >=", value, "cc");
            return (Criteria) this;
        }

        public Criteria andCcLessThan(Short value) {
            addCriterion("cc <", value, "cc");
            return (Criteria) this;
        }

        public Criteria andCcLessThanOrEqualTo(Short value) {
            addCriterion("cc <=", value, "cc");
            return (Criteria) this;
        }

        public Criteria andCcIn(List<Short> values) {
            addCriterion("cc in", values, "cc");
            return (Criteria) this;
        }

        public Criteria andCcNotIn(List<Short> values) {
            addCriterion("cc not in", values, "cc");
            return (Criteria) this;
        }

        public Criteria andCcBetween(Short value1, Short value2) {
            addCriterion("cc between", value1, value2, "cc");
            return (Criteria) this;
        }

        public Criteria andCcNotBetween(Short value1, Short value2) {
            addCriterion("cc not between", value1, value2, "cc");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}