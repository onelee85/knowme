package com.crwal.entity;

import javax.persistence.*;

/**
 * @author: jiao.li@ttpod.com
 * Date: 2018/11/14 15:23
 *
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `user_id` bigint(20) NOT NULL,
 *   `a_id` bigint(20) NOT NULL,
 *   `derive_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 *   `feedback` bit(1) DEFAULT NULL,
 *   `derive_algorithm` int(11) NOT NULL,
 */
@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "a_id")
    private Long aId;

    @Column(name = "derive_time")
    private Long deriveTime;

    @Column(name = "feedback")
    private int feedback;

    @Column(name = "derive_algorithm")
    private int deriveAlgorithm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getaId() {
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }

    public Long getDeriveTime() {
        return deriveTime;
    }

    public void setDeriveTime(Long deriveTime) {
        this.deriveTime = deriveTime;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    public int getDeriveAlgorithm() {
        return deriveAlgorithm;
    }

    public void setDeriveAlgorithm(int deriveAlgorithm) {
        this.deriveAlgorithm = deriveAlgorithm;
    }
}
