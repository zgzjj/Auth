package com.zjj.auth.utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Map;

public class SpecificationUtil {
    public static <T> Specification<T> buildSpecification(Map<String, Object> searchParams, Class<T> entityClazz) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p = null;
                for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
                    String key = entry.getKey();// EQ_state
                    Predicate p_ = null;
                    if (entry.getValue().equals(null) || entry.getValue().equals("")) {
                        continue;
                    }
                    if ("EQ".equals(key.substring(0, 2))) {
                        p_ = criteriaBuilder.equal(buildEx(root, entry.getKey().substring(3)), entry.getValue());
                    } else if ("NEQ".equals(key.substring(0, 3))) {
                        p_ = criteriaBuilder.notEqual(buildEx(root, entry.getKey().substring(4)), entry.getValue());
                    } else if ("INN".equals(key.substring(0, 3))) {
                        p_ = criteriaBuilder.isNotNull(buildEx(root, entry.getKey().substring(4)));
                    } else if ("LIKE".equals(key.substring(0, 4))) {
                        p_ = criteriaBuilder.like((Path) buildEx(root, entry.getKey().substring(5)), "%" + entry.getValue() + "%");
                    } else if ("GTE".equals(key.substring(0, 3))) {
                        p_ = criteriaBuilder.greaterThanOrEqualTo((Path) buildEx(root, entry.getKey().substring(4)), entry.getValue().toString());
                    } else if ("LTE".equals(key.substring(0, 3))) {
                        p_ = criteriaBuilder.lessThanOrEqualTo((Path) buildEx(root, entry.getKey().substring(4)), entry.getValue().toString());
                    } else if ("GT".equals(key.substring(0, 2))) {
                        p_ = criteriaBuilder.greaterThan((Path) buildEx(root, entry.getKey().substring(3)), entry.getValue() + "");
                    } else if ("LT".equals(key.substring(0, 2))) {
                        p_ = criteriaBuilder.lessThan((Path) buildEx(root, entry.getKey().substring(3)), entry.getValue() + "");
                    } else if ("IN".equals(key.substring(0, 2))) {
                        CriteriaBuilder.In<Object> in = criteriaBuilder.in(buildEx(root, entry.getKey().substring(3)));
                        String ids[] = entry.getValue().toString().split(",");
                        for (String s : ids) {
                            in.value(Integer.parseInt(s));
                        }
                        p_ = in;
                    } else {
                        continue;
                    }
                    if (p == null) {
                        p = p_;
                    } else {
                        p = criteriaBuilder.and(p_, p);
                    }
                }
                if (p == null) {
                    p = criteriaBuilder.and();
                }
                //排序
                criteriaQuery.where(criteriaBuilder.and(p));
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("operatorTime").as(String.class)));
                return criteriaQuery.getRestriction();
                //return p;
            }
        };

    }

    public static <T> Specification<T> buildSpecification(Map<String, Object> searchParams, Map<String, Object> orSearchParams, Class<T> entityClazz) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p = null;
                for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
                    String key = entry.getKey();// EQ_state
                    Predicate p_ = null;
                    if (entry.getValue().equals(null) || entry.getValue().equals("")) {
                        continue;
                    }
                    if ("EQ".equals(key.substring(0, 2))) {
                        p_ = criteriaBuilder.equal(buildEx(root, entry.getKey().substring(3)), entry.getValue());
                    } else if ("NEQ".equals(key.substring(0, 3))) {
                        p_ = criteriaBuilder.notEqual(buildEx(root, entry.getKey().substring(4)), entry.getValue());
                    } else if ("INN".equals(key.substring(0, 3))) {
                        p_ = criteriaBuilder.isNotNull(buildEx(root, entry.getKey().substring(4)));
                    } else if ("LIKE".equals(key.substring(0, 4))) {
                        p_ = criteriaBuilder.like((Path) buildEx(root, entry.getKey().substring(5)), "%" + entry.getValue() + "%");
                    } else if ("GTE".equals(key.substring(0, 3))) {
                        p_ = criteriaBuilder.greaterThanOrEqualTo((Path) buildEx(root, entry.getKey().substring(4)), entry.getValue().toString());
                    } else if ("LTE".equals(key.substring(0, 3))) {
                        p_ = criteriaBuilder.lessThanOrEqualTo((Path) buildEx(root, entry.getKey().substring(4)), entry.getValue().toString());
                    } else if ("GT".equals(key.substring(0, 2))) {
                        p_ = criteriaBuilder.greaterThan((Path) buildEx(root, entry.getKey().substring(3)), entry.getValue() + "");
                    } else if ("LT".equals(key.substring(0, 2))) {
                        p_ = criteriaBuilder.lessThan((Path) buildEx(root, entry.getKey().substring(3)), entry.getValue() + "");
                    } else if ("IN".equals(key.substring(0, 2))) {
                        CriteriaBuilder.In<Object> in = criteriaBuilder.in(buildEx(root, entry.getKey().substring(3)));
                        String ids[] = entry.getValue().toString().split(",");
                        for (String s : ids) {
                            in.value(Integer.parseInt(s));
                        }
                        p_ = in;
                    } else {
                        continue;
                    }
                    if (p == null) {
                        p = p_;
                    } else {
                        p = criteriaBuilder.and(p_, p);
                    }
                }
                if (p == null) {
                    p = criteriaBuilder.and();
                }
                Predicate orp = null;
                if (orSearchParams != null) {
                    for (Map.Entry<String, Object> entry : orSearchParams.entrySet()) {
                        String key = entry.getKey();// EQ_state
                        Predicate p_ = null;
                        if (entry.getValue().equals(null) || entry.getValue().equals("")) {
                            continue;
                        }
                        if ("EQ".equals(key.substring(0, 2))) {
                            p_ = criteriaBuilder.equal(buildEx(root, entry.getKey().substring(3)), entry.getValue());
                        } else if ("NEQ".equals(key.substring(0, 3))) {
                            p_ = criteriaBuilder.notEqual(buildEx(root, entry.getKey().substring(4)), entry.getValue());
                        } else if ("INN".equals(key.substring(0, 3))) {
                            p_ = criteriaBuilder.isNotNull(buildEx(root, entry.getKey().substring(4)));
                        } else if ("LIKE".equals(key.substring(0, 4))) {
                            p_ = criteriaBuilder.like((Path) buildEx(root, entry.getKey().substring(5)), "%" + entry.getValue() + "%");
                        } else if ("GTE".equals(key.substring(0, 3))) {
                            p_ = criteriaBuilder.greaterThanOrEqualTo((Path) buildEx(root, entry.getKey().substring(4)), entry.getValue().toString());
                        } else if ("LTE".equals(key.substring(0, 3))) {
                            p_ = criteriaBuilder.lessThanOrEqualTo((Path) buildEx(root, entry.getKey().substring(4)), entry.getValue().toString());
                        } else if ("GT".equals(key.substring(0, 2))) {
                            p_ = criteriaBuilder.greaterThan((Path) buildEx(root, entry.getKey().substring(3)), entry.getValue() + "");
                        } else if ("LT".equals(key.substring(0, 2))) {
                            p_ = criteriaBuilder.lessThan((Path) buildEx(root, entry.getKey().substring(3)), entry.getValue() + "");
                        } else if ("IN".equals(key.substring(0, 2))) {
                            CriteriaBuilder.In<Object> in = criteriaBuilder.in(buildEx(root, entry.getKey().substring(3)));
                            String ids[] = entry.getValue().toString().split(",");
                            for (String s : ids) {
                                in.value(Integer.parseInt(s));
                            }
                            p_ = in;
                        } else {
                            continue;
                        }
                        if (orp == null) {
                            orp = p_;
                        } else {
                            orp = criteriaBuilder.or(p_, orp);
                        }
                    }
                }
                if (orp != null) {
                    p = criteriaBuilder.and(p, orp);
                }

                //排序
                criteriaQuery.where(criteriaBuilder.and(p));
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("operatorTime").as(String.class)));
                return criteriaQuery.getRestriction();
                //return p;
            }
        };

    }

    public static <T> Expression<T> buildEx(Root<T> root, String key) {
        if (key.contains(".")) { //级联搜索
            String[] keys = key.split("\\.");
            return build(keys, root);

        } else {
            return root.get(key);
        }
    }

    public static <T> Expression<T> build(String[] keys, Root<T> root) {
        if (keys.length == 2) {
            return root.join(keys[0], JoinType.LEFT).get(keys[1]);
        } else {
            Join j = root.join(keys[0]);
            for (int i = 1; i < keys.length - 1; i++) {
                j = j.join(keys[i], JoinType.LEFT);
            }
            return j.get(keys[keys.length - 1]);
        }
    }

}
