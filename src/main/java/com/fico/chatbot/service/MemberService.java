package com.fico.chatbot.service;

import com.fico.chatbot.model.Member;
import com.fico.chatbot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Member business logic
 * @Service tells Spring this is a business logic component
 */
@Service
public class MemberService {
    
    @Autowired  // Spring automatically injects the repository
    private MemberRepository memberRepository;
    
    /**
     * Get all members
     */
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
    /**
     * Get member by ID
     */
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }
    
    /**
     * Get member by email
     */
    public Optional<Member> getMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
    
    /**
     * Save a new member or update existing one
     */
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
    
    /**
     * Delete member by ID
     */
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
    
    /**
     * Get members by subscription type
     */
    public List<Member> getMembersBySubscriptionType(String subscriptionType) {
        return memberRepository.findBySubscriptionType(subscriptionType);
    }
    
    /**
     * Get credit score range analysis
     */
    public String getCreditScoreAnalysis(Long memberId) {
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        
        if (memberOpt.isEmpty()) {
            return "Member not found";
        }
        
        Member member = memberOpt.get();
        Integer score = member.getFicoScore();
        
        if (score >= 800) {
            return "Exceptional credit score! You qualify for the best rates.";
        } else if (score >= 740) {
            return "Very good credit score! You're in great shape.";
        } else if (score >= 670) {
            return "Good credit score! Some room for improvement.";
        } else if (score >= 580) {
            return "Fair credit score. Focus on improvement strategies.";
        } else {
            return "Poor credit score. Let's work on rebuilding your credit.";
        }
    }
    
    /**
     * Get personalized improvement suggestions
     */
    public String getImprovementSuggestions(Long memberId) {
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        
        if (memberOpt.isEmpty()) {
            return "Member not found";
        }
        
        Member member = memberOpt.get();
        StringBuilder suggestions = new StringBuilder();
        
        // Analyze late payments
        if (member.getLatePayments() > 0) {
            suggestions.append("• Set up automatic payments to avoid future late payments. ");
        }
        
        // Analyze credit utilization
        String utilization = member.getCreditUtilization().replace("%", "");
        try {
            int utilizationPercent = Integer.parseInt(utilization);
            if (utilizationPercent > 30) {
                suggestions.append("• Reduce credit utilization below 30%. ");
            }
        } catch (NumberFormatException e) {
            // Handle parsing error gracefully
        }
        
        // Analyze recent inquiries
        if (member.getRecentCreditInquiries() > 2) {
            suggestions.append("• Limit new credit applications for the next 6 months. ");
        }
        
        if (suggestions.length() == 0) {
            suggestions.append("• Keep up the great work! Monitor your credit regularly.");
        }
        
        return suggestions.toString();
    }
}