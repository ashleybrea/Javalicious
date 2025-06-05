package com.fico.chatbot.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.*;

/**
 * Member entity - represents a FICO customer
 * @Entity tells Spring this is a database table
 */
@Entity
@Table(name = "members")
public class Member {
    
    @Id  // This marks the primary key
    private Long id;
    
    @Column(name = "firstName")
    private String firstName;
    
    @Column(name = "lastName") 
    private String lastName;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "memberSince")
    private String memberSince;
    
    @Column(name = "subscriptionType")
    private String subscriptionType;
    
    @Column(name = "ficoScore")
    private Integer ficoScore;
    
    @Column(name = "paymentHistory")
    private String paymentHistory;
    
    @Column(name = "creditUtilization")
    private String creditUtilization;
    
    @Column(name = "creditHistory")
    private String creditHistory;
    
    @Column(name = "creditMix")
    private String creditMix;
    
    @Column(name = "recentCreditInquiries")
    private Integer recentCreditInquiries;
    
    @Column(name = "latePayments")
    private Integer latePayments;
    
    // Default constructor (required by JPA)
    public Member() {}
    
    // Constructor with all fields
    public Member(Long id, String firstName, String lastName, String email, 
                  String phone, String memberSince, String subscriptionType,
                  Integer ficoScore, String paymentHistory, String creditUtilization,
                  String creditHistory, String creditMix, Integer recentCreditInquiries,
                  Integer latePayments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.memberSince = memberSince;
        this.subscriptionType = subscriptionType;
        this.ficoScore = ficoScore;
        this.paymentHistory = paymentHistory;
        this.creditUtilization = creditUtilization;
        this.creditHistory = creditHistory;
        this.creditMix = creditMix;
        this.recentCreditInquiries = recentCreditInquiries;
        this.latePayments = latePayments;
    }
    
    // Getters and Setters (required for JSON conversion)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getMemberSince() { return memberSince; }
    public void setMemberSince(String memberSince) { this.memberSince = memberSince; }
    
    public String getSubscriptionType() { return subscriptionType; }
    public void setSubscriptionType(String subscriptionType) { this.subscriptionType = subscriptionType; }
    
    public Integer getFicoScore() { return ficoScore; }
    public void setFicoScore(Integer ficoScore) { this.ficoScore = ficoScore; }
    
    public String getPaymentHistory() { return paymentHistory; }
    public void setPaymentHistory(String paymentHistory) { this.paymentHistory = paymentHistory; }
    
    public String getCreditUtilization() { return creditUtilization; }
    public void setCreditUtilization(String creditUtilization) { this.creditUtilization = creditUtilization; }
    
    public String getCreditHistory() { return creditHistory; }
    public void setCreditHistory(String creditHistory) { this.creditHistory = creditHistory; }
    
    public String getCreditMix() { return creditMix; }
    public void setCreditMix(String creditMix) { this.creditMix = creditMix; }
    
    public Integer getRecentCreditInquiries() { return recentCreditInquiries; }
    public void setRecentCreditInquiries(Integer recentCreditInquiries) { 
        this.recentCreditInquiries = recentCreditInquiries; 
    }
    
    public Integer getLatePayments() { return latePayments; }
    public void setLatePayments(Integer latePayments) { this.latePayments = latePayments; }
    
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ficoScore=" + ficoScore +
                '}';
    }
}